package com.finals.carrentapi.service;

import com.finals.carrentapi.model.Car;
import com.finals.carrentapi.payload.request.ChooseOrderRequest;
import com.finals.carrentapi.payload.request.FinalOrderRequest;

import java.io.IOException;
import java.util.List;


public interface MyBatisService {
    void insertCar(Car car) throws IOException;

    List<Car> getAvailableCars() throws IOException;

    List<Car> getAvailableCarsByCity(String city) throws IOException;

    Car updateCar(long id, Car car) throws IOException;

    void deleteCar(long id) throws IOException;

    Car getCarById(ChooseOrderRequest chooseOrderRequest) throws IOException;

    Car checkPaymentStatusByIdCar(long idCar) throws IOException;

    void updateFinalCar(FinalOrderRequest finalOrderRequest) throws IOException;
}
