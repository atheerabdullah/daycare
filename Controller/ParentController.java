package com.example.daycaresystem.Controller;


import com.example.daycaresystem.DTO.BookDTO;
import com.example.daycaresystem.DTO.ParentDTO;
import com.example.daycaresystem.Model.Booking;
import com.example.daycaresystem.Model.MyUser;
import com.example.daycaresystem.Model.Parent;
import com.example.daycaresystem.Service.BookingService;
import com.example.daycaresystem.Service.ParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parent")
@RequiredArgsConstructor
public class ParentController {



    private final ParentService parentService;
    private final BookingService bookingService;

    @GetMapping("/getAllParent")
    public ResponseEntity<List<Parent>> getParent() {
        List<Parent> parentList = parentService.getAllParents();
        return ResponseEntity.ok(parentList);
    }

    @PostMapping("/addParent")
    public ResponseEntity addParent(@Valid @RequestBody ParentDTO parent) {
                parentService.addParent(parent);
                return ResponseEntity.status(HttpStatus.CREATED).body("Welcome " + parent.getParentName() + " you have been added to our team");
            }


//    @PutMapping("/updateParent/{parentId}")
//        public ResponseEntity<Parent> updateParent(@Valid @RequestBody Parent parent, @PathVariable Integer parentId ,@AuthenticationPrincipal MyUser myUser) {
//            Parent updatedParent = parentService.updateParent(parent, parentId ,myUser);
//            return ResponseEntity.ok(updatedParent);
//        }

        @DeleteMapping("/DeletePercent/{parent_id}")
        public ResponseEntity<Void> deleteParent(@PathVariable Integer parent_id , @AuthenticationPrincipal MyUser myUser) {
            parentService.deleteParent(parent_id ,myUser);
            return ResponseEntity.ok().build();
    }

    // end of CRUD

    @PostMapping("/parents/{parentId}/bookings")
    public ResponseEntity<BookDTO> addBookToTheParent(@PathVariable Integer parentId, @RequestBody BookDTO bookDTO , @AuthenticationPrincipal MyUser myUser) {
        bookDTO.setParentId(parentId);
        parentService.addBookToTheParent(bookDTO);
        return ResponseEntity.ok(bookDTO);
    }
    @PostMapping("/addBook/{id}")
    public ResponseEntity addBook(@RequestBody Booking booking, @PathVariable Integer id){
        bookingService.addBooking(booking);
        return ResponseEntity.status(200).body("book reserved");
    }


    }

//    @PostMapping("/bookParent/bookings/{parentid}")
//    public Booking parentMakeBooking(@PathVariable Integer parentid, @RequestBody BookDTO bookDTO) {
//        bookDTO.setParentId(parentid);
//        return parentService.parentMakeBooking(parentid, bookDTO);
//
//    }

    // @PostMapping("/parents/{parentId}/bookings")
    //    public ResponseEntity<Booking> createBooking(@PathVariable Integer parentId, @RequestBody BookDTO bookingDTO) {
    //        Booking booking = parentService.parentMakeBooking(parentId, bookingDTO);
    //        return ResponseEntity.ok(booking);
    //    }

//    @GetMapping("/getBy/{id}")
//    public ResponseEntity getParentById(@PathVariable Integer id) {
//        Parent parentBy = parentService.getParentById(id);
//        if (parentBy == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Parent list is empty.");
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(parentBy);
//        }
//    }



