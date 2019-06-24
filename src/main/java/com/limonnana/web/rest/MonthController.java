package com.limonnana.web.rest;


import com.google.gson.Gson;
import com.limonnana.domain.MonthArrayOrder;
import com.limonnana.domain.MonthList;
import com.limonnana.domain.MonthListDTO;
import com.limonnana.monthFactory.MonthCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MonthController {

    @Autowired
   MonthCreator monthCreator;


    @GetMapping("/month")
    ResponseEntity<String>  getMonths(){

        MonthListDTO mDTO = monthCreator.getMockTestListReservations();

        Gson gson = new Gson();

        String result = gson.toJson(mDTO);

        return ResponseEntity.ok().body(result);
    }
}
