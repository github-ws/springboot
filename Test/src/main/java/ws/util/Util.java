package ws.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public class Util {

    public static void fanxingleixing(String f) throws Exception {
        Field filed = Util.class.getDeclaredField(f);
        ParameterizedType genericType = (ParameterizedType) filed.getGenericType();
        System.out.println(Arrays.toString(genericType.getActualTypeArguments()));
    }
}
