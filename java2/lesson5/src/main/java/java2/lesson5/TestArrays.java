/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson5;

/**
 *
 * @author igor
 */
public class TestArrays {
    private int size;
    private float[] arrayForTest;
    TestArrays(int size) {
        this.size = size;
        fillArray();
    }
    public long testOne() {
        float[] array1 = new float[this.size];
        System.arraycopy(this.arrayForTest, 0, array1, 0, size);
        long tt1 = System.currentTimeMillis();
        calculateArrayItems(array1, 1);
        long tt2 = System.currentTimeMillis();
        //Для просмотра элементов массива.
//        printArray(array1, "Test1:");
        return (tt2 - tt1);
    }
    public long testTwo() {
        float[] array = new float[this.size];
        int halfSize = this.size / 2;
        long tt1 = System.currentTimeMillis();
        float[] halfArray1 = new float[halfSize];
        System.arraycopy(this.arrayForTest, 0, halfArray1, 0, halfSize);
        calculateArrayItemsInThread(halfArray1, 1);
        float[] halfArray2 = new float[halfSize];
        System.arraycopy(this.arrayForTest, halfSize, halfArray2, 0, halfSize);
        calculateArrayItemsInThread(halfArray2, 2);
        System.arraycopy(halfArray1, 0, array, 0, halfSize);
        System.arraycopy(halfArray2, 0, array, halfSize, halfSize);
        long tt2 = System.currentTimeMillis();
        //Для просмотра элементов массива.
//        printArray(array, "Test2:");
        return (tt2 - tt1);
    }
    private void fillArray() {
        this.arrayForTest = new float[this.size];
        for(int i = 0; i < this.arrayForTest.length; i++) {
            this.arrayForTest[i] = 1;
        }
    }
    private void calculateArrayItems(float[] array, int part) {
        for(int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] 
            * Math.sin(0.2f + (i + (part - 1) * array.length) / 5)
            * Math.cos(0.2f + (i + (part - 1) * array.length) / 5)
            * Math.cos(0.4f + (i + (part - 1) * array.length) / 2));
        }    
    }
    private void calculateArrayItemsInThread(float[] array, int part) {
        Thread t = new Thread( new Runnable() {
            @Override
            public void run() {
                calculateArrayItems(array, part);
            }
        });
        t.start();
        try {
            t.join();
        } catch(InterruptedException interruptedException) {
            System.err.println(interruptedException);
        }
    }
    private void printArray(float[] array, String title) {
        System.out.println(title);
        for(int i = 0; i < array.length; i++) {
            if (i != (array.length - 1)) {
                System.out.print(array[i] + ", ");
            } else {
                System.out.println(array[i]);
            }
        }
    }
}
