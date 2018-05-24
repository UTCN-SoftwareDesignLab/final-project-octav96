package bookStore.repository;

import bookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAll();

    User save(User user);

    User findByUsername(String username);

    Optional<User> findById(Long id);

    @Transactional
    void deleteByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User u set u.address = ?1 where u.username = ?2")
    void updateUser(String address, String username);

    @Transactional
    @Modifying
    @Query("update User u set u.privacy = ?1 where u.username = ?2")
    void updateUserPrivacy(Boolean privacy, String username);
}
