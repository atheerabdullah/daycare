package com.example.daycaresystem.Controller;

import com.example.daycaresystem.ApiException.ApiException;
import com.example.daycaresystem.DTO.PackageDTO;
import com.example.daycaresystem.Model.Stuff;
import com.example.daycaresystem.Service.PackageService;
import com.example.daycaresystem.Service.StuffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/stuff")
@RequiredArgsConstructor
public class StuffController {

    private final StuffService stuffService;
    private final PackageService packageService;

    @GetMapping("/AllStuffs")
    public ResponseEntity getAllStuff() {
        List<Stuff> stuffList = stuffService.getAllStuff();
        if (stuffList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The stuff list is empty.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(stuffList);
        }
    }

    @GetMapping("/StuffId/{id}")
    public ResponseEntity getStuffById(@PathVariable Integer id) {
        Stuff stuff = stuffService.findStuffById(id);
        return ResponseEntity.status(HttpStatus.OK).body(stuff);
    }


    @PostMapping("/add-stuff")
    public ResponseEntity<String> createStuff(@RequestBody Stuff stuff) throws ApiException {
        Stuff createdStuff = stuffService.createStuff(stuff);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Welcome " + createdStuff.getStuffName() + " you have been added to " + createdStuff.getDaycare());
    }


    @PutMapping("/update-stuff/{id}")
    public ResponseEntity updateStuff(@PathVariable Integer id, @RequestBody Stuff updatedStuff) {
        Stuff updatedStuffEntity = stuffService.updateStuff(id, updatedStuff);
        return ResponseEntity.status(HttpStatus.OK).body("the information of " +updatedStuffEntity.getStuffName() + "was updated successfully");
    }

    @DeleteMapping("/delete-stuff/{id}")
    public ResponseEntity<String> deleteDaycare(@PathVariable Integer id) {
        stuffService.deleteStuff(id);
        return ResponseEntity.ok("The stuff with id " + id + " has been deleted.");


    }

    // assign stuff to service(Package) - Postman in daycare collections
    @PostMapping("/packages/{packageId}/stuffs/{stuffId}")
    public ResponseEntity<String> addStuffToPackage(@PathVariable Integer packageId, @PathVariable Integer stuffId) {
        stuffService.addStuffToPackage(packageId, stuffId);
        return ResponseEntity.ok("the Stuff added to package successfully");
    }


    //get All Stuffs In Package by package type
    @GetMapping("/packages/{packageName}/stuffs")
    public ResponseEntity getAllStuffsInPackage(@PathVariable String packageName) {
        Set<Stuff> stuffs = stuffService.getAllStuffsInPackage(packageName);
        if (stuffs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" no stuff assign yet ");
        } else
            return ResponseEntity.status(200).body(stuffs);
    }
    // remove Stuff From Package
    @DeleteMapping("/stuffs/{stuffId}/packages/{packageId}")
    public ResponseEntity<String> removeStuffFromPackage(@PathVariable Integer stuffId, @PathVariable Integer packageId) {
        Stuff stuff = stuffService.removeStuffFromPackage(stuffId, packageId);
        return ResponseEntity.status(200).body(" the Stuff " + stuff.getStuffName()+" was remove from package ");
    }

    // show the stuff with package
    @GetMapping("/StuffsInPackage/{id}/stuffs")
    public ResponseEntity getAllStuffsInPackageByid(@PathVariable Integer id) {
        Set<Stuff> stuffs = stuffService.getAllStuffsInPackageByID(id);
        if (stuffs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" no stuff assign yet ");
        } else
            return ResponseEntity.status(200).body(stuffs);
    }

    // find stuff by daycare name
    @GetMapping("/daycares/{daycareName}/stuffs")
    public List<Stuff> getStuffByDaycareName(@PathVariable String daycareName) {
        return stuffService.getStuffByDaycareName(daycareName);
    }

}