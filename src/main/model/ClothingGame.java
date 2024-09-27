package model;

import exceptions.ClothingNotFoundException;
import persistence.ClothingListFileReader;
import persistence.ClothingListFileWriter;
import persistence.OutfitListFileReader;
import persistence.OutfitListFileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// ClothingGame stores fields and methods that are relevant to the state of the game being played
// The information and methods in this class will be used for both the console and
//     the graphical applications of this game
// This means that this class will contain a significant amount of fields and methods related to
//     clothes, as this abstraction will be used for both the console and graphical version of the game
public class ClothingGame {
    // Make path variables final so that they can be accessed from anywhere
    public static final String CLOSET_CLOTHING_LIST_JSON = "./data/closet_clothing_list.json";
    public static final String SHOP_CLOTHING_LIST_JSON = "./data/shop_clothing_list.json";

    public static final String OUTFIT_LIST_JSON = "./data/outfit_list.json";

    private final List<String> clothingTypes; // A list of the clothing Types

    // List of the clothes in the clothes and in the shop
    private ClothingList closetClothes;
    private ClothingList shopClothes;
    private OutfitList outfits; // A list of the clothing Types

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

        clothing1 = new Clothing("red hat", "red", "hat", false);
        clothing2 = new Clothing("blue hat", "blue", "hat", false);
        clothing3 = new Clothing("red shirt", "red", "top", false);
        clothing4 = new Clothing("green shirt", "green", "top", false);
        clothing5 = new Clothing("blue pants", "blue", "pants",  false);
        clothing6 = new Clothing("green shoes", "green", "shoes", false);

        shopClothes.addClothing(clothing1);
        shopClothes.addClothing(clothing2);
        shopClothes.addClothing(clothing3);
        shopClothes.addClothing(clothing4);
        shopClothes.addClothing(clothing5);
        shopClothes.addClothing(clothing6);

        outfits = new OutfitList();
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
        outfits = new OutfitList();
    }

    // Series of simple getter/setter functions
    public ClothingList returnClosetClothesList() {
        return closetClothes;
    }

    public ClothingList returnShopClothesList() {
        return shopClothes;
    }

    public OutfitList returnOutfitList() {
        return outfits;
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
        shopClothes.getAtIndex(index).buy();
        shopClothes.removeClothing(index);
    }

    // REQUIRES: the index be within 0 and the size of the list of owned clothes minus one, inclusive
    // MODIFIES: this
    // EFFECTS: sells a piece of clothing determined by index and moves it from the closet to the shop
    public void sellClothingAtIndex(int index) {
        shopClothes.addClothing(closetClothes.getAtIndex(index));
        closetClothes.getAtIndex(index).sell();
        closetClothes.removeClothing(index);
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

    // MODIFIES: this
    // EFFECTS: saves both clothing lists and the outfit list
    // This wont be tested because it would be too redundant
    public void saveEverything() {
        saveClothingLists();
        saveOutfitList();
    }

    // MODIFIES: this
    // EFFECTS: load both clothing lists and the outfit list
    // This wont be tested because it would be too redundant
    public void loadEverything() {
        loadClothingLists();
        loadOutfitList();
    }

    // MODIFIES: this
    // EFFECTS: Writes the information in both clothing lists onto separate files
    public void saveClothingLists() {
        ClothingListFileWriter closetFileWriter = new ClothingListFileWriter(CLOSET_CLOTHING_LIST_JSON);
        ClothingListFileWriter shopFileWriter = new ClothingListFileWriter(SHOP_CLOTHING_LIST_JSON);

        try {
            closetFileWriter.open();
            shopFileWriter.open();
            closetFileWriter.write(closetClothes, true, currentClosetFilter);
            shopFileWriter.write(shopClothes, false, "");
            closetFileWriter.close();
            shopFileWriter.close();
            System.out.println("Clothing list files successfully saved");
        } catch (FileNotFoundException e) {
            System.out.println("Clothing list files saving failed");
        }
    }

    // MODIFIES: this
    // EFFECTS: Reads the information in both clothing list files onto the clothing list
    public void loadClothingLists() {
        ClothingListFileReader closetFileReader =  new ClothingListFileReader(CLOSET_CLOTHING_LIST_JSON);
        ClothingListFileReader shopFileReader =  new ClothingListFileReader(SHOP_CLOTHING_LIST_JSON);

        try {
            closetClothes = closetFileReader.clothingListFileReader();
            shopClothes = shopFileReader.clothingListFileReader();
            currentClosetFilter = closetFileReader.extractFilter();
            System.out.println("Clothing list files successfully loaded");
        } catch (IOException e) {
            System.out.println("Clothing list files loading failed");
        }
    }

    // MODIFIES: this
    // EFFECTS: Writes the information in the outfit list into a file
    public void saveOutfitList() {
        System.out.println(outfits.getSize());
        OutfitListFileWriter outfitFileWriter = new OutfitListFileWriter(OUTFIT_LIST_JSON);

        try {
            outfitFileWriter.open();
            outfitFileWriter.write(outfits);
            outfitFileWriter.close();
            System.out.println("Outfit list files successfully saved");
        } catch (FileNotFoundException e) {
            System.out.println("Outfit list files saving failed");
        }
    }

    // MODIFIES: this
    // EFFECTS: Reads the information in the outfit list file onto the outfit list
    public void loadOutfitList() {
        OutfitListFileReader outfitFileReader = new OutfitListFileReader(OUTFIT_LIST_JSON);

        try {
            outfits = outfitFileReader.outfitListFileReader();
            System.out.println("Outfit list file successfully loaded");
        } catch (IOException e) {
            System.out.println("Outfit list file loading failed");
        }

        syncOutfits();
    }

    // MODIFIES: this
    // EFFECTS: Synchronizes the outfits to make sure the outfit list and the clothing lists share the same instances
    //              of objects, as opposed to two separate instances of identical clothing items (this would make it
    //              so that a change to one clothing item in the closet would not be reflected in any outfits that use
    //              that piece of clothing
    public void syncOutfits() {
        for (int i = 0; i < outfits.getSize(); i++) {
            syncOutfit(outfits.getAtIndex(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: Synchronizes a single outfit
    public void syncOutfit(Outfit outfit) {
        try {
            if (outfit.getHat() != null) {
                outfit.setHat(returnClothingByName(outfit.getHat().getName()));
            }
            if (outfit.getTop() != null) {
                outfit.setTop(returnClothingByName(outfit.getTop().getName()));
            }
            if (outfit.getPants() != null) {
                outfit.setPants(returnClothingByName(outfit.getPants().getName()));
            }
            if (outfit.getShoes() != null) {
                outfit.setShoes(returnClothingByName(outfit.getShoes().getName()));
            }
        } catch (ClothingNotFoundException clothingNotFound) {
            System.out.println("Clothing by name not found!");
        }
    }

    // EFFECTS: Given the name of a piece of clothing, return the corresponding piece of clothing
    public Clothing returnClothingByName(String name) throws ClothingNotFoundException {
        for (int i = 0; i < closetClothes.getSize(); i++) {
            if (name.equals(closetClothes.getAtIndex(i).getName())) {
                return closetClothes.getAtIndex(i);
            }
        }
        for (int i = 0; i < shopClothes.getSize(); i++) {
            if (name.equals(shopClothes.getAtIndex(i).getName())) {
                return shopClothes.getAtIndex(i);
            }
        }
        throw new ClothingNotFoundException();
    }
}
