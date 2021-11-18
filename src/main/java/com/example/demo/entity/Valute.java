package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Valute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iden;

    private String CharCode;
    private String Value;
    private String ID;
    private Integer Nominal;
    private String NumCode;
    private String Name;
}
