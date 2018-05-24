package bookStore.service;

import bookStore.dto.FriendDTO;
import bookStore.entity.Friend;

import java.util.List;

public class CachingFriendService implements FriendService{
    private FriendService origin;


    public CachingFriendService(FriendService origin) {
        this.origin = origin;
    }

    @Override
    public List<Friend> findAll() {
        return origin.findAll();
    }

    @Override
    public Friend create(FriendDTO friendDTO) {
        return origin.create(friendDTO);
    }

    @Override
    public void deleteById(Long id) {
        origin.deleteById(id);
    }
}
