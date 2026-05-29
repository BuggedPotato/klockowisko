package pl.edu.pk.klockowisko.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ManufacturerRequest {

    @Nullable
    private Long id;
    @NotNull
    @Size(min = 3, max = 64, message = "Manufacturer name length must be between 3 and 64 characters")
    private String name;

    public String getName() {
        return name;
    }

    @Nullable
    public Long getId() {
        return id;
    }
}
