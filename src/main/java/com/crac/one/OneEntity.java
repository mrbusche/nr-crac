package com.crac.one;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OneEntity {
    @Id
    private long id;
}
