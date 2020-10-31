package com.example.cart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Configuration
@Document(collection = "cartservice")
public class CartModel implements Serializable {

	private static final long serialVersionUID = -1702628681215626466L;

	@Id
	private String cartid;
	private String userid;
	private List<Items> listOfItems; // List of items in the cart
	private double totalPrice;
//	private boolean checkoutState; // false: not checkout yet

	// this() will initialize ArrayList class member(here: listOfItem)
	public CartModel() {
		super();
		this.listOfItems = new ArrayList<Items>();
	}

	public CartModel(String userid) {
		this();
		this.userid = userid;
	}

	public CartModel(String userid, Items item) {
		this();
		this.userid = userid;
		this.listOfItems.add(item);
	}

	public CartModel(String userid, List<Items> itemsList) {
		this();
		this.userid = userid;
		this.listOfItems = itemsList;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCartid() {
		return cartid;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	// override the old totalPrice value
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

//	public boolean getCheckoutState() {
//		return checkoutState;
//	}
//
//	public void setCheckoutState(boolean checkoutState) {
//		this.checkoutState = checkoutState;
//	}

	//	empty the list of items
	public void doEmptyListOfItems() {
		this.listOfItems = new ArrayList<Items>();
		this.maketotalPrice();
		
	}
	
	// override old item list
	public void setItems(List<Items> itemList) {
		this.listOfItems = itemList;
		setTotalPrice(0.0);
		maketotalPrice();
	}

	// get the list of cart items
	public List<Items> getItems() {
		return listOfItems;
	}

	// add single item to list (or cart)
	public void addItem(Items newItem) {
		listOfItems.add(newItem);
		addToTotalPrice(newItem.getPrice() * newItem.getQty());
	}

	// append to the original list of items
	public void addItems(Items[] itemsArray) {
		for (Items i : itemsArray) {
//			i.setItemid("item" + Math.random() * 100000);
			addItem(i);
		}
		maketotalPrice();
	}

	// return item from the cart/list
	public boolean removeItem(String itemId) {
		Items findItem = listOfItems.stream().filter(i -> i.getItemid().equals(itemId)).findFirst().orElse(null);

		if (findItem != null && listOfItems.remove(findItem)) {
			subFromTotalPrice(findItem.getPrice() * findItem.getQty());
			return true;
		}
		return false;
	}

	// calculate the total amount of the cart by the list of items
	public void maketotalPrice() {
		setTotalPrice(0.0);
		listOfItems.forEach(i -> {
			addToTotalPrice(i.getPrice() * i.getQty());
		});
	}

	// for again calculate the total price after adding item to cart
	public void addToTotalPrice(Double itemPrice) {
		this.totalPrice += itemPrice;
	}

	// for again calculate the total price after deleting item from cart
	public void subFromTotalPrice(Double itemPrice) {
		this.totalPrice -= itemPrice;
	}

	@Override
	public String toString() {
		return "CartModel [cartid=" + cartid + ", userid=" + userid + ", items=" + listOfItems + ", totalPrice="
				+ totalPrice + "]";
	}
}
