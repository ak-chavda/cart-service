package com.example.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CartApplication { // implements CommandLineRunner {

//	@Autowired
//	CartRepository cartRepository;

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception { List<CartModel>
	 * cartlist = new ArrayList<CartModel>();
	 * 
	 * List<Items> temp = new ArrayList<Items>();
	 * 
	 * temp.add(new Items("item1", 1, 100)); CartModel c1 = new CartModel("user1");
	 * c1.addItem(new Items("item2", 1, 300));
	 * 
	 * temp.set(0, new Items("item2", 3, 500)); CartModel c2 = new
	 * CartModel("user2", temp);
	 * 
	 * cartlist.add(c1); cartlist.add(c2);
	 * 
	 * cartRepository.insert(cartlist); }
	 */
}
