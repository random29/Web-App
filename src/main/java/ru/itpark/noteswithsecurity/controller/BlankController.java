package ru.itpark.noteswithsecurity.controller;

import com.fasterxml.jackson.databind.deser.CreatorProperty;
import org.dom4j.rule.Mode;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.noteswithsecurity.entity.*;
import ru.itpark.noteswithsecurity.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Struct;
import java.util.List;


@Controller
@RequestMapping("/")
public class BlankController {

    private final NoteService noteService;
    private final AccountService service;
    private final CaesarService caesarService;
    private final VigenerService vigenereService;
    private final GammingService gammingService;
    private final RaeService raeService;
    private final MilRabService milRabService;
    private final RSAService rsaService;
    private final SoloveiService soloveiService;
    private final DiffHellService diffHellService;
    private final RhoService rhoService;
    private final RC4Service rc4Service;

    public BlankController(RhoService rhoService, AccountService service, DiffHellService diffHellService, VigenerService vigenereService, CaesarService caesarService, NoteService noteService, GammingService gammingService, RaeService raeService, MilRabService milRabService, RSAService rsaService, SoloveiService soloveiService, RC4Service rc4Service) {
        this.service = service;
        this.noteService = noteService;
        this.caesarService = caesarService;
        this.vigenereService = vigenereService;
        this.gammingService = gammingService;
        this.raeService = raeService;
        this.milRabService = milRabService;
        this.rsaService = rsaService;
        this.soloveiService = soloveiService;
        this.diffHellService = diffHellService;
        this.rhoService = rhoService;
        this.rc4Service = rc4Service;
    }


    @GetMapping
    public String blankRedirect(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String addForm() {
//        System.out.println("suck");
        return "register";
    }

    @GetMapping("/register1")
    public String send(Model model){
        model.addAttribute("content", "Kekes");
        return "pages/register1";
    }


    @GetMapping("/polardGen")
    public String wgen(Model model) {
        model.addAttribute("razm", 128);
        model.addAttribute("numbers", rhoService.findById().getNumbers());
        model.addAttribute("x", rhoService.findById().getX());
        return "pages/enc/rho";
    }

    @PostMapping("/polardGen")
    public String gen(Model model, int razm) {
        rhoService.gen(razm);
        model.addAttribute("razm", 128);
        model.addAttribute("numbers", rhoService.findById().getNumbers());
        model.addAttribute("x", rhoService.findById().getX());
        return "pages/enc/rho";
    }

    @GetMapping("/polard")
    public String wge2n(Model model) {
        model.addAttribute("razm", 128);
        model.addAttribute("numbers", rhoService.findById().getNumbers());
        model.addAttribute("x", rhoService.findById().getX());
        return "pages/enc/rho";
    }

    @PostMapping("/polard")
    public String ge2n(Model model, BigInteger x) {
        rhoService.gogo(x);
        model.addAttribute("razm", 128);
        model.addAttribute("numbers", rhoService.findById().getNumbers());
        model.addAttribute("x", rhoService.findById().getX());
        return "pages/enc/rho";
    }

    @PostMapping("/register")
    public String add(@ModelAttribute AccountEntity accountEntity) {
        service.registerUser(accountEntity);
        System.out.println(accountEntity.getUsername());
        return "redirect:/login";
    }

    @GetMapping("/encryption")
    public String encryption() {
        return "pages/encryption";
    }

    @GetMapping("/diffi-a-gen")
    public String diffiAGen(Model model) {
        DiffiHellEntity entity = diffHellService.findById();
        model.addAttribute("p", entity.getP());
        model.addAttribute("g", entity.getG());
        model.addAttribute("A", entity.getA());
        model.addAttribute("B", entity.getB());
        model.addAttribute("key1", entity.getKey2());
        model.addAttribute("key2", entity.getKey1());
        model.addAttribute("K", entity.getK());
//        model.addAttribute("p", "Рано");
//        model.addAttribute("g", "Рано");
//        model.addAttribute("A", "Рано");
//        model.addAttribute("B", "Рано");
//        model.addAttribute("key1", entity.getKey1());
//        model.addAttribute("key2", "Рано");
//        model.addAttribute("K", "Рано");
        return "pages/enc/diffhell";
    }

    @GetMapping("/diffi-b-gen")
    public String diffiBGen(Model model) {
        DiffiHellEntity entity = diffHellService.findById();
        model.addAttribute("p", entity.getP());
//        model.addAttribute("g", "Рано");
//        model.addAttribute("A", "Рано");
//        model.addAttribute("B", "Рано");
//        model.addAttribute("key2", entity.getKey2());
//        model.addAttribute("key1", "Рано");
//        model.addAttribute("K", "Рано");
        model.addAttribute("g", entity.getG());
        model.addAttribute("A", entity.getA());
        model.addAttribute("B", entity.getB());
        model.addAttribute("key1", entity.getKey2());
        model.addAttribute("key2", entity.getKey1());
        model.addAttribute("K", entity.getK());
        return "pages/enc/diffhell2";
    }

    @PostMapping("/diffi-a-gen")
    public String diffiAGen1(Model model, int razm) {
        diffHellService.gen(razm);
        return "redirect:/diffi-a-gen";
    }

    @PostMapping("/diffi-b-gen")
    public String diffiBGen1(Model model, int razm) {
        diffHellService.gen(razm);
        return "redirect:/diffi-b-gen";
    }

    @GetMapping("/diffi-open-a")
    public String diffiOpenA(Model model) {
        DiffiHellEntity entity = diffHellService.findById();
        model.addAttribute("p", entity.getP());
        model.addAttribute("g", entity.getG());
        model.addAttribute("A", entity.getA());
        model.addAttribute("B", entity.getB());
        model.addAttribute("key1", entity.getKey2());
        model.addAttribute("key2", entity.getKey1());
        model.addAttribute("K", entity.getK());
        return "pages/enc/diffhell";
    }

    @GetMapping("/rc4")
    public String fff(Model model) {
        RC4Entity rc4Entity = rc4Service.findById();
        BigInteger bigInteger = diffHellService.findById().getK();
        rc4Entity.setKey(bigInteger);
        model.addAttribute("key", bigInteger);
        model.addAttribute("cip", rc4Entity.getCip());
        return "pages/enc/rc4";
    }

    @PostMapping("/rc4")
    public String ere(Model model, String cip) throws UnsupportedEncodingException {

        byte[] cipe = rc4Service.encrypt(cip.getBytes("UTF-8"));
        RC4Entity rc4Entity = rc4Service.findById();
        rc4Entity.setCip(cipe.toString());
        rc4Service.save(rc4Entity);
        return "redirect:/rc4";
    }

    @GetMapping("/diffi-open-b")
    public String diffiOpenB(Model model) {
        DiffiHellEntity entity = diffHellService.findById();
        model.addAttribute("p", entity.getP());
        model.addAttribute("g", entity.getG());
        model.addAttribute("A", entity.getA());
        model.addAttribute("B", entity.getB());
        model.addAttribute("key1", entity.getKey2());
        model.addAttribute("key2", entity.getKey1());
        model.addAttribute("K", entity.getK());
        return "pages/enc/diffhell2";
    }

    @PostMapping("/diffi-open-a")
    public String NuGen(Model model) {
        diffHellService.genP();
        return "redirect:/diffi-open-a";
    }

    @PostMapping("/diffi-open-b")
    public String NuGen1(Model model) {
        return "redirect:/diffi-open-b";
    }

    @GetMapping("/diffi-gen-open-a")
    public String diffi2(Model model) {
        DiffiHellEntity entity = diffHellService.findById();
        model.addAttribute("p", entity.getP());
        model.addAttribute("g", entity.getG());
        model.addAttribute("A", entity.getA());
        model.addAttribute("B", entity.getB());
        model.addAttribute("key1", entity.getKey2());
        model.addAttribute("key2", entity.getKey1());
        model.addAttribute("K", entity.getK());
        return "pages/enc/diffhell";
    }

    @GetMapping("/diffi-gen-open-b")
    public String diffi21(Model model) {
        DiffiHellEntity entity = diffHellService.findById();
        model.addAttribute("p", entity.getP());
        model.addAttribute("g", entity.getG());
        model.addAttribute("A", entity.getA());
        model.addAttribute("B", entity.getB());
        model.addAttribute("key1", entity.getKey2());
        model.addAttribute("key2", entity.getKey1());
        model.addAttribute("K", entity.getK());
        return "pages/enc/diffhell2";
    }

    @PostMapping("/diffi-gen-open-a")
    public String diffi22(Model model) {
        diffHellService.genA();
        return "redirect:/diffi-gen-open-a";
    }

    @PostMapping("/diffi-gen-open-b")
    public String diffi23(Model model) {
        diffHellService.genA();
        return "redirect:/diffi-gen-open-b";
    }

    @GetMapping("/diffi-gen-a")
    public String diffi223(Model model) {
        DiffiHellEntity entity = diffHellService.findById();
        model.addAttribute("p", entity.getP());
        model.addAttribute("g", entity.getG());
        model.addAttribute("A", entity.getA());
        model.addAttribute("B", entity.getB());
        model.addAttribute("key1", entity.getKey2());
        model.addAttribute("key2", entity.getKey1());
        model.addAttribute("K", entity.getK());
        return "pages/enc/diffhell";
    }

    @GetMapping("/diffi-gen-b")
    public String diffi142(Model model) {
        DiffiHellEntity entity = diffHellService.findById();
        model.addAttribute("p", entity.getP());
        model.addAttribute("g", entity.getG());
        model.addAttribute("A", entity.getA());
        model.addAttribute("B", entity.getB());
        model.addAttribute("key1", entity.getKey2());
        model.addAttribute("key2", entity.getKey1());
        model.addAttribute("K", entity.getK());
        return "pages/enc/diffhell2";
    }

    @PostMapping("/diffi-gen-a")
    public String diffifif(Model model) {
        diffHellService.genK();
        return "redirect:/diffi-a-gen";
    }

    @PostMapping("/diffi-gen-b")
    public String diffifif1(Model model) {
        diffHellService.genK();
        return "redirect:/diffi-b-gen";
    }

    @GetMapping("/caesar")
    public String caesar(Model model) {
        String text = caesarService.findById().getContent();
        String plus = caesarService.findById().getPlus();
        String key = Integer.toString(caesarService.findById().getKey());
        String result = caesarService.findById().getResult();
        model.addAttribute("text", text);
        model.addAttribute("plus", plus);
        model.addAttribute("key", key);
        model.addAttribute("result", result);
        return "pages/enc/caesar";
    }

    @PostMapping("/caesar")
    public String caesarReturn(int key, Model model, String text, String plus) {
        caesarService.solve(text, key, plus);
        model.addAttribute("text", caesarService.findById().getContent());
        model.addAttribute("plus", caesarService.findById().getPlus());
        model.addAttribute("key", caesarService.findById().getKey());
        model.addAttribute("result", caesarService.findById().getResult());
        return "pages/enc/caesar";
    }

    @GetMapping("/vigenere")
    public String vigenere(Model model) {
        String text = vigenereService.findById().getContent();
        String key = vigenereService.findById().getKey();
        String result = vigenereService.findById().getResult();
        model.addAttribute("content", text);
        model.addAttribute("key", key);
        model.addAttribute("result", result);
        return "pages/enc/vigenere";
    }

    @PostMapping("/vigenere")
    public String vigenereReturn(String key, Model model, String content) {
        vigenereService.encrypt(content, key);
        String content1 = vigenereService.findById().getContent();
        String key1 = vigenereService.findById().getKey();
        String result = vigenereService.findById().getResult();
        model.addAttribute("content", content1);
        model.addAttribute("key", key1);
        model.addAttribute("result", result);
        return "pages/enc/vigenere";
    }

    @GetMapping("/gamma")
    public String gamming(Model model) {
        String text = gammingService.findById().getContent();
        String key = gammingService.findById().getKey();
        String result = gammingService.findById().getResult();
        model.addAttribute("content", text);
        model.addAttribute("key", key);
        model.addAttribute("result", result);
        return "pages/enc/gamma";
    }

    @PostMapping("/gamma")
    public String gammingReturn(String key, Model model, String content) {
        gammingService.encrypt(content, key);
        String content1 = gammingService.findById().getContent();
        String key1 = gammingService.findById().getKey();
        String result = gammingService.findById().getResult();
        model.addAttribute("content", content1);
        model.addAttribute("key", key1);
        model.addAttribute("result", result);
        return "pages/enc/gamma";
    }

    @GetMapping("/gammaSave")
    public String gamSave() {
        GammingEntity gammingEntity = gammingService.findById();
        gammingEntity.setContent(gammingEntity.getResult());
        gammingEntity.setResult("0");
        gammingService.save(gammingEntity);
        return "redirect:/gamma";
    }


    @GetMapping("/rsa")
    public String rsa(Model model) {
        int bitlen = rsaService.findById().getBitlen();
        BigInteger n = rsaService.findById().getN();
        BigInteger p = rsaService.findById().getP();
        BigInteger q = rsaService.findById().getQ();
        BigInteger fi = rsaService.findById().getFi();
        BigInteger e = rsaService.findById().getE();
        BigInteger d = rsaService.findById().getD();
        String decryptedText = rsaService.findById().getDecryptedText();
        String cipherText = rsaService.findById().getCipherText();
        model.addAttribute("n", n);
        model.addAttribute("p", p);
        model.addAttribute("q", q);
        model.addAttribute("fi", fi);
        model.addAttribute("e", e);
        model.addAttribute("d", d);
        model.addAttribute("bitlen", bitlen);
        model.addAttribute("dec", decryptedText);
        model.addAttribute("enc", cipherText);
        return "pages/enc/rsa";
    }

    @PostMapping("/rsa")
    public String rsaEnc(Model model, String dec, int bitlen) {
        String cipherText = rsaService.solve(dec, bitlen);
        System.out.println("lol");
        RSAEntity rsaEntity = rsaService.findById();
        BigInteger n = rsaEntity.getN();
        BigInteger p = rsaEntity.getP();
        BigInteger q = rsaEntity.getQ();
        BigInteger fi = rsaEntity.getFi();
        BigInteger e = rsaEntity.getE();
        BigInteger d = rsaEntity.getD();
        String decryptedText = rsaEntity.getDecryptedText();
        model.addAttribute("n", n);
        model.addAttribute("p", p);
        model.addAttribute("bitlen", bitlen);
        model.addAttribute("q", q);
        model.addAttribute("fi", fi);
        model.addAttribute("e", e);
        model.addAttribute("d", d);
        model.addAttribute("dec", decryptedText);
        model.addAttribute("enc", cipherText);
        return "pages/enc/rsa";
    }

    @GetMapping("/rsaDecrypt")
    public String rsaDec(Model model) {
        int bitlen = rsaService.findById().getBitlen();
        BigInteger n = rsaService.findById().getN();
        BigInteger p = rsaService.findById().getP();
        BigInteger q = rsaService.findById().getQ();
        BigInteger fi = rsaService.findById().getFi();
        BigInteger e = rsaService.findById().getE();
        BigInteger d = rsaService.findById().getD();
        String decryptedText = rsaService.findById().getDecryptedText();
        String cipherText = rsaService.findById().getCipherText();
        model.addAttribute("n", n);
        model.addAttribute("p", p);
        model.addAttribute("q", q);
        model.addAttribute("fi", fi);
        model.addAttribute("e", e);
        model.addAttribute("d", d);
        model.addAttribute("bitlen", bitlen);
        model.addAttribute("dec", decryptedText);
        model.addAttribute("enc", cipherText);
        return "pages/enc/rsaDecrypt";
    }

    @PostMapping("/rsaDecrypt")
    public String rsaEncDec(Model model, String enc, int bitlen) {
        String decrypted = rsaService.solveDec(enc, bitlen, rsaService.findById().getN(), rsaService.findById().getE(), rsaService.findById().getD());
        RSAEntity rsaEntity = rsaService.findById();
        BigInteger n = rsaEntity.getN();
        BigInteger p = rsaEntity.getP();
        BigInteger q = rsaEntity.getQ();
        BigInteger fi = rsaEntity.getFi();
        BigInteger e = rsaEntity.getE();
        BigInteger d = rsaEntity.getD();
        String decryptedText = decrypted;
        model.addAttribute("n", n);
        model.addAttribute("bitlen", bitlen);
        model.addAttribute("p", p);
        model.addAttribute("q", q);
        model.addAttribute("fi", fi);
        model.addAttribute("e", e);
        model.addAttribute("d", d);
        model.addAttribute("dec", decryptedText);
        model.addAttribute("enc", rsaEntity.getCipherText());
        return "pages/enc/rsaDecrypt";
    }


    @GetMapping("/rae")
    public String rae(Model model) {
        BigInteger a = raeService.findById().getA();
        BigInteger b = raeService.findById().getB();
        String d = raeService.findById().getD();
        String x = raeService.findById().getX();
        String y = raeService.findById().getY();
        if (a.equals(BigInteger.ZERO)) {
            model.addAttribute("a", "");
        } else {
            model.addAttribute("a", a);
        }
        if (b.equals(BigInteger.ZERO)) {
            model.addAttribute("b", "");
        } else {
            model.addAttribute("b", b);
        }
        if (d.equals("0")) {
            model.addAttribute("d", "Ещё нет результата.");
        } else {
            model.addAttribute("d", d);
        }
        if (x.equals("0")) {
            model.addAttribute("x", "Ещё нет результата.");
        } else {
            model.addAttribute("x", x);
        }
        if (y.equals("0")) {
            model.addAttribute("y", "Ещё нет результата.");
        } else {
            model.addAttribute("y", y);
        }
        return "pages/enc/rae";
    }

    @PostMapping("/rae")
    public String raeReturn(BigInteger a, Model model, BigInteger b) {
        raeService.solve(a, b);
        String d = raeService.findById().getD();
        String x = raeService.findById().getX();
        String y = raeService.findById().getY();
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        model.addAttribute("d", d);
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        return "pages/enc/rae";
    }

    @GetMapping("/solovei")
    public String solovei(Model model) {
        BigInteger n = soloveiService.findById().getN();
//        int k = milRabService.findById().getK();
        String result = soloveiService.findById().getResult();
        model.addAttribute("n", n);
//        model.addAttribute("k", k);
        model.addAttribute("result", result);
//        soloveiService.gen(128);
        return "pages/enc/solovei";
    }

    @GetMapping("/soloveiGen")
    public String soloveiGen(Model model) {
        BigInteger n = soloveiService.findById().getN();
//        int k = milRabService.findById().getK();
        int ra = soloveiService.findById().getRazm();
        String result = soloveiService.findById().getResult();

        model.addAttribute("n", n);
//        model.addAttribute("k", k);
        model.addAttribute("razm", ra);
        model.addAttribute("result", result);
//        soloveiService.gen(128);
        return "pages/enc/solovei";
    }

    @PostMapping("/soloveiGen")
    public String gen(int razm, Model model) {
        BigInteger n = soloveiService.gen(razm);
        soloveiService.save(n, "", razm);
        model.addAttribute("n", n);
        model.addAttribute("razm", razm);
        return "pages/enc/solovei";
    }

    @PostMapping("/solovei")
    public String soloveiRes(BigInteger n, Model model) {
        boolean res = soloveiService.solovei(n);
        model.addAttribute("n", n);
//        model.addAttribute("k", k);
        String resu;
        if (soloveiService.findById().getRes() == true) {
            resu = "Число вероятно простое.";
        }
        if (res) {
            resu = "Число вероятно простое.";
        } else {
            resu = "Число составное.";
        }
        model.addAttribute("result", resu);
        return "pages/enc/solovei";
    }

    @GetMapping("/mil-rab")
    public String milrab(Model model) {
        BigInteger n = milRabService.findById().getN();
//        int k = milRabService.findById().getK();
        String result = milRabService.findById().getResult();


        model.addAttribute("n", n);
//        model.addAttribute("k", k);
        model.addAttribute("result", result);
        return "pages/enc/milrab";
    }

    @PostMapping("/mil-rab")
    public String milrabRes(BigInteger n, Model model) {
        boolean res = milRabService.MilRab(n);
        model.addAttribute("n", n);
//        model.addAttribute("k", k);
        String resu;
        if (res) {
            resu = "Число вероятно простое.";
        } else {
            resu = "Число составное.";
        }
        model.addAttribute("result", resu);
        return "pages/enc/milrab";
    }

    @GetMapping("/inpow")
    public String inpow(Model model) {
        BigInteger a = raeService.findByIdInPow().getA();
        BigInteger b = raeService.findByIdInPow().getB();
        BigInteger n = raeService.findByIdInPow().getN();
        BigInteger result = raeService.findByIdInPow().getResult();
        if (a.equals(BigInteger.ZERO)) {
            model.addAttribute("a", "");
        } else {
            model.addAttribute("a", a);
        }
        if (b.equals(BigInteger.ZERO)) {
            model.addAttribute("b", "");
        } else {
            model.addAttribute("b", b);
        }
        if (n.equals(BigInteger.ZERO)) {
            model.addAttribute("n", "");
        } else {
            model.addAttribute("n", n);
        }
        if (result.equals(BigInteger.ZERO)) {
            model.addAttribute("result", "Ещё нет результата.");
        } else {
            model.addAttribute("result", result);
        }
        return "pages/enc/inpow";
    }

    @PostMapping("/inpow")
    public String inpowReturn(BigInteger a, Model model, BigInteger b, BigInteger n) {
        raeService.powder(a, b, n);
        BigInteger result = raeService.findByIdInPow().getResult();
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("n", n);
        model.addAttribute("result", result);
        return "pages/enc/inpow";
    }

    @GetMapping("/gammaRandom")
    public String gammingRan(Model model) {
        String text = gammingService.findById().getContent();
        String key = gammingService.findById().getKey();
        String result = gammingService.findById().getResult();
        model.addAttribute("content", text);
        model.addAttribute("key", key);
        model.addAttribute("result", result);
        return "pages/enc/gammaRandom";
    }

    @PostMapping("/gammaRandom")
    public String gammingRanReturn(Model model, String content) {
        gammingService.encryptRandom(content);
        String content1 = gammingService.findById().getContent();
        String key1 = gammingService.findById().getKey();
        String result = gammingService.findById().getResult();
        model.addAttribute("content", content1);
        model.addAttribute("key", key1);
        model.addAttribute("result", result);
        return "pages/enc/gammaRandom";
    }

    @GetMapping("/gammaRandomCheck")
    public String gammingRanCheck(Model model) {
        String text = gammingService.findById().getContent();
        String key = gammingService.findById().getKey();
        String result = gammingService.findById().getResult();
        model.addAttribute("content", text);
        model.addAttribute("key", key);
        model.addAttribute("result", result);
        return "pages/enc/gammaRandom";
    }

    @PostMapping("/gammaRandomCheck")
    public String gammaRanCheck(Model model) {
        gammingService.encryptRandomNoGen();
        String content1 = gammingService.findById().getContent();
        String key1 = gammingService.findById().getKey();
        String result = gammingService.findById().getResult();
        model.addAttribute("content", content1);
        model.addAttribute("key", key1);
        model.addAttribute("result", result);
        return "pages/enc/gammaRandom";
    }

    @GetMapping("/gammaSaveRandom")
    public String gamSaveRan() {
        GammingEntity gammingEntity = gammingService.findById();
        gammingEntity.setContent(gammingEntity.getResult());
        gammingEntity.setResult("0");
        gammingService.save(gammingEntity);
        return "redirect:/gammaRandom";
    }

    @GetMapping("/vigenereDec")
    public String vigenereGet(Model model) {
        String text = vigenereService.findById().getContent();
        String key = vigenereService.findById().getKey();
        String result = vigenereService.findById().getResult();
        model.addAttribute("content", text);
        model.addAttribute("key", key);
        model.addAttribute("result", result);
        return "pages/enc/vigenereDec";
    }

    @PostMapping("/vigenereDec")
    public String vigenereDecReturn(String key, Model model, String content) {
        vigenereService.decrypt(content, key);
        String content1 = vigenereService.findById().getContent();
        String key1 = vigenereService.findById().getKey();
        String result = vigenereService.findById().getResult();
        model.addAttribute("content", content1);
        model.addAttribute("key", key1);
        model.addAttribute("result", result);
        return "pages/enc/vigenereDec";
    }

    @GetMapping("/vigenereSave")
    public String vigSave() {
        VigenerEntity vigenerEntity = vigenereService.findById();
        vigenerEntity.setContent(vigenerEntity.getResult());
        vigenerEntity.setResult("0");
        vigenereService.save(vigenerEntity);
        return "redirect:/vigenere";
    }

    @GetMapping("/vigenereDecSave")
    public String vigDecSave() {
        VigenerEntity vigenerEntity = vigenereService.findById();
        vigenerEntity.setContent(vigenerEntity.getResult());
        vigenerEntity.setResult("0");
        vigenereService.save(vigenerEntity);
        return "redirect:/vigenereDec";
    }

    @GetMapping("/account/edit")
    public String editAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountEntity accountEntity = (AccountEntity) service.loadUserByUsername(userName);
        String name = accountEntity.getName();
        String sur = accountEntity.getSurname();
        String grp = accountEntity.getGroupID();
        model.addAttribute("name", name);
        model.addAttribute("sur", sur);
        model.addAttribute("grp", grp);
        return "pages/account-edit";
    }

    @GetMapping("/balance")
    public String upBalance() {
        return "pages/balance";

    }

    @GetMapping("/deleteVigenere")
    public String deleteVigenere() {
        VigenerEntity vigenerEntity = new VigenerEntity(3, "0", "0", "0");
        vigenereService.save(vigenerEntity);
        return "redirect:/vigenere";
    }

    @PostMapping("/balance")
    public String balanceUp(@ModelAttribute AccountEntity entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountEntity accountEntity = (AccountEntity) service.loadUserByUsername(userName);
        accountEntity.setMoney(accountEntity.getMoney() + entity.getMoney());
        service.saveUser(accountEntity);
        return "redirect:/account";
    }

    @PostMapping("/account/edit")
    public String saveAccount(@ModelAttribute AccountEntity accountEntity1) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountEntity accountEntity = (AccountEntity) service.loadUserByUsername(userName);
        accountEntity.setName(accountEntity1.getName());
        accountEntity.setSurname(accountEntity1.getSurname());
        accountEntity.setGroupID(accountEntity1.getGroupID());
        service.saveUser(accountEntity);
        return "redirect:/account";
    }

//    @GetMapping("all-notes/{id}")
//    public String get(@PathVariable int id, Model model) {
//        model.addAttribute("note", noteService.findById(id));
//        NoteEntity noteEntity = noteService.findById(id);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        AccountEntity accountEntity = (AccountEntity) service.loadUserByUsername(userName);
//        boolean result = false;
//        int res = 0;
//        for (int i = 0; i < accountEntity.getChecked().size(); i++) {
//            int test = accountEntity.getChecked().get(i);
//            if (noteEntity.getId() == test) {
//                result = true;
//                res = accountEntity.getTime().get(i);
//            }
//        }
//        if (res == 0) {
//            result = false;
//        }
//        model.addAttribute("check", false);
//        model.addAttribute("test", result);
//        model.addAttribute("input", res);
//        return "pages/note";
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @GetMapping("/account")
    public String nowAccountMy(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountEntity accountEntity = (AccountEntity) service.loadUserByUsername(userName);

        int money1 = accountEntity.getMoney();
//        model.addAttribute("res", res);
        String money = "Ваш баланс" + " - " + money1 + " рублей";
        model.addAttribute("money", money);
        boolean check = true;
        model.addAttribute("check", check);
        String name = accountEntity.getUsername();
        model.addAttribute("user", name);
        String initial = "Инициалы - " + accountEntity.getName() + " " + accountEntity.getSurname();
        model.addAttribute("init", initial);
        String group = "Группа № " + accountEntity.getGroupID();
        model.addAttribute("group", group);
        return "account";
    }

    @GetMapping("/account/{id}")
    public String nowAccount(@PathVariable int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            AccountEntity accountEntity = (AccountEntity) service.loadUserByUsername(userName);
//            AccountInfoEntity accountInfoEntity = accountEntity.getAccountInfoEntity();
            int money1 = accountEntity.getMoney();
            AccountEntity accountEntity1;
            accountEntity1 = service.loadUserById(id);
//        model.addAttribute("res", res);
            boolean check1 = false;
            if (accountEntity1.getUsername() == accountEntity.getUsername()) {
                check1 = true;
            }
//            String money = "Ваш баланс" + " - " + money1 + " рублей";
            model.addAttribute("check", check1);
//            model.addAttribute("money", money);
//        String name = accountEntity.getUsername();
            String name = accountEntity1.getUsername();
            model.addAttribute("user", name);
            String initial = "Инициалы - " + accountEntity1.getName() + " " + accountEntity1.getSurname();
            model.addAttribute("init", initial);
            String group = "Группа № " + accountEntity1.getGroupID();
            model.addAttribute("group", group);
            return "account";
        } catch (Exception e) {
            return "error-no-acc";
        }

    }
}
