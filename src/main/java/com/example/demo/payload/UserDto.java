package com.example.demo.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String name;
    private String username;

    public UserDto(UUID id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

}
