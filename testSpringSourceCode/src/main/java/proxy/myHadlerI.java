package proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface myHadlerI {
Object invoke(Method method) throws InvocationTargetException, IllegalAccessException;
}
