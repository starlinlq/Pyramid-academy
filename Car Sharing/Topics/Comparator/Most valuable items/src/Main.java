import java.util.Comparator;
import java.util.List;

class StockItem {
    private String name;
    private double pricePerUnit;
    private int quantity;

    public StockItem(String name, double pricePerUnit, int quantity) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + ": " + pricePerUnit + ", " + quantity + ";";
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }
}

class Utils {
    public static List<StockItem> sort(List<StockItem> stockItems) {
        // your code here
      Comparator<StockItem> com = new Comparator<StockItem>() {
          @Override
          public int compare(StockItem stockItem, StockItem t1) {
              double p = stockItem.getQuantity() * stockItem.getPricePerUnit();
              double p2 = t1.getPricePerUnit() * t1.getQuantity();
              return Double.compare(p2, p);
          }
      };
       stockItems.sort(com);
       return stockItems;
    }
}