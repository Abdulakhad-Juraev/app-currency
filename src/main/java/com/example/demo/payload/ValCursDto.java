package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValCursDto {
    private Long id;
    private String name;
    private String Date;
    private List<ValuteDto> Valute;
}
