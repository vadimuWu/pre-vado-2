package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getConnection();
    private Session session = null;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        session = (Session) sessionFactory.openSession().beginTransaction();
        try {
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS test.users" +
                    " (id mediumint not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").executeUpdate();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        session = (Session) sessionFactory.openSession().beginTransaction();
        try {
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = (Session) sessionFactory.openSession().beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }
    @Override
    public void removeUserById(long id) {
        session = (Session) sessionFactory.openSession().beginTransaction();
        try {
            session.delete(session.get(User.class, id));
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        session.beginTransaction();
        List<User> userList = session.createQuery(criteriaQuery).getResultList();
        try {
            session.close();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return userList;
    }
    @Override
    public void cleanUsersTable() {
        session = (Session) sessionFactory.openSession().beginTransaction();
        try {
            session.createNativeQuery("TRUNCATE TABLE users;").executeUpdate();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
