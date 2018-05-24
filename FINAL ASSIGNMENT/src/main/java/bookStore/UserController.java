package bookStore;

import bookStore.dto.FriendDTO;
import bookStore.dto.UserDTO;
import bookStore.entity.Friend;
import bookStore.entity.User;
import bookStore.repository.UserRepository;
import bookStore.service.FriendService;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    //list all friends
    //add friend
    //delete friend
    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @GetMapping(path = "/user")
    public String user(/*Model model, Principal principal*/){
      //  User loggedInUser = userService.findByUsername(principal.getName());
       // model.addAttribute("user", loggedInUser);
        return "user";
    }

    @GetMapping("/list_all_friends")
    public ModelAndView listAllFriends(Principal principal){
       User user = userService.findByUsername(principal.getName());
       List<Friend> friendList = friendService.findAll();
        int ii = 0;
        while(ii < friendList.size()){
           if(friendList.get(ii).getUserOneId() != user.getId())
               friendList.remove(ii);
           else
               ii++;
       }
       List<User> users = new ArrayList<>();
       for(int i = 0;i < friendList.size();i++){
           users.add(userService.findById(friendList.get(i).getUserTwoId()).get());
       }
        ModelAndView mav = new ModelAndView("list_all_users");
        mav.addObject("users",users);
        return mav;
    }

    @GetMapping("/add_friend")
    public String addFriendGet(Model model){
        model.addAttribute("user", new UserDTO());
        return "add_friend";
    }

    @PostMapping("/add_friend")
    public String addFriendPost(@ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult,
                                Principal principal){
        User user = userService.findByUsername(userDTO.getUsername());
        List<Friend> friendList = friendService.findAll();
        if(user == null){
            bindingResult.rejectValue("username", "error.user", "USER DOESN'T NOT EXIST");
            return "add_friend";
        }
        if(user.getUsername().equals(principal.getName())){
            bindingResult.rejectValue("username", "error.user", "YOU CAN'T ADD YOURSELF AS A FRIEND");
            return "add_friend";
        }
        for(int i = 0;i< friendList.size();i++){
            if(friendList.get(i).getUserOneId() == userService.findByUsername(principal.getName()).getId() &&
                    friendList.get(i).getUserTwoId() == userService.findByUsername(userDTO.getUsername()).getId()){
                bindingResult.rejectValue("username", "error.user",
                        "YOU ARE ALREADY FRIENDS WITH THIS USER");
                return "add_friend";
            }

        }
        FriendDTO friendDTO = new FriendDTO();
        FriendDTO friendDTO1 = new FriendDTO();

        friendDTO.setUserOneId(userService.findByUsername(principal.getName()).getId());
        friendDTO.setUserTwoId(user.getId());

        friendDTO1.setUserOneId(user.getId());
        friendDTO1.setUserTwoId(userService.findByUsername(principal.getName()).getId());

        friendService.create(friendDTO);
        friendService.create(friendDTO1);
        return "add_friend_successful";
    }

    @GetMapping("remove_friend")
    public String removeFriendGet(Model model){
        model.addAttribute("user", new UserDTO());
        return "remove_friend";
    }

    @PostMapping("/remove_friend")
    public String removeFriendPost(@ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Principal principal){
        List<Friend> friendList = friendService.findAll();
        int k = 0;
        User user = userService.findByUsername(userDTO.getUsername());
        if(user == null){
            bindingResult.rejectValue("username", "error.user", "USER DOESN'T NOT EXIST");
            return "remove_friend";
        }
        for(int i = 0;i < friendList.size();i++){
            if(friendList.get(i).getUserOneId() == userService.findByUsername(principal.getName()).getId() &&
                    friendList.get(i).getUserTwoId() == userService.findByUsername(userDTO.getUsername()).getId()){
                friendService.deleteById(friendList.get(i).getId());
                k = 1;
            }

            if(friendList.get(i).getUserOneId() == userService.findByUsername(userDTO.getUsername()).getId() &&
                    friendList.get(i).getUserTwoId() == userService.findByUsername(principal.getName()).getId()){
                friendService.deleteById(friendList.get(i).getId());
                 k = 1;
            }
        }
        if(k == 0) {
            bindingResult.rejectValue("username", "error.user", "YOU DO NOT HAVE ANY FRIENDS WITH THIS SPECIFIC USERNAME");
            return "remove_friend";
        }
        return "remove_friend_successful";
    }

    @PostMapping("/list_all_users_friends")
    public ModelAndView listFriendsPost(@RequestParam("username") String username){
        User user = userService.findByUsername(username);
        if(user.getPrivacy()) {
            ModelAndView mav = new ModelAndView("access_denied");

            return mav;
            //return "access_denied";
        }
        System.out.println(user.getUsername());
        List<Friend> friendList = friendService.findAll();
        List<User> users = new ArrayList<>();

        for(int i = 0;i < friendList.size();i++){
            if(friendList.get(i).getUserOneId() == user.getId())
                users.add(userService.findById(friendList.get(i).getUserTwoId()).get());
        }
        ModelAndView mav = new ModelAndView("list_friends");
        mav.addObject("users",users);
        return mav;
    }


    @PostMapping("/add_friend_of_friend")
    public String addFriendOfFriend(@RequestParam("username") String username,
                                    Principal principal){
        User user = userService.findByUsername(username);
        if(user.getId() == userService.findByUsername(principal.getName()).getId()){
            return "self_addition";
        }

        List<Friend> friendList = friendService.findAll();
        for(int i = 0;i< friendList.size();i++){
            if(friendList.get(i).getUserOneId() == userService.findByUsername(principal.getName()).getId() &&
                    friendList.get(i).getUserTwoId() == userService.findByUsername(user.getUsername()).getId()){
               return "already_friends";
            }

        }

        FriendDTO friendDTO = new FriendDTO();
        FriendDTO friendDTO1 = new FriendDTO();

        friendDTO.setUserOneId(userService.findByUsername(principal.getName()).getId());
        friendDTO.setUserTwoId(user.getId());

        friendDTO1.setUserOneId(user.getId());
        friendDTO1.setUserTwoId(userService.findByUsername(principal.getName()).getId());

        friendService.create(friendDTO);
        friendService.create(friendDTO1);
        return "add_friend_successful";

    }
}
