package bookStore.service;

import bookStore.dto.FriendDTO;
import bookStore.entity.Friend;
import bookStore.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FriendServiceImpl implements FriendService{
    FriendRepository friendRepository;

    @Autowired
    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public List<Friend> findAll() {
        return friendRepository.findAll();
    }

    @Override
    public Friend create(FriendDTO friendDTO) {
        Friend friend = new Friend(friendDTO.getUserOneId(), friendDTO.getUserTwoId());
        return friendRepository.save(friend);
    }

    @Override
    public void deleteById(Long id) {
        friendRepository.deleteById(id);
    }
}
