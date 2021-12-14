import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Get list of public fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public String[] getPublicFields(Object object) {
        List<String> list = new ArrayList<>();

        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field: fields){
            int modifier = field.getModifiers();

            if(Modifier.isPublic(modifier)){
                list.add(field.getName());
            }

        }

        return list.toArray(new String[0]);

    }

}