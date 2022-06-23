package com.booking.koval.service;

import com.booking.koval.dao.RoomDAO;
import com.booking.koval.dao.RoomDAO;
import com.booking.koval.entities.Room;
import com.booking.koval.entities.Room;

import java.util.List;

public class RoomService {
    public List<Room> getAll(){
        RoomDAO roomDAO = new RoomDAO();
        return roomDAO.findAll();
    }

    public Room findRoomById(long room_id) {
        RoomDAO roomDAO = new RoomDAO();
        Room room = roomDAO.findById(room_id);
        roomDAO.closeConnection();
        return room;
    }
}
