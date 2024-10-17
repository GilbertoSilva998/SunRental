package za.ac.cput.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ContactMessage;
import za.ac.cput.service.ContactMessageService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @Autowired
    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping("/contact-message")
    public ContactMessage submitContactMessage(@RequestBody ContactMessage contactMessage) {
        return contactMessageService.create(contactMessage);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Backend is running and accessible!";
    }

    @GetMapping("/allMessages")
    public List<ContactMessage> getAllMessages() {
        return contactMessageService.getAll();
    }

    @GetMapping("/read/{id}")
    public ContactMessage readContactMessage(@PathVariable Long id) {
        return contactMessageService.read(id);
    }

    @PostMapping("/update")
    public ContactMessage updateContactMessage(@RequestBody ContactMessage contactMessage) {
        return contactMessageService.update(contactMessage);
    }


}

