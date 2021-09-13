package com.finals.carrentapi.payload.request;

import com.finals.carrentapi.enums.CarStatus;
import com.finals.carrentapi.enums.CarType;
import com.finals.carrentapi.enums.Driver;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class FinalOrderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCar;
    private String brand;
    private String carName;
    private CarType carType;
    private Long amountOfPassenger;
    private Long amountOfLuggage;
    private Long pricePerDay;
    private Driver driver;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private String city;
    private String vendorName;
    private CarStatus carStatus;
    private Double rating;
    private Integer baseRating = 10;
    private boolean cleanTrip;
    private boolean refund;
    private boolean reschedule;

    private String review;

    private String paymentStatus;
    private String imageLink;

    private Integer isDeleted = 0;
}
