package util;

import model.HistoryItem;
import model.Result;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;


/**
 * Created by eglushchenko on 14.08.2017.
 */
public class Converter {
    private static final SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );
    public static JSONObject toJson(HistoryItem historyItem) {
        JSONObject obj = new JSONObject();
        obj.put("id", historyItem.getId());
        obj.put("date", df.format(historyItem.getDate()));
        obj.put("age", historyItem.getAge());
        obj.put("gender", historyItem.getGender() );
        obj.put("height", historyItem.getHeight() );
        obj.put("weight", historyItem.getWeight() );
        obj.put("bmi", historyItem.getBmi() );
        obj.put("pi", historyItem.getPi());
        obj.put("kind", historyItem.getKind() );
        return obj;
    }

    public static JSONObject toJson(Result result) {
        JSONObject obj = new JSONObject();
        obj.put("bmi", result.getBmi());
        obj.put("pi", result.getPi());
        obj.put("kind", result.getKind());
        return obj;
    }
}
