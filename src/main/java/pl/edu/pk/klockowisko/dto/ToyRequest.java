package pl.edu.pk.klockowisko.dto;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class ToyRequest {
    @Nullable
    private Long id;
    @NotNull
    @Size(min = 3, max = 192, message = "Name must be between 3 and 192 characters long")
    private String name;

    @Nullable
    private Long manufacturerId;

    @NotNull
    private Long ownerId;

    @Nullable
    private Date purchaseDate;

    @Nullable
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    public String getName() {
        return name;
    }

    @Nullable
    public Long getManufacturerId() {
        return manufacturerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    @Nullable
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    @Nullable
    public Double getPrice() {
        return price;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ToyRequest{" +
                "name='" + name + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", ownerId=" + ownerId +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                '}';
    }
}
