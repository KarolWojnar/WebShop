package com.webshop.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Code {

    @Id
    @Column(name = "code_id")
    private Long id;
    boolean isActive;
    double discount;
    String code;
}
