package fintech.controller;

import fintech.model.Conta;
import fintech.model.Transacao;
import fintech.repository.ContaRepository;
import fintech.repository.ClienteRepository;
import fintech.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    // 1. Método para ABRIR uma conta nova
    @PostMapping
    public Conta abrirConta(@RequestBody Conta novaConta) {
        return contaRepository.save(novaConta);
    }

    // 2. Método para DEPOSITAR
    @PatchMapping("/{id}/deposito")
    public Conta depositar(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request) {
        BigDecimal valorDeposito = request.get("valor");
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));

        conta.setSaldo(conta.getSaldo().add(valorDeposito));
        return contaRepository.save(conta);
    }

    // 3. Método para realizar uma TRANSFERÊNCIA (Pix)
    @PostMapping("/transferencia")
    @Transactional
    public String transferir(@RequestBody Map<String, String> request) {

        Long idOrigem = Long.valueOf(request.get("origem"));
        Long idDestino = Long.valueOf(request.get("destino"));
        BigDecimal valorTransferencia = new BigDecimal(request.get("valor"));

        Conta contaOrigem = contaRepository.findById(idOrigem)
                .orElseThrow(() -> new RuntimeException("Conta de origem não existe!"));

        Conta contaDestino = contaRepository.findById(idDestino)
                .orElseThrow(() -> new RuntimeException("Conta de destino não existe!"));

        if (contaOrigem.getSaldo().compareTo(valorTransferencia) < 0) {
            throw new RuntimeException("Saldo insuficiente para o Pix!");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valorTransferencia));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valorTransferencia));

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        // --- GERANDO OS RECIBOS ---
        Transacao reciboOrigem = new Transacao("PIX_ENVIADO", valorTransferencia, contaOrigem);
        Transacao reciboDestino = new Transacao("PIX_RECEBIDO", valorTransferencia, contaDestino);

        transacaoRepository.save(reciboOrigem);
        transacaoRepository.save(reciboDestino);

        return "Sucesso! O Pix de R$ " + valorTransferencia + " foi enviado.";
    } // <-- AQUI FECHA O TRANSFERIR!

    // 4. Método para TIRAR O EXTRATO (Agora sim, do lado de fora!)
    @GetMapping("/{id}/extrato")
    public List<Transacao> tirarExtrato(@PathVariable Long id) {
        return transacaoRepository.findByContaId(id);
    }
}