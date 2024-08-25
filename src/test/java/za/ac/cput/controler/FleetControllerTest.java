package za.ac.cput.controler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Van;
import za.ac.cput.service.FleetService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FleetControllerTest {

    @Mock
    private FleetService fleetService;

    @InjectMocks
    private FleetController fleetController;

    private Van van;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        van = new Van.Builder()
                .setVanID("1")
                .setMake("Ford")
                .setModel("Transit")
                .setYear("2020")
                .setLicensePlate("ABC123")
                .setVin("1HGCM82633A123456")
                .setCapacity("10 cubic meters")
                .setFuelType("Diesel")
                .setRentalStatus("Available")
                .build();
    }

    @Test
    public void testGetAllVans() {
        List<Van> vanList = Arrays.asList(van);
        when(fleetService.getAllVans()).thenReturn(vanList);

        List<Van> result = fleetController.getAllVans();

        assertEquals(1, result.size());
        assertEquals(van.getVanID(), result.get(0).getVanID());
        verify(fleetService, times(1)).getAllVans();
    }

    @Test
    public void testGetVanById() {
        when(fleetService.getVanById(van.getVanID())).thenReturn(Optional.of(van));

        ResponseEntity<Van> result = fleetController.getVanById(van.getVanID());

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(van.getVanID(), result.getBody().getVanID());
        verify(fleetService, times(1)).getVanById(van.getVanID());
    }

    @Test
    public void testGetVanById_NotFound() {
        when(fleetService.getVanById(any(String.class))).thenReturn(Optional.empty());

        ResponseEntity<Van> result = fleetController.getVanById("invalidID");

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(fleetService, times(1)).getVanById("invalidID");
    }

    @Test
    public void testCreateVan() {
        when(fleetService.createVan(any(Van.class))).thenReturn(van);

        Van result = fleetController.createVan(van);

        assertEquals(van.getVanID(), result.getVanID());
        verify(fleetService, times(1)).createVan(van);
    }

    @Test
    public void testUpdateVan() {
        when(fleetService.updateVan(any(String.class), any(Van.class))).thenReturn(Optional.of(van));

        ResponseEntity<Van> result = fleetController.updateVan(van.getVanID(), van);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(van.getVanID(), result.getBody().getVanID());
        verify(fleetService, times(1)).updateVan(van.getVanID(), van);
    }

    @Test
    public void testUpdateVan_NotFound() {
        when(fleetService.updateVan(any(String.class), any(Van.class))).thenReturn(Optional.empty());

        ResponseEntity<Van> result = fleetController.updateVan("invalidID", van);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(fleetService, times(1)).updateVan("invalidID", van);
    }

    @Test
    public void testDeleteVan() {
        when(fleetService.deleteVan(van.getVanID())).thenReturn(true);

        ResponseEntity<Void> result = fleetController.deleteVan(van.getVanID());

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(fleetService, times(1)).deleteVan(van.getVanID());
    }

    @Test
    public void testDeleteVan_NotFound() {
        when(fleetService.deleteVan("invalidID")).thenReturn(false);

        ResponseEntity<Void> result = fleetController.deleteVan("invalidID");

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(fleetService, times(1)).deleteVan("invalidID");
    }
}

