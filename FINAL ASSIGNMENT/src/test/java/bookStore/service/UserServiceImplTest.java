package bookStore.service;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    User user;
    UserDTO userDTO;
    @Before
    public void setUp() {


        userService = new UserServiceImpl(userRepository);

        List<User> users = new ArrayList<>();


        user = new User("OCTAV", "abcBAC123$",21, "AIUD", "ADMINISTRATOR");
        users.add(user);

        userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setAddress(user.getAddress());
        userDTO.setAge(user.getAge());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        when(userRepository.findAll()).thenReturn(users);
      //  Mockito.when(bookRepository.findAll()).thenReturn(books);

        //Mockito.when(bookRepository.findAllByTitle("ALO")).thenReturn((Collections.singletonList(book)));
        //when(userRepository.deleteByUsername("OCTAV")).thenReturn(users);

    }


    @Test
    public void create() {
       // when(userRepository.save(any(User.class))).thenReturn(new User());
        when(userRepository.save(any(User.class))).thenReturn(new User());
        UserDTO userDTO = new UserDTO();

        assertThat(userService.create(userDTO), is(notNullValue()));
    }

    @Test
    public void getAll() {

       // List<User> users = userService.getAll();
        assertTrue(userService.getAll().size() == 1);
    }

    @Test
    public void findAll() {
        assertTrue(userService.findAll().size() == 1);
    }

    @Test
    public void deleteByUsername() throws RuntimeException{

        doThrow(new RuntimeException()).when(userRepository).deleteByUsername("OCTAV");

    }


    @Test()
    public void updateUser() throws RuntimeException{

        doThrow(RuntimeException.class).when(userRepository).updateUser("Jamaica", "OCTAV");

    }
}