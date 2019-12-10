package java2.lesson1.obstacles;

import java2.lesson1.interfaces.Competitor;
import java2.lesson1.interfaces.Obstacle;

public class Water extends Obstacle {
    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}