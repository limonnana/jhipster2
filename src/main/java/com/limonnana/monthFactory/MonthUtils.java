package com.limonnana.monthFactory;

import com.limonnana.domain.*;
import com.limonnana.repository.ListWrapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;


@Component
public class MonthUtils {


    @Autowired
    ListWrapperRepository listWrapperRepository;



    private int createIdListWraper(int year, int month, int day){
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        if(month<10){
            sb.append("0");
        }
        sb.append(month);
        if(day<10){
            sb.append("0");
        }
        sb.append(day);
        String idListWrapperS = sb.toString();
        int idListWrapper = Integer.valueOf(idListWrapperS);
        return idListWrapper;
    }

    ListWrapper getListFromListWrapper( int idListWrapper){
        ListWrapper listWrapper = null;
        List<UnitOfCalendar> list = null;
        Optional<ListWrapper> listWrapperOptional = listWrapperRepository.findById(idListWrapper);
        if(!listWrapperOptional.isPresent()) {
            listWrapper = new ListWrapper();
            listWrapper.setId(idListWrapper);
            list = new ArrayList<>();
            listWrapper.setList(list);
        }else{
            listWrapper = listWrapperRepository.findById(idListWrapper).get();
        }
        return listWrapper;
    }

    public void saveEntity(MonthDTO monthDTO){
        ListWrapper listWrapper = null;
        int day = monthDTO.getDay();
        Month m = Month.valueOf(monthDTO.getName().toUpperCase());
        int month = m.getValue();
        int year = monthDTO.getYear();
        int idListWrapper = createIdListWraper(year, month, day);
        listWrapper = getListFromListWrapper(idListWrapper);
        List list = listWrapper.getList();
            UnitOfCalendar uc = new UnitOfCalendar();
            uc.setUserId(monthDTO.getUserLogin());
            list.add(uc);
            listWrapper.setList(list);
        listWrapperRepository.save(listWrapper);
    }

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

    public void getTrueList(MonthList monthList) {
        for (int i = monthList.getFrom(); i <= monthList.getUntill(); i++) {
            List<UnitOfCalendar> unitsList = new ArrayList<>();
            int idListWrapper = createIdListWraper(monthList.getYear(), monthList.getName().getValue(), i);
            ListWrapper lw = getListFromListWrapper(idListWrapper);
            monthList.getM().put(i, lw);
        }
    }
/*
    public void createEmptyList(MonthList monthList) {
        for (int i = monthList.getFrom(); i <= monthList.getUntill(); i++) {
            List<UnitOfCalendar> unitsList = new ArrayList<>();
            ListWrapper lw = new ListWrapper();
            lw.setList(unitsList);
            monthList.getM().put(i, lw);
        }
    }
*/
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

    public MonthList fromMonthDTO(MonthDTO monthDTO){

       MonthList ml = new MonthList();

        if(monthDTO.getId().intValue() == 0){
            ml.setId(null);
        }else{
            ml.setId(monthDTO.getId());
        }

        ml.setName(Month.valueOf(monthDTO.getName()));
        ml.setYear(monthDTO.getYear());
        ml.setFrom(monthDTO.getFrom());
        ml.setUntill(monthDTO.getUntill());
        Map<Integer, ListWrapper> m = new TreeMap<>();
        ListWrapper lw = new ListWrapper();
        List<UnitOfCalendar> list = new ArrayList<>();
        UnitOfCalendar u = new UnitOfCalendar();
        u.setUserId(monthDTO.getUserLogin());
        list.add(u);
        lw.setList(list);
        m.put(monthDTO.getDay(), lw);

        return ml;
    }
}
