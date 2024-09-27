package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClothingTest {
    private Clothing clothing1;
    private Clothing clothing2;
    private Clothing clothing3;

    @BeforeEach
    public void runBefore() {
        clothing1 = new Clothing( "1", "red", "hat", false);
        clothing2 = new Clothing("2", "green", "shirt", true);
        clothing3 = new Clothing("3", "blue", "pants", false);

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

    @Test
    public void boolToStringTest() {
        assertEquals(clothing1.boolToString(true), "true");
        assertEquals(clothing1.boolToString(false), "false");
    }

    @Test
    public void clothingToJsonTest() {
        assertEquals(clothing1.clothingToJson().getString("name"), "1");
        assertEquals(clothing1.clothingToJson().getString("color"), "red");
        assertEquals(clothing1.clothingToJson().getString("type"), "hat");
        assertEquals(clothing1.clothingToJson().getString("bought"), "false");
    }
}
