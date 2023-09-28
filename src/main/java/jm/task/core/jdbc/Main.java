package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args){

        userService.createUsersTable();

        userService.saveUser("Vladimir", "Putin", (byte) 70);
        userService.saveUser("Lavrov", "Sergey", (byte) 73);
        userService.saveUser("Shoigu", "Sergey", (byte) 68);
        userService.saveUser("Biden", "Joseph", (byte) 80);

        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
