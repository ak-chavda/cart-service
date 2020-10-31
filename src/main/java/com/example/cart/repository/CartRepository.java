package com.example.cart.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.cart.model.CartModel;

@Configuration
@Repository
public interface CartRepository extends MongoRepository<CartModel, String> {

	CartModel findBycartid(String cartid);

	CartModel findByuserid(String userid);

	CartModel findByCartidAndUserid(String cartid, String userid);

}
