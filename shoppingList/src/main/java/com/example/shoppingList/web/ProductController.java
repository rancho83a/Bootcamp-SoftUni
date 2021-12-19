package com.example.shoppingList.web;

import com.example.shoppingList.model.binding.ProductAddBindingModel;
import com.example.shoppingList.model.service.ProductServiceModel;
import com.example.shoppingList.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid ProductAddBindingModel productAddBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";
        }

        productService.addProduct(modelMapper.map(productAddBindingModel, ProductServiceModel.class));
        return "redirect:/home";
    }

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }

    @GetMapping("/buy/{id}")
    public String buyById(@PathVariable Long id){
       productService.buyProduct(id);

        return "redirect:/home";
    }
    @GetMapping("/buy/all")
    public String buyAll(){
        productService.buyAll();

        return "redirect:/";
    }

    @PreAuthorize("isOwner(#id)")
    @GetMapping("/edit/{id}")
    public String editById(@PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser){

        return "product-edit";
    }
}

