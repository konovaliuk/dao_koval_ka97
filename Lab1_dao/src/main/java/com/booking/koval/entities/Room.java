package com.booking.koval.entities;


import java.util.Objects;

public class Room {
    private long room_id;
    private long price_per_night;
    private long capacity;
    private Class class_;

    public Room() {
    }

    public Room(long price_per_night) {
        this.price_per_night = price_per_night;
    }

    public Room(long price_per_night, long capacity, Class class_) {
        this.price_per_night = price_per_night;
        this.capacity = capacity;
        this.class_ = class_;
    }

    public Room(long room_id, long price_per_night, long capacity, Class class_) {
        this.room_id = room_id;
        this.price_per_night = price_per_night;
        this.capacity = capacity;
        this.class_ = class_;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public long getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(long price_per_night) {
        this.price_per_night = price_per_night;
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


}
