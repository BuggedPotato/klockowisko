package pl.edu.pk.klockowisko.dto;

import java.util.Date;

public class ToyResponse {
    private Long id;
    private String name;
    private ManufacturerResponse manufacturer;
    private UserResponse owner;
    private Date purchaseDate;
    private Double price;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(ManufacturerResponse manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setOwner(UserResponse owner) {
        this.owner = owner;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ManufacturerResponse getManufacturer() {
        return manufacturer;
    }

    public UserResponse getOwner() {
        return owner;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ToyResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer=" + (manufacturer == null ? "NULL" : manufacturer.getName()) +
                ", owner=" + owner.getUsername() +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                '}';
    }
}
