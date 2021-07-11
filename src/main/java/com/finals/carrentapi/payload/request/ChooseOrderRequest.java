package com.finals.carrentapi.payload.request;

import com.finals.carrentapi.enums.Driver;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChooseOrderRequest {

    private Long idCar;
    private Driver driver;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private String city;
}
