package pl.edu.pk.klockowisko.mapper;

import pl.edu.pk.klockowisko.dto.ToyRequest;
import pl.edu.pk.klockowisko.dto.ToyResponse;
import pl.edu.pk.klockowisko.entity.Toy;

public class ToyMapper {
    public static Toy toEntity(ToyRequest req){
        Toy toy = new Toy();
        toy.setName(req.getName());
        toy.setPrice(req.getPrice());
        toy.setPurchaseDate(req.getPurchaseDate());
        System.out.println(toy);
        return toy;
    }

    public static ToyResponse toResponse(Toy toy){
        ToyResponse res = new ToyResponse();
        res.setId(toy.getId());
        res.setName(toy.getName());
        res.setPrice(toy.getPrice());
        res.setPurchaseDate(toy.getPurchaseDate());
        res.setOwner(toy.getOwner());
        res.setManufacturer(toy.getManufacturer());
        return res;
    }
}
