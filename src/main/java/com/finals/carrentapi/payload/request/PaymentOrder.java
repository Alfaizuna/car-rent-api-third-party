package com.finals.carrentapi.payload.request;

import lombok.Data;

@Data
public class PaymentOrder {

    private Long id;
    private Long idOrder;
    private String brand;
    private String carName;
    private String paymentStatus;
    private String paymentMethod;
    private Long pricePerDay;

}
