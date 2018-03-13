package com.extraaedge.contentsaveandget.structure;
import java.io.Serializable;

/**
 * <b>UserListItem</b>
 * <p>This is model class used combine all data in single class.</p>
 * created by Subodh Kumar
 */

public class UserListItem implements Serializable {

    private int id;
    private String name;
    private String city;
    private String email;

    public UserListItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
