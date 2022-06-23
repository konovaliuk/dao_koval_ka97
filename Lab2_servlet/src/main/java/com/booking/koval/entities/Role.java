package com.booking.koval.entities;


import java.util.Objects;

public class Role {
    private long role_id;
    private String role_name;

    public Role() {
    }

    public Role(String role_name) {
        this.role_name = role_name;
    }

    public Role(long role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return getRole_name().equals(role.getRole_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole_name());
    }
}
