package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OutfitTest {
    private Clothing clothing1;
    private Clothing clothing2;
    private Clothing clothing3;
    private Clothing clothing4;

    private Outfit outfit;

    @BeforeEach
    public void runBefore() {
        clothing1 = new Clothing( "1", "red", "hat", false);
        clothing2 = new Clothing("2", "green", "shirt", false);
        clothing3 = new Clothing("3", "blue", "pants", false);
        clothing4 = new Clothing("3", "red", "shoes", false);

        outfit = new Outfit(clothing1, clothing2, clothing3, clothing4);
    }

    @Test
    public void getHatTest() {
        assertEquals(outfit.getHat(), clothing1);
    }

    @Test
    public void getTopTest() {
        assertEquals(outfit.getTop(), clothing2);
    }

    @Test
    public void getPantsTest() {
        assertEquals(outfit.getPants(), clothing3);
    }

    @Test
    public void getShoesTest() {
        assertEquals(outfit.getShoes(), clothing4);
    }

    @Test
    public void setHatTest() {
        Clothing newHat = new Clothing("2", "blue",
                "hat", false);

        outfit.setHat(newHat);
        assertEquals(outfit.getHat(), newHat);
    }

    @Test
    public void setTopTest() {
        Clothing newTop = new Clothing("2", "blue",
                "top", false);

        outfit.setTop(newTop);
        assertEquals(outfit.getTop(), newTop);
    }

    @Test
    public void setPantsTest() {
        Clothing newPants = new Clothing("2", "blue",
                "pants", false);

        outfit.setPants(newPants);
        assertEquals(outfit.getPants(), newPants);
    }

    @Test
    public void setShoesTest() {
        Clothing newShoes = new Clothing("2", "blue",
                "shoes", false);

        outfit.setShoes(newShoes);
        assertEquals(outfit.getShoes(), newShoes);
    }

    @Test
    public void outfitToJsonTest() {
        assertEquals(outfit.outfitToJson().getJSONObject("hat").getString("name"),
                "1");
        assertEquals(outfit.outfitToJson().getJSONObject("hat").getString("color"),
                "red");
        assertEquals(outfit.outfitToJson().getJSONObject("hat").getString("type"),
                "hat");
        assertEquals(outfit.outfitToJson().getJSONObject("hat").getString("bought"),
                "false");
    }

    @Test
    public void outfitToJsonTest2() {
        Outfit outfit2 = new Outfit(null, null, null, null);
        assertEquals(outfit2.outfitToJson().getString("hat"),
                "none");
        assertEquals(outfit2.outfitToJson().getString("hat"),
                "none");
        assertEquals(outfit2.outfitToJson().getString("hat"),
                "none");
        assertEquals(outfit2.outfitToJson().getString("hat"),
                "none");
    }
}
