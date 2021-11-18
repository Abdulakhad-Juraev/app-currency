package com.example.demo.service;

import com.example.demo.entity.ValCurs;
import com.example.demo.entity.Valute;
import com.example.demo.payload.ResPageable;
import com.example.demo.payload.ValCursDto;
import com.example.demo.payload.ValuteDto;
import com.example.demo.repository.ValCursRepository;
import com.example.demo.repository.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValCursService {
    final
    ValuteRepository valuteRepository;
    final
    ValCursRepository valCursRepository;
    final
    ValuteService valuteService;

    public ValCursService(ValuteRepository valuteRepository, ValCursRepository valCursRepository, ValuteService valuteService) {
        this.valuteRepository = valuteRepository;
        this.valCursRepository = valCursRepository;
        this.valuteService = valuteService;
    }

    // CREATE
    public ValCurs saveAllData(ValCursDto valCursDto) {
        try {
            ValCurs valCurs = new ValCurs();
            valCurs.setName(valCursDto.getName());
            valCurs.setDate(valCursDto.getDate());
            List<Valute> valuteList = new ArrayList<>();
            for (ValuteDto valuteDto : valCursDto.getValute()) {
                valuteList.add(new Valute(
                        valuteDto.getIden(),
                        valuteDto.getCharCode(),
                        valuteDto.getValue(),
                        valuteDto.getID(),
                        valuteDto.getNominal(),
                        valuteDto.getNumCode(),
                        valuteDto.getName())
                );
            }
            valCurs.setValute(valuteRepository.saveAll(valuteList));
            return valCursRepository.save(valCurs);
        } catch (Exception e) {
            return null;
        }
    }


    // GET_VALUE_BY_CODE
    public String getCurrencyByCode(String code) {
        return valuteRepository.getOneValute(code);
    }

    // GET_ALL
    public ResPageable getList(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ValCurs> allValCurs = valCursRepository.findAll(pageable);
            return new ResPageable(
                    page,
                    size,
                    allValCurs.getTotalElements(),
                    allValCurs.getTotalPages(),
                    allValCurs.getContent().stream().map(this::getOne).collect(Collectors.toList()));
        } catch (Exception e) {
            return null;
        }
    }
    // Get_One_Val_Curs
    public ValCursDto getOne(ValCurs valCurs) {
        return new ValCursDto(
                valCurs.getId(),
                valCurs.getName(),
                valCurs.getDate(),
                valCurs.getValute().stream().map(valuteService::getOneValute).collect(Collectors.toList()));
    }

}
