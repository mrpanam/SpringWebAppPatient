package com.example.yousfi_patient.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity @Data @NoArgsConstructor @ToString @AllArgsConstructor
public class Patient {
    @Id @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 2,max = 15)
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    @DecimalMin("1")
    private int dept;
    private boolean malade;

}
