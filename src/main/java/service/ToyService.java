package service;

import entity.Toy;
import org.springframework.stereotype.Service;
import repository.ToyRepository;

import java.util.List;

@Service
public class ToyService {
    private final ToyRepository toyRepo;

    public ToyService(ToyRepository toyRepo) {
        this.toyRepo = toyRepo;
    }

    public List<Toy> getAllToys(){
        return this.toyRepo.findAll();
    }

    public List<Toy> getAllToysForUser(Long userId){
        return toyRepo.findAllByOwnerId(userId);
    }

    public Toy createToy(Toy toy){
        return this.toyRepo.save(toy);
    }

    public void deleteToyById(Long id){
        this.toyRepo.deleteById(id);
    }
}
