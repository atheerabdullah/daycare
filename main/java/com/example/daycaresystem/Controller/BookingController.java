package com.example.daycaresystem.Controller;

import com.example.daycaresystem.DTO.BookDTO;
import com.example.daycaresystem.DTO.ParentDTO;
import com.example.daycaresystem.DTO.RateBookDTO;
import com.example.daycaresystem.Model.Booking;
import com.example.daycaresystem.Model.Parent;
import com.example.daycaresystem.Service.BookingService;
import com.example.daycaresystem.Service.ParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/Booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final ParentService parentService;

    // CRUD
    @GetMapping("/getAll")
    public ResponseEntity getAllBooking(){
        List<Booking> bookings=bookingService.getAllBooking();
        return ResponseEntity.status(200).body(bookings);
    }

    @PostMapping("/addBooking")
    public ResponseEntity addBooking(@Valid @RequestBody Booking booking ){
        bookingService.addBooking(booking);
        return ResponseEntity.status(200).body("Booking added Successfully");
    }


    @PutMapping("/updateBooking/{id}")
    public ResponseEntity updateBooking(@Valid @RequestBody Booking booking, @PathVariable Integer id){
        bookingService.updateBooking(booking,id);
        return ResponseEntity.status(200).body(" Booking Updated");
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity deleteBooking(@PathVariable Integer id){
        bookingService.deleteBooking(id);
        return ResponseEntity.status(200).body("Booking deleted");
    }

    // add book to the parent
    @PostMapping ("/bookToparent/{parentId}")
    public Booking addBookToTheParent( @RequestBody BookDTO bookDTO,@PathVariable Integer parentId) {
        bookDTO.setParentId(parentId);
        return bookingService.addBookToTheParent(bookDTO);
    }
    // ADD RATE
    @PutMapping("/makeRating/{parentid}/{bookId}")
    public  ResponseEntity getRating(@PathVariable Integer parentid, @PathVariable Integer bookId, @RequestBody RateBookDTO rateBookDTO){
        bookingService.rateService(parentid,bookId,rateBookDTO);
        return ResponseEntity.status(200).body("rating done");
    }


//    @PostMapping("/ParentMakeBooking")
//    public ResponseEntity<Parent> ParentMakeBooking(@RequestBody ParentDTO parentDTO) {
//        Parent parent = parentService.ParentMakeBooking(parentDTO);
//        return new ResponseEntity<>(parent, HttpStatus.CREATED);
//    }

    // Create Booking For parent
//    @PostMapping("/ParentBooking")
//    public ResponseEntity<Booking> createBooking(@RequestBody BookDTO bookingDTO) {
//        Booking savedBooking = bookingService.ParentMakeBooking(bookingDTO);
//        return ResponseEntity.ok(savedBooking);
//    }
}