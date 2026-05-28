package pl.edu.pk.klockowisko.repository;

import pl.edu.pk.klockowisko.entity.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {
    List<Toy> findAllByOwnerId(Long userId);
}
