package com.tekup.travel_agency.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "voyage_id")
    private Voyage voyage;

    @Temporal(TemporalType.DATE)
    private Date reservationDate;

    // Constructors, getters, and setters
}

