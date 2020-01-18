package java2.lesson1.obstacles;

import java2.lesson1.interfaces.Competitor;
import java2.lesson1.interfaces.Obstacle;

public class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}