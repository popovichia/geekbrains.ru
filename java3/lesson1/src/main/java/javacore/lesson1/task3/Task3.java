/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacore.lesson1.task3;

/**
 *
 * @author igor
 */
public class Task3 {
    public void test() {
        System.out.println("Test started! Task3.\n");

        Apple apple1 = new Apple(2.0f);
        Apple apple2 = new Apple(2.0f);
        Apple apple3 = new Apple(2.0f);
        
        Orange orange1 = new Orange(1.0f);
        Orange orange2 = new Orange(1.1f);
        Orange orange3 = new Orange(1.5f);
                
        Box boxApples = new Box();
        boxApples.addFruit(apple1);
        boxApples.addFruit(apple2);
        boxApples.addFruit(apple3);
        System.out.println("boxApples - " + boxApples);
        System.out.println("boxApples weight - " + boxApples.getWeight());
        
        Box boxOranges = new Box();
        boxOranges.addFruit(orange1);
        boxOranges.addFruit(orange2);
        boxOranges.addFruit(orange3);
        System.out.println("boxOranges - " + boxOranges);
        System.out.println("boxOranges weight - " + boxOranges.getWeight());

        System.out.println("Compare boxOranges and boxApples - " + boxOranges.compare(boxApples));
        
        Box boxFruit = new Box();
        System.out.println("boxApples - " + boxApples);
        System.out.println("boxFruit - " + boxFruit);        
        boxApples.putTo(boxFruit);
        System.out.println("boxApples - " + boxApples);
        System.out.println("boxFruit - " + boxFruit);        
        
        System.out.println("--------------------------\n");
    }
}
