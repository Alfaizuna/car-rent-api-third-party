package com.finals.carrentapi.controller;

import com.finals.carrentapi.model.Car;
import com.finals.carrentapi.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rent")
public class RestApiController {

    @Autowired
    MyBatisService myBatisService;

    @PostMapping("/addCar")
    public Car addCar(@RequestBody Car car) throws IOException {
        myBatisService.insertCar(car);
        return car;
    }

    @GetMapping("/getAvailableCars")
    public List<Car> getAvailableCars() throws IOException {
        List<Car> cars = myBatisService.getAvailableCars();
        return cars;
    }

    @PostMapping("/updateCar/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Car car) throws IOException {
        Car c = myBatisService.updateCar(id, car);
        return c;
    }

    @DeleteMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable("id") Long id) throws IOException {
        myBatisService.deleteCar(id);
        return "Data has been deleted!";
    }
}
