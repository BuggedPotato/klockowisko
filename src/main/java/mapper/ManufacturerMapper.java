package mapper;

import dto.ManufacturerRequest;
import dto.ManufacturerResponse;
import entity.Manufacturer;

public class ManufacturerMapper {
    public static Manufacturer toEntity(ManufacturerRequest req){
        Manufacturer m = new Manufacturer();
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
