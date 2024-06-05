package jp.te4a.spring.boot.myapp13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import java.lang.CharSequence;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // PBKDF2によるパスワード暗号化
    //CharSequenceSE secret, int saltLength, int iterations, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm
    @Bean
    public PasswordEncoder passwordEncoder() {
        CharSequence secret = "f";
        return new Pbkdf2PasswordEncoder(secret, 10, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1);
    }
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //全ユーザアクセス可能パス
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/webjars/**", "/css/**").permitAll()
                .requestMatchers("/loginForm").permitAll()
                .requestMatchers("/users").permitAll()
                .requestMatchers("/users/create").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                //ログイン処理URL
                .loginProcessingUrl("/login")
                //ログインページURL
                .loginPage("/loginForm")
                //ログイン失敗時URL
                .failureUrl("/loginForm?error")
                //ログイン成功時URL
                .defaultSuccessUrl("/books", true)
                .usernameParameter("username")
                .passwordParameter("password")
            )
            //ログアウト時
            .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginForm")
            );
        
        return http.build();
    }
}