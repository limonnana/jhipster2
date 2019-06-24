package com.limonnana.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class UnitOfCalendarI implements UnitOfCalendar {

    private static final long serialVersionUID = 1L;


    private Long id;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return  userId;
    }
}
