package fintech.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login; // Vamos usar o e-mail como login

    @Column(nullable = false)
    private String senha; // Aqui vai ficar a senha criptografada (hash)

    // Construtor Vazio
    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // --- Métodos do Contrato UserDetails (Obrigatórios para o Spring Security) ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Todo mundo que cadastrar será um usuário comum ("ROLE_USER")
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha; // Ensina pro Spring onde está a senha
    }

    @Override
    public String getUsername() {
        return login; // Ensina pro Spring onde está o login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta não expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta não está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // A senha não expira
    }

    @Override
    public boolean isEnabled() {
        return true; // O usuário está ativo
    }

    // --- Getters e Setters Padrões ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}