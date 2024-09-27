package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutfitListTest {

    private Clothing clothing1;
    private Clothing clothing2;
    private Clothing clothing3;
    private Clothing clothing4;

    private Outfit outfit1;
    private Outfit outfit2;

    private OutfitList outfitList1;
    private OutfitList outfitList2;

    @BeforeEach
    public void runBefore() {
        clothing1 = new Clothing( "1", "red", "hat", false);
        clothing2 = new Clothing("2", "green", "shirt", false);
        clothing3 = new Clothing("3", "blue", "pants", false);
        clothing4 = new Clothing("3", "red", "shoes", false);

        outfit1 = new Outfit(clothing1, clothing2, clothing3, clothing4);
        outfit2 = new Outfit(null, null, null, null);

        outfitList1 = new OutfitList();
        outfitList2 = new OutfitList();

        outfitList1.addOutfit(outfit1);
        outfitList1.addOutfit(outfit2);
    }

    @Test
    public void getSizeTest() {
        assertEquals(outfitList1.getSize(), 2);
        assertEquals(outfitList2.getSize(), 0);
    }

    @Test
    public void addOutfitTest() {
        assertEquals(outfitList2.getSize(), 0);
        outfitList2.addOutfit(outfit1);
        assertEquals(outfitList2.getAtIndex(0), outfit1);
    }

    @Test
    public void removeOutfitTest() {
        assertEquals(outfitList1.getSize(), 2);
        outfitList1.removeOutfit(0);
        assertEquals(outfitList1.getAtIndex(0), outfit2);
    }

    @Test
    public void getAtIndexTest() {
        assertEquals(outfitList1.getAtIndex(0), outfit1);
        assertEquals(outfitList1.getAtIndex(1), outfit2);
    }

    @Test
    public void clothingListToJsonTest() {
        JSONObject json = outfitList1.outfitListToJson();

        JSONArray jsonArray = json.getJSONArray("outfitList");
        assertEquals(((JSONObject) jsonArray.get(0)).getJSONObject("hat").getString("name"), "1");
        assertEquals(((JSONObject) jsonArray.get(1)).getString("hat"), "none");
    }
}
