package com.numbervalidator.exercise.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private String countryName;
    private String countryCode;
    private String pattern;
}
