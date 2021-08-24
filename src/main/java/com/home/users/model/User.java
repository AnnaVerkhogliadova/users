package com.home.users.model;

public class User {
    public static final String ID = "Id";
    public static final String FIRSTNAME = "Firstname";

    private final long id;
    private final String firstname;

    public User(long id, String firstname) {
        this.id = id;
        this.firstname = firstname;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    @Override
    public String toString() {
        return ID + ": " + id + ", " + FIRSTNAME + ": " + firstname;
    }
}
