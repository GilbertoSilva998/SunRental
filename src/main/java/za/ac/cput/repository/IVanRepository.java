package za.ac.cput.repository;

import za.ac.cput.domain.Van;
import java.util.List;

public interface IVanRepository extends IRepository <Van, String> {

    public List<Van> getAll();
}
