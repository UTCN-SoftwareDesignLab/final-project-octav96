package bookStore.service;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class CachingUserService implements UserService{
    private UserService origin;

    public CachingUserService(UserService origin) {
        this.origin = origin;
    }

    @Override
    public User create(UserDTO userDTO) {
        return origin.create(userDTO);
    }

    @Override
    public List<User> getAll() {
        return origin.getAll();
    }

    @Override
    public List<User> findAll() {
        return origin.findAll();
    }

    @Override
    public void deleteByUsername(String username) {
        origin.deleteByUsername(username);
    }

    @Override
    public void updateUser(String address, String username) {
        origin.updateUser(address,username);
    }

    @Override
    public User findByUsername(String username) {
        return origin.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return origin.findById(id);
    }
}
