package fintech.controller;

import fintech.infra.TokenService;
import fintech.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager; // O "Segurança" do Spring que configuramos lá atrás

    @Autowired
    private TokenService tokenService; // A nossa Máquina de Pulseiras

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        // 1. Empacota o e-mail e a senha que vieram do Insomnia
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        // 2. O Segurança vai no banco de dados e verifica se a senha bate
        var authentication = manager.authenticate(authenticationToken);

        // 3. Se chegou aqui, a senha tá certa! Pega o usuário e imprime a Pulseira VIP (Token)
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // 4. Devolve a pulseira pro cliente no formato JSON
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    // --- DTOs (Data Transfer Objects) ---
    // Usamos 'records' (um atalho do Java) para não ter que criar classes inteiras só pra ler/devolver JSON
    public record DadosAutenticacao(String login, String senha) {}

    public record DadosTokenJWT(String token) {}
}