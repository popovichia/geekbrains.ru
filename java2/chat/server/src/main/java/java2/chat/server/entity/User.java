/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.server.entity;

import java.util.ArrayList;
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
    private ArrayList<String> blackList;

    public User(String nickName) {
        this.nickName = nickName;
        UserService.connect();
        this.id = Integer.valueOf(UserService.getFieldValueByNickName("id", nickName));
        this.login = UserService.getFieldValueByNickName("login", nickName);
        this.fullName = UserService.getFieldValueByNickName("fullname", nickName);
        UserService.disconnect();
        loadBlackList(this);
        
    }
    public User(int id, String login, String nickName, String fullname) {
        this.id = id;
        this.login = login;
        this.nickName = nickName;
        this.fullName = fullname;
    }
    public void loadBlackList(User blackListOwner) {
        UserService.connect();
        this.blackList = UserService.getBlackListByNickName(blackListOwner);
        UserService.disconnect();        
    }
    public void addUserToBlackList(String nickName) {
        if (!this.blackList.contains(nickName)) {
            this.blackList.add(nickName);      
            UserService.connect();
            UserService.saveInBlackListByNickName(this, nickName);
            UserService.disconnect();
        }
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
    public ArrayList<String> getBlackList() {
        return this.blackList;
    }
}
