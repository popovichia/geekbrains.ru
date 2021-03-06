/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson2.enums;

/**
 *
 * @author igor
 */
public enum DayOfWeek {
    MONDAY(1, "Понедельник", 40),
    TUESDAY(2, "Вторник", 32),
    WEDNESDAY(3, "Среда", 24),
    THURSDAY(4, "Четверг", 16),
    FRIDAY(5, "Пятница", 8),
    SATURDAY(6, "Суббота", 0),
    SUNDAY(7, "Воскресенье", 0);
    
    private int index;
    private String nameRu;
    private int workTime;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }
    
    
    
    DayOfWeek(int index, String nameRu, int workTime) {
        this.index = index;
        this.nameRu = nameRu;
        this.workTime = workTime;
    }
}
