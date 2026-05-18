package fintech.controller;

import fintech.model.Usuario;
import fintech.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // A nossa "máquina de moer carne" (Criptografia)

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {

        // 1. Pegamos a senha limpa e passamos no liquidificador do BCrypt
        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        // 2. Criamos o objeto Usuario com a senha já blindada
        Usuario novoUsuario = new Usuario(dados.login(), senhaCriptografada);

        // 3. Salvamos no banco de dados
        repository.save(novoUsuario);

        // 4. Retornamos 200 OK (Sem devolver a senha de volta, por segurança!)
        return ResponseEntity.ok().body("Usuário cadastrado com sucesso!");
    }

    // DTO para receber os dados do Insomnia
    public record DadosCadastroUsuario(
            @NotBlank String login,
            @NotBlank String senha
    ) {}
}