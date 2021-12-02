import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        // write your code here
        int pairs = 0;
        for(String key: map1.keySet()){
            if(map2.containsKey(key)){
                if(map2.get(key).equals(map1.get(key))){
                    pairs += 1;
                }
            }
        }
        System.out.println(pairs);

    }
}