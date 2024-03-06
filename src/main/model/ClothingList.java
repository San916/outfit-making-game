package model;

import java.util.ArrayList;
import java.util.List;

public class ClothingList {
    private List<Clothing> clothingList;

    public ClothingList() {
        this.clothingList = new ArrayList<Clothing>();
    }

    public int getSize() {
        return clothingList.size();
    }

    public void addClothing(Clothing clothing) {
        clothingList.add(clothing);
    }

    public void removeClothing(int index) {
        clothingList.remove(index);
    }

    public Clothing getAtIndex(int index) {
        return this.clothingList.get(index);
    }
}
