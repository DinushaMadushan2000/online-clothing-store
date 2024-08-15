package com.zosh.controller;

import com.zosh.model.Cloth;
import com.zosh.model.User;
import com.zosh.request.CreateClothRequest;
import com.zosh.response.MessageResponse;
import com.zosh.service.ClothService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/cloth")
public class AdminClothController {

    @Autowired
    private ClothService clothService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Cloth> createCloth(@RequestBody CreateClothRequest req,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cloth cloth=clothService.createCloth(req,req.getCategory());

        return new ResponseEntity<>(cloth, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteCloth(@PathVariable Long id,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        clothService.deleteCloth(id);

        MessageResponse res=new MessageResponse();
        res.setMessage("food deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cloth> updateClothAvailabilityStatus(@PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cloth cloth=clothService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(cloth, HttpStatus.CREATED);
    }
}
