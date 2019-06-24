package com.limonnana.web.rest;


import com.google.gson.Gson;
import com.limonnana.domain.MonthArrayOrder;
import com.limonnana.domain.MonthList;
import com.limonnana.domain.MonthListDTO;
import com.limonnana.monthFactory.MonthCreator;
import com.limonnana.monthFactory.MonthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MonthController {

    @Autowired
   MonthCreator monthCreator;

    @Autowired
    MonthUtils monthUtils;


    @GetMapping("/month")
    ResponseEntity<String>  getMonths(){

        MonthListDTO mDTO = monthCreator.getMockTestListReservations();

        Gson gson = new Gson();

        String result = gson.toJson(mDTO);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/nextMonth/{month}/{year}")
    ResponseEntity<String>  getNextMonths(@PathVariable String month, @PathVariable int year){

        Month m = Month.valueOf(month);
        int monthInt = m.getValue();
        LocalDateTime ld = LocalDateTime.of(year,m, 1, 1, 1);
        LocalDateTime nextMonth = ld.plusMonths(1);

        MonthListDTO mDTO = monthCreator.getMockTestListReservationsNext(nextMonth);


       // if(month.equals(Month.DECEMBER.toString())) {
       //     mDTO.setYear(year);
       // }


       // mDTO.setName(nextMonth.getMonth());

        Gson gson = new Gson();

        String result = gson.toJson(mDTO);

        return ResponseEntity.ok().body(result);
    }
}
