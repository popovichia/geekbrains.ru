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
public class Fruit {
    protected float weight;
    public Fruit(float weight) {
        this.weight = weight;
    }
    public float getWeight() {
        return this.weight;
    }
    @Override
    public String toString() {
        return this.getClass().getCanonicalName();
    }
}
