package com.example.demo.repository;

import com.example.demo.entity.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValuteRepository extends JpaRepository<Valute,Long> {
    @Query(nativeQuery = true,value = "SELECT value FROM valute WHERE lower(char_code) = lower(:data) ORDER BY iden DESC LIMIT 1")
    String getOneValute(String data);
}
