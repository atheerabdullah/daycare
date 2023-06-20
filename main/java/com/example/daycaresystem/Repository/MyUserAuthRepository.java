package com.example.daycaresystem.Repository;

import com.example.daycaresystem.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserAuthRepository extends JpaRepository<MyUser, Integer> {

    MyUser findMyUserByUsername(String name);
    MyUser findMyUserByIdIs(Integer id);
}
