package fintech.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                // Avisa ao Spring: "Não guarde sessão na memória, nós vamos usar Tokens (Pulseira VIP)!"
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/v3/api-docs/**", "/v3/api-docs", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    // Porta Aberta 1: Qualquer um pode tentar fazer Login
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();

                    // Porta Aberta 2: Qualquer um pode se Cadastrar (Clientes)
                    req.requestMatchers(HttpMethod.POST, "/clientes").permitAll();

                    // MÁGICA AQUI: Porta Aberta 3: Qualquer um pode criar um Usuário de acesso!
                    req.requestMatchers(HttpMethod.POST, "/usuarios").permitAll();

                    // O Resto: Trancado! Só entra com a Pulseira VIP (Token JWT)
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Ensina o Spring a injetar o Gerenciador de Autenticação (vamos usar no Controller)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Ensina o Spring que as senhas no nosso banco estarão criptografadas com o algoritmo BCrypt!
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}