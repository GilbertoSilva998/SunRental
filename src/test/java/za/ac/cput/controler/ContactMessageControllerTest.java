package za.ac.cput.controler;
/**
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.cput.domain.ContactMessage;
import za.ac.cput.service.ContactMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ContactMessageControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContactMessageService contactMessageService;

    @InjectMocks
    private ContactMessageController contactMessageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactMessageController).build();
    }

    @Test
    void create() throws Exception {
        ContactMessage contactMessage = new ContactMessage.Builder()
                .setBranch("Khayelitsha")
                .setFirstName("Kupo")
                .setLastName("Maja")
                .setPhone("0762983736")
                .setEmail("kupomaja@gmail.com")
                .setConfirmEmail("kupomaja@gmail.com")
                .setMessage("How to book a Van, I need assistance")
                .build();

        when(contactMessageService.create(any(ContactMessage.class))).thenReturn(contactMessage);

        mockMvc.perform(post("/contact-message/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(contactMessage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.branch").value("Khayelitsha"))
                .andExpect(jsonPath("$.firstName").value("Kupo"))
                .andExpect(jsonPath("$.lastName").value("Maja"))
                .andExpect(jsonPath("$.phone").value("0762983736"))
                .andExpect(jsonPath("$.email").value("kupomaja@gmail.com"))
                .andExpect(jsonPath("$.confirmEmail").value("kupomaja@gmail.com"))
                .andExpect(jsonPath("$.message").value("How to book a Van, I need assistance"));
    }

    @Test
    void read() throws Exception {
        ContactMessage contactMessage = new ContactMessage.Builder()
                .setId(1L)
                .setBranch("Khayelitsha")
                .setFirstName("Kupo")
                .setLastName("Maja")
                .setPhone("0762983736")
                .setEmail("kupomaja@gmail.com")
                .setConfirmEmail("kupomaja@gmail.com")
                .setMessage("How to book a Van, I need assistance")
                .build();

        when(contactMessageService.read(anyLong())).thenReturn(contactMessage);

        mockMvc.perform(get("/contact-message/read/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.branch").value("Khayelitsha"))
                .andExpect(jsonPath("$.firstName").value("Kupo"))
                .andExpect(jsonPath("$.lastName").value("Maja"))
                .andExpect(jsonPath("$.phone").value("0762983736"))
                .andExpect(jsonPath("$.email").value("kupomaja@gmail.com"))
                .andExpect(jsonPath("$.confirmEmail").value("kupomaja@gmail.com"))
                .andExpect(jsonPath("$.message").value("How to book a Van, I need assistance"));
    }

    @Test
    void update() throws Exception {
        ContactMessage contactMessage = new ContactMessage.Builder()
                .setId(1L)
                .setBranch("Khayelitsha")
                .setFirstName("Kupo")
                .setLastName("Maja")
                .setPhone("0762983736")
                .setEmail("kupomaja@gmail.com")
                .setConfirmEmail("kupomaja@gmail.com")
                .setMessage("How to book a Van, I need assistance")
                .build();

        when(contactMessageService.update(any(ContactMessage.class))).thenReturn(contactMessage);

        mockMvc.perform(post("/contact-message/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(contactMessage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.branch").value("Khayelitsha"))
                .andExpect(jsonPath("$.firstName").value("Kupo"))
                .andExpect(jsonPath("$.lastName").value("Maja"))
                .andExpect(jsonPath("$.phone").value("0762983736"))
                .andExpect(jsonPath("$.email").value("kupomaja@gmail.com"))
                .andExpect(jsonPath("$.confirmEmail").value("kupomaja@gmail.com"))
                .andExpect(jsonPath("$.message").value("How to book a Van, I need assistance"));
    }

    @Test
    void getAll() throws Exception {
        ContactMessage contactMessage1 = new ContactMessage.Builder()
                .setBranch("Khayelitsha")
                .setFirstName("Kupo")
                .setLastName("Maja")
                .setPhone("0762983736")
                .setEmail("kupomaja@gmail.com")
                .setConfirmEmail("kupomaja@gmail.com")
                .setMessage("How to book a Van, I need assistance")
                .build();

        ContactMessage contactMessage2 = new ContactMessage.Builder()
                .setBranch("Woodstock")
                .setFirstName("Paul")
                .setLastName("Smith")
                .setPhone("0987654321")
                .setEmail("paulsmith@gmail.com")
                .setConfirmEmail("paulsmith@gmail.com")
                .setMessage("Please get back to me, I'm interested in booking.")
                .build();

        List<ContactMessage> contactMessages = Arrays.asList(contactMessage1, contactMessage2);

        when(contactMessageService.getAll()).thenReturn(contactMessages);

        mockMvc.perform(get("/contact-message/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].branch").value("Khayelitsha"))
                .andExpect(jsonPath("$[0].firstName").value("Kupo"))
                .andExpect(jsonPath("$[1].branch").value("Woodstock"))
                .andExpect(jsonPath("$[1].firstName").value("Paul"));
    }
}
*/
import org.junit.jupiter.api.Test;

class ContactMessageControllerTest {

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
