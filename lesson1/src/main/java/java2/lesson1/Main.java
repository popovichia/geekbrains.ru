package java2.lesson1;

import java2.lesson1.Marathon.Cat;
import java2.lesson1.Marathon.Competitor;
import java2.lesson1.Marathon.Cross;
import java2.lesson1.Marathon.Dog;
import java2.lesson1.Marathon.Human;
import java2.lesson1.Marathon.Obstacle;
import java2.lesson1.Marathon.Wall;
import java2.lesson1.Marathon.Water;

public class Main {
    public static void main(String[] args) {
        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        Obstacle[] course = {new Cross(80), new Wall(2), new Water(50), new Cross(120)};
        for (Competitor c : competitors) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
        for (Competitor c : competitors) {
            c.info();
        }
    }
}

