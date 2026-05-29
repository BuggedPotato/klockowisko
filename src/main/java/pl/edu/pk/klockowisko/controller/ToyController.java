package pl.edu.pk.klockowisko.controller;


import org.springframework.web.bind.annotation.*;
import pl.edu.pk.klockowisko.dto.ManufacturerRequest;
import pl.edu.pk.klockowisko.dto.ManufacturerResponse;
import pl.edu.pk.klockowisko.dto.ToyRequest;
import pl.edu.pk.klockowisko.dto.ToyResponse;
import pl.edu.pk.klockowisko.entity.Toy;
import jakarta.validation.Valid;
import pl.edu.pk.klockowisko.mapper.ToyMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pk.klockowisko.service.ToyService;
import tools.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/toys")
public class ToyController {
    private final ToyService service;

    public ToyController(ToyService service) {
        this.service = service;
    }

    @GetMapping
    public List<ToyResponse> getAll(){
        var list = this.service.getAllToys().stream().map(ToyMapper::toResponse).toList();
        for (ToyResponse t : list){
            System.out.println(t);

        }
        return list;
    }

    @GetMapping("/{id}")
    public ToyResponse getById(@Valid @PathVariable Long id){
        Optional<Toy> res = this.service.getToyById(id);
        if(res.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        return ToyMapper.toResponse(res.get());
    }

    @PostMapping
    public ToyResponse create(@Valid @RequestBody ToyRequest req){
        System.out.println(req);
        return ToyMapper.toResponse(
                this.service.createToyFromRequest(
                        req
                )
        );
    }
}
