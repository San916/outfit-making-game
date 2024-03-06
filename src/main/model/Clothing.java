package model;

// A class representing a single piece of clothing
// A piece of clothing will have a name, color, type, buy and sell prices,
//     and a flag indicating whether it has been bought
public class Clothing {

    private final String name;
    private final String color;
    private final String clothingType;
    private final int buyPrice;
    private final int sellPrice;
    private boolean bought;

    // REQUIRES: color is one of: "red", "green", "blue"
    //           buyPrice and sellPrice are nonnegative
    // EFFECTS: sets the fields of this as the parameters given
    public Clothing(String name, String color, String clothingType, int buyPrice, int sellPrice, boolean bought) {
        this.name = name;
        this.color = color;
        this.clothingType = clothingType;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.bought = bought;
    }

    // A series of getter functions for the fields defined in this class
    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public String getClothingType() {
        return this.clothingType;
    }

    public int getBuyPrice() {
        return this.buyPrice;
    }

    public int getSellPrice() {
        return this.sellPrice;
    }

    public boolean isBought() {
        return this.bought;
    }

    // REQUIRES: this item is not already bought
    // MODIFIES: this
    // EFFECTS: sets the bought field to true
    public void buy() {
        this.bought = true;
    }

    // REQUIRES: this item is currently bought
    // MODIFIES: this
    // EFFECTS: sets the bought field to false
    public void sell() {
        this.bought = false;
    }
}
