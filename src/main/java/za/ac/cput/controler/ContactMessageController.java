package za.ac.cput.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ContactMessage;
import za.ac.cput.service.ContactMessageService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class ContactMessageController {

    private static final Logger logger = LoggerFactory.getLogger(ContactMessageController.class);

    private final ContactMessageService contactMessageService;

    @Autowired
    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping("/contact-message")
    public ResponseEntity<String> submitContactMessage(@RequestBody ContactMessage contactMessage) {
        logger.info("Received contact message: {}", contactMessage);
        ContactMessage savedMessage = contactMessageService.saveContactMessage(contactMessage);
        return ResponseEntity.ok("Form submitted successfully with ID ");
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Backend is running and accessible!");
    }
}

