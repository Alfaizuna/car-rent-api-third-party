package com.finals.carrentapi.model;


import com.finals.carrentapi.enums.CarSatus;
import com.finals.carrentapi.enums.CarType;
import com.finals.carrentapi.enums.Driver;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idCar;
    private String brand;
    private String carName;
    private CarType carType;
    private Long amountOfPassenger;
    private Long amountOfLuggage;
    private Long pricePerDay;
    private Driver driver;
    private Date startDateAndTime;
    private Date endDateAndTime;
    private String city;
    private String vendorName;
    private CarSatus carStatus;
    private Integer isDeleted = 0;


}
