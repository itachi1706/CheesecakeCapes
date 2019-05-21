/*
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package com.jadarstudios.developercapes.user;

import com.jadarstudios.developercapes.DevCapes;
import com.jadarstudios.developercapes.cape.CapeManager;
import com.jadarstudios.developercapes.cape.ICape;

import java.util.Collection;
import java.util.HashMap;

import static com.jadarstudios.developercapes.DevCapes.DEV_CAPE;
import static com.jadarstudios.developercapes.DevCapes.DEV_SKIN;

/**
 * Users can not be trusted to put capes on by themselves
 * 
 * @author jadar
 */
public class UserManager {

    protected static UserManager instance;

    protected HashMap<String, User> users;

    public UserManager() {
        this.users = new HashMap<String, User>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getUser(String username) {
        return this.users.get(username);
    }

    public void addUser(User user) throws NullPointerException {
        if (user == null || user.userUUID == null || user.userUUID.isEmpty()) {
            throw new NullPointerException("Cannot add a null user!");
        }

        this.users.put(user.userUUID, user);
        CapeManager.getInstance().addCapes(user.capes);
    }

    public void addUsers(Collection<User> users) throws NullPointerException {
        for (User u : users) {
            this.addUser(u);
        }
    }

    public User newUser(String username) {
        User user = null;
        if (this.users.containsKey(username)) {
            user = this.getUser(username);
        } else {
            user = new User(username);
            this.users.put(username, user);
        }

        return user;
    }

    /*
    Parses that accepts only capes, use {#parse(user,cape,skin)} for offline skin support
     */
    public User parse(String user, Object cape) {
        User userInstance = new User(user);

        ICape capeInstance = (cape instanceof ICape) ? (ICape)cape : CapeManager.getInstance().parse(user, cape.toString(), DEV_CAPE);

        if (capeInstance != null) {
            userInstance.capes.add(capeInstance);
        } else {
            DevCapes.logger.error(String.format("Error parsing cape, %s", cape.toString()));
        }

        return userInstance;
    }

    public User parse(String user, Object cape, Object offlineSkin) {
        User userInstance = parse(user, cape);

        ICape offlineSkinInstance = (offlineSkin instanceof ICape) ? (ICape)offlineSkin : CapeManager.getInstance().parse(user, offlineSkin.toString(), DEV_SKIN);

        if (offlineSkinInstance != null) {
            userInstance.skins.add(offlineSkinInstance);
        } else {
            DevCapes.logger.error(String.format("Error parsing offline skin, %s", offlineSkinInstance.toString()));
        }

        return userInstance;
    }
}