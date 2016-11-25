package xinQing.springsecurity.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * Created by xuan on 16-11-23.
 */
public class User implements Serializable{

    private Integer id;

    private String username;

    private String password;

    // 登录失败尝试次数
    private short attemptTimes;

    // 最后一次登录尝试时间
    private Date lastAttemptDate;

    // 账号过期时间，过期则不能进行认证
    private Date accountExpiredDate;

    // 密码过期时间，过期则不能进行认证
    private Date credentialsExpiredDate;

    // 用户是否可用，false则不能进行认证
    private Boolean enable;

    // 其他用户相关属性...

    // 角色id
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getAttemptTimes() {
        return attemptTimes;
    }

    public void setAttemptTimes(short attemptTimes) {
        this.attemptTimes = attemptTimes;
    }

    public Date getLastAttemptDate() {
        return lastAttemptDate;
    }

    public void setLastAttemptDate(Date lastAttemptDate) {
        this.lastAttemptDate = lastAttemptDate;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getAccountExpiredDate() {
        return accountExpiredDate;
    }

    public void setAccountExpiredDate(Date accountExpiredDate) {
        this.accountExpiredDate = accountExpiredDate;
    }

    public Date getCredentialsExpiredDate() {
        return credentialsExpiredDate;
    }

    public void setCredentialsExpiredDate(Date credentialsExpiredDate) {
        this.credentialsExpiredDate = credentialsExpiredDate;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", attemptTimes=" + attemptTimes +
                ", lastAttemptDate=" + lastAttemptDate +
                ", accountExpiredDate=" + accountExpiredDate +
                ", credentialsExpiredDate=" + credentialsExpiredDate +
                ", enable=" + enable +
                ", roleId=" + roleId +
                '}';
    }
}
