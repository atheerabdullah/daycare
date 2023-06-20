package com.example.daycaresystem;

import com.example.daycaresystem.Controller.DayCareController;

import com.example.daycaresystem.DTO.DaycareDTO;
import com.example.daycaresystem.DTO.StuffDTO;
import com.example.daycaresystem.DTO.TimeDTO;
import com.example.daycaresystem.Model.Daycare;
import com.example.daycaresystem.Model.MyUser;
import com.example.daycaresystem.Model.Stuff;
import com.example.daycaresystem.Service.DayCareService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



import org.junit.Test;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DaycareControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DayCareService dayCareService;

    @InjectMocks
    private DayCareController dayCareController;

    private MyUser testUser;
    private Daycare testDayCare;
    private StuffDTO testStuffDto;


    @Before
    public void setUp() {

        // Create a test daycare
        testDayCare = new Daycare();
        testDayCare.setId(1);
        testDayCare.setDaycareName("Test Daycare");
        testDayCare.setCommercialId("test-commercial-id");

        // Create an updated version of the test daycare
        Daycare updatedDayCare = new Daycare();
        updatedDayCare.setId(1);
        updatedDayCare.setDaycareName("Updated Daycare");
        updatedDayCare.setCommercialId("test-commercial-id");

        // Create a list of DaycareDTO objects to return from the controller
        List<DaycareDTO> testDaycareList = new ArrayList<>();
        DaycareDTO daycare1 = new DaycareDTO();
        daycare1.setDaycareName("Daycare1");
        daycare1.setFacilities(("Facility1"));
        testDaycareList.add(daycare1);

    }

    @Test
    public void testUpdateDaycareBadRequest() throws Exception {
        // Set up the DayCareService mock to return a daycare with a different commercial ID when updateDaycare is called
        Daycare daycareWithDifferentCommercialId = new Daycare();
        daycareWithDifferentCommercialId.setId(testDayCare.getId());
        daycareWithDifferentCommercialId.setDaycareName("Test Daycare");
        daycareWithDifferentCommercialId.setCommercialId("different-commercial-id");
        when(dayCareService.updateDaycare(eq(testDayCare.getId()), any(Daycare.class))).thenReturn(daycareWithDifferentCommercialId);

    }

    @Test
    public void testFindDaycareByDaycareName() throws Exception {
        DaycareDTO mockDaycare = new DaycareDTO();
        mockDaycare.setDaycareName("Test Daycare");

        Mockito.when(dayCareService.findDaycareByDaycareName("Test Daycare")).thenReturn(mockDaycare);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/daycares/findDayCareByName/{name}", "Test Daycare"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.daycareName").value("Test Daycare"));
    }

    @Test
    public void testGetAllDaycareSuccess() throws Exception {
        Daycare mockDaycare1 = new Daycare();
        mockDaycare1.setDaycareName("Test Daycare 1");

        Daycare mockDaycare2 = new Daycare();
        mockDaycare2.setDaycareName("Test Daycare 2");
        List<Daycare> mockDaycareList = new ArrayList<>();
        mockDaycareList.add(mockDaycare1);
        mockDaycareList.add(mockDaycare2);

        Mockito.when(dayCareService.getAllDaycare()).thenReturn(mockDaycareList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/daycares/AllDayCares"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].daycareName").value("Test Daycare 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].daycareName").value("Test Daycare 2"));
    }
    @Test
    public void testGetAllDaycareNotFound() throws Exception {
        Mockito.when(dayCareService.getAllDaycare()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/daycares/AllDayCares"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("The daycare list is empty."));
    }
    @Test
    public void testAddStuffForDaycareFailure() throws Exception {
        // Create a mock StuffDTO to add
        StuffDTO mockStuffDTO = new StuffDTO();
        mockStuffDTO.setStuffName("Test Stuff");

        // Mock the DaycareService's addStuffForDaycare method to return null
        Mockito.when(dayCareService.addStuffForDaycare(Mockito.any(StuffDTO.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/daycares/{daycareId}/staff", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockStuffDTO)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Failed to add stuff."));
    }
    @Test
    public void testGetDaycareHoursByName() throws Exception {
        TimeDTO mockTimeDTO = new TimeDTO();
        mockTimeDTO.setOpeningTime("6:00");
        mockTimeDTO.setClosingTime("8:00");

        String mockName = "MockDaycare";
        Mockito.when(dayCareService.getDaycareHoursByName("Mock Daycare")).thenReturn(mockTimeDTO);
                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/daycares/AllDayCares"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].daycareName").value("Test Daycare 1"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[1].daycareName").value("Test Daycare 2"));
            }




















    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


