import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by genya on 15/08/2017.
 */
public class SomeTest {
    private static final SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );
    @Test
    public void test(){
        Map map = new HashMap();
        map.put("id", 1);
        map.put("date", df.format(new Date()));
        map.put("age", 10);
        map.put("gender", "Male");
        map.put("height", 1.7);
        map.put("weight", 16.5);
        map.put("bmi", 60.4 );
        map.put("pi", 100);
        map.put("kind", "dfsdfs" );

        StringJoiner sj = new StringJoiner(",","{","}");


    }

    /*private static String toJSONString(String key,Object value, StringBuffer sb){
        sb.append('\"');
        if(key == null)
            sb.append("null");
        else
            JSONValue.escape(key, sb);
        sb.append('\"').append(':');

        sb.append(JSONValue.toJSONString(value));

        return sb.toString();
    }*/
}
