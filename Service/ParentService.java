package com.example.daycaresystem.Service;

import com.example.daycaresystem.ApiException.ApiException;
import com.example.daycaresystem.DTO.BookDTO;
import com.example.daycaresystem.DTO.DaycareDTO;
import com.example.daycaresystem.DTO.ParentDTO;
import com.example.daycaresystem.Model.*;
import com.example.daycaresystem.Model.Package;
import com.example.daycaresystem.Repository.BookingRepository;
import com.example.daycaresystem.Repository.MyUserAuthRepository;
import com.example.daycaresystem.Repository.PackageRepository;
import com.example.daycaresystem.Repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {


    private final ParentRepository parentRepository;
    private final BookingRepository bookingRepository;
    private final PackageRepository packageRepository;
    private final MyUserAuthRepository myUserAuthRepository;


    //CRUD

    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    public void addParent(ParentDTO dot) {
        String hash = new BCryptPasswordEncoder().encode(dot.getPassword());
        dot.setPassword(hash);
        MyUser myUser = new MyUser(null, dot.getParentName(), dot.getPassword(), "parent", null, null);
        Parent parent = new Parent(null, dot.getParentName(), dot.getAddress(), dot.getEmail(), dot.getPassword(), dot.getPhone(), dot.getRelated(), myUser, null, null);
        myUser.setParentSecurity(parent);
        myUserAuthRepository.save(myUser);
        parentRepository.save(parent);
    }





//    public void updateParent(Parent parent, Integer id , MyUser myUser) {
//        Parent updatedParent = parentRepository.findParentById(id);
//        MyUser myUser=myUserAuthRepository.findMyUserByIdIs(userID);
//    if (updatedParent==null){
//              throw new ApiException("Parent not found with id: " + id);
//            }
//    else if (updatedParent.getMyUser().getId()!= userID) {
//              throw new ApiException("Sorry , You do not have the authority to update this parent!");
//    }
//        updatedParent.setParentName(parent.getParentName());
//        updatedParent.setRelated(parent.getRelated());
//        updatedParent.setEmail(parent.getEmail());
//        updatedParent.setPhone(parent.getPhone());
//        updatedParent.setAddress(parent.getAddress());
//    }


    //    else if(oldTodo.getMyuser().getId()!=auth){
    //      throw new ApiExeption("Sorry , You do not have the authority to update this Todo!");
    //    }



    public void deleteParent(Integer id , MyUser myUser) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with id: " + id));
        parentRepository.delete(parent);
    }


    public Parent getParentById(Integer id) {
        return parentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with id: " + id));
    }


    public void addBookToTheParent(BookDTO bookDTO) {
        Parent parent = parentRepository.findById(bookDTO.getParentId())
                .orElseThrow(() -> new ApiException("Parent not found with id: " + bookDTO.getParentId()));
        Booking booking = new Booking();
        booking.setAddress(bookDTO.getAddress());
        booking.setStart_time(booking.getStart_time());
        booking.setEnd_time(booking.getEnd_time());
        booking.setStatus(bookDTO.getStatus());
        booking.setServiceType(bookDTO.getServiceType());
        booking.setParent(parent); // assign the parent to the booking
        parent.getBookingSet().add(booking);
        booking = bookingRepository.save(booking);
        parentRepository.save(parent); // set the booking object in the parent entity
    }


//    public void addBook (Booking booking, Integer packageId){
//        Package apackage = packageRepository.findPackageById(packageId);
//        booking.setId(apackage.getId());
//        booking.setTotalPrice(apackage.getPackagePrice());
//        booking.setDurationType(apackage.getPackageType());
//        booking.setServiceType(apackage.getPackageType());
//        booking.setStatus("in progress");
//        bookingRepository.save(booking);
//    }
}




//    public Booking parentMakeBooking(Integer parentId, BookDTO bookingDTO) {
//
//        Parent parent = parentRepository.findParentById(parentId);
//        if(parent == null){
//            throw new EntityNotFoundException("Parent not found");
//        }
//
//        // Set packageDuration and packagePrice based on packageType and user selection
//        String packageType = bookingDTO.getServiceType();
//        String packageDuration = bookingDTO.getDurationType();
//        Double packagePrice = null;
//
//        if (packageType.equals("On-Site")) {
//            if (packageDuration.equals("3 months")) {
//                packagePrice = 300.0;
//            } else if (packageDuration.equals("6 months")) {
//                packagePrice = 500.0;
//            } else if (packageDuration.equals("1 year")) {
//                packagePrice = 1550.0;
//            }
//        } else if (packageType.equals("Session")) {
//            if (packageDuration.equals("3 months")) {
//                packagePrice = 400.0;
//            } else if (packageDuration.equals("6 months")) {
//                packagePrice = 800.0;
//            } else if (packageDuration.equals("1 year")) {
//                packagePrice = 1500.0;
//            }
//        }
//
//        // Retrieve the Package object from the repository
//        Package aPackage = packageRepository.findPackageById(bookingDTO.getPackageId());
//        if (aPackage == null) {
//            throw new ApiException("Package not found");
//        }
//
//        // Create a new Booking object and set its properties
//        Booking booking = new Booking();
//        booking.setParent(parent);
//        booking.setTotalPrice(packagePrice);
//        booking.setServiceType(bookingDTO.getServiceType());
//        booking.setDurationType(bookingDTO.getDurationType());
//        booking.setStatus(bookingDTO.getStatus());
//        booking.setAddress(bookingDTO.getAddress());
//        booking.setChildName(bookingDTO.getChildName());
//        booking.setPackageObj(aPackage);// set the Package object on the Booking object

//        // Set the package on the booking
//        Package aPackage1 = packageRepository.findPackageById(bookingDTO.getPackageId());
//        if (aPackage1 == null) {
//            throw new ApiException("Package not found");
//        }
//
//        // Save the booking and parent to the database
//        bookingRepository.save(booking);
//        parentRepository.save(parent);
//
//        return booking;
//    }

//    public Parent ParentMakeBooking(ParentDTO parentDTO) {
//        // Create a new Parent object and set its properties
//        Parent parent = new Parent();
//        parent.setParentName(parentDTO.getParentName());
//        parent.setRelated(parentDTO.getRelated());
//        parent.setEmail(parentDTO.getEmail());
//        parent.setPhone(parentDTO.getPhone());
//        parent.setAddress(parentDTO.getAddress());
//
//        // Create new Booking objects and set their properties
//        List<Booking> bookings = new ArrayList<>();
//        if (parentDTO.getBookings() != null)
//            for (BookDTO bookingDTO : parentDTO.getBookings()) {
//            Booking booking = new Booking();
//            booking.setAddress(bookingDTO.getAddress());
//            booking.setStatus(bookingDTO.getStatus());
//            booking.setServiceType(bookingDTO.getServiceType());
//            bookings.add(booking);
//        }
//
//        // Associate the bookings with the parent and save everything
//        parent.setBookingSet(bookings);
//        parent = parentRepository.save(parent);
//
//        // Set the parent for each booking and save them to the database
//        for (Booking booking : bookings) {
//            booking.setParent(parent);
//            bookingRepository.save(booking);
//        }
//
//        return parent;
//    }

    //assign a book to the parent
//    public Booking addBookToTheParent(BookDTO bookDTO) {
//        Parent parent = parentRepository.findById(bookDTO.getParentId())
//                .orElseThrow(() -> new ApiException("Parent not found with id: " + bookDTO.getParentId()));
//        Booking booking = new Booking();
//        booking.setAddress(bookDTO.getAddress());
//        booking.setStatus(bookDTO.getStatus());
//        booking.setServiceType(bookDTO.getServiceType());
//        booking.setParent(parent);
//        parent.getBookingSet().add(booking);
//        booking = bookingRepository.save(booking);
//        return booking;
//    }


//}
