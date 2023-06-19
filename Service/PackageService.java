package com.example.daycaresystem.Service;

import com.example.daycaresystem.ApiException.ApiException;
import com.example.daycaresystem.Model.Package;
import com.example.daycaresystem.Repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;

    public List<Package> getAllPackage() {
        return packageRepository.findAll();
    }


    public Package findPackageById(Integer id) {
        Package aPackage = packageRepository.findPackageById(id);
        if (aPackage == null) {
            throw new ApiException("the Package not found with id: " + id);
        }
        return aPackage;
    }



    public Package createPackage(Package apackage) {
        return packageRepository.save(apackage);
    }


    public Package updatePackage(Integer id, Package updatedPackage) {
        Package aPackage = packageRepository.findPackageById(id);
        if (aPackage == null) {
            throw new ApiException("Package not found with id: " + id);
        }
        aPackage.setId(updatedPackage.getId());
        aPackage.setPackagePrice(updatedPackage.getPackagePrice());
        aPackage.setPackageType(updatedPackage.getPackageType());
        aPackage.setPackageDuration(updatedPackage.getPackageDuration());
//        aPackage.setStartTime(updatedPackage.getStartTime());
//        aPackage.setEndTime(updatedPackage.getEndTime());
//        aPackage.setDate(updatedPackage.getDate());
        return packageRepository.save(aPackage);
    }

    public void deletePackage(Integer id) {
        Package aPackage = packageRepository.findPackageById(id);
        packageRepository.delete(aPackage);
    }

    public Package findPackageByPackageType(String type){
        Package findPackage = packageRepository.findPackageByPackageType(type);
        if (findPackage == null){
            throw new ApiException("the Package not found" + type);
        }
        return findPackage;
    }



}
