package fintech;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String responderPing() {
        return "Pong! Meu servidor Fintech está no ar e respirando!";
    }
}