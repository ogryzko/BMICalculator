package model;

import org.json.simple.JSONObject;
import util.Converter;

/**
 * Created by eglushchenko on 14.08.2017.
 */
public class Result implements BaseModel {
    private final float bmi;
    private final float pi;
    private final String kind;

    public Result(float bmi, float pi, String kind) {
        this.bmi = bmi;
        this.pi = pi;
        this.kind = kind;
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

    @Override
    public JSONObject toJson() {
        return Converter.toJson(this);
    }
}
