package xinQing.springsecurity.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xinQing.springsecurity.configuration.AppConfig;

/**
 * 测试RoleDao
 *
 * Created by xuan on 16-11-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class RoleDaoTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void getById() {
        Assert.assertEquals("ROLE_ADMIN", roleDao.getById(1).getName());
    }
}
