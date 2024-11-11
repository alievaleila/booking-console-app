package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.AlreadyExistsException;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.PassengerRequestDto;
import az.edu.turing.model.dto.PassengerResponseDto;
import az.edu.turing.service.PassengerService;

public class PassengerServiceImpl implements PassengerService {

    public final PassengerDao passengerDao;
    public final PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerDao passengerDao, PassengerMapper passengerMapper) {
        this.passengerDao = passengerDao;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public PassengerResponseDto createPassenger(PassengerRequestDto passengerRequestDto) {
        final String id = passengerRequestDto.getId();
        if (passengerDao.existsById(id)){
            throw new AlreadyExistsException("Passenger already exists");
        }

        PassengerEntity savedEntity = passengerDao.save(passengerMapper.toEntity(passengerRequestDto));
        return new PassengerResponseDto(savedEntity.getName(), savedEntity.getSurname());
    }
}
