package model;

import org.json.simple.JSONObject;
import util.Converter;

import java.util.Date;

/**
 * Created by eglushchenko on 14.08.2017.
 */
public class HistoryItem implements BaseModel {
    private final int id;
    private final Date date;
    private final int age;
    private final String gender;
    private final float height;
    private final float weight;
    private final float bmi;
    private final float pi;
    private final String kind;


    public HistoryItem(int id, Date date, int age, String gender, float height, float weight, float bmi, float pi, String kind) {
        this.id = id;
        this.date = date;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.pi = pi;
        this.kind = kind;
    }

    public JSONObject toJson(){
        return Converter.toJson(this);
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public float getBmi() {
        return bmi;
    }

    public float getPi() {
        return pi;
    }

    public String getKind() {
        return kind;
    }
}
