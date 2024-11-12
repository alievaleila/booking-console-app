package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.AlreadyExistsException;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.PassengerResponseDto;
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
        if (passengerDao.existsById(passengerRequestDto.getId())){
            throw new AlreadyExistsException("Passenger already exists");
        }

        PassengerEntity savedEntity = passengerDao.create(passengerMapper.toEntity(passengerRequestDto));
        return new PassengerResponseDto(savedEntity.getName(), savedEntity.getSurname());
    }
}
