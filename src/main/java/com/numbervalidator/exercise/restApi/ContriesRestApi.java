package com.numbervalidator.exercise.restApi;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContriesRestApi {

    @Operation(summary  = "get Contries names from the saved memory")
    public ResponseEntity<List<String>> getCountriesList();
}
