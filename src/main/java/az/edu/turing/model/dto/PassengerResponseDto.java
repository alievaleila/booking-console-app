package az.edu.turing.model.dto;

import java.util.UUID;

public class PassengerResponseDto {
    private String name;
    private String surname;

    public PassengerResponseDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
