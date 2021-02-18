package com.example.myBookstore.dao;

import com.example.myBookstore.entity.CartSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartSummaryRepository extends JpaRepository<CartSummary, Long> {
    CartSummary findByCartSummaryId(Long cartSummaryId);

}
