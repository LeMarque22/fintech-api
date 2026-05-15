package fintech.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice // "Atenção Spring, eu sou o Advogado de Defesa de todos os Controllers!"
public class TratadorDeErros {

    // Quando acontecer uma exceção de Validação (MethodArgumentNotValidException), chame este método!
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {

        // Pega a lista de todos os campos que deram erro
        List<FieldError> erros = ex.getFieldErrors();

        // Transforma a lista do Spring numa lista limpa só com o Campo e a Mensagem
        var errosFormatados = erros.stream()
                .map(DadosErroValidacao::new)
                .toList();

        // Devolve o Erro 400, mas agora com o nosso JSON maquiado no "body"
        return ResponseEntity.badRequest().body(errosFormatados);
    }

    // Um "Record" é um atalho do Java moderno para criar uma classe simples de dados
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}