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
@Entity(name = "courts")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String surface;
    @Column
    private boolean indoor;
    @Column
    private boolean lighting;
    @Column
    private boolean glass;
    @Column
    private String partner;

    @OneToMany (mappedBy = "court")
    @JsonBackReference
    private List<Match> matches;

    @ManyToOne
    @JoinColumn (name = "sport_center")
    @JsonBackReference
    private Center center;
}
