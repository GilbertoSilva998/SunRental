package za.ac.cput.controler;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Van;
import za.ac.cput.factory.VanFactory;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VanControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/van";

    private static Van van;

    static byte[] image = new byte[0];

    @BeforeAll
    public static void setUp(){
        van = VanFactory.buildvan("CA9000",
                                  "Quantum",
                                  "Q1",
                                  2025,
                                  "Qua125",
                                  16,
                                  "Diesel",
                                  true,
                                   1500,
                                   image);
        System.out.println(van);
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Van> postResponse = restTemplate.postForEntity(url, van, Van.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Van vanSaved = postResponse.getBody();
        assertNotNull(van.getLicensePlate(), vanSaved.getLicensePlate());
        System.out.println("Saved data: " + vanSaved);
    }

    @Test
    void read(){
        String url = BASE_URL + "/read/" + van.getLicensePlate();
        System.out.println("URL: " +url);
        ResponseEntity<Van> response = restTemplate.getForEntity(url, Van.class);
        assertEquals(van.getLicensePlate(), response.getBody().getLicensePlate());
        System.out.println("Read: " + response.getBody());

    }

    @Test
    void update(){
        String url = BASE_URL + "/update";
        Van newVan = new Van.Builder().copy(van)
                .setCapacity(9).build();
        ResponseEntity<Van> postResponse = restTemplate.postForEntity(url, newVan ,Van.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Van vanUpdated = postResponse.getBody();
        assertNotNull(newVan.getLicensePlate(), vanUpdated.getLicensePlate());
        System.out.println("Updated data: " + vanUpdated);
    }

//    @Test
//    @Disabled
//    void delete(){
//        String url = BASE_URL + "/delete";
//        ResponseEntity<Van> response = restTemplate.delete(url, van, Van.class);
//    }


    @Test
    void getAll() {
        String url = BASE_URL + "/allVans";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show All");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}