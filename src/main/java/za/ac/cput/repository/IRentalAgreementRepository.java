package za.ac.cput.repository;
import za.ac.cput.domain.RentalAgreement;

import java.util.List;
/*
    Paulose Maja 220214115
 */
public interface IRentalAgreementRepository extends IRepository <RentalAgreement, String>{
    public List<RentalAgreement> getAll();
}
