import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class ReflectionUtils {

    public static int countPublicMethods(Class targetClass) {
        int publicAvl = 0;
        Method[] fields = targetClass.getDeclaredMethods();

        for(Method method: fields){
            int modifiers = method.getModifiers();
            if(Modifier.isPublic(modifiers)){
                publicAvl += 1;
            }
        }

        return publicAvl;

    }
}