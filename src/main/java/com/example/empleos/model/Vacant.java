package com.example.empleos.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vacant {
    private Integer id;
    private String name;
    private String description;
    private Date date;
    private Double salary;
    private boolean outstanding; // Empleo Destacado
    private String image;

    public Vacant(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            setDate(sdf.parse(sdf.format( new Date())));
            setImage("no-image.png");
            setOutstanding(false);
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isOutstanding() {
        return outstanding;
    }

    public String getImage() {
        return image;
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

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Vacant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", salary=" + salary +
                '}';
    }
}
