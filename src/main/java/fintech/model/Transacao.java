package fintech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo; // Vai guardar se foi "PIX_ENVIADO", "PIX_RECEBIDO" ou "DEPOSITO"

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now(); // Pega a hora exata do servidor!

    // A Mágica do Relacionamento: Várias transações pertencem a UMA conta
    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    @JsonIgnore // Dica de Sênior: Evita que o Java entre num loop infinito ao gerar o JSON
    private Conta conta;

    // --- Construtor Vazio (Obrigatório) ---
    public Transacao() {
    }

    // --- Construtor Inteligente (Para facilitar a nossa vida) ---
    public Transacao(String tipo, BigDecimal valor, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
        this.dataHora = LocalDateTime.now();
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
}