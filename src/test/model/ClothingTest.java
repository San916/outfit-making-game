package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClothingTest {
    private Clothing clothing1;
    private Clothing clothing2;
    private Clothing clothing3;

    @BeforeEach
    public void runBefore() {
        clothing1 = new Clothing( "1", "red", "hat", 100, 80, false);
        clothing2 = new Clothing("2", "green", "shirt", 120, 70, true);
        clothing3 = new Clothing("3", "blue", "pants", 140, 60, false);

    }

    @Test
    public void getNameTest() {
        assertEquals(clothing1.getName(), "1");
        assertEquals(clothing2.getName(), "2");
        assertEquals(clothing3.getName(), "3");
    }

    @Test
    public void getColorTest() {
        assertEquals(clothing1.getColor(), "red");
        assertEquals(clothing2.getColor(), "green");
        assertEquals(clothing3.getColor(), "blue");
    }

    @Test
    public void getClothingTypeTest() {
        assertEquals(clothing1.getClothingType(), "hat");
        assertEquals(clothing2.getClothingType(), "shirt");
        assertEquals(clothing3.getClothingType(), "pants");
    }

    @Test
    public void getBuyPriceTest() {
        assertEquals(clothing1.getBuyPrice(), 100);
        assertEquals(clothing2.getBuyPrice(), 120);
        assertEquals(clothing3.getBuyPrice(), 140);
    }

    @Test
    public void getSellPriceTest() {
        assertEquals(clothing1.getSellPrice(), 80);
        assertEquals(clothing2.getSellPrice(), 70);
        assertEquals(clothing3.getSellPrice(), 60);
    }

    @Test
    public void isBoughtTest() {
        assertFalse(clothing1.isBought());
        assertTrue(clothing2.isBought());
        assertFalse(clothing3.isBought());
    }

    @Test
    public void buyTest() {
        assertFalse(clothing1.isBought());
        clothing1.buy();
        assertTrue(clothing1.isBought());
    }

    @Test
    public void sellTest() {
        assertTrue(clothing2.isBought());
        clothing2.sell();
        assertFalse(clothing2.isBought());
    }
}
