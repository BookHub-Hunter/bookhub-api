package com.ingsw.bookhubapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "collection_books")
@IdClass(CollectionBookPK.class)
public class CollectionBook {
    @Id
    private Integer book;
    @Id
    private Integer collection;

    @Column(name = "added_date", nullable = false)
    private LocalDateTime addedDate;
}
