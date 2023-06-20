package com.example.daycaresystem.Repository;


import com.example.daycaresystem.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking , Integer> {

    Booking findAllById(Integer id);

    Booking findBookingById(Integer id);


}
