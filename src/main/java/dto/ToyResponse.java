package dto;

import entity.Manufacturer;
import entity.User;

import java.util.Date;

public class ToyResponse {
    private Long id;
    private String name;
    private Manufacturer manufacturer;
    private User owner;
    private Date purchaseDate;
    private Double price;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
