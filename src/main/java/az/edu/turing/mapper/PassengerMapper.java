package az.edu.turing.mapper;

import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.model.dto.PassengerRequestDto;

public class PassengerMapper implements EntityMapper<PassengerEntity, PassengerRequestDto>{
    @Override
    public PassengerEntity toEntity(PassengerRequestDto passengerRequestDto) {
        return new PassengerEntity(
                passengerRequestDto.getName(),
                passengerRequestDto.getSurname()
        );

    }

    @Override
    public PassengerRequestDto toDto(PassengerEntity passengerEntity) {
        return new PassengerRequestDto(
                passengerEntity.getName(),
                passengerEntity.getSurname()
        );
    }
}
