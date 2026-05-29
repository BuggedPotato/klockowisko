package pl.edu.pk.klockowisko.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pk.klockowisko.dto.ToyRequest;
import pl.edu.pk.klockowisko.entity.Manufacturer;
import pl.edu.pk.klockowisko.entity.Toy;
import org.springframework.stereotype.Service;
import pl.edu.pk.klockowisko.entity.User;
import pl.edu.pk.klockowisko.mapper.ToyMapper;
import pl.edu.pk.klockowisko.repository.ManufacturerRepository;
import pl.edu.pk.klockowisko.repository.ToyRepository;
import pl.edu.pk.klockowisko.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ToyService {
    private final ToyRepository toyRepo;
    private final ManufacturerRepository manufacturerRepo;
    private final UserRepository userRepo;

    public ToyService(ToyRepository toyRepo, ManufacturerRepository manufacturerRepo, UserRepository userRepo) {
        this.toyRepo = toyRepo;
        this.manufacturerRepo = manufacturerRepo;
        this.userRepo = userRepo;
    }

    public List<Toy> getAllToys(){
        return this.toyRepo.findAll();
    }

    public Optional<Toy> getToyById(Long id){
        return this.toyRepo.findById(id);
    }

    public List<Toy> getAllToysForUser(Long userId){
        return toyRepo.findAllByOwnerId(userId);
    }

    public Toy createToy(Toy toy){
        return this.toyRepo.save(toy);
    }

    public Toy createToyFromRequest(ToyRequest req){
        Toy toy = ToyMapper.toEntity(req);
        if(req.getManufacturerId() != null){
            Optional<Manufacturer> res = this.manufacturerRepo.findById(req.getManufacturerId());
            if(res.isEmpty())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "No manufacturer with id = " + req.getManufacturerId());
            toy.setManufacturer(res.get());
        }

        Optional<User> res = this.userRepo.findById(req.getOwnerId());
        if(res.isEmpty())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "No user with id = " + req.getOwnerId());
        toy.setOwner(res.get());

        System.out.println(toy);
        return this.toyRepo.save(toy);
    }

    public void deleteToyById(Long id){
        this.toyRepo.deleteById(id);
    }
}
