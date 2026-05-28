package service;

import entity.Manufacturer;
import org.springframework.stereotype.Service;
import repository.ManufacturerRepository;

import java.util.List;

@Service
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepo;

    public ManufacturerService(ManufacturerRepository manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    public List<Manufacturer> getAll(){
        return this.manufacturerRepo.findAll();
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer){
        return this.manufacturerRepo.save(manufacturer);
    }
}
