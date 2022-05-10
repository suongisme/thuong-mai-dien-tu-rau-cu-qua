package com.ecolife.demo.controler;

import com.ecolife.demo.dto.ResultDto;
import com.ecolife.demo.entity.Customer;
import com.ecolife.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final HttpSession httpSession;

    @GetMapping("/my-account")
    public String myAccount(Model model) {
        model.addAttribute("currentUser", this.httpSession.getAttribute("currentUser"));
        return "my-account";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new Customer());
        return "login";
    }

    @PostMapping("/login")
    public String onLogin(@ModelAttribute(name = "user") Customer customer, Model model) {
        ResultDto<Customer> result = this.customerService.login(customer);
        if (result.getIsError()) {
            model.addAttribute("error", result.getMessage());
            return "login";
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String onRegister(@ModelAttribute(name = "user") Customer customer, Model model) {
        ResultDto<Customer> result = this.customerService.register(customer);
        if (result.getIsError()) {
            model.addAttribute("error", result.getMessage());
            return "login";
        }
        return "redirect:/login";
    }

    @PostMapping("/customer/update")
    public String onUpdate(@ModelAttribute(name = "currentUser") Customer customer) {
        ResultDto<Customer> result = this.customerService.register(customer);
        httpSession.setAttribute("currentUser", result.getData());
        return "redirect:/my-account";
    }
}