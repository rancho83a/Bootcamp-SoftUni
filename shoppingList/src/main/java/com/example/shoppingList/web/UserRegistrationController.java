package com.example.shoppingList.web;

import com.example.shoppingList.model.binding.UserRegistrationBindingModel;
import com.example.shoppingList.model.service.UserRegistrationServiceModel;
import com.example.shoppingList.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }

    @GetMapping("/users/register")
    public String registerUser(Model model) {
        if(!model.containsAttribute("isPasswordConfirm")){
            model.addAttribute("isPasswordConfirm", true);
        }

        return "register";
    }


    @PostMapping("/users/register")
    public String register(

            @Valid UserRegistrationBindingModel userRegistrationBindingModel,  // throw 404 error
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("userModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);
            return "redirect:/users/register";
        }

        boolean isPasswordConfirm = userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword());

        if ( !isPasswordConfirm) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("isPasswordConfirm", false);
            return "redirect:registerUser";
        }



        UserRegistrationServiceModel userRegistrationServiceModel = modelMapper
                .map(userRegistrationBindingModel, UserRegistrationServiceModel.class);


        userService.registerAndLoginUser(userRegistrationServiceModel);

        return "redirect:/";
    }
}
