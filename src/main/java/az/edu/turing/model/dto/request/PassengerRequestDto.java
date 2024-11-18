package az.edu.turing.model.dto.request;

import java.util.UUID;

public class PassengerRequestDto {

    private Long id;
    private String name;
    private String surname;

    private static long idCounter = 1;

    public PassengerRequestDto() {
    }

    public PassengerRequestDto(Long id) {
        this.id = id;
    }

    public PassengerRequestDto(Long id, String name, String surname) {
        this.id = idCounter++;
        this.name = name;
        this.surname = surname;
    }

    public PassengerRequestDto(String name, String surname) {
        this.id = idCounter++;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "PassengerRequestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}


