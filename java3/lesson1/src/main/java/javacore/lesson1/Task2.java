/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacore.lesson1;

import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class Task2 <T extends Object> {
    public ArrayList<T> convertToArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<>();
        for(int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }
    public void test() {
        System.out.println("Test started! Task2.\n");
        
        Task2 mainObject = new Task2();
        
        Integer[] arrayInteger = {1,2,3,4,5,6,7,8,9,0};
        System.out.println(arrayInteger.getClass().getCanonicalName());
        System.out.println(mainObject.convertToArrayList(arrayInteger));

        String[] arrayString = {"a1","a2","a3","a4","a5","a6","a7","a8","a9","a0"};
        System.out.println(arrayString.getClass().getCanonicalName());        
        System.out.println(mainObject.convertToArrayList(arrayString));
        
        System.out.println("--------------------------\n");
    }
}
