package bookStore.service;

import bookStore.dto.FriendDTO;
import bookStore.entity.Friend;

import java.util.List;

public interface FriendService {
    List<Friend> findAll();
    Friend create(FriendDTO friendDTO);
    void deleteById(Long id);
}
