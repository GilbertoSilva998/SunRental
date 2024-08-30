package za.ac.cput.service;
/**
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.ContactMessage;
import za.ac.cput.repository.ContactMessageRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactMessageServiceTest {

    @Mock
    private ContactMessageRepository repository;

    @InjectMocks
    private ContactMessageService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        ContactMessage contactMessage = new ContactMessage.Builder()
                .setBranch("Khayelitsha")
                .setFirstName("Kupo")
                .setLastName("Maja")
                .setPhone("0123456789")
                .setEmail("kupomaja@gmail.com")
                .setConfirmEmail("kupomaja@gmail.com")
                .setMessage("This is a test message")
                .build();

        when(repository.create(any(ContactMessage.class))).thenReturn(contactMessage);

        ContactMessage created = service.create(contactMessage);

        assertNotNull(created);
        assertEquals(contactMessage, created);
        verify(repository, times(1)).create(contactMessage);
    }

    @Test
    void read() {
        Long id = 1L;
        ContactMessage contactMessage = new ContactMessage.Builder()
                .setId(id)
                .setBranch("Khayelitsha")
                .setFirstName("Kupo")
                .setLastName("Maja")
                .setPhone("0123456789")
                .setEmail("kupomaja@gmail.com")
                .setConfirmEmail("kupomaja@gmail.com")
                .setMessage("This is a test message")
                .build();

        when(repository.read(id)).thenReturn(contactMessage);

        ContactMessage found = service.read(id);

        assertNotNull(found);
        assertEquals(contactMessage, found);
        verify(repository, times(1)).read(id);
    }

    @Test
    void update() {
        ContactMessage contactMessage = new ContactMessage.Builder()
                .setId(1L)
                .setBranch("Khayelitsha")
                .setFirstName("Kupo")
                .setLastName("Maja")
                .setPhone("0123456789")
                .setEmail("kupomaja@gmail.com")
                .setConfirmEmail("kupomaja@gmail.com")
                .setMessage("This is a test message")
                .build();

        when(repository.update(any(ContactMessage.class))).thenReturn(contactMessage);

        ContactMessage updated = service.update(contactMessage);

        assertNotNull(updated);
        assertEquals(contactMessage, updated);
        verify(repository, times(1)).update(contactMessage);
    }

    @Test
    void getAll() {
        ContactMessage contactMessage1 = new ContactMessage.Builder()
                .setBranch("Khayelitsha")
                .setFirstName("Kupo")
                .setLastName("Maja")
                .setPhone("0123456789")
                .setEmail("kupomaja@gmail.com")
                .setConfirmEmail("kupomaja@gmail.com")
                .setMessage("Please get to me, with how to book.")
                .build();

        ContactMessage contactMessage2 = new ContactMessage.Builder()
                .setBranch("Woodstock")
                .setFirstName("Paul")
                .setLastName("Smith")
                .setPhone("0987654321")
                .setEmail("paulsmith@gmail.com")
                .setConfirmEmail("paulsmith@gmail.com")
                .setMessage("I need to book, how to do it?")
                .build();

        List<ContactMessage> contactMessages = Arrays.asList(contactMessage1, contactMessage2);

        when(repository.getAll()).thenReturn(contactMessages);

        List<ContactMessage> allMessages = service.getAll();

        assertNotNull(allMessages);
        assertEquals(2, allMessages.size());
        assertTrue(allMessages.contains(contactMessage1));
        assertTrue(allMessages.contains(contactMessage2));
        verify(repository, times(1)).getAll();
    }
}
*/

import org.junit.jupiter.api.Test;

class ContactMessageServiceTest {

    @Test
    void create() {
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {
    }
}

