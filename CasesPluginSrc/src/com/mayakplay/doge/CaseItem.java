package com.mayakplay.doge;

/**
 * Created by Константин on 10.01.2016.
 */
public class CaseItem {

    private int id;
    private int meta;
    private int rarity;
    private int minStackSize;
    private int maxStackSize;
    private int caseId;

    public CaseItem(int id, int meta, int rarity, int minStackSize, int maxStackSize, int caseId) {
        this.id = id;
        this.meta = meta;
        this.rarity = rarity;
        this.minStackSize = minStackSize;
        this.maxStackSize = maxStackSize;
        this.caseId = caseId;
    }

    public int getId() {
        return id;
    }

    public int getMeta() {
        return meta;
    }

    public int getRarity() {
        return rarity;
    }

    public int getMinStackSize() {
        return minStackSize;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public int getCaseId() {
        return caseId;
    }
}
