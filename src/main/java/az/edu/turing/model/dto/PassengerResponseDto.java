package az.edu.turing.model.dto;

import java.util.UUID;

public class PassengerResponseDto {
    private final String id;
    private String name;
    private String surname;

    public PassengerResponseDto(String name, String surname) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.surname = surname;
    }
}
