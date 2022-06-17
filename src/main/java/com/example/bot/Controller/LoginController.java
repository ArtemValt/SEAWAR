package com.example.bot.Controller;

import com.example.bot.entity.User;
import com.example.bot.mainview.Storage;
import com.example.bot.model.BattleUser;
import com.example.bot.servies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final Storage storage;
    private static int count = 0;

    @Autowired
    private UserService userService;

    public LoginController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/login")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "login";
    }

    @PostMapping("/login")
    public String addUser(@ModelAttribute("userForm") User userForm, Model model) {


        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "login";
        }
        userService.saveUser(userForm);

        count++;
        storage.getUserStorage().put(count, new BattleUser(String.valueOf(count), userForm.getUsername()));

        return "redirect:/seaGame";
    }
}