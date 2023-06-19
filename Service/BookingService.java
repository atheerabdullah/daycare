package com.example.daycaresystem.Service;

import com.example.daycaresystem.ApiException.ApiException;
import com.example.daycaresystem.DTO.BookDTO;
import com.example.daycaresystem.DTO.RatingDTO;
import com.example.daycaresystem.Model.Booking;
import com.example.daycaresystem.Model.Package;
import com.example.daycaresystem.Model.Parent;
import com.example.daycaresystem.Repository.BookingRepository;
import com.example.daycaresystem.Repository.PackageRepository;
import com.example.daycaresystem.Repository.ParentRepository;
import com.example.daycaresystem.Repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {


    public final BookingRepository bookingRepository;
    public final ParentRepository parentRepository;
    public final RatingRepository ratingRepository;
    public final PackageRepository packageRepository;

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }


    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public void updateBooking(Booking booking, Integer id) {
        Booking updatedBooking = bookingRepository.findAllById(id);

        if (updatedBooking == null) {
            throw new ApiException("Enter  correct Id");
        }
        updatedBooking.setAddress(booking.getAddress());
        updatedBooking.setStatus(booking.getStatus());
        updatedBooking.setTotalPrice(booking.getTotalPrice());
        updatedBooking.setServiceType(booking.getServiceType());


        bookingRepository.save(updatedBooking);
    }

    public void deleteBooking(Integer id) {
        Booking deleteBooking = bookingRepository.findAllById(id);
        if (deleteBooking == null) {
            throw new ApiException("Product not found");
        }
        bookingRepository.delete(deleteBooking);
    }

    //assign a book to the parent
    public Booking addBookToTheParent(BookDTO bookDTO) {
        Parent parent = parentRepository.findById(bookDTO.getParentId())
                .orElseThrow(() -> new ApiException("Parent not found with id: " + bookDTO.getParentId()));
        Booking booking = new Booking();
        booking.setStart_time(bookDTO.getStart_time());
        booking.setEnd_time(bookDTO.getEnd_time());
        booking.setTotalPrice(bookDTO.getTotalPrice());
        booking.setServiceType(bookDTO.getServiceType());
        booking.setChildName(bookDTO.getChildName());
        booking.setChildName(bookDTO.getChildName());
        booking.setDurationType(bookDTO.getDurationType());
        booking.setAddress(bookDTO.getAddress());
        booking.setStatus("InProgress");
        booking.setParent(parent);
        parent.getBookingSet().add(booking);
        booking = bookingRepository.save(booking);
        return booking;
    }
}

    //Rating
//    public void rateService(Integer parentid, Integer bookIdid, RatingDTO ratingDto){
//        Parent parent = parentRepository.findParentById(parentid);
//        Booking booking=bookingRepository.findBookingById(bookIdid);
//        Booking bookingR = bookingRepository.findBookingById(ratingDto.getBookId());
//        if (parent.getBookingSet() == null || booking.getStatus().equalsIgnoreCase("inProgress")) {
//            throw new ApiExeption("Sorry YOu do NOT have a booking Or Yor Booking InProgress You Can't make a rate");
//        }
//        booking.setRating(ratingDto.getRating());
//        bookingRepository.save(booking);


    // the parent makes a booking
//        public Booking ParentMakeBooking(BookDTO bookingDTO){
//            // Find the package for the booking
//            Package aPackage = packageRepository.findPackageByPackageType(bookingDTO.getServiceType());
//            if (aPackage == null) {
//                throw new ApiException(("Package not found for the given parameters"));
//            }
//
//            // Find the parent making the booking
//            Parent parent = parentRepository.findParentById(bookingDTO.getParentId());
//            if (parent == null) {
//                throw new ApiException(("Parent not found for the given parameters"));
//            }
//
//            // Create a new Booking object and set its properties
//            Booking booking = new Booking();
//            booking.setServiceType(aPackage.getPackageType());
//            booking.setDurationType(aPackage.getPackageDuration());
//            booking.setStatus("In progress");
//            booking.setAddress(parent.getAddress());
//            booking.setChildName(bookingDTO.getChildName());
//            booking.setTotalPrice(aPackage.getPackagePrice());
//
//            // Add the parent and package to the booking
//            booking.setParent(parent);
//            booking.setPackageObj(aPackage);
//
//            // Save the new booking
//            booking = bookingRepository.save(booking);
//            return booking;
//        }

