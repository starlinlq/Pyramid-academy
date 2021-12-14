package metro;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static List<String> lines = new LinkedList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //String path = args[0];
       // readFile(path);
        readJsonFile();
        metroLines();
    }

    public static void readJsonFile() throws IOException {
        Gson gson = new Gson();
        Path path = Paths.get("/Users/starlinlq/Desktop/Untitled/HyperMetro/HyperMetro/task/src/metro/test.json");
        Reader reader = Files.newBufferedReader(path);

        Map<String, HashMap<String, String>> railWayMap = gson.fromJson(reader, new TypeToken<Map<String, HashMap<String, String>>>() {}.getType());

        Map<String, List<Station>> sortedMap = new HashMap<>();


        for(String key: railWayMap.keySet()){
            var railWay = railWayMap.get(key);
            sortedMap.put(key, new ArrayList<>());

            for(String key2: railWay.keySet()){
                Station station = new Station(railWay.get(key2), Integer.parseInt(key2));
                sortedMap.get(key).add(station);
            }
        }
        Collections.sort(sortedMap.get("Metro-Railway"));
        System.out.println(sortedMap.get("Metro-Railway").get(2).getName());

    }


    public static void readFile(String path) throws FileNotFoundException {
        File file = new File(path);


        if(!file.exists()){
            System.out.println("Error! Such a file doesn't exist!");
            return;
        } else if (file.length() == 0){
            return;
        }

       try(Scanner reader = new Scanner(file)){
           lines.add("Depot");
           while(reader.hasNextLine()){
               String station  = reader.nextLine();
               lines.add(station);
           }
           lines.add("Depot");
       } catch (Exception ex){
           System.out.println(ex.getMessage());
       }
    }

    public static void metroLines(){
        int x = 0;
        int i  = 1;

        while(x < lines.size()){

            if(i == 3){
                System.out.println(lines.get(x) + " ");
                if(x == lines.size() - 1) break;
                x--;
                i = 1;
                continue;
            } else
                System.out.print(lines.get(x) +" - ");

            i++;
            x++;

        }
    }
}
