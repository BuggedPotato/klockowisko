package service;

import entity.Manufacturer;
import org.springframework.stereotype.Service;
import repository.ManufacturerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepo;

    public ManufacturerService(ManufacturerRepository manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    public List<Manufacturer> getAll(){
        return this.manufacturerRepo.findAll();
    }

    public Optional<Manufacturer> getManufacturerById(Long id){
        return this.manufacturerRepo.findById(id);
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer){
        return this.manufacturerRepo.save(manufacturer);
    }
}
