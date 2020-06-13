package com.example.empleos.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="Vacants")
public class Vacant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Date dateRelease;
    private Double salary;
    private boolean outstanding; // Empleo Destacado
    private String image;
    private String status;
    private String details;
    //@Transient //Ignora el mapeo par ael siguiente atributo
    @OneToOne
    @JoinColumn(name = "idCategory")
    private Category category;



    public Vacant() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            setDateRelease(sdf.parse(sdf.format(new Date())));
            setImage("no-image.png");
            setOutstanding(false);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isOutstanding() {
        return outstanding;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }



    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(boolean outstanding) {
        this.outstanding = outstanding;
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

    public Date getDateRelease() {
        return dateRelease;
    }

    private void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Vacant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateRelease=" + dateRelease +
                ", salary=" + salary +
                ", outstanding=" + outstanding +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", details='" + details + '\'' +
                ", category=" + category +
                '}';
    }
}
