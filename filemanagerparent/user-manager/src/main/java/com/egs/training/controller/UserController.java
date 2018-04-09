package com.egs.training.controller;

import com.egs.training.entity.User;
import com.egs.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "usersList";
    }

    @GetMapping("/addForm")
    public String addForm(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "userForm";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/user/list";
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam("userId") Long id, Model model) {

        User user = userService.getUser(id);
        model.addAttribute("user", user);

        return "userForm";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);

        return "redirect:/user/list";
    }
}
