package com.smart;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import javax.persistence.*;
import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="T_USER")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    protected int userId;

    @Column(name = "user_name")
    protected String userName;

    protected String password;

    @Column(name = "last_visit")
    protected Date lastVisit;

    @Column(name = "last_ip")
    protected String lastIp;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String toString() {
        return (new ReflectionToStringBuilder(this) {
            protected boolean accept(Field f) {
                if(f.getType().isPrimitive() || f.getType() == String.class ){
                    return true;
                }else{
                    return false;
                }
            }
        }).toString();
    }
}
