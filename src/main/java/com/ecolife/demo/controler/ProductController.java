package com.ecolife.demo.controler;

import com.ecolife.demo.entity.Product;
import com.ecolife.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final HttpSession httpSession;

    @GetMapping("/single-product/{idProduct}")
    private String signleProduct(@PathVariable Integer idProduct, Model model) {
        Product product = this.productService.findById(idProduct);
        if (Objects.isNull(product)) {
            return "redirect:/";
        }
        model.addAttribute("product", product);
        return "single-product";
    }

    @GetMapping("/shop")
    public String shop(Model model, @RequestParam(required = false) Integer idCategory) {
        if (Objects.isNull(idCategory)) {
            model.addAttribute("products", this.productService.findAll());
        } else {
            model.addAttribute("products", this.productService.findByIdCategory(idCategory));
        }
        return "shop";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        Object cart = this.httpSession.getAttribute("wishlist");
        if (Objects.isNull(cart)) {
            cart = new ArrayList<>();
        }
        model.addAttribute("wishlist", cart);
        return "wishlist";
    }

    @GetMapping("/wishlist/{idProduct}")
    public String addToWishlist(@PathVariable Integer idProduct) {
        Object cart = httpSession.getAttribute("wishlist");
        if (Objects.isNull(cart)) {
            cart = new ArrayList<>();
            this.httpSession.setAttribute("wishlist", cart);
        }

        Optional<Product> productOptional = ((List<Product>) cart).stream()
                .filter(p -> p.getId().equals(idProduct))
                .findFirst();

        if (!productOptional.isPresent()) {
            Product product = this.productService.findById(idProduct);
            ((List<Product>) cart).add(product);
        }

        return "redirect:/wishlist";
    }

}