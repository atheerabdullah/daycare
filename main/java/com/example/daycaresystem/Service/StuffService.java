package com.example.daycaresystem.Service;


import com.example.daycaresystem.ApiException.ApiException;
import com.example.daycaresystem.DTO.PackageDTO;
import com.example.daycaresystem.DTO.StuffDTO;
import com.example.daycaresystem.Model.Package;
import com.example.daycaresystem.Model.Stuff;
import com.example.daycaresystem.Repository.DaycareRepository;
import com.example.daycaresystem.Repository.StuffRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StuffService {


    private final StuffRepository stuffRepository;
    private final DaycareRepository daycareRepository;
    private final PackageService packageRepository;

    public List<Stuff> getAllStuff() {
        return stuffRepository.findAll();
    }

    public Stuff findStuffById(Integer id) {
        Stuff stuff = stuffRepository.findStuffByid(id);
        if (stuff == null) {
            throw new ApiException("Stuff not found with id: " + id);
        }
        return stuff;
    }


    // stuff not working fine
    public Stuff createStuff(Stuff stuff) {
        if (stuffRepository.existsStuffsByCivilRecord(stuff.getCivilRecord())) {
            throw new ApiException("A stuff with the same civil record already exists");
        }
        if (stuff.getDaycare() == null || stuff.getDaycare().getId() == null) {
            throw new ApiException("The daycare field of the stuff object cannot be null");
        }
        if (!daycareRepository.existsById(stuff.getDaycare().getId())) {
            throw new ApiException("The specified daycare does not exist");
        }
        return stuffRepository.save(stuff);
    }

    public Stuff updateStuff(Integer id, Stuff updatedStuff) {
        Stuff stuffToUpdate = stuffRepository.findStuffByid(id);
        if (stuffToUpdate == null) {
            throw new ApiException("Stuff not found with id: " + id);
        }
        if (!updatedStuff.getCivilRecord().equals(stuffToUpdate.getCivilRecord())) {
            throw new PersistenceException("The Civil Record field cannot be updated.");
        }
        stuffToUpdate.setStuffName(updatedStuff.getStuffName());
        stuffToUpdate.setPhone(updatedStuff.getPhone());
        stuffToUpdate.setPosition(updatedStuff.getPosition());
        stuffToUpdate.setQualifications(updatedStuff.getQualifications());
        stuffToUpdate.setCivilRecord(stuffToUpdate.getCivilRecord());
        return stuffRepository.save(stuffToUpdate);
    }

    public void deleteStuff(Integer id) {
        Stuff stuff = stuffRepository.findStuffByid(id);
        stuffRepository.delete(stuff);
    }

    // add the stuff to package
    public void addStuffToPackage(Integer packageId, Integer stuffId) {
        Package addPackage = packageRepository.findPackageById(packageId);
        Stuff stuffEntity = stuffRepository.findById(stuffId)
                .orElseThrow(() -> new EntityNotFoundException("Stuff not found"));
        addPackage.getStuffs().add(stuffEntity);
        packageRepository.createPackage(addPackage);
    }

    //show stuff with Package
    public PackageDTO getPackageWithStuffsById(Integer id) {
        Package packageEntity = packageRepository.findPackageById(id);
        if (packageEntity == null) {
            throw new EntityNotFoundException("Package not found");
        }

        List<StuffDTO> stuffs = new ArrayList<>();
        for (Stuff stuffEntity : packageEntity.getStuffs()) {
            StuffDTO stuffDto = new StuffDTO();
            stuffDto.setCivilRecord(stuffEntity.getCivilRecord());
            stuffDto.setStuffName(stuffEntity.getStuffName());
            stuffs.add(stuffDto);
        }

        PackageDTO packageDto = new PackageDTO();
        packageDto.setPackageType(packageEntity.getPackageType());
        packageDto.setPackageDuration(packageEntity.getPackageDuration());
        return packageDto;
    }

    // get all stuff in the package
    public Set<Stuff> getAllStuffsInPackage(String packageName) {
        Package packageEntity = packageRepository.findPackageByPackageType(packageName);
        if (packageEntity == null) {
            throw new ApiException("Package not found with id: " + packageName);
        }
        return packageEntity.getStuffs();
    }

    //
    public Set<Stuff> getAllStuffsInPackageByID(Integer packageid) {
        Package packageEntity = packageRepository.findPackageById(packageid);
        if (packageEntity == null) {
            throw new ApiException("Package not found with id: " + packageid);
        }
        return packageEntity.getStuffs();
    }


    // Remove Stuff From Package
    public Stuff removeStuffFromPackage(Integer stuffId, Integer packageId) {
        Stuff stuff = stuffRepository.findStuffByid(stuffId);
        if (stuff == null) {
            throw new ApiException("Stuff not found");
        }
        Package removePackage = packageRepository.findPackageById(packageId);
        if (removePackage == null) {
            throw new ApiException("Package not found");
        }
        removePackage.getStuffs().remove(stuff);
        stuff.getPackages().remove(removePackage);
        packageRepository.createPackage(removePackage);
        return stuff;
    }

    // find stuff by daycare name
    public List<Stuff> getStuffByDaycareName(String name) {
        return stuffRepository.findStuffByDaycare_DaycareName(name);
    }
}

