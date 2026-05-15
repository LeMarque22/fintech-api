package fintech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF; // Mágica: O Java já conhece a matemática do CPF!

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco!")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório!")
    @Email(message = "Formato de e-mail inválido!")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O CPF é obrigatório!")
    @CPF(message = "O CPF informado é inválido!")
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    // --- Construtor Vazio ---
    public Cliente() {
    }

    // --- Getters e Setters  ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}