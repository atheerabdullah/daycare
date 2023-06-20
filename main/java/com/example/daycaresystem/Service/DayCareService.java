package com.example.daycaresystem.Service;


import com.example.daycaresystem.ApiException.ApiException;
import com.example.daycaresystem.DTO.*;
import com.example.daycaresystem.Model.*;
import com.example.daycaresystem.Model.Package;
import com.example.daycaresystem.Repository.*;
import jakarta.persistence.PersistenceException;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DayCareService {

    private final DaycareRepository daycareRepository;
    private final StuffRepository stuffRepository;
    private final PackageRepository packageRepository;
    private final ParentRepository parentRepository;
    private final BookingRepository bookingRepository;

    private final MyUserAuthRepository myUserAuthRepository;

    public List<Daycare> getAllDaycare() {
        return daycareRepository.findAll();
    }

    public void addDaycare(DaycareDTO daycare) {
        String hash = new BCryptPasswordEncoder().encode(daycare.getPassword());
        daycare.setPassword(hash);
        MyUser myUser = new MyUser(null, daycare.getDaycareName(), daycare.getPassword(), "daycare",null, null);

        Daycare daycare1 = new Daycare(myUser.getId(), daycare.getCommercialId(),daycare.getDaycareName(), daycare.getPassword(), daycare.getPhone(), daycare.getDescription(), daycare.getFacilities(),
                daycare.getEmail(), daycare.getCapacity(), daycare.getLocation(), daycare.getOpeningTime(), daycare.getClosingTime(), daycare.getRate(), null,null,null,myUser);
        myUser.setDaycareSecurity(daycare1);
        myUserAuthRepository.save(myUser);
        daycareRepository.save(daycare1);

    }

    public Daycare updateDaycare(Integer id, Daycare updatedDaycare) {
        Daycare updateDaycare = daycareRepository.findDaycareById(id);
        if (updateDaycare == null) {
            throw new ApiException("Daycare not found with id: " + id);
        }
        if (!updatedDaycare.getCommercialId().equals(updateDaycare.getCommercialId())) {
            throw new PersistenceException("The Commercial id field cannot be updated.");
        }
//        if (!updateDaycare.getRates().equals(updateDaycare.getRates())){
//            throw new PersistenceException("the Rate not updated ");
//        }
        updateDaycare.setDaycareName(updatedDaycare.getDaycareName());
        updateDaycare.setPhone(updatedDaycare.getPhone());
        updateDaycare.setDescription(updatedDaycare.getDescription());
        updateDaycare.setFacilities(updatedDaycare.getFacilities());
        updateDaycare.setEmail(updatedDaycare.getEmail());
        updateDaycare.setCapacity(updatedDaycare.getCapacity());
        updateDaycare.setLocation(updatedDaycare.getLocation());
        updateDaycare.setOpeningTime(updatedDaycare.getOpeningTime());
        updateDaycare.setClosingTime(updatedDaycare.getClosingTime());
        updateDaycare.setRate(updatedDaycare.getRate());
        return daycareRepository.save(updateDaycare);
    }

    public void deleteDaycare(Integer id) {
        Daycare daycare = daycareRepository.findDaycareById(id);
        if (daycare == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Daycare not found with id: " + id);
        }
        daycareRepository.delete(daycare);
    }

    //add stuff for daycare
    public Stuff addStuffForDaycare(StuffDTO stuffDto) {
        Daycare daycare = daycareRepository.findById(stuffDto.getDaycareId())
                .orElseThrow(() -> new ApiException("Daycare not found with id: " + stuffDto.getDaycareId()));
        Stuff stuff = new Stuff();
        stuff.setCivilRecord(stuffDto.getCivilRecord());
        stuff.setStuffName(stuffDto.getStuffName());
        stuff.setPhone(stuffDto.getPhone());
        stuff.setPosition(stuffDto.getPosition());
        stuff.setQualifications(stuffDto.getQualifications());
        stuff.setDaycare(daycare);
        daycare.getStuffList().add(stuff);
        stuff = stuffRepository.save(stuff);
        return stuff;
    }


    // add Package to daycare

    public Package addPackageForDaycare(PackageDTO serviceDto) {
        Daycare daycare = daycareRepository.findById(serviceDto.getDaycareId())
                .orElseThrow(() -> new ApiException("Daycare not found with id: " + serviceDto.getDaycareId()));
        Package addPackage = new Package();
        addPackage.setPackageType(serviceDto.getPackageType());
        addPackage.setPackageDuration(serviceDto.getPackageDuration());
        addPackage.setPackagePrice(serviceDto.getPackagePrice());
        addPackage.setDescription(serviceDto.getDescription());
        addPackage.setDaycare(daycare);
        daycare.getPackageList().add(addPackage);

            // Set packageDuration and packagePrice based on packageType and user selection
            if (serviceDto.getPackageType().equals("On-Site")) {
                String packageDuration = serviceDto.getPackageDuration();
                Double packagePrice = null;
                if (packageDuration.equals("3 months")) {
                    packagePrice = 300.0;
                } else if (packageDuration.equals("6 months")) {
                    packagePrice = 500.0;
                } else if (packageDuration.equals("1 year")) {
                    packagePrice = 1550.0;
                }
                addPackage.setPackageDuration(packageDuration);
                addPackage.setPackagePrice(packagePrice);
            } else if (serviceDto.getPackageType().equals("Session")) {
                String packageDuration = serviceDto.getPackageDuration();
                Double packagePrice = null;
                if (packageDuration.equals("3 months")) {
                    packagePrice = 400.0;
                } else if (packageDuration.equals("6 months")) {
                    packagePrice = 800.0;
                } else if (packageDuration.equals("1 year")) {
                    packagePrice = 1500.0;
                }
                addPackage.setPackageDuration(packageDuration);
                addPackage.setPackagePrice(packagePrice);
            }

        addPackage = packageRepository.save(addPackage);
        daycareRepository.save(daycare);
        return addPackage;
    }



    // get the opening and closing time daycare Hours by name
    public TimeDTO getDaycareHoursByName(String daycareName) {
        Daycare daycare = daycareRepository.findDaycareByDaycareName(daycareName);
        if (daycare == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Daycare not found with name: " + daycareName);
        }
        return new TimeDTO(daycare.getOpeningTime(), daycare.getClosingTime());
    }

    // find Daycare By Facilities
    public List<DaycareDTO> searchByFacilities(String facilities) {
        List<Daycare> daycares = daycareRepository.findByFacilitiesContaining(facilities);
        List<DaycareDTO> dtoList = new ArrayList<>();
        for (Daycare daycare : daycares) {
            dtoList.add(new DaycareDTO(daycare));
        }
        return dtoList;
    }


    // get the daycare by name without the list of stuff
    public DaycareDTO findDaycareByDaycareName(String daycareName) {
        Daycare daycare = daycareRepository.findDaycareByDaycareName(daycareName);
        if (daycare == null) {
            throw new ApiException("The Daycare not found with this name ");
        }
        return new DaycareDTO(daycare);
    }

    // get high rate
    public Daycare getHighestRatedDaycare() {
        List<Daycare> daycares = daycareRepository.findAll();
        Daycare highestRatedDaycare = null;
        Double highestRate = 0.0;
        for (Daycare daycare : daycares) {
            if (daycare.getRate() > highestRate) {
                highestRate = daycare.getRate();
                highestRatedDaycare = daycare;
            }
        }
        return highestRatedDaycare;
    }
    // change Statues of order
    public Booking changeStatues(BookDTO bookDTO,Integer ParentId, Integer bookId){
//               Booking booking=bookingRepository.findBookingByParentsId(ParentId);
        Parent parent =parentRepository.findParentById(ParentId);
        Booking booking=bookingRepository.findBookingById(bookId);
        Booking bookingR = bookingRepository.findBookingById(bookDTO.getParentId());


        if (booking == null) {
            throw new ApiException("You don't have a booking");
        }
        booking.setStatus(bookDTO.getStatus());
        return   bookingRepository.save(booking);
    }

    //public Booking changeStatues(BookDTO bookDTO,Integer ParentId, Integer bookId){
    ////               Booking booking=bookingRepository.findBookingByParentsId(ParentId);
    //                Parent parent =parentRepository.findParentById(ParentId);
    //                Booking booking=bookingRepository.findBookingById(bookId);
    //                Booking bookingR = bookingRepository.findBookingById(bookDTO.getParentId());
    //
    //
    //                if (booking == null) {
    //                        throw new ApiExeption("You don't have a booking");
    //                }
    //                booking.setStatus(bookDTO.getStatus());
    //              return   bookingRepository.save(booking);
    //        }



}

