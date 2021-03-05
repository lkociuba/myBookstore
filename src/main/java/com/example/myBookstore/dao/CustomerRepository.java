package com.example.myBookstore.dao;

import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerInfo, Long> {
    CustomerInfo findByUser(User user);
}
