package jdbc;

import jdbc.dao.AbstractDaoFactory;
import jdbc.dao.GenericDao;
import jdbc.model.User;

public class JdbcUserTest {
    public static void main(String[] args) {
        GenericDao<User, Long> userDao =
                AbstractDaoFactory.getDaoFactory(User.class).getDao();

        System.out.println(userDao.read(1L));

    }
}
