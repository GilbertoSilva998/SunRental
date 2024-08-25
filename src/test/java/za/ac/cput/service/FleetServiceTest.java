package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.Van;
import za.ac.cput.repository.FleetRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FleetServiceTest {

    @Mock
    private FleetRepository fleetRepository;

    @InjectMocks
    private FleetService fleetService;

    private Van van;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        van = new Van.Builder()
                .setVanID("V001")
                .setMake("Ford")
                .setModel("Transit")
                .setYear("2023")
                .setLicensePlate("XYZ123GP")
                .setVin("1HGCM82633A123456")
                .setCapacity("3 seats, 10 cubic meters")
                .setFuelType("Diesel")
                .setRentalStatus("Available")
                .build();
    }

    @Test
    void testCreateVan() {
        when(fleetRepository.save(any(Van.class))).thenReturn(van);
        Van createdVan = fleetService.createVan(van);
        assertNotNull(createdVan);
        assertEquals(van.getVanID(), createdVan.getVanID());
        verify(fleetRepository, times(1)).save(van);
    }

    @Test
    void testGetVanById() {
        when(fleetRepository.findById("V001")).thenReturn(Optional.of(van));
        Optional<Van> foundVan = fleetService.getVanById("V001");
        assertTrue(foundVan.isPresent());
        assertEquals(van.getVanID(), foundVan.get().getVanID());
        verify(fleetRepository, times(1)).findById("V001");
    }

    @Test
    void testDeleteVan() {
        when(fleetRepository.existsById("V001")).thenReturn(true);
        boolean isDeleted = fleetService.deleteVan("V001");
        assertTrue(isDeleted);
        verify(fleetRepository, times(1)).deleteById("V001");
    }
}
