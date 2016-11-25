package xinQing.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import xinQing.springsecurity.security.LimitLoginAuthenticationProvider;
import xinQing.springsecurity.security.LoginAuthenticationFilter;
import xinQing.springsecurity.security.MyAuthenticationFailureHandler;
import xinQing.springsecurity.security.MyUserDetailsServiceImpl;

import javax.sql.DataSource;


/**
 * Spring Security配置
 *
 * Created by xuan on 16-11-23.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/welcome").permitAll()// 首页不需要认证
                .antMatchers("/captcha").permitAll()// 验证码不需要认证
                .antMatchers("/admin").hasRole("ADMIN")//   需要ROLE_ADMIN角色
                .anyRequest().authenticated()
                .and()
                .formLogin()//  自定义登录页面
                .loginPage("/login")
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler(authenticationFailureHandler())// 登录失败处理
                .defaultSuccessUrl("/welcome")//    登录成功处理
                .and()
                .logout()// 注销，如果不禁用csrf，那么需要post请求才能注销；可以自己在Controller中处理/logout，注销session
                .logoutUrl("/logout")
                .permitAll()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .and()
                .addFilter(loginAuthenticationFilter());//  登录过滤，校验验证码
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 使用内存记住用户名和密码
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("USER", "ADMIN")
//                .and()
//                .withUser("user").password("user").roles("USER")
//                .and()
//                .passwordEncoder(new Md5PasswordEncoder());
        // 自定义AuthenticationProvider
        auth
                .authenticationProvider(authenticationProvider());
    }

    /**
     * 全局安全方法必须配置authenticationManagerBean
     *
     * @return AuthenticationManagerDelegator
     * @throws Exception 异常信息
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 自定义UserDetailsService
     * 主要是根据username获取UserDetails信息
     * 跟Shiro的Realm类似
     *
     * @return MyUserDetailsServiceImpl
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsServiceImpl();
    }

    /**
     * 自定义身份认证Provider
     * 默认是DaoAuthenticationProvider
     * 实现AuthenticationProvider，自定义自己的身份认证Provider
     *
     * @return LimitLoginAuthenticationProvider 对登录失败尝试限制
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        LimitLoginAuthenticationProvider authenticationProvider = new LimitLoginAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * 自定义认证失败处理
     * 实现了AuthenticationFailureHandler，根据身份认证Provider抛出的身份认证异常做不同的处理
     *
     * @return MyAuthenticationFailureHandler   返回身份认证失败信息，请求转发到/login
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new MyAuthenticationFailureHandler(null);
    }

    /**
     * 持久化Token
     *
     * @return JdbcTokenRepositoryImpl
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    /**
     * 加密
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(9);
    }

    /**
     * 登录过滤，认证验证码是否正确
     * 然后调用super.attemptAuthentication(request, response)
     * 必须设置AuthenticationManager
     *
     * @return LoginAuthenticationFilter
     */
    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter();
        loginAuthenticationFilter.setAuthenticationManager(authenticationManager());
        // 设置自定义AuthenticationFailureHandler，默认会401状态码
        loginAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return loginAuthenticationFilter;
    }
}
