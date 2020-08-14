package ru.itpark.noteswithsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itpark.noteswithsecurity.entity.*;
import ru.itpark.noteswithsecurity.repository.*;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class NotesWithSecurityApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(NotesWithSecurityApplication.class, args);

        var repository = context.getBean(AccountRepository.class);
        var encoder = context.getBean(PasswordEncoder.class);
        var reposiroryNotes = context.getBean(NoteRepository.class);
        var repositoryCaesar = context.getBean(CaesarRepository.class);
        var repositoryVigener = context.getBean(VigenerRepository.class);
        var repositoryGamma = context.getBean(GammingRepository.class);
        var repositoryRae = context.getBean(RaeRepository.class);
        var repositoryInPow = context.getBean(InPowRepository.class);
        var repositoryMilRab = context.getBean(MilRabRepository.class);
        var repositoryRSA = context.getBean(RSARepository.class);
        var repositorySolovei = context.getBean(SoloveiRepository.class);
        var repositoryDiff = context.getBean(DiffHellRepository.class);
        var repositoryRho = context.getBean(RhoRepository.class);
        var repositoryRc4 = context.getBean(RC4repository.class);
//        var inforepo = context.getBean(AccountInfoRepository.class);

        // регистрация
        repository.saveAll(
                List.of(
                        new AccountEntity(
                                0,
                                "admin",
                                encoder.encode("randompassword"),
                                List.of(
                                        new SimpleGrantedAuthority("ADD"),
                                        new SimpleGrantedAuthority("REMOVE"),
                                        new SimpleGrantedAuthority("USER")
                                ),
                                true,
                                true,
                                true,
                                true,
                                Collections.emptyList(),
                                Collections.emptyList(),
                                10000,
                                "Админ",
                                "Админович",
                                "09631", List.of(3)
                        ),
                        new AccountEntity(
                                0,
                                "user",
                                encoder.encode("user"),
                                Collections.emptyList(),
                                true,
                                true,
                                true,
                                true,
                                Collections.emptyList(),
                                Collections.emptyList(),
                                10000,
                                "Пользователь",
                                "Пользователич",
                                "09631", List.of(3))

                )
        );
        reposiroryNotes.saveAll(
                List.of(
                        new NoteEntity(
                                3,
                                "Английский язык",
                                "Курсы английского языка от носителей. Окунитесь в мир прекрасного английского всего за 10000 рублей. Ограниченное предложение!",
                                List.of("Неплохо, совсем неплохо.", "Материал местами подкачал, а так ничего."), 10000
                        )
                )
        );
        repositoryCaesar.saveAll(
                List.of(
                        new CaesarEntity(
                                3,
                                "0",
                                "+",
                                0,
                                "0"
                        )
                )
        );
        repositoryVigener.saveAll(
                List.of(
                        new VigenerEntity(
                                3,
                                "0",
                                "0",
                                "0"
                        )
                )
        );
        repositoryGamma.saveAll(
                List.of(
                        new GammingEntity(
                                3,
                                "0",
                                "0",
                                "0"
                        )
                )
        );
        repositoryRae.saveAll(
                List.of(
                        new RaeEntity(
                                3,
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0),
                                "0",
                                "0",
                                "0"
                        )
                )
        );
        repositoryInPow.saveAll(
                List.of(
                        new InPowEntity(
                                3,
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0)
                        )
                )
        );
        repositoryMilRab.saveAll(
                List.of(
                        new MilRabEntity(
                                3,
                                BigInteger.valueOf(0),
                                0,
                                "Ещё нет."
                        )
                )
        );
        repositorySolovei.saveAll(
                List.of(
                        new SoloveiEntity(
                                3,
                                BigInteger.valueOf(0),
                                0,
                                "Ещё нет.",
                                0,
                                false
                        )
                )
        );
        repositoryRSA.saveAll(
                List.of(
                        new RSAEntity(
                                3,
                                1024,
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(0),
                                " ",
                                "Ещё нет."
                        )
                )
        );
        repositoryDiff.saveAll(
                List.of(
                        new DiffiHellEntity(
                                3,
                                BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, 0)
                )
        );
        repositoryRho.saveAll(
                List.of(
                        new RhoEntity(
                                3,
                                List.of(),
                                BigInteger.ZERO
                        )
                )
        );
        repositoryRc4.saveAll(
                List.of(
                        new RC4Entity(3, BigInteger.valueOf(0), " ")
                )
        );
//        inforepo.saveAll(List.of(
//                new AccountInfoEntity(0, 10000,
//                        "Админ",
//                        "Админович",
//                        "09631"),
//                new AccountInfoEntity(1,
//                        10000,
//                        "Пользователь",
//                        "Пользователич",
//                        "09631")
//                )
//        );
    }

}

