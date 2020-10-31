package com.example.cart.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cart.ApiJsonKeys;
import com.example.cart.exception.PayloadException;
import com.example.cart.model.CartModel;
import com.example.cart.model.Items;
import com.example.cart.service.CartService;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {

//	Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	@PutMapping("/initcart/{userId}")
	public String initCart(@PathVariable("userId") String userid) {
		return cartService.createOrInitCart(userid);
	}

//	@GetMapping("/")
//	public List<CartModel> listAllCarts() {
//		System.out.println("get all carts");
//		return cartService.getAllCart();
//	}

//	@GetMapping("/{cartId}/{userId}")
//	public CartModel getCart(@PathVariable("cartId") String cartid, @PathVariable("userId") String userid) {
//		return cartService.getCartByCartidAndUserid(cartid, userid);
//	}

	@GetMapping("/{userId}")
	public CartModel getCart(@PathVariable("userId") String userid) {
		return cartService.getCartByUserid(userid);
	}
	
	//get cart data for place order
	@GetMapping("/{userId}/forPlaceOrder")
	public CartModel getCartForOrder(@PathVariable("userId") String userid) {
		return cartService.getCartByUserid(userid);
	}

	// update cart
	@PostMapping("/{userId}")
	public CartModel updateCart(@PathVariable("userId") String userid, @RequestBody CartModel cartModel) {
		if (cartModel != null)
			return cartService.updateCart(userid, cartModel);
		else
			throw new PayloadException("NULL Payload exception");
	}

	// add item to cart
	@PostMapping("/addProduct/{userId}")
	public CartModel addItems(@PathVariable("userId") String userid, @RequestBody Map<String, Items[]> newItems) {
		if (newItems.isEmpty())
			throw new PayloadException("Payload missing");

		if (!newItems.containsKey(ApiJsonKeys.ADD_ITEM_TO_CART))
			throw new PayloadException("JSON key not matched or missing");

		return cartService.addItemsToCart(userid, newItems);
	}
	
	// remove ALL items from cart
			@DeleteMapping("/removeAll/{userId}")
			public CartModel removeItemFromCart(@PathVariable("userId") String userid) {
				return cartService.removeItemFromCart(userid);
			}
	
	// remove item from cart
		@DeleteMapping("/{userId}/{itemId}")
		public CartModel removeItemFromCart(@PathVariable("userId") String userid, @PathVariable("itemId") String itemid) {
			if (itemid.isEmpty())
				throw new PayloadException("Payload missing");
			return cartService.removeItemFromCart(userid, itemid);
		}

		

	// remove items from cart
	@DeleteMapping("/{userId}")
	public CartModel removeItemsFromCart(@PathVariable("userId") String userid,
			@RequestBody Map<String, String[]> removeItemIds) {
		if (removeItemIds.isEmpty())
			throw new PayloadException("Payload missing");

		if (!removeItemIds.containsKey(ApiJsonKeys.REMOVE_ITEM_FROM_CART))
			throw new PayloadException("JSON key not matched or missing!");

		return cartService.removeItemsFromCart(userid, removeItemIds);
	}
}

//// add item to cart
//@PostMapping("/{cartId}/{userId}/items")
//public CartModel addItems(@PathVariable("cartId") String cartid, @PathVariable("userId") String userid,
//		@RequestBody Map<String, Items[]> newItems) {
//	if (newItems.isEmpty())
//		throw new PayloadException("Payload missing");
//
//	if (!newItems.containsKey(ApiJsonKeys.ADD_ITEM_TO_CART))
//		throw new PayloadException("JSON key not matched or missing");
//
//	return cartService.addItemsToCart(cartid, userid, newItems);
//}
