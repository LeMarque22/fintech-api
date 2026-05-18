package fintech.repository;

import fintech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método mágico do Spring: Busca o usuário pelo login (e-mail)
    UserDetails findByLogin(String login);
}