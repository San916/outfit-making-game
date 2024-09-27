package model;

import exceptions.ClothingNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.ClothingListFileReader;
import persistence.OutfitListFileReader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClothingGameTest {
    ClothingGame clothingGame1;
    ClothingGame clothingGame2;

    ClothingList shopClothes;
    ClothingList closetClothes;

    List<String> clothingTypes;

    Clothing clothing1;
    Clothing clothing2;
    Clothing clothing3;
    Clothing clothing4;
    Clothing clothing5;
    Clothing clothing6;

    @BeforeEach
    public void runBefore1() {
        clothing1 = new Clothing("red hat", "red", "hat", false);
        clothing2 = new Clothing("blue hat", "blue", "hat", false);
        clothing3 = new Clothing("red shirt", "red", "top", false);

        clothing4 = new Clothing("green shirt", "red", "top", false);
        clothing5 = new Clothing("blue pants", "red", "pants",  false);
        clothing6 = new Clothing("green shoes", "red", "shoes", false);

        closetClothes = new ClothingList();
        closetClothes.addClothing(clothing1);
        closetClothes.addClothing(clothing2);
        closetClothes.addClothing(clothing3);

        shopClothes = new ClothingList();
        shopClothes.addClothing(clothing4);
        shopClothes.addClothing(clothing5);
        shopClothes.addClothing(clothing6);

        clothingTypes = new ArrayList<String>();
        clothingTypes.add("hat");
        clothingTypes.add("top");
        clothingTypes.add("pants");
        clothingTypes.add("shoes");
    }

    @BeforeEach
    public void runBefore2() {
        clothingGame1 = new ClothingGame();
        clothingGame2 = new ClothingGame(clothingTypes, closetClothes, shopClothes, "hat");
    }

    @Test
    public void returnClosetClothesListTest() {
        ClothingList emptyShop = new ClothingList();
        ClothingList emptyCloset = new ClothingList();
        clothingGame1 = new ClothingGame(clothingTypes, emptyCloset, emptyShop, "none");

        assertEquals(clothingGame2.returnClosetClothesList(), closetClothes);
        assertEquals(clothingGame1.returnClosetClothesList(), emptyCloset);
    }

    @Test
    public void returnShopClothesListTest() {
        ClothingList emptyShop = new ClothingList();
        ClothingList emptyCloset = new ClothingList();
        clothingGame1 = new ClothingGame(clothingTypes, emptyCloset, emptyShop, "none");

        assertEquals(clothingGame2.returnShopClothesList(), shopClothes);
        assertEquals(clothingGame1.returnShopClothesList(), emptyShop);
    }

    @Test
    public void returnClosetFilterTest() {
        assertEquals(clothingGame1.returnClosetFilter(), "none");
        assertEquals(clothingGame2.returnClosetFilter(), "hat");
    }

    @Test
    public void setClosetFilterTest() {
        clothingGame1.setClosetFilter("hat");
        clothingGame2.setClosetFilter("pants");
        assertEquals(clothingGame1.returnClosetFilter(), "hat");
        assertEquals(clothingGame2.returnClosetFilter(), "pants");
        clothingGame2.setClosetFilter("none");
        assertEquals(clothingGame2.returnClosetFilter(), "none");
    }

    @Test
    public void buyClothingAtIndexTest() {
        clothingGame2.buyClothingAtIndex(1);
        clothingGame2.buyClothingAtIndex(0);
        assertEquals(clothingGame2.returnClosetClothesList().getAtIndex(3), clothing5);
        assertEquals(clothingGame2.returnClosetClothesList().getAtIndex(4), clothing4);
    }

    @Test
    public void buyClothingAtIndexTest2() {
        clothingGame2.returnClosetClothesList().removeClothing(0);
        clothingGame2.returnClosetClothesList().removeClothing(0);
        clothingGame2.returnClosetClothesList().removeClothing(0);

        clothingGame2.buyClothingAtIndex(1);
        clothingGame2.buyClothingAtIndex(0);

        assertEquals(clothingGame2.returnClosetClothesList().getAtIndex(0), clothing5);
        assertEquals(clothingGame2.returnClosetClothesList().getAtIndex(1), clothing4);
    }

    @Test
    public void sellClothingAtIndexTest() {
        clothingGame2.sellClothingAtIndex(1);
        clothingGame2.sellClothingAtIndex(0);
        assertEquals(clothingGame2.returnShopClothesList().getAtIndex(3), clothing2);
        assertEquals(clothingGame2.returnShopClothesList().getAtIndex(4), clothing1);
    }

    @Test
    public void validClothingTypeTest() {
        assertFalse(clothingGame2.validClothingType("None"));
        assertFalse(clothingGame2.validClothingType("none"));

        assertFalse(clothingGame2.validClothingType("Hat"));
        assertTrue(clothingGame2.validClothingType("hat"));
        assertFalse(clothingGame2.validClothingType("h4t"));
        assertFalse(clothingGame2.validClothingType("hAt"));
        assertTrue(clothingGame2.validClothingType("pants"));
    }

    @Test
    public void saveClothingListsTest() {
        clothingGame2 = new ClothingGame(clothingTypes, closetClothes, shopClothes, "none");
        clothingGame2.saveClothingLists();

        ClothingListFileReader closetFileReader =  new ClothingListFileReader(clothingGame2.CLOSET_CLOTHING_LIST_JSON);
        ClothingListFileReader shopFileReader =  new ClothingListFileReader(clothingGame2.SHOP_CLOTHING_LIST_JSON);

        try {
            closetClothes = closetFileReader.clothingListFileReader();
            shopClothes = shopFileReader.clothingListFileReader();
            assertEquals(closetClothes.getSize(), 3);
            assertEquals(shopClothes.getSize(), 3);

        } catch (IOException e) {
            fail("There should be no exception");
        }
    }

    @Test
    public void loadClothingListsTest() {
        clothingGame2 = new ClothingGame(clothingTypes, closetClothes, shopClothes, "none");
        clothingGame2.saveClothingLists(); // Save from one game

        ClothingListFileReader closetFileReader =  new ClothingListFileReader(clothingGame2.CLOSET_CLOTHING_LIST_JSON);
        ClothingListFileReader shopFileReader =  new ClothingListFileReader(clothingGame2.SHOP_CLOTHING_LIST_JSON);

        clothingGame1.loadClothingLists(); // Load to another game
        assertEquals(clothingGame1.returnClosetClothesList().getAtIndex(0).getName(), clothing1.getName());
        assertEquals(clothingGame1.returnShopClothesList().getAtIndex(0).getName(), clothing4.getName());
    }

    @Test
    public void saveOutfitListTest() {
        clothingGame2 = new ClothingGame(clothingTypes, closetClothes, shopClothes, "none");
        clothingGame2.returnOutfitList().addOutfit(new Outfit(clothing1, clothing3, clothing5, clothing6));
        clothingGame2.saveOutfitList();

        OutfitList outfitList;

        OutfitListFileReader outfitFileReader =  new OutfitListFileReader(clothingGame2.OUTFIT_LIST_JSON);

        try {
            outfitList = outfitFileReader.outfitListFileReader();
            assertEquals(outfitList.getAtIndex(0).getPants().getName(), "blue pants");

        } catch (IOException e) {
            fail("There should be no exception");
        }
    }

    @Test
    public void loadOutfitListTest() {
        clothingGame2 = new ClothingGame(clothingTypes, closetClothes, shopClothes, "none");
        clothingGame2.returnOutfitList().addOutfit(new Outfit(clothing1, clothing3, clothing5, clothing6));
        clothingGame2.saveOutfitList(); // Save from one game

        OutfitList outfitList;

        OutfitListFileReader outfitFileReader =  new OutfitListFileReader(clothingGame2.OUTFIT_LIST_JSON);

        clothingGame1.loadOutfitList(); // Load to another game
        assertEquals(clothingGame1.returnOutfitList().getAtIndex(0).getPants().getName(), "blue pants");
    }

    @Test
    public void returnClothingByNameTest() {
        try {
            clothingGame2.returnClothingByName("pink ball");
            fail("There should be an exception thrown");
        } catch (ClothingNotFoundException e) {

        }
    }

    @Test
    public void returnClothingByNameTest2() {
        try {
            assertEquals(clothingGame2.returnClothingByName("red hat"), clothing1);
            assertEquals(clothingGame2.returnClothingByName("blue pants"), clothing5);
        } catch (ClothingNotFoundException e) {
            fail("There should not be an exception thrown");
        }
    }

    @Test
    public void syncOutfitTest() {
        // clothing1Copy goes into clothingGame2.returnOutfitList().getAtIndex(0).getHat()
        Clothing clothing1Copy = new Clothing("red hat", "red", "hat", false);
        Outfit outfit = new Outfit (clothing1Copy, clothing3, clothing5, clothing6);
        clothingGame2.returnOutfitList().addOutfit(outfit);
        // Before sync, we can see that despite having the same descriptions as clothing1, clothing1Copy is not the same
        assertFalse(clothingGame2.returnOutfitList().getAtIndex(0).getHat() == clothing1);

        clothingGame2.syncOutfit(outfit);
        // After syncing, we can see that they are now the same
        assertTrue(clothingGame2.returnOutfitList().getAtIndex(0).getHat() == clothing1);
    }

    @Test
    public void syncOutfitsTest() {
        // clothing1Copy goes into clothingGame2.returnOutfitList().getAtIndex(0).getHat()
        Clothing clothing1Copy = new Clothing("red hat", "red", "hat", false);
        Outfit outfit = new Outfit (clothing1Copy, clothing3, clothing5, clothing6);
        clothingGame2.returnOutfitList().addOutfit(outfit);
        // Before sync, we can see that despite having the same descriptions as clothing1, clothing1Copy is not the same
        assertFalse(clothingGame2.returnOutfitList().getAtIndex(0).getHat() == clothing1);

        clothingGame2.syncOutfits();
        // After syncing, we can see that they are now the same
        assertTrue(clothingGame2.returnOutfitList().getAtIndex(0).getHat() == clothing1);
    }
}
