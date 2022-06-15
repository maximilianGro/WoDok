package eHealth.Repository;

import eHealth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find the user by the unique mail.
     *
     * @param email of the user
     * @return the found user
     */
    User findUserByEmail(String email);

    User findUserById(Long id);
}
