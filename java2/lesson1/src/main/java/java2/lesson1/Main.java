package java2.lesson1;

import java2.lesson1.competitors.Cat;
import java2.lesson1.interfaces.Competitor;
import java2.lesson1.obstacles.Cross;
import java2.lesson1.competitors.Dog;
import java2.lesson1.competitors.Human;
import java2.lesson1.marathon.Course;
import java2.lesson1.marathon.Team;
import java2.lesson1.obstacles.Wall;
import java2.lesson1.obstacles.Water;

public class Main {
    public static void main(String[] args) {
        Course course = new Course(new Cross(80), new Wall(2), new Water(50), new Cross(120),
                new Cross(80), new Wall(2), new Water(50), new Cross(120));
        Team team = new Team("Первая команда", new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"),
                new Human("Бил"), new Cat("Мурзки"), new Dog("Шарик"));
        course.doIt(team);
        team.showResults();
        team.showAllCompetitors();
    }
}

