package controller;


import dto.ToyResponse;
import entity.Toy;
import jakarta.validation.Valid;
import mapper.ToyMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import service.ToyService;

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
        return this.service.getAllToys().stream().map(ToyMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public ToyResponse getById(@Valid @PathVariable Long id){
        Optional<Toy> res = this.service.getToyById(id);
        if(res.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        return ToyMapper.toResponse(res.get());
    }
}
