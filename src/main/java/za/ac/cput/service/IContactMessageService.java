package za.ac.cput.service;

import za.ac.cput.domain.ContactMessage;

import java.util.List;

public interface IContactMessageService extends IService<ContactMessage, Long> {
    void delete(long messageId);

    List<ContactMessage> getAll();
}
