/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.server.entity;

import java.util.ArrayList;
import java2.chat.server.services.BlackListService;

/**
 *
 * @author igor
 */
public class BlackList {
    private ArrayList<String> blackList;
    private User blackListOwner;
    public BlackList(User blackListOwner) {
        this.blackListOwner = blackListOwner;
        loadBlackList(blackListOwner);
    }
    public ArrayList<String> getBlackList() {
        return this.blackList;
    }
    public void addUserToBlackList(User user) {
        if (!this.blackList.contains(user.getNickName())) {
            this.blackList.add(user.getNickName());      
            BlackListService.connect();
            BlackListService.saveInBlackListByNickName(this.blackListOwner, user);
            BlackListService.disconnect();
        }
    }
    public void loadBlackList(User blackListOwner) {
        BlackListService.connect();
        this.blackList = BlackListService.getBlackListByNickName(blackListOwner);
        BlackListService.disconnect();        
    }
}
