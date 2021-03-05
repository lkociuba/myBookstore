package com.example.myBookstore.service;

import com.example.myBookstore.dao.OrderItemRepository;
import com.example.myBookstore.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CataloqueServiceImpl bookService;

    @Override
    public void saveOrder() {
       /*
        CartInfo cartInfo = Utils.getCartSession(request);
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();

        Order order = new Order();
        order.setOrderNumber(1);
        order.setAmount(cartInfo.calculatedPrice());
        order.setCustomerName(customerInfo.getCustomerName());
        order.setCustomerAddress(customerInfo.getCustomerAddress());
        order.setCustomerEmail(customerInfo.getCustomerEmail());
        order.setCustomerPhone(customerInfo.getCustomerPhone());
        orderRepository.save(order);

        List<CartItemInfo> cartItemInfoList = cartInfo.getCartItemInfoList();
        for (CartItemInfo item : cartItemInfoList){
            Long bookId = item.getBookInfo().getBookId();
            Book book = bookService.findBookById(bookId);

            OrderItem orderDetail = new OrderItem();
            orderDetail.setOrder(order);
            orderDetail.setBook(book);
            orderDetail.setPrice(item.getBookInfo().getPrice());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setAmount(item.getAmount());

            orderDetailRepository.save(orderDetail);
        }

        */
    }
}
