package java2.lesson1.marathon;

import java2.lesson1.interfaces.Competitor;
import java2.lesson1.interfaces.Obstacle;

public class Course {
    Obstacle[] obstacles;
    public Course(Obstacle... obstacles) {
        if(obstacles.length > 0 ) {
            this.obstacles = obstacles;
        } else {
            System.out.println("Марафон должен содержать минимум одно препятсвие!");
        }
    }
    public void doIt(Team team) {
        for(Competitor competitor : team.getCompetitors()) {
            for(Obstacle obstacle : obstacles) {
                obstacle.doIt(competitor);
            }
        }
    }
}
