package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ContactMessage;
import za.ac.cput.repository.ContactMessageRepository;

import java.util.List;

@Service
public class ContactMessageService implements IContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Override
    public ContactMessage create(ContactMessage contactMessage) {
        return contactMessageRepository.save(contactMessage);
    }

    @Override
    public ContactMessage read(Long messageId) {
        contactMessageRepository.findById(messageId);
        return null;
    }

    @Override
    public ContactMessage update(ContactMessage contactMessage) {
        return contactMessageRepository.save(contactMessage);
    }

    @Override
    public void delete(long messageId) {
        contactMessageRepository.deleteById(messageId);
    }

    @Override
    public List<ContactMessage> getAll() {
        return contactMessageRepository.findAll();
    }

}

