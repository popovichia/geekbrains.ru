package java2.lesson1.marathon;

import java2.lesson1.interfaces.Competitor;

public class Team {
    String name;
    Competitor[] competitors;
    public Team(String name, Competitor... competitors) {
        if(competitors.length < 1 ) {
            System.out.println("Список участников марафона не может быть пустым.");
        } else if (name.isEmpty()){
            System.out.println("Пожалуйста, задайте имя команды.");
        } else {
            this.name = name;
            this.competitors = competitors;
        }
    }
    public Competitor[] getCompetitors() {
        return this.competitors;
    }
    public void showResults() {
        for(Competitor competitor : this.getCompetitors()) {
            if(competitor.isOnDistance()) {
                competitor.info();
            }
        }
    }
    public void showAllCompetitors() {
        System.out.println("Состав команды \"" + this.name + "\":");
        for(Competitor competitor : this.getCompetitors()) {
            competitor.info();
        }
    }
}
