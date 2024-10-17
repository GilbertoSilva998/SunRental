
package za.ac.cput.controler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.ContactMessage;
import za.ac.cput.service.ContactMessageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class ContactMessageControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private ContactMessageService contactMessageService;

        private ContactMessage contactMessage;

        @BeforeEach
        void setUp() {
            contactMessage = new ContactMessage.Builder()
                    .setFirstName("John")
                    .setLastName("Doe")
                    .setEmail("johndoe@example.com")
                    .setPhone("1234567890")
                    .setMessage("Test message")
                    .build();

            contactMessageService.create(contactMessage);
        }

        @Test
        void submitContactMessage() {
            ContactMessage newMessage = new ContactMessage.Builder()
                    .setFirstName("Jane")
                    .setLastName("Smith")
                    .setEmail("janesmith@example.com")
                    .setPhone("0987654321")
                    .setMessage("Another test message")
                    .build();

            ResponseEntity<ContactMessage> responseEntity = restTemplate.postForEntity("/api/contact-message", newMessage, ContactMessage.class);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertNotNull(responseEntity.getBody());
            assertEquals("Jane", responseEntity.getBody().getFirstName());
        }

        @Test
        void readContactMessage() {
            ResponseEntity<ContactMessage> responseEntity = restTemplate.getForEntity("/api/read/{id}", ContactMessage.class, contactMessage.getMessageId());

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertNotNull(responseEntity.getBody());
            assertEquals("John", responseEntity.getBody().getFirstName());
        }

        @Test
        void updateContactMessage() {
            contactMessage = new ContactMessage.Builder()
                    .copy(contactMessage)
                    .setMessage("Updated message")
                    .build();

            HttpEntity<ContactMessage> requestUpdate = new HttpEntity<>(contactMessage);
            ResponseEntity<ContactMessage> responseEntity = restTemplate.exchange("/api/update", HttpMethod.POST, requestUpdate, ContactMessage.class);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertNotNull(responseEntity.getBody());
            assertEquals("Updated message", responseEntity.getBody().getMessage());
        }

        @Test
        void getAllMessages() {
            ResponseEntity<ContactMessage[]> responseEntity = restTemplate.getForEntity("/api/allMessages", ContactMessage[].class);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertNotNull(responseEntity.getBody());
            assertEquals(1, responseEntity.getBody().length);
        }

        @Test
        void ping() {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/ping", String.class);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("Backend is running and accessible!", responseEntity.getBody());
        }
    }


