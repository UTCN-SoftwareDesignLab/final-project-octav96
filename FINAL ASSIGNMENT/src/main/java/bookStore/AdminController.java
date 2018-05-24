package bookStore;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.service.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/admin")
    public String admin(){
        return "/admin";
    }

    //CREATE USER
    @GetMapping(path = "/add_user")
    public String addUserForm(Model model){
        model.addAttribute("user", new UserDTO());
        return "add_user";

    }

    @PostMapping(path = "/add_user")
    public String addUserSubmit(@ModelAttribute("user") @Valid UserDTO userDTO,
                                BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "add_user";
        }
      //  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if(userDTO.getRole().equals("USER"))
            userDTO.setIcon(BlobProxy.generateProxy(getImage(userDTO.getUsername())));
        userDTO.setPassword(userDTO.getPassword());
        userService.create(userDTO);
        return "add_user_result";
    }

    public static byte[] getImage(String string){
        Path path = Paths.get("inputProfilePictures/" + string +".JPG");
        byte[] data = null;

        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    //DELETE USER
    @GetMapping(path = "/delete_user")
    public String deleteUserForm(Model model){
        model.addAttribute("user", new UserDTO());
        return "delete_user";
    }

    @PostMapping(path = "/delete_user")
    public String deleteUserSubmit(@ModelAttribute("user") @Valid UserDTO userDTO,
                                   BindingResult bindingResult){
        if (bindingResult.hasFieldErrors("username")) {
            return "delete_user";
        }
        userService.deleteByUsername(userDTO.getUsername());
        return "delete_user_result";
    }

    //UPDATE USER
    @GetMapping(path = "/update_user")
    public String updateUserForm(Model model){
        model.addAttribute("user",new UserDTO());
        return "/update_user";
    }

    @PostMapping(path = "/update_user")
    public String updateUserSubmit(@ModelAttribute("user") @Valid UserDTO userDTO,
                                   BindingResult bindingResult){
        if (bindingResult.hasFieldErrors("address") && bindingResult.hasFieldErrors("username")) {
            return "update_user";
        }
        userService.updateUser(userDTO.getAddress(), userDTO.getUsername());
        return "/update_user_result";
    }

    //LIST ALL USERS
    @GetMapping(path = "/list_all_users_admin")
    public ModelAndView ListAllUsers(){
        List<User> users =  userService.getAll();

        ModelAndView mav = new ModelAndView("list_all_users_admin");
        mav.addObject("users",users);
        return mav;
    }

}
