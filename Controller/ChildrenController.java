package com.example.daycaresystem.Controller;

import com.example.daycaresystem.Model.Children;
import com.example.daycaresystem.Service.ChildrenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/child")
@RequiredArgsConstructor
public class ChildrenController {

    private final ChildrenService childernService;



    @GetMapping("/get")
    public ResponseEntity getAllChildern() {
        List<Children> childerns = childernService.getAllChildern();
        return ResponseEntity.status(200).body(childerns);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Children childern) {
        childernService.addChildern(childern);
        return ResponseEntity.status(200).body("childern added Successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody Children childern, @PathVariable Integer id) {
        childernService.updateChildern(childern, id);
        return ResponseEntity.status(200).body("children Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        childernService.deleteChildern(id);
        return ResponseEntity.status(200).body("Children deleted");
    }
    // add child to the parent


}
