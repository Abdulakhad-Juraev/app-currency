package com.example.demo.service;

import com.example.demo.entity.Valute;
import com.example.demo.payload.ValuteDto;
import org.springframework.stereotype.Service;

@Service
public class ValuteService {

    //Get_One_Valute
    public ValuteDto getOneValute(Valute valute) {
        return new ValuteDto(
                valute.getIden(),
                valute.getCharCode(),
                valute.getValue(),
                valute.getID(),
                valute.getNominal(),
                valute.getNumCode(),
                valute.getName()
        );
    }
}
