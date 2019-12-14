/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson2;

import java2.lesson2.myexceptions.MyArrayDataException;
import java2.lesson2.myexceptions.MyArraySizeException;

/**
 *
 * @author igor
 */
public class ExceptionsProgram {    
    public int arraySum(String[][] stringArray) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        int arrayItem;
        if (stringArray == null
        || stringArray.length != 4) {
            throw new MyArraySizeException("Ошибка в размере строк двумерного массива.");
        } else {
            for(String[] array : stringArray) {
                if(array.length != 4) {
                    throw new MyArraySizeException("Ошибка в размере столбцов двумерного массива.");
                }
            }
        }
        for(int i = 0; i < stringArray.length; i++) {
            for(int j = 0; j < stringArray[i].length; j++) {
                try {
                    arrayItem = Integer.valueOf(stringArray[i][j]);
                } catch(NumberFormatException e) {
                    throw new MyArrayDataException("Символ " + stringArray[i][j] + " в массиве,"
                    + " не является целым числом. Строка - " + (i+1) + ", столбец - " + (j+1));
                }
                sum += arrayItem;
            }
        }
        return sum;
    }
}
