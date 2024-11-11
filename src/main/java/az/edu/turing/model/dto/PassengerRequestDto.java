package az.edu.turing.model.dto;

import java.util.UUID;

public class PassengerRequestDto {

    private final String id;
    private String name;
    private String surname;

    public PassengerRequestDto(String name, String surname) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.surname = surname;
    }

    public PassengerRequestDto(String id, String name, String surname) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
