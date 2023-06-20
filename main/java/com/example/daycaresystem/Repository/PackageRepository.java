package com.example.daycaresystem.Repository;

import com.example.daycaresystem.Model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository <Package , Integer> {

    Package findPackageById(Integer id);

    Package findPackageByPackageType(String type);


    //PackageWithStuffDTO
//    @Query("SELECT DISTINCT p FROM Package p JOIN FETCH p.stuffs s WHERE p.daycare.daycareName = :daycareName")
//    List<Package> findPackagesWithStuffByDaycareName(String daycareName);;
}
