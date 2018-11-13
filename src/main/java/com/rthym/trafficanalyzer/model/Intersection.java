package com.rthym.trafficanalyzer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Intersection {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) Integer id;
    private String intersectionId;
    private  @Enumerated(EnumType.STRING) @Column(length = 25) Direction direction;
    private  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinTable(name = "Lane_intersection",
            joinColumns = @JoinColumn(name = "lane_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "intersection_id", referencedColumnName ="id" )) Set<Lane> lanes;
    private //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "dd/MM/yyyy") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") Date date;

    enum Direction {
        // Direction in 4 co-ordinates.
        EastBound,
        WestBound,
        NorthBound,
        SouthBound

    }
}
