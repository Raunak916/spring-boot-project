// package com.example.IMS.controllers;

// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class CartController {

// }

package com.example.IMS.controllers;

import com.example.IMS.entities.Cart;
import com.example.IMS.entities.Product;
import com.example.IMS.entities.User;
import com.example.IMS.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private com.example.IMS.repositories.ProductRepository productRepository;
    @Autowired
    private com.example.IMS.repositories.UserRepository userRepository;

    //  Add item to cart
    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Integer productId,
                            @RequestParam("userId") Integer userId) {
        // cartService.addToCart(cart);
        // return "Item added to cart successfully!";
          Product product = productRepository.findById(productId.longValue()).orElse(null);
          User user = userRepository.findById(userId).orElse(null);

    if (product != null && user != null) {
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cartService.addToCart(cart);
    }

    return "redirect:/userPage"; // or wherever your user page is mapped

    }

    //  Remove item from cart
    @DeleteMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Integer id) {
        cartService.removeFromCart(id);
        return "Item removed from cart successfully!";
    }

    //  View item in cart by ID
    @GetMapping("/view/{id}")
    public Cart viewCart(@PathVariable("id") Integer id) {
        return cartService.viewCart(id);
    }


}
