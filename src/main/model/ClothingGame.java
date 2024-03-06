package model;

import java.util.ArrayList;
import java.util.List;

// ClothingGame stores fields and methods that are relevant to the state of the game being played
// The information and methods in this class will be used for both the console and
//     the graphical applications of this game
// This means that this class will contain a significant amount of fields and methods related to
//     clothes, as this abstraction will be used for both the console and graphical version of the game
public class ClothingGame {
    private final List<String> clothingTypes; // A list of the clothing Types

    // List of the clothes in the clothes and in the shop
    ClothingList closetClothes;
    ClothingList shopClothes;

    // Stores any filters applied onto the closet
    String currentClosetFilter = "none";

    // Placeholder clothes that will be put into the shop at the start of the game
    Clothing clothing1;
    Clothing clothing2;
    Clothing clothing3;
    Clothing clothing4;
    Clothing clothing5;
    Clothing clothing6;

    // MODIFIES: this
    // EFFECTS: default constructor for ClothingGame. Sets  the clothingTypes, closetClothes,
    //              and shopClothes fields.
    public ClothingGame() {
        clothingTypes = new ArrayList<String>();
        clothingTypes.add("hat");
        clothingTypes.add("top");
        clothingTypes.add("pants");
        clothingTypes.add("shoes");

        closetClothes = new ClothingList();
        shopClothes = new ClothingList();

        clothing1 = new Clothing("red hat", "red", "hat", 50, 50, false);
        clothing2 = new Clothing("blue hat", "blue", "hat", 50, 50, false);
        clothing3 = new Clothing("red shirt", "red", "top", 100, 50, false);
        clothing4 = new Clothing("green shirt", "green", "top", 100, 50, false);
        clothing5 = new Clothing("blue pants", "blue", "pants",  150, 50, false);
        clothing6 = new Clothing("green shoes", "green", "shoes", 150, 50, false);

        shopClothes.addClothing(clothing1);
        shopClothes.addClothing(clothing2);
        shopClothes.addClothing(clothing3);
        shopClothes.addClothing(clothing4);
        shopClothes.addClothing(clothing5);
        shopClothes.addClothing(clothing6);
    }

    // MODIFIES: this
    // EFFECTS: General constructor of ClothingGame, sets the clothingTypes, closetClothes,
    //              shopClothes, and currentClosetFilter fields as given by the caller
    public ClothingGame(List<String> clothingTypes, ClothingList closetClothes,
                        ClothingList shopClothes, String currentClosetFilter) {
        this.clothingTypes = clothingTypes;
        this.closetClothes = closetClothes;
        this.shopClothes = shopClothes;
        this.currentClosetFilter = currentClosetFilter;
    }

    // Series of simple getter/setter functions
    public ClothingList returnClosetClothesList() {
        return closetClothes;
    }

    public ClothingList returnShopClothesList() {
        return shopClothes;
    }

    public String returnClosetFilter() {
        return currentClosetFilter;
    }

    public void setClosetFilter(String filter) {
        currentClosetFilter = filter;
    }

    // REQUIRES: the index be within 0 and the size of the list of shop clothes minus one, inclusive
    // MODIFIES: this
    // EFFECTS: buys a piece of clothing determined by index and moves it from the shop to the closet
    public void buyClothingAtIndex(int index) {
        closetClothes.addClothing(shopClothes.getAtIndex(index));
        shopClothes.removeClothing(index);
    }

    // EFFECTS: returns true if the string given is a valid clothing type
    public boolean validClothingType(String str) {
        for (int i = 0; i < clothingTypes.size(); i++) {
            if (str.equals(clothingTypes.get(i))) {
                return true;
            }
        }
        return false;
    }
}
