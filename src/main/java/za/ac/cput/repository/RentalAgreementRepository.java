package za.ac.cput.repository;

import za.ac.cput.domain.RentalAgreement;

import java.util.ArrayList;
import java.util.List;
/*
    Paulose Maja 220214115
 */
public class RentalAgreementRepository implements IRentalAgreementRepository {
    private static IRentalAgreementRepository repository = null;

    private List<RentalAgreement> rentals = new ArrayList<>();

    private RentalAgreementRepository() {
        rentals = new ArrayList<>();
    }

    public static IRentalAgreementRepository getRepository() {
        if (repository == null) {
            repository = new RentalAgreementRepository();
        }
        return repository;
    }

    @Override
    public List<RentalAgreement> getAll() {
        return rentals;
    }

    @Override
    public RentalAgreement create(RentalAgreement rentalAgreement) {
        boolean success = rentals.add(rentalAgreement);
        if (success) {
            return rentalAgreement;
        }
        return null;
    }

    @Override
    public RentalAgreement read(String agreementID) {
        for (RentalAgreement rental : rentals) {
            if (rental.getAgreementID().equals(agreementID)) {
                return rental;
            }
        }
        return null;
    }

    @Override
    public RentalAgreement update(RentalAgreement rentalAgreement) {
        String id = rentalAgreement.getAgreementID();
        RentalAgreement rentalOld = read(id);
        if (rentalOld == null)
            return null;

        boolean success = delete(id);
        if (success) {
            if (rentals.add(rentalAgreement))
                return rentalAgreement;
        }
        return null;
    }

    @Override
    public boolean delete(String agreementID) {
        RentalAgreement rentalToDelete = read(agreementID);
        if (rentalToDelete == null)
            return false;
        return rentals.remove(rentalToDelete);
    }
}
