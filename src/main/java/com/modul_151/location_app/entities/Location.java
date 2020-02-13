package com.modul_151.location_app.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;
    private String description;
    private Date date;
    String location;

// https://en.wikibooks.org/wiki/Java_Persistence/ManyToOne

    public Location() {
    }

    public Location(long id, User user, String description, Date date, String location) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.date = date;
        this.location = location;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User username) {
        this.user = username;
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

    public void setDate(Date targetDate) {
        this.date = targetDate;
    }

    public String isDone() {
        return location;
    }

    public void setDone(String done) {
        location = done;
    }


    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", username='" + user + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + date +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Location location = (Location) obj;

        return id == location.id;
    }
}