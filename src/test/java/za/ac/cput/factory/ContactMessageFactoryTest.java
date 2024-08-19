package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.ContactMessage;

import static org.junit.jupiter.api.Assertions.*;

class ContactMessageFactoryTest {

    @Test
    void buildContactMessageSuccess() {
        ContactMessage contactMessage = ContactMessageFactory.buildContactMessage(
                "Khayelitsha",
                "Kupo",
                "Maja",
                "0762983736",
                "kupomaja@gmail.com",
                "kupomaja@gmail.com",
                "I am interested in renting a van.");

        assertNotNull(contactMessage);
        assertEquals("Khayelitsha", contactMessage.getBranch());
        assertEquals("Kupo", contactMessage.getFirstName());
        assertEquals("Maja", contactMessage.getLastName());
        assertEquals("0762983736", contactMessage.getPhone());
        assertEquals("kupomaja@gmail.com", contactMessage.getEmail());
        assertEquals("I am interested in renting a van.", contactMessage.getMessage());
    }

    @Test
    void buildContactMessageFailure() {
        ContactMessage contactMessage = ContactMessageFactory.buildContactMessage(
                null,
                "Kupo",
                "Maja",
                "0762983736",
                "kupomaja@gmail.com",
                "kupomaja@gmail.com",
                "I am interested in renting a van.");

        assertNull(contactMessage);
    }
}
