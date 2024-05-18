package za.ac.cput.repository;

import za.ac.cput.domain.Van;

import java.util.ArrayList;
import java.util.List;

public class VanRepository implements IVanRepository {

    private static IVanRepository repository = null;

    private List<Van> vanList = new ArrayList<>();

    private VanRepository(){
        vanList = new ArrayList<Van>();
    }

    public static IVanRepository getRepository(){
        if (repository == null) {
            repository = new VanRepository();

        }
        return repository;
    }

    @Override
    public Van create(Van van) {
        boolean success = vanList.add(van);
        if (success) {
            return van;

        }
        return null;
    }


    @Override
    public Van read(String vanID) {
        for (Van c : vanList){
            if (c.getVanID().equals(vanID)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Van update(Van van) {
        String id = van.getVanID();
        Van vanOld = read(id);
        if(vanOld == null)
        return null;

        boolean success = delete(id);
        if(success){
            if(vanList.add(van))
                return van;
        }
        return null;
    }


    @Override
    public boolean delete(String id) {
        Van vanToDelete = read(id);
        if (vanToDelete == null)
        return false;
        return (vanList.remove(vanToDelete));
    }

    @Override
    public List<Van> getAll() {
        return vanList;
    }

}
