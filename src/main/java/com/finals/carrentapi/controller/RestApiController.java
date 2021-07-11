package com.finals.carrentapi.controller;

import com.finals.carrentapi.enums.Driver;
import com.finals.carrentapi.model.Car;
import com.finals.carrentapi.payload.request.ChooseOrderRequest;
import com.finals.carrentapi.payload.request.PaymentOrder;
import com.finals.carrentapi.payload.response.ChooseOrderResponse;
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

    //--------------------------------------------------------------------------------------

    @PostMapping("/chooseFirstOrder")
    public List<Car> getCarListFirstOrder(@RequestBody ChooseOrderRequest request) throws IOException {

        List<Car> cars = myBatisService.getAvailableCarsByCity(request.getCity());

        return cars;
    }

    @PostMapping("/chooseFirstOrderById")
    public Car getCarListFirstOrderById(@RequestBody ChooseOrderRequest request) throws IOException {
        Car car =myBatisService.getCarById(request);
        return car;
    }

    @PostMapping("/paymentOrderCar")
    public String paymentOrder(@RequestBody PaymentOrder paymentOrder){
        //post final order

        //delay
        delay();
        //find payment status

        //return


        return null;
    }

    public static void delay(){
        try{
            Thread.sleep(30000);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
