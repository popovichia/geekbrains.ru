/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.server.entity;

import java2.chat.server.services.UserService;

/**
 *
 * @author igor
 */
public class User {
    private int id;
    private String login;
    private String nickName;
    private String fullName;

    public User(String nickName) {
        this.nickName = nickName;
        UserService.connect();
        this.id = Integer.valueOf(UserService.getFieldValueByNickName("id", nickName));
        this.login = UserService.getFieldValueByNickName("login", nickName);
        this.fullName = UserService.getFieldValueByNickName("fullname", nickName);
        UserService.disconnect();
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
