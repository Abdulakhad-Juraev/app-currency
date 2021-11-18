package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValuteDto {
   private Long iden;
   private String CharCode;
   private String Value;
   private String ID;
   private Integer Nominal;
   private String NumCode;
   private String Name;
}
