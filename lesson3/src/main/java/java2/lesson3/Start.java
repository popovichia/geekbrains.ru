/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java2.lesson3.task1.WordsArray;
import java2.lesson3.task2.PhoneBook;

/**
 *
 * @author igor
 */
public class Start {
    public static void main(String[] args) {
        String[] words = {
            "нутро",
            "ива",
            "ива",
            "ива",
            "искра",
            "мешок",
            "мешок",
            "мешок",
            "мешок",
            "мешок",
            "мешок",
            "мешок",
            "люди",
            "трактор",
            "трактор",
            "мусор",
            "хулиган",
            "сталь",
            "одиночество",
            "конёк",
            "табун"
        };
        WordsArray wordsArray = new WordsArray(words);
        HashMap uniqElements = wordsArray.getUniqElements(words);
        Set<Map.Entry<String, Integer>> set = uniqElements.entrySet();
        for(Map.Entry<String, Integer> m : set) {
            System.out.println(m.getKey() + " - " + m.getValue());
        }
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Igor", "123");
        phoneBook.add("Igor", "123");
        phoneBook.add("Igor", "1234");
        phoneBook.add("Igor1", "123");
        phoneBook.add("Igor2", "123");
        System.out.println(phoneBook.get("Igor1"));
    }
}
