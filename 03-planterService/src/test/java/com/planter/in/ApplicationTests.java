package com.planter.in;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.planter.in.dto.request.PlanterDto;
import com.planter.in.entity.Planter;
import com.planter.in.exception.ApplicationException;
import com.planter.in.repository.PlanterRepository;
import com.planter.in.service.PlanterServiceImpl;

@SpringBootTest
class ApplicationTests {

	@InjectMocks
    private PlanterServiceImpl planterService;

    @Mock
    private PlanterRepository planterRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPlanter() {
        PlanterDto planterDto = new PlanterDto();
        planterDto.setPlanterName("Designer Pot");
        Planter planter = new Planter();
        BeanUtils.copyProperties(planterDto, planter);

        when(planterRepo.save(any(Planter.class))).thenReturn(planter);

        Planter result = planterService.addPlanter(planterDto);

        assertNotNull(result);
        assertEquals("Designer Pot", result.getPlanterName());
        verify(planterRepo, times(1)).save(any(Planter.class));
    }

    @Test
    public void testAllPlanters() {
        List<Planter> planterList = new ArrayList<Planter>();
        Planter planter = new Planter();
        planter.setPlanterName("Clay Pot");
        planterList.add(planter);

        when(planterRepo.findAll()).thenReturn(planterList);

        List<Planter> result = planterService.allPlanters();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(planterRepo, times(1)).findAll();
    }

    @Test
    public void testAddDescription() {
        Planter planter = new Planter();
        planter.setDescription("A beautiful terracotta pot");

        when(planterRepo.save(any(Planter.class))).thenReturn(planter);

        Planter result = planterService.addDescription(planter);

        assertNotNull(result);
        assertEquals("A beautiful terracotta pot", result.getDescription());
        verify(planterRepo, times(1)).save(any(Planter.class));
    }

    @Test
    public void testDeletePlanterById() {
        int planterId = 1;
        doNothing().when(planterRepo).deleteById(planterId);

        planterService.deletePlanterById(planterId);

        verify(planterRepo, times(1)).deleteById(planterId);
    }

    @Test
    public void testUpdatePlanter() {
        int planterId = 1;
        PlanterDto planterDto = new PlanterDto();
        planterDto.setPlanterName("Ceramic Planter");

        Planter existingPlanter = new Planter();
        when(planterRepo.findById(planterId)).thenReturn(Optional.of(existingPlanter));
        when(planterRepo.save(any(Planter.class))).thenReturn(existingPlanter);

        Planter result = planterService.updatePlanter(planterId, planterDto);

        assertNotNull(result);
        verify(planterRepo, times(1)).findById(planterId);
        verify(planterRepo, times(1)).save(any(Planter.class));
    }

    @Test
    public void testUpdatePlanterThrowsException() {
        int planterId = 1;
        PlanterDto planterDto = new PlanterDto();

        when(planterRepo.findById(planterId)).thenReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> planterService.updatePlanter(planterId, planterDto));

        verify(planterRepo, times(1)).findById(planterId);
        verify(planterRepo, never()).save(any(Planter.class));
    }

    @Test
    public void testGetPlanterByPrice() {
        List<Planter> planterList = new ArrayList<>();
        Planter planter = new Planter();
        planter.setPrice(500.0f);
        planterList.add(planter);

        when(planterRepo.findAll()).thenReturn(planterList);

        Planter result = planterService.getPlanterByPrice(500.0f);

        assertNotNull(result);
        assertEquals(500.0f, result.getPrice());
        verify(planterRepo, times(1)).findAll();
    }

    @Test
    public void testGetPlanterByPriceNotFound() {
        List<Planter> planterList = new ArrayList<>();

        when(planterRepo.findAll()).thenReturn(planterList);

        Planter result = planterService.getPlanterByPrice(500.0f);

        assertNull(result);
        verify(planterRepo, times(1)).findAll();
    }
}


