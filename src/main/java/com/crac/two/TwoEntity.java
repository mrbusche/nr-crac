package com.crac.two;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TwoEntity {
    @Id
    private long id;
}
