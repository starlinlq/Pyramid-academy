package task;

import java.util.HashMap;

public class Color {
    private String color;
    private  String category;
    private String type;
    private HashMap<String, Object> code;

    public Color(){}

    public Color(String color, String category, String type, HashMap<String, Object> code){
        this.color = color;
        this.category = category;
        this.type = type;
        this.code = code;
    }

    public HashMap<String, Object> getCode(){
        return this.code;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
