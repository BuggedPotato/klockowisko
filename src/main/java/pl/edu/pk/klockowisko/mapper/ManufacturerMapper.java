package pl.edu.pk.klockowisko.mapper;

import pl.edu.pk.klockowisko.dto.ManufacturerRequest;
import pl.edu.pk.klockowisko.dto.ManufacturerResponse;
import pl.edu.pk.klockowisko.entity.Manufacturer;

public class ManufacturerMapper {
    public static Manufacturer toEntity(ManufacturerRequest req){
        Manufacturer m = new Manufacturer();
        m.setId(req.getId());
        m.setName(req.getName());
        return m;
    }

    public static ManufacturerResponse toResponse(Manufacturer m){
        ManufacturerResponse res = new ManufacturerResponse();
        res.setId(m.getId());
        res.setName(m.getName());
        return res;
    }
}
