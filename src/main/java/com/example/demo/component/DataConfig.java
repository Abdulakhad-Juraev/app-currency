package com.example.demo.component;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ValCursDto;
import com.example.demo.service.ValCursService;
import com.example.demo.utils.MessageConst;
import com.google.gson.Gson;
import kong.unirest.Unirest;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Bean
    CommandLineRunner runner(ValCursService valCursService) {
        return args -> {
            // read json and write to db
            try {
                JSONObject json = XML.toJSONObject(Unirest.get("http://www.cbr.ru/scripts/XML_daily.asp").asString().getBody());
                Gson gson = new Gson();
                ValCursDto valCursDto = gson.fromJson(json.get("ValCurs").toString(), ValCursDto.class);
                valCursService.saveAllData(valCursDto);
                System.out.println(new ApiResponse(MessageConst.SAVED_MESSAGE,true));
            } catch (Exception e) {
                System.out.println(new ApiResponse(MessageConst.TRY_ERROR_MESSAGE + e.getMessage(),false));
            }
        };
    }
}
