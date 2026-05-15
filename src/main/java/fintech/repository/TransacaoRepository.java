package fintech.repository;

import fintech.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    // MÁGICA: Só de escrever "findByContaId", o Spring já sabe que ele tem que ir
    // no banco e trazer todas as transações que pertencem a uma conta específica!
    List<Transacao> findByContaId(Long contaId);
}