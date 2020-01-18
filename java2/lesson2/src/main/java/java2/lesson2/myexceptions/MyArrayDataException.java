/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson2.myexceptions;

/**
 *
 * @author igor
 */
public class MyArrayDataException extends Exception {
    public MyArrayDataException(String errorMessage) {
        super(errorMessage);
    }
}
