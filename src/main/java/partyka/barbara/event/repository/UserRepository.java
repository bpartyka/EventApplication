package partyka.barbara.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import partyka.barbara.event.model.User;
import partyka.barbara.event.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);


    List<User> findAllByName(String userName);
}