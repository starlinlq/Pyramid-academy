package metro;

import java.io.Serializable;

public class Station implements Comparable<Station> {
    private int id;
    private String name;

    public  Station(String name, int id){
        this.name = name;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Station s) {
        if(this.id > s.getId()){
            return 1;
        } else if (this.id < s.getId()){
            return -1;
        }
        return 0;
    }
}
