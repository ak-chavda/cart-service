package com.example.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.ApiJsonKeys;
import com.example.cart.exception.AlreadyExistsException;
import com.example.cart.exception.NoContentFound;
import com.example.cart.exception.NotFoundException;
import com.example.cart.model.CartModel;
import com.example.cart.model.Items;
import com.example.cart.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;

	// temporary CartModel object for check if null
	CartModel tCartModel;

	public String createOrInitCart(String userid) {
		if (cartRepository.findByuserid(userid) != null)
			throw new AlreadyExistsException("Cart is already intiated");
		else {
			tCartModel = cartRepository.insert(new CartModel(userid));
			if (tCartModel != null)
				return tCartModel.getCartid();
			else
				return null;
		}
	}

	public List<CartModel> getAllCart() {
		List<CartModel> tCartModelList = cartRepository.findAll();
		if (tCartModelList.isEmpty())
			return cartRepository.findAll();
		throw new NoContentFound("Cart list empty!");
	}

	public CartModel getCartByUserid(String userid) {
		tCartModel = cartRepository.findByuserid(userid);
		if (tCartModel != null)
			return tCartModel;
		throw new NotFoundException("Cart not found!");
	}

	public CartModel getCartByCartidAndUserid(String cartid, String userid) {
		tCartModel = cartRepository.findByCartidAndUserid(cartid, userid);
		if (tCartModel != null)
			return tCartModel;
		throw new NotFoundException("Cart not found!");
	}

	public CartModel addItemsToCart(String userid, Map<String, Items[]> newItems) {

		tCartModel = cartRepository.findByuserid(userid);

		if (tCartModel == null) {
			tCartModel = new CartModel(userid);
		}

		// extract list of items objects from JSON array by key.
		Items[] itemsArray = newItems.get(ApiJsonKeys.ADD_ITEM_TO_CART);

		tCartModel.addItems(itemsArray);

//		System.out.println("Cart service -> add items to cart --->" + tCartModel);

//		update (or add) new item to cart for that save() fn used.
		return cartRepository.save(tCartModel);
	}

	public CartModel updateCart(String userid, CartModel cartModel) {
		tCartModel = getCartByUserid(userid);
		tCartModel.setItems(cartModel.getItems());
		return cartRepository.save(tCartModel);
	}

	//	remove all the items of cart
	public CartModel removeItemFromCart(String userid) {
		tCartModel = getCartByUserid(userid);
		tCartModel.doEmptyListOfItems();
		return cartRepository.save(tCartModel);
	}
	
	public CartModel removeItemFromCart(String userid, String itemid) {
		HashMap<String, String[]> tempMap = new HashMap<String, String[]>();
		tempMap.put(ApiJsonKeys.REMOVE_ITEM_FROM_CART, new String[] { itemid });
		return removeItemsFromCart(userid, tempMap);

	}

	public CartModel removeItemsFromCart(String userid, Map<String, String[]> itemIds) {
		tCartModel = getCartByUserid(userid);
		boolean removeStatus = true;

		String[] items = itemIds.get(ApiJsonKeys.REMOVE_ITEM_FROM_CART);

		for (String item : items) {
			if (!tCartModel.removeItem(item)) {
				removeStatus = false;
				break;
			}
		}

		if (removeStatus) {
			// update (or add) new item to cart for that save() fn used.
			return cartRepository.save(tCartModel);
		}
		return tCartModel;
	}
}
