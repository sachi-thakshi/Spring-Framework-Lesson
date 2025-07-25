package lk.ijse.gdse.o_13_spring_security_with_jwt.repository;

import lk.ijse.gdse.o_13_spring_security_with_jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
