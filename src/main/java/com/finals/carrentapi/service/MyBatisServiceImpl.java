package com.finals.carrentapi.service;

import com.finals.carrentapi.model.Car;
import com.finals.carrentapi.util.CommonMessage;
import com.finals.carrentapi.util.MapperUtil;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

@Service
public class MyBatisServiceImpl implements MyBatisService{
    private SqlSession session;

    public void connectMyBatis() throws IOException {
        Reader reader = Resources.getResourceAsReader(CommonMessage.SQL);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    @Override
    public void insertCar(Car car) throws IOException {
        connectMyBatis();

        car.setStartDateAndTime(Date.from(Instant.now()));
        car.setEndDateAndTime(Date.from(Instant.now()));

        session.insert(CommonMessage.INSERT_CAR, car);
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
    public Car updateCar(long id, Car car) throws IOException {
        connectMyBatis();

        //select a particular student using id
        Car getCarById = (Car) session.selectOne("Car.getById", id);
        System.out.println(getCarById.toString());

        //Set new values to the mail and phone number of the student
//        Car car2 = MapperUtil.parse(getCarById, Car.class, MatchingStrategies.STRICT);
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
        getCarById.setStartDateAndTime(Date.from(Instant.now()));
        getCarById.setEndDateAndTime(Date.from(Instant.now()));
        //Update the student record
        session.update("Car.updateCar",getCarById);
        System.out.println("Record updated successfully");
        session.commit();
        session.close();
        return getCarById;
        //verifying the record
//        Car c = (Car) session.selectOne("Student.getById", id);
//        System.out.println("Details of the student after update operation" );
//        System.out.println(c.toString());
//        session.commit();
//        session.close();
//
//        session.commit();
//        session.close();
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
