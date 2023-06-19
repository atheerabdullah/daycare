package com.example.daycaresystem.Controller;

import com.example.daycaresystem.Model.MyUser;
import com.example.daycaresystem.Service.MyUserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MyUserAuthController {

    private final MyUserAuthService myUserAuthService;



    @GetMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("welcome you have been logged successfully ");
    }


    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }
}
