package com.rthym.trafficanalyzer.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Vehicle{
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    private @Enumerated(EnumType.STRING) @Column(length = 25) VehicleType type;
    private double speed;
    private String intersectionLeft;

    enum VehicleType {
        Car,
        Truck,
        Cycle
        // More Vehicle types to be added in future
    }
}
