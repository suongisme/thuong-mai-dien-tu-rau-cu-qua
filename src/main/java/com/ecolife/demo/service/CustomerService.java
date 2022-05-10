package com.ecolife.demo.service;

import com.ecolife.demo.dto.ResultDto;
import com.ecolife.demo.entity.Customer;
import com.ecolife.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final HttpSession session;

    public ResultDto<Customer> login(Customer customer) {
        Optional<Customer> customerOPT = this.customerRepository.findByEmail(customer.getEmail());

        if (!customerOPT.isPresent()) {
            return new ResultDto<>("Email doen't exist", null, true);
        }

        Customer customerFound = customerOPT.get();
        if (!customerFound.getPassword().equals(customer.getPassword())) {
            return new ResultDto<>("Password is incorrect", null, true);
        }

        this.session.setAttribute("currentUser", customerFound);
        return new ResultDto<>("Login successfully", customerFound, false);
    }

    public ResultDto<Customer> register(Customer customer) {
        Optional<Customer> customerOPT = this.customerRepository.findByEmail(customer.getEmail());
        if (customerOPT.isPresent()) {
            return new ResultDto<>("Email exist", null, true);
        }

        if (Objects.nonNull(customer.getPhone()) && !customer.getPhone().matches("(\\+84|0{9})")) {
            return new ResultDto<>("Phone number is invalid", null, true);
        }
        Customer save = this.customerRepository.save(customer);
        return new ResultDto<>("Register successfully", save, false);
    }
}