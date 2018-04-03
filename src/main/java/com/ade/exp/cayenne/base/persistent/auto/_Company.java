package com.ade.exp.cayenne.base.persistent.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import com.ade.exp.cayenne.base.persistent.User;

/**
 * Class _Company was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Company extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> ADDRESS = Property.create("address", String.class);
    public static final Property<String> NAME = Property.create("name", String.class);
    public static final Property<List<User>> USERS = Property.create("users", List.class);

    public void setAddress(String address) {
        writeProperty("address", address);
    }
    public String getAddress() {
        return (String)readProperty("address");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void addToUsers(User obj) {
        addToManyTarget("users", obj, true);
    }
    public void removeFromUsers(User obj) {
        removeToManyTarget("users", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return (List<User>)readProperty("users");
    }


}
