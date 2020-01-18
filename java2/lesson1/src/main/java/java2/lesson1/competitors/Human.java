package java2.lesson1.competitors;

import java2.lesson1.interfaces.Competitor;

public class Human implements Competitor {
    String name;

    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;

    boolean active;

    @Override
    public boolean isOnDistance() {
        return active;
    }

    public Human(String name) {
        this.name = name;
        this.maxRunDistance = 5000;
        this.maxJumpHeight = 30;
        this.maxSwimDistance = 200;
        this.active = true;
    }

    @Override
    public void run(int dist) {
        System.out.println("Дистанция кросса: " + dist + ", максимальная дистанция участника: " + maxRunDistance);
        if (dist <= maxRunDistance) {
            System.out.println(name + " хорошо справился с кроссом");
        } else {
            System.out.println(name + " не справился с кроссом");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        System.out.println("Высота стены: " + height + ", максимальная высота прыжка участника: " + maxJumpHeight);
        if (height <= maxJumpHeight) {
            System.out.println(name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(name + " не смог перепрыгнуть стену");
            active = false;
        }
    }

    @Override
    public void swim(int dist) {
        System.out.println("Дистанция заплыва: " + dist + ", максимальная длина заплыва участника: " + maxSwimDistance);
        if (dist <= maxSwimDistance) {
            System.out.println(name + " отлично проплыл");
        } else {
            System.out.println(name + " не смог проплыть");
            active = false;
        }
    }

    @Override
    public void info() {
        System.out.println(name + " - " + active);
    }
}
