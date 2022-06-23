package com.booking.koval.entities;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class Order {
    private long order_id;
    private long capacity;
    private Class class_;
    private Date start_date;
    private Date end_date;
    private Status status;
    private long room_id;

    public Order() {
    }

    public Order(long order_id, long capacity, Class class_, Date start_date, Date end_date, Status status, long room_id) {
        this.order_id = order_id;
        this.capacity = capacity;
        this.class_ = class_;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.room_id = room_id;
    }

    public Order(long capacity, Class class_, Date start_date, Date end_date, Status status) {
        this.capacity = capacity;
        this.class_ = class_;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }

    public Order(long room_id) {
        this.room_id = room_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public Class getClass_() {
        return class_;
    }

    public void setClass_(Class class_) {
        this.class_ = class_;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getStatus().equals(order.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus());
    }
}
