package ru.itpark.noteswithsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/games").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/images/*").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/register").permitAll()
//                .antMatchers("/encryption").permitAll()
                .antMatchers("/caesar").permitAll()
                .antMatchers("/vigenere/**").permitAll()
                .antMatchers("/vigenereDec/**").permitAll()
                .antMatchers("/vigenereDecSave/**").permitAll()
                .antMatchers("/vigenereSave/**").permitAll()
                .antMatchers("/gamma/**").permitAll()
                .antMatchers("/gammaRandom/**").permitAll()
                .antMatchers("/gammaRandomCheck/**").permitAll()
                .antMatchers("/gammaSaveRandom/**").permitAll()
                .antMatchers("/gammaSave/**").permitAll()
                .antMatchers("/deleteVigenere/**").permitAll()
                .antMatchers("/encryption/**").permitAll()
                .antMatchers("/rae/**").permitAll()
                .antMatchers("/inpow/**").permitAll()
                .antMatchers("/mil-rab/**").permitAll()
                .antMatchers("/rsa/**").permitAll()
                .antMatchers("/rsaDecrypt/**").permitAll()
                .antMatchers("/solovei/**").permitAll()
                .antMatchers("/soloveiGen/**").permitAll()
                .antMatchers("/login-error").permitAll()
                .antMatchers("/").permitAll()
                // /media/img/a.jpg -> **
//                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/account", true)
                    .permitAll()
                .and()
                .logout().permitAll();
    }
}
