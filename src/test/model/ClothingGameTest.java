package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        clothing1 = new Clothing("red hat", "red", "hat", 50, 50, false);
        clothing2 = new Clothing("blue hat", "blue", "hat", 50, 50, false);
        clothing3 = new Clothing("red shirt", "red", "top", 100, 50, false);

        clothing4 = new Clothing("green shirt", "red", "top", 100, 50, false);
        clothing5 = new Clothing("blue pants", "pants", "red",  150, 50, false);
        clothing6 = new Clothing("green shoes", "red", "shoes", 150, 50, false);

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
    public void validClothingTypeTest() {
        assertFalse(clothingGame2.validClothingType("None"));
        assertFalse(clothingGame2.validClothingType("none"));

        assertFalse(clothingGame2.validClothingType("Hat"));
        assertTrue(clothingGame2.validClothingType("hat"));
        assertFalse(clothingGame2.validClothingType("h4t"));
        assertFalse(clothingGame2.validClothingType("hAt"));
        assertTrue(clothingGame2.validClothingType("pants"));
    }
}
