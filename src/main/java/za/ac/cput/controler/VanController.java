package za.ac.cput.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Van;
import za.ac.cput.service.VanService;

import java.util.List;

@RestController
@RequestMapping("/van")
public class VanController {

    @Autowired
    private VanService service;

    @PostMapping("/create")
    public Van create (@RequestBody Van van){
        return service.create(van);
    }

    @GetMapping("/read/{vanID}")
    public Van read (@PathVariable String vanID) {
        return service.read(vanID);
    }

    @PostMapping("/update")
    public Van update (@RequestBody Van van){
        return service.update(van);
    }


//    @DeleteMapping("/delete/id")
//    public void delete (@PathVariable String id){
//        return service.delete(id);
//    }

    @GetMapping("/allVans")
    public List<Van> getAll(){return service.getAll();}
}
