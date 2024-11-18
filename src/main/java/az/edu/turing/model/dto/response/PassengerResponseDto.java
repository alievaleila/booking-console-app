package az.edu.turing.model.dto.response;

public class PassengerResponseDto {
    private String name;
    private String surname;

    public PassengerResponseDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
