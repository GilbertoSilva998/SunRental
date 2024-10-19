package za.ac.cput.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Van;
import za.ac.cput.service.VanService;

import java.util.List;

@RestController
@RequestMapping("/van")
@CrossOrigin("http://localhost:8080")
public class VanController {
    private final VanService service;

    @Autowired
    public VanController(VanService service){
        this.service = service;
    }


    @PostMapping("/create")
    public ResponseEntity<Van> create (@RequestBody Van van){
        Van createdVan = service.create(van);
        return new ResponseEntity<>(createdVan, HttpStatus.CREATED);
    }

    @GetMapping("/read/{licensePlate}")
    public ResponseEntity<Van> read (@PathVariable String licensePlate) {
        Van read = service.read(licensePlate);
        return new ResponseEntity<>(read, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Van> update (@RequestBody Van van){
        Van updatedVan = service.update(van);
        return new ResponseEntity<>(updatedVan, HttpStatus.OK);
    }


    @DeleteMapping("/deleteById/{licensePlate}")
    public ResponseEntity<Void> deleteById (@PathVariable String licensePlate){
        service.deleteById(licensePlate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allVans")
    public List<Van> getAll(){
        return service.getAll();
    }

    @GetMapping("/image/{licensePlate}")
    public ResponseEntity<byte[]> getVanImage(@PathVariable("licensePlate") String licensePlate) {

        byte[] imageData = service.getVanImage(licensePlate);

        if (imageData != null){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();

        }
    }

}

