package com.finals.carrentapi.service;

import com.finals.carrentapi.model.Car;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface MyBatisService {
    void insertCar(Car car) throws IOException;

    List<Car> getAvailableCars() throws IOException;

    Car updateCar(long id, Car car) throws IOException;

    void deleteCar(long id) throws IOException;
}
