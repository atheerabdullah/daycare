package com.example.daycaresystem.Controller;


import com.example.daycaresystem.Model.Package;
import com.example.daycaresystem.Service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/package")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @GetMapping("/All-Package")
    public ResponseEntity<List<Package>> getAllPackage() {
        List<Package> packageList = packageService.getAllPackage();
        return ResponseEntity.status(HttpStatus.OK).body(packageList);
    }


    @GetMapping("/packageId/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable Integer id) {
        Package aPackage = packageService.findPackageById(id);
        return ResponseEntity.status(HttpStatus.OK).body(aPackage);
    }



    @PostMapping("/add-package")
    public ResponseEntity<Package> createPackage(@RequestBody Package apackage) {
        Package createdPackage = packageService.createPackage(apackage);
        return ResponseEntity.status(HttpStatus.OK).body(createdPackage);
    }

    @PutMapping("/update-package/{id}")
    public ResponseEntity<Package> updatePackage(@PathVariable Integer id, @RequestBody Package apackage) {
        Package updatedPackage = packageService.updatePackage(id, apackage);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPackage);
    }

    @DeleteMapping("/delete-Package/{id}")
    public ResponseEntity<String> deletePackage(@PathVariable Integer id) {
        packageService.deletePackage(id);
        return ResponseEntity.ok("The Package with id " + id + " has been deleted.");
    }

    // find Package By PackageType (Package Name)
    @GetMapping("/packageType/{PackageName}")
    public ResponseEntity<Package> findPackageByPackageType(@PathVariable String PackageName) {
        Package aPackage = packageService.findPackageByPackageType(PackageName);
        return ResponseEntity.status(HttpStatus.OK).body(aPackage);
    }




}
