package com.mayakplay.cscase.pojo;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Константин on 08.01.2016.
 */
public class CaseItem {

    private ItemStack item;
    private int rarity;

    public CaseItem(ItemStack item, int rarity) {
        this.item = item;
        this.rarity = rarity;
    }

    public ItemStack getItemStack() {
        return item;
    }

    public int getRarity() {
        return rarity;
    }
}
