package com.example.enjoypadelapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "center")
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private int capacity;
    @Column(name = "changing_rooms")
    private boolean changingRooms;
    @Column (name = "subscription_price")
    private float subscriptionPrice;

    @OneToMany(mappedBy = "center")
    private List<Court> courts;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;
}
