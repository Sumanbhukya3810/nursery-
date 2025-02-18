package com.seed.in;

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

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.seed.in.dto.request.SeedDto;
import com.seed.in.entity.Seed;
import com.seed.in.exception.ApplicationException;
import com.seed.in.repository.SeedRepository;
import com.seed.in.service.SeedServiceImpl;

@SpringBootTest
class ApplicationTests {

	@InjectMocks
    private SeedServiceImpl seedService;

    @Mock
    private SeedRepository seedRepo;

  

    @Test
    public void testAddSeedName() {
        SeedDto seedDto = new SeedDto();
        seedDto.setSeedName("Sunflower");
        Seed seed = new Seed();
        BeanUtils.copyProperties(seedDto, seed);

        when(seedRepo.save(any(Seed.class))).thenReturn(seed);

        Seed result = seedService.addSeedName(seedDto);

        assertNotNull(result);
        assertEquals("Sunflower", result.getSeedName());
        verify(seedRepo, times(1)).save(any(Seed.class));
    }

    @Test
    public void testAllSeeds() {
        List<Seed> seedList = new ArrayList<Seed>();
        Seed seed = new Seed();
        seed.setSeedName("Sunflower");
        seedList.add(seed);

        when(seedRepo.findAll()).thenReturn(seedList);

        List<Seed> result = seedService.allSeeds();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(seedRepo, times(1)).findAll();
    }

    @Test
    public void testAddDescription() {
        Seed seed = new Seed();
        seed.setDescription("A beautiful flower");

        when(seedRepo.save(any(Seed.class))).thenReturn(seed);

        Seed result = seedService.addDescription(seed);

        assertNotNull(result);
        assertEquals("A beautiful flower", result.getDescription());
        verify(seedRepo, times(1)).save(any(Seed.class));
    }

    @Test
    public void testDeleteSeedById() {
        int seedId = 1;
        doNothing().when(seedRepo).deleteById(seedId);

        seedService.deleteSeedById(seedId);

        verify(seedRepo, times(1)).deleteById(seedId);
    }

    @Test
    public void testUpdateSeed() {
        int seedId = 1;
        SeedDto seedDto = new SeedDto();
        seedDto.setSeedName("Rose");

        Seed existingSeed = new Seed();
        when(seedRepo.findById(seedId)).thenReturn(Optional.of(existingSeed));
        when(seedRepo.save(any(Seed.class))).thenReturn(existingSeed);

        Seed result = seedService.updateSeed(seedId, seedDto);

        assertNotNull(result);
        verify(seedRepo, times(1)).findById(seedId);
        verify(seedRepo, times(1)).save(any(Seed.class));
    }

    @Test
    public void testUpdateSeedThrowsException() {
        int seedId = 1;
        SeedDto seedDto = new SeedDto();

        when(seedRepo.findById(seedId)).thenReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> seedService.updateSeed(seedId, seedDto));

        verify(seedRepo, times(1)).findById(seedId);
        verify(seedRepo, never()).save(any(Seed.class));
    }

    @Test
    public void testGetSeedById() {
        int seedId = 1;
        Seed seed = new Seed();
        seed.setSeedName("Tulip");

        when(seedRepo.findById(seedId)).thenReturn(Optional.of(seed));

        Seed result = seedService.getSeedById(seedId);

        assertNotNull(result);
        assertEquals("Tulip", result.getSeedName());
        verify(seedRepo, times(1)).findById(seedId);
    }

    @Test
    public void testGetSeedByIdNotFound() {
        int seedId = 1;

        when(seedRepo.findById(seedId)).thenReturn(Optional.empty());

        Seed result = seedService.getSeedById(seedId);

        assertNull(result);
        verify(seedRepo, times(1)).findById(seedId);
    }
}