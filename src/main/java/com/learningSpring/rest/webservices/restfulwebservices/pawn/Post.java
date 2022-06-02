package com.learningSpring.rest.webservices.restfulwebservices.pawn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Pawn pawn;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Pawn getPawn() {
        return pawn;
    }
}
