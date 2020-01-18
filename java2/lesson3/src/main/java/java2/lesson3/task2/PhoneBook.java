/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson3.task2;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author igor
 */
public class PhoneBook {
    private String lastName;
    private String phoneNumber;
    private static HashMap<String, HashSet<String>> phoneBookList = new HashMap<>();
    public boolean add(String lastName, String phoneNumber) {
        boolean result = false;
        HashSet<String> listPhoneNumber;
        if(phoneBookList == null) {
            listPhoneNumber = new HashSet<>();
        } else {
            listPhoneNumber = phoneBookList.get(lastName);
            if(listPhoneNumber == null) {
                listPhoneNumber = new HashSet<>();
            }
        }
        listPhoneNumber.add(phoneNumber);
        phoneBookList.put(lastName, listPhoneNumber);
        result = true;
        return result;
    }
    public HashSet get(String lastName) {
        return phoneBookList.get(lastName);
    }
}
