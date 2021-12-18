package com.example.shoppingList.web;

import com.example.shoppingList.model.entity.enums.CategoryEnum;
import com.example.shoppingList.model.service.ProductServiceModel;
import com.example.shoppingList.model.view.ProductViewModel;
import com.example.shoppingList.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public HomeController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String indexPage(@AuthenticationPrincipal UserDetails principal) {

        if (principal != null) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {

        List<ProductServiceModel> productServiceModels = productService.getAllProductsByCategory(CategoryEnum.FOOD);
        List<ProductViewModel> productViewModels = productServiceModels.stream()
                .map(productServiceModel -> modelMapper.map(productServiceModel, ProductViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("foodProducts", productViewModels);

        model.addAttribute("drinkProducts",
                productService.getAllProductsByCategory(CategoryEnum.DRINK)
                        .stream()
                        .map(productServiceModel -> modelMapper.map(productServiceModel, ProductViewModel.class))
                        .collect(Collectors.toList()));

        model.addAttribute("houseProducts",
                productService.getAllProductsByCategory(CategoryEnum.HOUSEHOLD)
                        .stream()
                        .map(productServiceModel -> modelMapper.map(productServiceModel, ProductViewModel.class))
                        .collect(Collectors.toList()));

        model.addAttribute("otherProducts",
                productService.getAllProductsByCategory(CategoryEnum.OTHER)
                        .stream()
                        .map(productServiceModel -> modelMapper.map(productServiceModel, ProductViewModel.class))
                        .collect(Collectors.toList()));

        BigDecimal totalSum = this.productService.getTotalSum();
        if (null == (totalSum)) {
            totalSum = BigDecimal.ZERO;
        }
        model.addAttribute("totalSum", totalSum);


        return "home";
    }
}
