package com.example.daycaresystem.Service;

import com.example.daycaresystem.ApiException.ApiException;
import com.example.daycaresystem.Model.Children;
import com.example.daycaresystem.Repository.ChildrenRepository;
import com.example.daycaresystem.Repository.ParentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ChildrenService {

    public final ChildrenRepository childrenRepository;
    public final ParentRepository parentRepository;

    public List<Children> getAllChildern() {
        return childrenRepository.findAll();
    }
    public void addChildern(Children childern) {

        childrenRepository.save(childern);
    }

    public void updateChildern(Children childern, Integer id) {
        Children updatedChildern = childrenRepository.findChildernById(id);

        if (updatedChildern == null) {
            throw new ApiException("Enter the correct Id");
        }
        updatedChildern.setAge(childern.getAge());
        updatedChildern.setChildName(childern.getChildName());
        updatedChildern.setGender(childern.getGender());
//        updatedChildern.setDisable();childern.());
        childrenRepository.save(updatedChildern);
    }
    public void deleteChildern(Integer id) {
        Children deleteChildern = childrenRepository.findChildernById(id);
        if (deleteChildern == null) {
            throw new ApiException("Product not found");
        }
        childrenRepository.delete(deleteChildern);
    }


}