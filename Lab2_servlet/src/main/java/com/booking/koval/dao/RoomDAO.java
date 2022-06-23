package com.booking.koval.dao;

import com.booking.koval.connection.ConnectionPool;
import com.booking.koval.dao.interfaces.IRoomDAO;
import com.booking.koval.entities.*;
import com.booking.koval.entities.Class;
import com.booking.koval.entities.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomDAO implements IRoomDAO {
    private final String COLUMN_ROOM_ID = "room_id";
    private final String COLUMN_PRICE_PER_NIGHT = "price_per_night";
    private final String COLUMN_CAPACITY = "capacity";
    private final String COLUMN_CLASS_ = "class_";
    private Connection connection;
    private Statement statement;

    public RoomDAO() {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Room getRoom(ResultSet result) throws SQLException {
        long room_id = result.getLong(COLUMN_ROOM_ID);
        long price_per_night = result.getLong(COLUMN_PRICE_PER_NIGHT);
        long capacity = result.getLong(COLUMN_CAPACITY);
        Class class_ = Class.valueOf(result.getString(COLUMN_CLASS_));
        return new Room(room_id, price_per_night, capacity, class_);
    }

    public RoomDAO(final Connection connect) throws SQLException {
        this.connection = connect;
        this.statement = connect.createStatement();
    }

    @Override
    public List<Room> findAll() {
        String query = "SELECT * FROM rooms";
        List<Room> roomList = new ArrayList<Room>();

        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                roomList.add(getRoom(result));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    @Override
    public Room findById(long room_id) {
        String query = "SELECT * FROM rooms WHERE room_id = "+room_id;
        Room room = new Room();

        try {
            ResultSet result = statement.executeQuery(query);
            result.next();
            room = getRoom(result);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public long create(Room room) {
        String query = "INSERT INTO rooms (price_per_night, capacity, class_) VALUES ('"+room.getPrice_per_night()+"', '"+room.getCapacity()+"', '"+room.getClass_()+"')";
        long room_id = 0;
        try {
            statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                room_id = result.getLong(1);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room_id;
    }

    @Override
    public void update(long room_id, Room room) {
        String query = "UPDATE rooms SET price_per_night = '"+room.getPrice_per_night()+"' WHERE room_id = "+room_id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void delete(long room_id) {
        String query = "DELETE FROM rooms WHERE room_id = "+room_id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    public void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
