package com.limonnana.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.time.Month;

@Entity
@Table(name = "month_list")
public class MonthList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name", columnDefinition = "smallint")
    @Enumerated
    private Month name;
    private int year;
    private int from;
    private int untill;

    @ElementCollection
    private Map<Integer, ListWrapper> m = new TreeMap<>();

    public MonthList(){}

    public MonthList(int from, int untill, int year, Month name){

        this.name = name;
        this.from = from;
        this.untill = untill;
        this.year = year;
    }

    public Month getName() {
        return name;
    }

    public void setName(Month name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getUntill() {
        return untill;
    }

    public void setUntill(int untill) {
        this.untill = untill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Map<Integer, ListWrapper> getM() {
        return m;
    }

    public void setM(Map<Integer, ListWrapper> m) {
        this.m = m;
    }
}
