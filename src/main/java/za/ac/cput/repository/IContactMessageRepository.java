package za.ac.cput.repository;

import za.ac.cput.domain.ContactMessage;
import java.util.List;

public interface IContactMessageRepository extends IRepository<ContactMessage, Long> {
    List<ContactMessage> getAll();
}
