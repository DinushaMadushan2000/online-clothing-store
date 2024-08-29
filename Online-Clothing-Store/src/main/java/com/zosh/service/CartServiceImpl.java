package com.zosh.service;

import com.zosh.model.Cart;
import com.zosh.model.CartItem;
import com.zosh.model.Cloth;
import com.zosh.model.User;
import com.zosh.repository.CartItemRepository;
import com.zosh.repository.CartRepository;
import com.zosh.repository.ClothRepository;
import com.zosh.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ClothRepository clothRepository;

    @Autowired
    private ClothService clothService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        Cloth cloth = clothService.findClothById(req.getClothId());

        Cart cart=cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getItem()){
            if (cartItem.getCloth().equals(cloth)){
                int newQuantity=cartItem.getQuantity()+ req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem=new CartItem();
        newCartItem.setCloth(cloth);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setTotalPrice(req.getQuantity()*cloth.getPrice());

        CartItem savedCartItem=cartItemRepository.save(newCartItem);

        cart.getItem().add(savedCartItem);

        return savedCartItem;
    }
    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional=cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItem item=cartItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getCloth().getPrice()*quantity);


        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);

        Cart cart=cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional=cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItem item=cartItemOptional.get();

        cart.getItem().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total=0L;

        for (CartItem cartItem:cart.getItem()){
            total+=cartItem.getCloth().getPrice()*cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart=cartRepository.findById(id);
        if (optionalCart.isEmpty()){
            throw new Exception("cart not found with id "+id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=findCartByUserId(jwt);

        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}
