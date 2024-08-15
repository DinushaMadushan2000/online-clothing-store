package com.zosh.controller;

import com.zosh.model.Cloth;
import com.zosh.model.User;
import com.zosh.request.CreateClothRequest;
import com.zosh.service.ClothService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class ClothController {

    @Autowired
    private ClothService clothService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Cloth>> searchCloth(@RequestParam String name,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Cloth> cloths=clothService.searchCloth(name);

        return new ResponseEntity<>(cloths, HttpStatus.CREATED);
    }




}
