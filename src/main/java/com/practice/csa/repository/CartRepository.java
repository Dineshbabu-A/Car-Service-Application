package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.csa.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
