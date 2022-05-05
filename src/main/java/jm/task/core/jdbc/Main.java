package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;


public class Main {
    private static final User user1 = new User("kto", "ponimaet", (byte) 1);
    private static final User user2 = new User("v etom", "mire", (byte) 2);
    private static final User user3 = new User("postgre", "s", (byte) 33);
    private static final User user4 = new User("oshibkoi", "null", (byte) 4);

    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userDao.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userDao.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userDao.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
