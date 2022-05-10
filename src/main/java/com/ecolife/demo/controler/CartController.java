package com.ecolife.demo.controler;

import com.ecolife.demo.entity.Product;
import com.ecolife.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final HttpSession httpSession;
    private final ProductService productService;

    @GetMapping
    public String cart(Model model) {
        Object cart = this.httpSession.getAttribute("cart");
        if (Objects.isNull(cart)) {
            cart = new ArrayList<>();
        }
        Integer totalPrice = ((List<Product>) cart).stream()
                .map(Product::getPrice)
                .reduce(0, (total, current) -> total + current);
        cart = ((List<Product>) cart).stream().filter(product -> product.getQuantity() > 0).collect(Collectors.toList());

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/{productId}")
    public String addToCart(@PathVariable Integer productId, @RequestParam(defaultValue = "1") Integer quantity) {
        Object cart = httpSession.getAttribute("cart");
        if (Objects.isNull(cart)) {
            cart = new ArrayList<>();
            this.httpSession.setAttribute("cart", cart);
        }

        Optional<Product> productOptional = ((List<Product>) cart).stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQuantity( product.getQuantity() + quantity);
        } else {
            Product product = this.productService.findById(productId);
            product.setQuantity(quantity);
            ((List<Product>) cart).add(product);
        }

        return "redirect:/cart";
    }

    @GetMapping("/clear-all")
    public String cartClearAll(Model model) {
        this.httpSession.removeAttribute("cart");
        return this.cart(model);
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
        Object cart = this.httpSession.getAttribute("cart");
        if (Objects.nonNull(cart)) {
            List<Product> products = (List<Product>) cart;
            products = products.stream()
                    .filter(product -> !product.getId().equals(productId))
                    .collect(Collectors.toList());
            this.httpSession.setAttribute("cart", products);
        }
        return "redirect:/cart";
    }

    @GetMapping("/increase/{productId}")
    @ResponseBody
    public String increaseQuantity(@PathVariable Integer productId) throws JsonProcessingException {
        List<Product> cart = (List<Product>) this.httpSession.getAttribute("cart");
        Optional<Product> productOpt = cart.stream().filter(c -> c.getId().equals(productId))
                .findFirst();

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setQuantity( product.getQuantity() + 1);
            Integer totalPrice = product.getQuantity() * product.getPrice();
            return "{\"totalPrice\": \"" + totalPrice + "\"}";
        }
        return "";
    }

    @GetMapping("/decrease/{productId}")
    @ResponseBody
    public String decreaseQuantity(@PathVariable Integer productId) {
        List<Product> cart = (List<Product>) this.httpSession.getAttribute("cart");
        Optional<Product> productOpt = cart.stream().filter(c -> c.getId().equals(productId))
                .findFirst();

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setQuantity( product.getQuantity() - 1);
            Integer totalPrice = product.getQuantity() * product.getPrice();
            return "{\"totalPrice\": \"" + totalPrice + "\"}";
        }
        return "";
    }
}