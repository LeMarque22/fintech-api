package fintech.controller;

import fintech.model.Cliente;
import fintech.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    // Método para LISTAR todo mundo que tá no banco (GET)
    @GetMapping
    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    // Método ÚNICO para CADASTRAR um cliente novo, agora com o Escudo (@Valid)
    @PostMapping
    public Cliente cadastrar(@RequestBody @Valid Cliente novoCliente) {
        return repository.save(novoCliente);
    }
}