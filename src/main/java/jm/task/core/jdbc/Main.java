package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();

        us.createUsersTable();
        us.saveUser("Egor", "Zotov", (byte) 29);
        us.saveUser("Margarita", "Zotova", (byte) 30);
        us.saveUser("Anisiya", "Zotova", (byte) 5);
        us.saveUser("Taisiya", "Zotova", (byte) 3);
        us.getAllUsers();
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
