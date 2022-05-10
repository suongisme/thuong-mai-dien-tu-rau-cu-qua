package com.ecolife.demo.controler;

import com.ecolife.demo.dto.ResultDto;
import com.ecolife.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model model) {
        ResultDto result = this.orderService.checkout();
        if (result.getIsError()) {
            model.addAttribute("error", result.getMessage());
            return "cart";
        }
        return "redirect:/";
    }
}