package com.finals.carrentapi.service;

import com.finals.carrentapi.enums.CarStatus;
import com.finals.carrentapi.enums.Driver;
import com.finals.carrentapi.model.Car;
import com.finals.carrentapi.payload.request.ChooseOrderRequest;
import com.finals.carrentapi.payload.request.FinalOrderRequest;
import com.finals.carrentapi.util.CommonMessage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class MyBatisServiceImpl implements MyBatisService {
    private SqlSession session;

    public void connectMyBatis() throws IOException {
        Reader reader = Resources.getResourceAsReader(CommonMessage.SQL);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    @Override
    public void insertCar(Car car) throws IOException {
        connectMyBatis();

//        car.setStartDateAndTime(Date.from(Instant.now()));
//        car.setEndDateAndTime(Date.from(Instant.now()));
        car.setDriver(null);
        car.setCarStatus(CarStatus.AVAILABLE);
        car.setPaymentStatus(null);
        car.setReview(null);
        car.setCleanTrip(true);
        car.setRefund(true);
        car.setReschedule(true);
        car.setRating(10.0);
        car.setBaseRating(10);
        car.setImageLink(null);

        session.insert(CommonMessage.INSERT_CAR, car);
        session.commit();
        session.close();
    }

    @Override
    public Car getCarById(ChooseOrderRequest chooseOrderRequest) throws IOException {
        connectMyBatis();

//        List<Car> cars = session.selectList(CommonMessage.GET_AVA_CAR, CommonMessage.AVAILABLE);
        Car car = session.selectOne("Car.getById", chooseOrderRequest.getIdCar());
        car.setDriver(chooseOrderRequest.getDriver());
        car.setStartDateAndTime(chooseOrderRequest.getStartDateAndTime());
        car.setEndDateAndTime(chooseOrderRequest.getEndDateAndTime());

        session.commit();
        session.close();
        return car;
    }

    @Override
    public Car checkPaymentStatusByIdCar(long idCar) throws IOException {
        connectMyBatis();
        Car car = session.selectOne("Car.checkPaymentStatusByIdCar", idCar);
        session.commit();
        session.close();
        return car;
    }

    @Override
    public void updateFinalCar(FinalOrderRequest finalOrderRequest) throws IOException {
        connectMyBatis();

        session.update("Car.updateFinal", finalOrderRequest);

        session.commit();
        session.close();
    }

    @Override
    public List<Car> getAvailableCars() throws IOException {
        connectMyBatis();

        List<Car> cars = session.selectList(CommonMessage.GET_AVA_CAR, CommonMessage.AVAILABLE);

        session.commit();
        session.close();
        return cars;
    }

    @Override
    public List<Car> getAvailableCarsByCity(String city) throws IOException {
        connectMyBatis();

        List<Car> cars = session.selectList(CommonMessage.GET_AVA_CAR_BY_CITY, city);
//        for(Car car : cars){
//            car.setDriver(driver);
//        }

        session.commit();
        session.close();
        return cars;
    }

    @Override
    public Car updateCar(long id, Car car) throws IOException {
        connectMyBatis();

        //select a particular car using id
        Car getCarById = (Car) session.selectOne("Car.getById", id);
        System.out.println(getCarById.toString());

        //Set new values of the car
        getCarById.setIdCar(id);
        getCarById.setBrand(car.getBrand());
        getCarById.setCarName(car.getCarName());
        getCarById.setCarType(car.getCarType());
        getCarById.setAmountOfPassenger(car.getAmountOfPassenger());
        getCarById.setAmountOfLuggage(car.getAmountOfLuggage());
        getCarById.setPricePerDay(car.getPricePerDay());
        getCarById.setDriver(car.getDriver());
        getCarById.setCity(car.getCity());
        getCarById.setVendorName(car.getVendorName());
        getCarById.setCarStatus(car.getCarStatus());
        getCarById.setIsDeleted(0);
        getCarById.setStartDateAndTime(car.getStartDateAndTime());
        getCarById.setEndDateAndTime(car.getEndDateAndTime());

        //Update the student record
        session.update("Car.updateCar", getCarById);
        System.out.println("Record updated successfully");
        session.commit();
        session.close();
        return getCarById;
    }

    public void deleteCar(long id) throws IOException {
        connectMyBatis();
        //Delete operation
        session.delete("Car.deleteById", id);
        session.commit();
        session.close();
        System.out.println("Record deleted successfully");
    }

}
