package com.ecolife.demo.service;

import com.ecolife.demo.dto.ResultDto;
import com.ecolife.demo.entity.Customer;
import com.ecolife.demo.entity.OrderDetail;
import com.ecolife.demo.entity.Orders;
import com.ecolife.demo.entity.Product;
import com.ecolife.demo.repository.OrderDetailRepository;
import com.ecolife.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final HttpSession httpSession;

    public ResultDto checkout() {

        try {
            List<Product> cart = (List<Product>) this.httpSession.getAttribute("cart");
            Customer currentUser = (Customer) this.httpSession.getAttribute("currentUser");

            if (Objects.isNull(cart) || cart.isEmpty()) {
                return new ResultDto("Chưa chọn sản phẩm", null, true);
            }

            Integer totalMoney = cart.stream()
                    .map(Product::getPrice)
                    .reduce(0, (total, current) -> total + current);

            Orders order = new Orders();
            order.setCustomerId(currentUser.getId());
            order.setCreateAt(new Date());
            order.setTotalMoney(totalMoney);

            Orders save = this.orderRepository.save(order);

            cart.stream()
                    .filter(c -> c.getQuantity() > 0)
                    .map(c -> new OrderDetail(save.getId(), c.getId(), c.getQuantity(), c.getPrice()))
                    .forEach(this.orderDetailRepository::save);

            return new ResultDto("Thanh toán thành công", null, false);
        } catch(Exception ex) {
            return new ResultDto("Có lỗi xảy ra khi thanh toán", null, true);
        }
    };
}