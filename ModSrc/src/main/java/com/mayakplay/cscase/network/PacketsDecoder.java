package com.mayakplay.cscase.network;

import com.mayakplay.cscase.pojo.Case;
import com.mayakplay.cscase.pojo.CaseItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Константин on 07.01.2016.
 */
public class PacketsDecoder {

    public static List<Case> getCases() {
        List<Case> cases = new ArrayList<Case>();

        String[] decode = Recieve.CASES_LIST.split(",");

        for (int i = 0; i < decode.length / 3; i++) {
            String name = decode[i * 3];
            int price = Integer.parseInt(decode[i * 3 + 1]);
            String texture = decode[i * 3 + 2];

            cases.add(new Case(name, price, texture));
        }

        //cases.add(new Case("Пики точеные", 228, "http://cs414419.vk.me/u10069748/docs/5fa7f5dc38f6/Case.png"));
        //cases.add(new Case("Хуи дроченые", 1488, "http://cs404426.vk.me/u10069748/docs/1b9ea2780611/CaseLol.png"));

        return cases;
    }

    public static List<CaseItem> getCaseItemsList() {
        List<CaseItem> items = new ArrayList<CaseItem>();

        String[] decode = Recieve.CURRENT_CASE_ITEMS_LIST.split(",");

        for (int i = 0; i < decode.length / 3; i++) {
            int id = Integer.parseInt(decode[i * 3]);
            int meta = Integer.parseInt(decode[i * 3 + 1]);
            int rarity = Integer.parseInt(decode[i * 3 + 2]);

            ItemStack is = new ItemStack(Item.getItemById(id));
            is.setItemDamage(meta);
            items.add(new CaseItem(is, rarity));
        }

        //items.add(new CaseItem(Items.brick, 1));
        //items.add(new CaseItem(Items.brick, 2));
        //items.add(new CaseItem(Items.brick, 3));
        //items.add(new CaseItem(Items.brick, 4));
        //items.add(new CaseItem(Items.brick, 5));

        return items;
    }

    // 40
    // 10
    // 4
    // 2
    // 1
    public static List<CaseItem> getRandomItemsForRoll() {
        List<CaseItem> itemsFin = new ArrayList<CaseItem>();
        List<CaseItem> items1 = new ArrayList<CaseItem>();
        List<CaseItem> items2 = new ArrayList<CaseItem>();
        List<CaseItem> items3 = new ArrayList<CaseItem>();
        List<CaseItem> items4 = new ArrayList<CaseItem>();
        List<CaseItem> items5 = new ArrayList<CaseItem>();

        for (int i = 0; i < getCaseItemsList().size(); i++) {
            switch (getCaseItemsList().get(i).getRarity()) {
                case 1:
                    items1.add(getCaseItemsList().get(i));
                    break;
                case 2:
                    items2.add(getCaseItemsList().get(i));
                    break;
                case 3:
                    items3.add(getCaseItemsList().get(i));
                    break;
                case 4:
                    items4.add(getCaseItemsList().get(i));
                    break;
                case 5:
                    items5.add(getCaseItemsList().get(i));
                    break;
            }
        }

        for (int i = 0; i < 47; i++) {
            itemsFin.add(items1.get(randInt(0, items1.size()-1)));
        }

        for (int i = 0; i < 7; i++) {
            itemsFin.add(items2.get(randInt(0, items2.size()-1)));
        }

        for (int i = 0; i < 3; i++) {
            itemsFin.add(items3.get(randInt(0, items3.size()-1)));
        }

        for (int i = 0; i < 1; i++) {
            itemsFin.add(items4.get(randInt(0, items4.size()-1)));
        }

        for (int i = 0; i < 1; i++) {
            itemsFin.add(items5.get(randInt(0, items5.size()-1)));
        }

        Collections.shuffle(itemsFin);

        String[] decode = Recieve.WON_ITEM.split(",");
        ItemStack is = new ItemStack(Item.getItemById(Integer.parseInt(decode[0])), Integer.parseInt(decode[2]));
        is.setItemDamage(Integer.parseInt(decode[1]));

        itemsFin.add(57, new CaseItem(is, Integer.parseInt(decode[3])));


        return itemsFin;
    }

    public static int randInt(int min, int max) {
        int randomNum = new Random().nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static float randFloat(float min, float max) {
        return min + new Random().nextFloat() * (max - min);
    }

}
