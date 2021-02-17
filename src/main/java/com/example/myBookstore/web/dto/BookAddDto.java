package com.example.myBookstore.web.dto;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookAddDto {

    @Size(min = 1, max = 50, message = "Book name length between 1 and 50")
    @NotEmpty
    private String name;

    @Size(min = 5, max = 50, message = "Book description length between 5 and 50")
    @NotEmpty
    private String description;

    @DecimalMin(value = "5.00", message = "Book price must be equal or greater than 5 PLN")
    private double price;

    public BookAddDto(){}

    public BookAddDto(String name, String description, double price) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
