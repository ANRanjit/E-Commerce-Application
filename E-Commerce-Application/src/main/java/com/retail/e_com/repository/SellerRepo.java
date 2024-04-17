package com.retail.e_com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.e_com.entity.Seller;

public interface SellerRepo extends JpaRepository<Seller, Integer> {

}
