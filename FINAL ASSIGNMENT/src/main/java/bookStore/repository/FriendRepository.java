package bookStore.repository;

import bookStore.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long>{
    Friend save(Friend friend);
    List<Friend> findAllByUserOneId(Long userOneId);

   // void delete
/*
    @Modifying
    @Transactional
    @Query("DELETE FROM friend WHERE userOneId = ?1 and userTwoId = ?2")*/
    void deleteById(Long id);

}
