package org.launchcode.controllers;

import org.launchcode.data.UserRepository;
import org.launchcode.models.Book;
import org.launchcode.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displayAllUsers(@RequestParam(required = false) Integer categoryId, Model model){
        model.addAttribute("title", "All users!");
        model.addAttribute("users", userRepository.findAll());
        return "users/index";
    }

    @GetMapping("create")
    public String renderCreateUserForm(Model model){
        model.addAttribute("title", "Create User");
        model.addAttribute(new User());
        return "users/create";
    }

    @PostMapping("create")
    public String createUser(@ModelAttribute @Valid User newUser, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create User");
            return "users/create";
        }
        newUser.setPassword(encodePassword(newUser.getPassword()));
        userRepository.save(newUser);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteUserForm(Model model){
        model.addAttribute("title", "Delete Users");
        model.addAttribute("users", userRepository.findAll());
        return "users/delete";
    }

    @PostMapping("delete")
    public String processDeleteUserForm(@RequestParam(required = false) int[] userIds){

        if(userIds != null) {
            for (int id : userIds) {
                userRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("update")
    public String displayUpdateUserForm(Model model){
        model.addAttribute("title", "Update User");
        model.addAttribute(new User());
        model.addAttribute("userIds", userRepository.findAll());
        return "users/update";
    }

    @PostMapping("update")
    public String updateUser(@RequestParam(required = false) int[] userIds, Model model, @ModelAttribute @Valid User newUser, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Update User");
            return "users/update";
        }

        if(userIds != null) {
            for (int id : userIds) {
                userRepository.deleteById(id);
            }
        }
        newUser.setPassword(encodePassword(newUser.getPassword()));
        userRepository.save(newUser);
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayUserDetails(@RequestParam Integer userId, Model model){

        Optional<User> result = userRepository.findById(userId);

        if(result.isEmpty()){
            model.addAttribute("title" + "Invalid user Id : " + userId);
        }else{
            User user = result.get();
            model.addAttribute("title", user.getUsername() + " details ");
            model.addAttribute("user", user);
        }
        return "users/detail";
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}