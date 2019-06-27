package com.limonnana.monthFactory;

import com.limonnana.domain.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class MonthUtils {

    /*
    EntityOnCalendar saveEntity(int from, int untill, int year, int eFrom, int eUntill, Month name, long userId){

        //get calendar from database, (if == null)
        MonthList monthInstance = new MonthList(from, untill, year, name);
        monthInstance.setFrom(eFrom);
        createEmptyList(monthInstance);
        // get User from database ? Shoudn't be necesary check dont genarate or update with null values
        User u = new User();
        u.setId(userId);

        Reservation reservation = new Reservation();
        reservation.setUser(u);

        LocalDate ldFrom = LocalDate.of(year, name, eFrom);
        LocalDate ldUntill = LocalDate.of(year, name, eUntill);
        reservation.from(ldFrom);
        reservation.untill(ldUntill);

        EntityOnCalendar e = new EntityOnCalendar();
        e.setReservation(reservation);

        for(int i=eFrom;i<eUntill;i++) {
            monthInstance.getM().get(i).getList().add(e);
        }

        // call service and save all ( reservation, monthList SYNCRONIZE AVOID TWO CALENDARS SAME MONTH YEAR

        return e;
    }

     */

    public MonthList getCurrentMonth() {
        MonthList month = new MonthList();
        LocalDateTime now = LocalDateTime.now();
        int yearCurrent = now.getYear();
        int day = now.getDayOfMonth();
        month.setName(now.getMonth());
        month.setFrom(day);
        month.setUntill(LocalDate.now().lengthOfMonth());
        month.setYear(yearCurrent);

        return month;
    }


    public MonthList getMonthList(LocalDateTime day){

        MonthList monthList = new MonthList();
        LocalDateTime now = day;
        LocalDate theTime = day.toLocalDate();
        int year = now.getYear();
        monthList.setName(now.getMonth());
        monthList.setFrom(1);
        monthList.setUntill(theTime.lengthOfMonth());

        monthList.setYear(year);

        return monthList;
    }

    public void createEmptyList(MonthList monthList) {
        for (int i = monthList.getFrom(); i <= monthList.getUntill(); i++) {
            List<UnitOfCalendar> unitsList = new ArrayList<>();
            ListWrapper lw = new ListWrapper();
            lw.setList(unitsList);
            monthList.getM().put(i, lw);
        }
    }

    public MonthArrayOrder getArrayOrderMonth(LocalDateTime day, int amountMaximunUnitsPerDay){

        MonthArrayOrder monthArrayOrder = new MonthArrayOrder();
        LocalDateTime now = day;
        int yearCurrent = now.getYear();
        monthArrayOrder.setName(now.getMonth());
        monthArrayOrder.setFrom(1);
        monthArrayOrder.setUntill(LocalDate.now().lengthOfMonth());
        monthArrayOrder.setYear(yearCurrent);

        for(int i = 1; i<= monthArrayOrder.getUntill(); i++){
            UnitOfCalendar[] units = new UnitOfCalendar[amountMaximunUnitsPerDay];
            monthArrayOrder.getM().put(i, units);
        }

        return monthArrayOrder;
    }

    public LocalDateTime getMonthForward(int howManyMonthForward){

        LocalDateTime firstDayNextMonth = getNextMonth();
        int month = firstDayNextMonth.getMonth().getValue();
        int nextMonth = month + 1;
        // firstDayNextMonth.
        return firstDayNextMonth.plusMonths(howManyMonthForward);
    }

    public LocalDateTime getLocalDateTime(Month m, int year, int day){
        LocalDateTime ld = LocalDateTime.of(year,m, day, 1, 1);
        return ld;
    }

    public LocalDateTime getNextMonth(){
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int untill = LocalDate.now().lengthOfMonth();
        int toEndOfThisMonth = (untill - day) + 1;

        return now.plusDays(toEndOfThisMonth);
    }

    public MonthListDTO toMonthListDTO(MonthList monthList) {
        MonthListDTO mDTO = new MonthListDTO();
        mDTO.setId(monthList.getId());
        mDTO.setFrom(monthList.getFrom());
        mDTO.setUntill(monthList.getUntill());
        mDTO.setName(monthList.getName());
        mDTO.setYear((monthList.getYear()));
        Map<Integer, ListWrapper> m = monthList.getM();

        LocalDateTime ld = getLocalDateTime(monthList.getName(),monthList.getYear(),monthList.getFrom());

        for (Map.Entry<Integer, ListWrapper> entry : m.entrySet()) {

            Integer keyMap =  entry.getKey();
            ListWrapper ls = entry.getValue();
            MonthListDTOMapKey mapKey = new MonthListDTOMapKey();
            mapKey.setDayNumber(keyMap);
            mapKey.setDayName(ld.getDayOfWeek().toString().toLowerCase());
            mDTO.getM().put(mapKey, ls.getList());
            ld = ld.plusDays(1);
        };

        return mDTO;
    }
}
