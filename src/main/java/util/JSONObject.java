package util;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by genya on 15/08/2017.
 */
public class JSONObject extends HashMap {

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",","{","}");
        return sj.toString();
    }
}
