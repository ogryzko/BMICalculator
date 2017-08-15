package model;

import java.util.Date;

/**
 * Created by eglushchenko on 15.08.2017.
 */
public class HistoryItemFactory {
    private int currentId = 1;
    private Date date;

    public HistoryItem getHistoryItem(Integer age, String gender, Float height, Float weight, float bmi, float pi, String kind) {
        date = new Date();
        if(age == null || gender == null || height == null || weight == null || kind == null){
            return null;
        }
        HistoryItem item = new HistoryItem(currentId, date, age, gender, height, weight, bmi, pi, kind);
        currentId++;
        return item;
    }
}
