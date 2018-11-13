package com.rthym.trafficanalyzer.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Lane {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    private String phase;
    private @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinTable(name = "Lane_Vehicle",
            joinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lane_id", referencedColumnName ="id" )) Set<Vehicle> vehicles;
}
