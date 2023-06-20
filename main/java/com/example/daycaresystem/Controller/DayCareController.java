package com.example.daycaresystem.Controller;


import com.example.daycaresystem.DTO.*;
import com.example.daycaresystem.Model.Daycare;
import com.example.daycaresystem.Model.MyUser;
import com.example.daycaresystem.Model.Package;
import com.example.daycaresystem.Model.Stuff;
import com.example.daycaresystem.Service.DayCareService;
import com.example.daycaresystem.Service.StuffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/daycares")
@RequiredArgsConstructor
public class DayCareController {

    private final DayCareService dayCareService;
    private final StuffService stuffService;


    @GetMapping("/AllDayCares")
    public ResponseEntity getAllDaycare() {
        List<Daycare> daycareList = dayCareService.getAllDaycare();
        if (daycareList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The daycare list is empty.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(daycareList);
        }
    }

//    @PostMapping("/add-daycare")
//    public ResponseEntity addDaycare(@RequestBody DaycareDTO daycare) {
//        if (createdDaycare == null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("A daycare with the same commercial ID already exists");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body("Welcome " + daycare.getDaycareName() + " you have been added to our team");
//    }
@PostMapping("/add-daycare")
    public ResponseEntity addDaycare(@RequestBody @Valid DaycareDTO daycare) {
        dayCareService.addDaycare(daycare);
    return ResponseEntity.status(HttpStatus.CREATED).body("Welcome " + daycare.getDaycareName() + " you have been added to our team");
        }

    @PutMapping("/update-daycare/{id}")
    public ResponseEntity<String> updateDaycare( @AuthenticationPrincipal MyUser myUser, @PathVariable Integer id, @RequestBody Daycare daycare)  {
        Daycare updatedDaycare = dayCareService.updateDaycare(id, daycare);
        if (updatedDaycare == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Daycare not found with id: " + id);

        } else if (!daycare.getCommercialId().equals(updatedDaycare.getCommercialId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The corimical id field cannot be updated.");
        } else {
            return ResponseEntity.ok("The information of " + updatedDaycare.getDaycareName() + " has been updated.");
        }
    }

    @DeleteMapping("/delete-Daycare/{id}")
    public ResponseEntity<String> deleteDaycare(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer id) {
        dayCareService.deleteDaycare(id);
        return ResponseEntity.ok("The daycare with id " + id + " has been deleted.");
    }

    // add stuff to the daycare
    @PostMapping("/{daycareId}/staff")
    public Stuff addStuffForDaycare(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer daycareId, @RequestBody StuffDTO stuffDto) {
        stuffDto.setDaycareId(daycareId);
        return dayCareService.addStuffForDaycare(stuffDto);
    }

    //add Package to the daycare
    @PostMapping("/{daycareId}/Package")
    public Package addPackageForDaycare(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer daycareId, @RequestBody PackageDTO packageDTO) {
        packageDTO.setDaycareId(daycareId);
        return dayCareService.addPackageForDaycare(packageDTO);
    }

    // get the working hours by daycare name
    @GetMapping("/{name}/hours")
    public ResponseEntity<TimeDTO> getDaycareHoursByName(@PathVariable String name) {
        TimeDTO timeDTO = dayCareService.getDaycareHoursByName(name);
        return ResponseEntity.ok(timeDTO);
    }

    // get the daycare by name without a list of stuff
    @GetMapping("/findDayCareByName/{name}")
    public DaycareDTO findDaycareByDaycareName(@PathVariable String name) {
        return dayCareService.findDaycareByDaycareName(name);
    }


    //  search Daycare's By Facilities
    @GetMapping("/search/{facilities}")
    public List<DaycareDTO> searchByFacilities(@PathVariable("facilities") String facilities) {
        return dayCareService.searchByFacilities(facilities);
    }

    //     get highest-rate
    @GetMapping("/highest-rate")
    public DaycareDTO getDaycareWithHighestRate() {
        Daycare daycare = dayCareService.getHighestRatedDaycare();
        DaycareDTO dto = new DaycareDTO(daycare);
        return dto;
    }

    // change Statues
    @PutMapping("/changeStatues/{parentId}/{bookId}")
    public ResponseEntity changeStatues(@RequestBody BookDTO bookDTO, @PathVariable Integer parentId, @PathVariable Integer bookId) {
        dayCareService.changeStatues(bookDTO, parentId, bookId);
        return ResponseEntity.status(200).body("Booked Status Updated Successfully ");
    }
}




