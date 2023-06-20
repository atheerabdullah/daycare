package com.example.daycaresystem;


import com.example.daycaresystem.Model.Parent;
import com.example.daycaresystem.Repository.ParentRepository;
import com.example.daycaresystem.Service.DayCareService;
import com.example.daycaresystem.Service.ParentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParentServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ParentService parentService;


    @Mock
    private ParentRepository parentRepository;



    @Test
    public void testDeleteParent() {
        Parent mockParent = new Parent();
        mockParent.setId(1);
        Mockito.when(parentRepository.findById(1)).thenReturn(Optional.of(mockParent));
        parentService.deleteParent(1, null);
        Mockito.verify(parentRepository).delete(mockParent);
    }

    @Test
    public void testGetParentById() {
        Parent mockParent = new Parent();
        mockParent.setId(1);

        Mockito.when(parentRepository.findById(1)).thenReturn(Optional.of(mockParent));

        Parent result = parentService.getParentById(1);

        Mockito.verify(parentRepository).findById(1);

        Assert.assertEquals(mockParent, result);
    }
}
