package xinQing.springsecurity.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xinQing.springsecurity.configuration.AppConfig;
import xinQing.springsecurity.entity.User;

/**
 * 测试UserDao
 *
 * Created by xuan on 16-11-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getByUsername() {
        User user = userDao.getByUsername("admin");
        System.out.println(user.getAccountExpiredDate());
    }

    @Test
    public void updateNotNullById() {
        User user = new User();
        user.setId(1);
        user.setAttemptTimes((short) 1);
        userDao.updateNotNullById(user);
    }

    @Test
    public void updateFailAttemptTimes() {
        userDao.updateFailAttemptTimes("admin");
    }

    @Test
    public void resetFailAttemptTimes() {
        userDao.resetFailAttemptTimes("admin");
    }

}
