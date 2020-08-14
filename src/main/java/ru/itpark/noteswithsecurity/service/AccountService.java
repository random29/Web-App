package ru.itpark.noteswithsecurity.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.encoder.AccountPasswordEncoder;
import ru.itpark.noteswithsecurity.entity.AccountEntity;
import ru.itpark.noteswithsecurity.exception.ProductNotFoundException;
import ru.itpark.noteswithsecurity.repository.AccountRepository;
import ru.itpark.noteswithsecurity.repository.NoteRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Primary
@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository repository;
    private final NoteRepository noteRepository;

    public AccountService(AccountRepository repository, NoteRepository noteRepository) {
        this.noteRepository = noteRepository; this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByUsername(s)
                .orElseThrow();
    }
    public List<AccountEntity> findAll() {
        return repository.findAll();
    }

    // TODO: spring-security-thymeleaf, вместо написания своих методов
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // anonymous
        return !(authentication instanceof AnonymousAuthenticationToken);
    }
    public AccountEntity loadUserById(int id) throws UsernameNotFoundException {
        return repository.findById(id).orElseThrow();
    }
    public boolean hasAuthority(String authority) {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(o -> o.getAuthority().equals(authority))
                ;
    }

    public boolean isBought(int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountEntity accountEntity = (AccountEntity) loadUserByUsername(userName);
        System.out.println(accountEntity.getCourseId().contains(id));
        return accountEntity.getCourseId().contains(id);
    }

    public boolean isOwned(int id) {
        AccountEntity entity = (AccountEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return entity.getId() == 1;
        // TODO: какую угодно логику когда нужно
    }

    public void registerUser(AccountEntity user) {
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAuthorities(Collections.emptyList());
        user.setAuthorities(List.of(new SimpleGrantedAuthority("USER")));
        user.setCredentialsNonExpired(true);
        String temp = user.getPassword();
        AccountPasswordEncoder accountPasswordEncoder = new AccountPasswordEncoder();
        String temp1 = accountPasswordEncoder.encode(temp);
        user.setPassword(temp1);
        user.setEnabled(true);
        try {
            repository.save(user);
            List<AccountEntity> accountEntities = repository.findAll();
            System.out.println(accountEntities);
        } catch (Exception e){
            throw new ProductNotFoundException();
        }
    }
//    public AccountEntity findByEmail(String email) {
//        return repository.findByEmail(email);
//    }
//
//    public AccountEntity findByConfirmationToken(String confirmationToken) {
//        return repository.findByConfirmationToken(confirmationToken);
//    }

    public void saveUser(AccountEntity user) {
        repository.save(user);
    }
}
