//package ru.itpark.noteswithsecurity.controller;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.itpark.noteswithsecurity.entity.AccountEntity;
//import ru.itpark.noteswithsecurity.entity.NoteEntity;
//import ru.itpark.noteswithsecurity.service.AccountService;
//import ru.itpark.noteswithsecurity.service.NoteService;
//
//
//@Controller
//@RequestMapping("/register")
//public class RegisterController {
//
//    private final AccountService service;
//
//    public RegisterController(AccountService service) {
//        this.service = service;
//    }
//    public String getAll(Model model) {
//        model.addAttribute("games", service.findAll());
//        return "register";
//    }
//}
