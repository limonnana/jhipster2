package com.limonnana.web.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.limonnana.domain.MonthArrayOrder;
import com.limonnana.domain.MonthDTO;
import com.limonnana.domain.MonthList;
import com.limonnana.domain.MonthListDTO;
import com.limonnana.monthFactory.MonthCreator;
import com.limonnana.monthFactory.MonthUtils;
import com.limonnana.repository.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MonthController {

    @Autowired
    private MonthRepository monthRepository;

    @Autowired
   private MonthCreator monthCreator;

    @Autowired
    private MonthUtils monthUtils;

    protected Gson gson;

    @PostMapping("/addEntity")
    ResponseEntity<String> saveEntity(@RequestBody MonthDTO monthDTO){

        MonthList monthList = monthRepository.save(monthUtils.fromMonthDTO(monthDTO));

        MonthListDTO mDTO = monthUtils.toMonthListDTO(monthList);

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

        String result = gson.toJson(mDTO);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/month")
    public ResponseEntity<String>  getMonths(){

        MonthListDTO mDTO = monthCreator.getMockTestListReservations();

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

        String result = gson.toJson(mDTO);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/nextMonth/{month}/{year}/{direction}")
    ResponseEntity<String>  getNextMonths(@PathVariable String month, @PathVariable int year, @PathVariable int direction){

        Month m = Month.valueOf(month);
        LocalDateTime ld = monthUtils.getLocalDateTime(m, year, 1);
        LocalDateTime nextMonth = ld.plusMonths(1);
         if(direction == -1){
             nextMonth = ld.minusMonths(1);
        }

        MonthListDTO mDTO = monthCreator.getMockTestListReservationsNext(nextMonth);

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String result = gson.toJson(mDTO);

        return ResponseEntity.ok().body(result);
    }
}
