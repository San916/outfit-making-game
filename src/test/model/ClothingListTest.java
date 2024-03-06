package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ClothingListTest {
    private ClothingList clothingList1;
    private ClothingList clothingList2;

    private Clothing clothing1;
    private Clothing clothing2;
    private Clothing clothing3;
    private Clothing clothing4;

    @BeforeEach
    public void runBefore() {
        clothing1 = new Clothing( "1", "red", "hat", 100, 80, false);
        clothing2 = new Clothing("2", "green", "shirt", 120, 70, true);
        clothing3 = new Clothing("3", "blue", "pants", 140, 60, false);
        clothing4 = new Clothing("3", "red", "pants", 140, 60, true);

        clothingList1 = new ClothingList();
        clothingList2 = new ClothingList();

        clothingList2.addClothing(clothing1);
        clothingList2.addClothing(clothing2);
        clothingList2.addClothing(clothing3);
    }

    @Test
    public void getSizeTest() {
        assertEquals(clothingList1.getSize(), 0);
        assertEquals(clothingList2.getSize(), 3);
    }

    @Test
    public void addClothingTest() {
        assertEquals(clothingList2.getAtIndex(0), clothing1);
        assertEquals(clothingList2.getAtIndex(1), clothing2);
        assertEquals(clothingList2.getAtIndex(2), clothing3);

        clothingList1.addClothing(clothing4);
        assertEquals(clothingList1.getAtIndex(0), clothing4);
    }

    @Test
    public void removeClothingTest() {
        clothingList2.removeClothing(1);
        assertEquals(clothingList2.getAtIndex(1), clothing3);
        clothingList2.removeClothing(1);
        assertEquals(clothingList2.getAtIndex(0), clothing1);

        clothingList1.addClothing(clothing1);
        clothingList1.addClothing(clothing2);
        clothingList1.removeClothing(0);
        assertEquals(clothingList1.getAtIndex(0), clothing2);
    }

    @Test
    public void getAtIndexTest() {
        assertEquals(clothingList2.getAtIndex(0), clothing1);
        assertEquals(clothingList2.getAtIndex(1), clothing2);
        assertEquals(clothingList2.getAtIndex(2), clothing3);
    }
}
