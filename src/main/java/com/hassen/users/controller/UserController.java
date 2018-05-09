package com.hassen.users.controller;

import com.hassen.users.model.User;
import com.hassen.users.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = { "", "/" })
    public String index(Model model,@RequestParam(defaultValue = "0") int page) {

        model.addAttribute("activePage", "users");
        model.addAttribute("users", this.userService.getAllUsers(page));
       // model.addAttribute("users",this.userService.getAllUsers(new PageRequest(page,4)));
        model.addAttribute("currentPage",page);
        return "users/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserForm(User user, Model model) {
        model.addAttribute("activePage", "users");
        return "users/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "users");
            return "users/add";
        }

        this.userService.saveUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/view/{id}")
    public String viewUser(@PathVariable Integer id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("activePage", "users");
        return "users/view";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("activePage", "users");
        return "users/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(User user) {
        System.out.println("User ID: " + user.getId());
        this.userService.saveUser(user);
        return "redirect:/users/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id) {
        this.userService.deleteUser(id);
        return "redirect:/users";
    }


}
