package com.numbervalidator.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String customerName;
    private String phoneNumber;
    private String countryCode;
    private String countryName;
    private boolean isValid;
}
