package ru.itpark.noteswithsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.noteswithsecurity.entity.AccountEntity;
import ru.itpark.noteswithsecurity.entity.AccountInfoEntity;
import ru.itpark.noteswithsecurity.entity.NoteEntity;
import ru.itpark.noteswithsecurity.service.AccountService;
import ru.itpark.noteswithsecurity.service.NoteService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/games")
public class NoteController {
    private final NoteService service;
    private final AccountService accountService;

    public NoteController(NoteService service, AccountService accountService) {
        this.service = service;
        this.accountService = accountService;
    }

        @GetMapping
    public String getAll(Model model) {
        model.addAttribute("games", service.findAll());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        AccountEntity accountEntity = (AccountEntity) accountService.loadUserByUsername(userName);
//        List<Integer> list = accountEntity.getTime();
//        int res = 0;
//        for (Integer integer : list) {
//            res += integer;
//        }
////        AccountInfoEntity accountInfoEntity = accountEntity.getAccountInfoEntity();
////        int money = accountInfoEntity.getMoney();
////        model.addAttribute("res", res);
////        model.addAttribute("money", money);
        return "pages/games";
    }
//    @GetMapping
//    public String getAll(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        AccountEntity accountEntity = (AccountEntity) accountService.loadUserByUsername(userName);
//        System.out.println(service.findAllById(accountEntity.getCourseId()));
//        List<NoteEntity> entities = new ArrayList<>();
//        List<NoteEntity> entities1 = service.findAllById(accountEntity.getCourseId());
//        for (NoteEntity entity : service.findAll()) {
//           if (!entities1.contains(entity))
//               entities.add(entity);
//        }
//        model.addAttribute("games", entities);
//        List<Integer> list = accountEntity.getTime();
//        int res = 0;
//        for (Integer integer : list) {
//            res += integer;
//        }
////        AccountInfoEntity accountInfoEntity = accountEntity.getAccountInfoEntity();
////        int money = accountInfoEntity.getMoney();
//        model.addAttribute("res", res);
////        model.addAttribute("money", money);
//        return "pages/games";
//    }

    @GetMapping(value = "/search", params = "name") // Mapping - определяет то, что должно быть в запросе
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("name", name); // чтобы отображать в поле поиска
        model.addAttribute("games", service.findByName(name));
        return "pages/games";
    }

    @PreAuthorize("hasAuthority('REMOVE')")
    @GetMapping("/{id}/edit") // <- value = "/edit"
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("item", service.getByIdOrEmpty(id));
        return "pages/note-edit";
    }

    @PreAuthorize("hasAuthority('REMOVE')")
    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable int id,
            @ModelAttribute NoteEntity dto
    ) {
        NoteEntity noteEntity = service.getByIdOrEmpty(id);
        dto.setComments(noteEntity.getComments());
        service.add(dto);

        return "redirect:/games";
    }

    @PreAuthorize("hasAuthority('ADD')")
    @GetMapping("/add")
    public String addForm() {
        return "pages/note-add";
    }

    @PreAuthorize("hasAuthority('ADD')")
    @PostMapping("/add")
    public String add(@ModelAttribute NoteEntity note) {
        service.add(note);
        return "redirect:/games";
    }

    @PostMapping("/{id}/checked")
    public String checkedIs(@PathVariable int id, @ModelAttribute("input") String string) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountEntity accountEntity = (AccountEntity) accountService.loadUserByUsername(userName);
        NoteEntity noteEntity = service.findById(id);
        boolean result = false;
        System.out.println("test");
        System.out.println(string);
        int parse = Integer.parseInt(string);

        for (int i = 0; i < accountEntity.getChecked().size(); i++) {
            int test = accountEntity.getChecked().get(i);
            if (id == test) {
                result = true;
                accountEntity.getTime().set(i, parse);

            }
        }
        if (result == false) {
            accountEntity.getChecked().add(id);
            accountEntity.getTime().add(parse);

        }

//        System.out.println("test123");
        accountService.saveUser(accountEntity);
        service.edit(noteEntity);
//        model.addAttribute("note", service.findById(id));
        return "redirect:/games";
    }


    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("note", service.findById(id));
        NoteEntity noteEntity = service.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountEntity accountEntity = (AccountEntity) accountService.loadUserByUsername(userName);
        boolean result = false;
        int res = 0;
        for (int i = 0; i < accountEntity.getChecked().size(); i++) {
            int test = accountEntity.getChecked().get(i);
            if (noteEntity.getId() == test) {
                result = true;
                res = accountEntity.getTime().get(i);
            }
        }
        if (res == 0) {
            result = false;
        }
        model.addAttribute("test", result);
        model.addAttribute("input", res);
        return "pages/note";
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable int id, String comment) {
        NoteEntity noteEntity = service.findById(id);
        List<String> stringList = noteEntity.getComments();
        stringList.add(comment);
        noteEntity.setComments(stringList);
        service.edit(noteEntity);
        System.out.println(service.findAll().toString());
        return "redirect:/games/{id}";
    }

    @PostMapping("/{id}/buy")
    public String buy(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountEntity accountEntity = (AccountEntity) accountService.loadUserByUsername(userName);
        NoteEntity noteEntity = service.findById(id);
        if (noteEntity.getPrice() <= accountEntity.getMoney()){
            accountEntity.setMoney(accountEntity.getMoney() - noteEntity.getPrice());
            List<Integer> integers = accountEntity.getCourseId();
            integers.add(id);
            accountEntity.setCourseId(integers);
            accountService.saveUser(accountEntity);
            return "success-bought";
        }
        else return "error-no-money";
    }

    @PreAuthorize("hasAuthority('REMOVE')")
    @PostMapping("/{id}/remove")
    public String remove(@PathVariable int id) {
        System.out.println("qweqwe");
        service.removeById(id);
        return "redirect:/games";
    }

    @PreAuthorize("@accountService.isOwned(#id)") // #id -> @PathVariable int id
    @GetMapping("/{id}/owned")
    public String preAuthorizeWithOurService(@PathVariable int id) {
        return "pages/owned";
    }
}