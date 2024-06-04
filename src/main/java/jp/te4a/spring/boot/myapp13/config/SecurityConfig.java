package jp.te4a.spring.boot.myapp13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // PBKDF2によるパスワード暗号化
    //CharSequenceSE secret, int saltLength, int iterations, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder(null,10,10,null);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/webjars/**", "/css/**").permitAll()
                .requestMatchers("/loginForm").permitAll()
                .requestMatchers("/users").permitAll()
                .requestMatchers("/users/create").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginProcessingUrl("/login")
                .loginPage("/loginForm")
                .failureUrl("/loginForm?error")
                .defaultSuccessUrl("/books", true)
                .usernameParameter("username")
                .passwordParameter("password")
            )
            .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginForm")
            );
        
        return http.build();
    }
}
/*
 * http
 *  全ユーザアクセス可能パス
    .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/webjars/**", "/css/**").permitAll()
        .requestMatchers("/loginForm").permitAll()
        .requestMatchers("/users").permitAll()
        .requestMatchers("/users/create").permitAll()
        .anyRequest().authenticated()
    )
    .formLogin((form) -> form
        .loginProcessingUrl("/login") ログイン処理URL
        .loginPage("/loginForm")　ログインページURL
        .failureUrl("/loginForm?error")　ログイン失敗時URL
        .defaultSuccessUrl("/books", true)　ログイン成功時URL
        .usernameParameter("username")
        .passwordParameter("password")
    )
    ログアウト時
    .logout((logout) -> logout
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/loginForm")
    );
 * 
 */