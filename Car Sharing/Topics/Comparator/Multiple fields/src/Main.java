import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "=" + age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Utils {

    public static void sortUsers(List<User> users) {
        Comparator<User> com = new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
               if(user.getName().equals(t1.getName())){
                   if(user.getAge() > t1.getAge()) {
                       return -1;
                   }else if (user.getAge() < t1.getAge()){
                       return 1;
                   }
                  return 0;
               }
              return  user.getName().compareTo(t1.getName());
            }
        };
        users.sort(com);
    }
}