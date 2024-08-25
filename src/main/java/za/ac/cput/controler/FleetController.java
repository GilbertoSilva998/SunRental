package za.ac.cput.controler;

import za.ac.cput.domain.Van;
import za.ac.cput.service.FleetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fleet")
public class FleetController {

    private final FleetService fleetService;

    public FleetController(FleetService fleetService) {
        this.fleetService = fleetService;
    }

    // Get all vans in the fleet
    @GetMapping
    public List<Van> getAllVans() {
        return fleetService.getAllVans();
    }

    // Get a specific van by ID
    @GetMapping("/{id}")
    public ResponseEntity<Van> getVanById(@PathVariable String id) {
        Optional<Van> van = fleetService.getVanById(id);
        return van.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new van in the fleet
    @PostMapping
    public Van createVan(@RequestBody Van van) {
        return fleetService.createVan(van);
    }

    // Update an existing van by ID
    @PutMapping("/{id}")
    public ResponseEntity<Van> updateVan(@PathVariable String id, @RequestBody Van vanDetails) {
        Optional<Van> updatedVan = fleetService.updateVan(id, vanDetails);
        return updatedVan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a van by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVan(@PathVariable String id) {
        if (fleetService.deleteVan(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
