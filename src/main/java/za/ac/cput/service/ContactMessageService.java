package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ContactMessage;
import za.ac.cput.repository.ContactMessageRepository;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public ContactMessage saveContactMessage(ContactMessage contactMessage) {
        return contactMessageRepository.save(contactMessage);
    }
}


