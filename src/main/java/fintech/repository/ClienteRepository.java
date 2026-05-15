package fintech.repository;

import fintech.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Bom que o Spring Data JPA já embute os métodos save(), findAll(), deleteById() aqui. Bom demaize... :)
}