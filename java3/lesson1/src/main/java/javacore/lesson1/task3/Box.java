/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacore.lesson1.task3;

import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class Box<T extends Fruit> {
    private ArrayList<T> fruits;
    public Box() {
        fruits = new ArrayList<>();
    }
    public void addFruit(T fruit) {
        this.fruits.add(fruit);
    }
    public float getWeight() {
        float resultWeight = 0f;
        if(fruits.size() == 0) {
            return resultWeight;
        }
        for(T fruit: fruits) {
            resultWeight += fruit.getWeight();
        }
        return resultWeight;
    }
    public ArrayList<T> getFruits() {
        return this.fruits;
    }
    public boolean compare(Box anotherBox) {
        return this.getWeight() == anotherBox.getWeight() ? true : false; 
    }
    public void putTo(Box anotherBox) {
        if (anotherBox.getFruits().isEmpty()) {
            anotherBox.getFruits().addAll(this.getFruits());
            this.getFruits().clear();
        }
    }
    @Override
    public String toString() {
        return this.fruits.toString();
    }
}
