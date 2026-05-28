package pl.edu.pk.klockowisko.controller;

import pl.edu.pk.klockowisko.dto.ManufacturerResponse;
import pl.edu.pk.klockowisko.entity.Manufacturer;
import jakarta.validation.Valid;
import pl.edu.pk.klockowisko.mapper.ManufacturerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pk.klockowisko.service.ManufacturerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    private final ManufacturerService service;

    public ManufacturerController(ManufacturerService service) {
        this.service = service;
    }

    @GetMapping
    public List<ManufacturerResponse> getAll(){
        return this.service.getAll().stream().map(ManufacturerMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public ManufacturerResponse getById(@Valid @PathVariable Long id){
        Optional<Manufacturer> res = this.service.getManufacturerById(id);
        if(res.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacturer not found");
        return ManufacturerMapper.toResponse(res.get());
    }
}
