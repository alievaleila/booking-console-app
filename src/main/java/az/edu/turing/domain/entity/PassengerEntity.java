package az.edu.turing.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class PassengerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String surname;

    private static long idCounter = 1;

    public PassengerEntity() {
    }

    public PassengerEntity(Long id) {
        this.id = id;
    }

    public PassengerEntity(String name, String surname) {
        this.id = idCounter++;
        this.name = name;
        this.surname = surname;
    }

    public PassengerEntity(Long id, String name, String surname) {
        this.id = id;
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PassengerEntity that = (PassengerEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }


    @Override
    public String toString() {
        return String.format("PassengerEntity{id='%s', name='%s', surname='%s'}", id, name, surname);
    }
}
