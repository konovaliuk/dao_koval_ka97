package com.booking.koval.dao.interfaces;

import com.booking.koval.entities.Room;

import java.util.List;

public interface IRoomDAO {
    List<Room> findAll();
    Room findById(long room_id);
    long create(Room room);
    void update(long room_id, Room room);
    void delete(long room_id);
}
