package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.ContactMessage;
import za.ac.cput.factory.ContactMessageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactMessageServiceTest {

    @Autowired
    private ContactMessageService contactMessageService;

    private ContactMessage message;

    @BeforeEach
    void setUp() {
        message = ContactMessageFactory.buildContactMessage(
                null, "Khayelitsha", "Paulose", "Maja", "073 828 8378",
                "paulosemaja14@gmail.com", "paulosemaja14@gmail.com",
                "The booking button is not working on my side."
        );
    }

    @Test
    @Order(1)
    void create() {
        ContactMessage created = contactMessageService.create(message);
        assertNotNull(created);
        message = new ContactMessage.Builder()
                .copy(created)
                .build(); // Updated to use the created message object directly
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        ContactMessage createdMessage = contactMessageService.create(message);
        ContactMessage readMessage = contactMessageService.read(createdMessage.getMessageId()); // Read using the generated messageId
        assertNotNull(readMessage);
        System.out.println(readMessage);
    }

    @Test
    @Order(3)
    void update() {
        ContactMessage created = contactMessageService.create(message);
        ContactMessage newMessage = new ContactMessage.Builder()
                .copy(created)
                .setBranch("Wellington")
                .build();
        ContactMessage updated = contactMessageService.update(newMessage);
        assertNotNull(updated);
        assertEquals("Wellington", updated.getBranch());
        System.out.println(updated);
    }

    @Test
    @Order(4)
    void getAll() {
        contactMessageService.create(message);
        List<ContactMessage> messages = contactMessageService.getAll();
        assertNotNull(messages);
        assertFalse(messages.isEmpty());
        System.out.println(messages);
    }

    @Test
    @Order(5)
    @Disabled  // Disabled to avoid deleting now, will test this later
    void delete() {
        ContactMessage created = contactMessageService.create(message);
        contactMessageService.delete(created.getMessageId());
        assertNull(contactMessageService.read(created.getMessageId())); // the message is deleted
        System.out.println("Success: Deleted the contact message!");
    }
}
