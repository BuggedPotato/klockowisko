package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue
    private Long Id;
    private String Name;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<Toy> toys = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }
}
