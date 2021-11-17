package com.pyramidAcademy.pyramidAir.boardingPass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pyramidAcademy.pyramidAir.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "boardingPass")
public class BoardingPass {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "number")
    private UUID number;

    @Size(min=3)
    @Column(name = "destination")
    private String destination;

    @Column(name = "origin")
    private String origin;

    @Column(name="date")
    private LocalDate date;

    @Column(name = "eta")
    private LocalTime eta;

    @Column(name="departureTime")
    private LocalTime departureTime;

    @Column(name="price")
    private double price;

   @ManyToOne
   @JoinColumn(name="UserID")
   private User user;


    public BoardingPass(){}

    public long getId() {
        return id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }
    @JsonProperty
    public void setUser(User user) {
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public UUID getNumber() {
        return number;
    }

    @JsonIgnore
    public void setNumber(UUID number) {
        this.number = number;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEta() {
        return eta;
    }

    public void setEta(LocalTime eta) {
        this.eta = eta;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
