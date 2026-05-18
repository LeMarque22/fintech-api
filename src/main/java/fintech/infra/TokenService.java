package fintech.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import fintech.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // A senha mestra da sua API. Em produção, isso fica escondido nas variáveis do servidor!
    private String secret = "minha_senha_super_secreta_123";

    public String gerarToken(Usuario usuario) {
        try {
            // Define o algoritmo de criptografia e a senha mestra
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("Fintech API") // Quem emitiu a pulseira?
                    .withSubject(usuario.getLogin()) // Para quem é a pulseira?
                    .withExpiresAt(dataExpiracao()) // Quando a pulseira perde a validade?
                    .sign(algoritmo); // Assina e carimba!
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    // Regra de Negócio: A pulseira (Token) vale por 2 horas!
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Fintech API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject(); // Devolve o e-mail que está dentro do token!
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
}