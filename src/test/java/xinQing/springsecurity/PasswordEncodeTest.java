package xinQing.springsecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xinQing.springsecurity.configuration.AppConfig;
import xinQing.springsecurity.configuration.WebSecurityConfig;

/**
 * Created by xuan on 16-11-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebSecurityConfig.class})
public class PasswordEncodeTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encode() {
        System.out.println(passwordEncoder.encode("admin"));
        System.out.println(passwordEncoder.encode("user"));
        System.out.println(passwordEncoder.encode("111111"));
    }
}
