package lessons.l1;

import lessons.l1.Marathon.Cat;
import lessons.l1.Marathon.Competitor;
import lessons.l1.Marathon.Cross;
import lessons.l1.Marathon.Dog;
import lessons.l1.Marathon.Human;
import lessons.l1.Marathon.Obstacle;
import lessons.l1.Marathon.Wall;
import lessons.l1.Marathon.Water;

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

