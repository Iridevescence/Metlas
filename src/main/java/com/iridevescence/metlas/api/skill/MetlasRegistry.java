package com.iridevescence.metlas.api.skill;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

public class MetlasRegistry {
    private static final HashMap<Identifier, Restriction.Item.Use> RESTRICTIONS_ITEM_USE = new HashMap<>();
    private static final HashMap<Identifier, Restriction.Item.Equip> RESTRICTIONS_ITEM_EQUIP = new HashMap<>();
    private static final HashMap<Identifier, Restriction.Item.Craft> RESTRICTIONS_ITEM_CRAFT = new HashMap<>();
    private static final HashMap<Identifier, Restriction.Block> RESTRICTIONS_BLOCK = new HashMap<>();

    private static final HashMap<Item, ArrayList<Restriction.Item>> RESTRICTIONS_ITEM_GMO = new HashMap<>();
    private static final HashMap<Block, ArrayList<Restriction.Block>> RESTRICTIONS_BLOCK_GMO = new HashMap<>();

    public static Restriction.Item.Use registerItemUseRestriction(Identifier name, Restriction.Item.Use restriction) {
        RESTRICTIONS_ITEM_USE.put(name, restriction);
        ArrayList<Restriction.Item> list = RESTRICTIONS_ITEM_GMO.getOrDefault(restriction.item(), new ArrayList<>());
        list.add(restriction);
        RESTRICTIONS_ITEM_GMO.put(restriction.item(), list);
        return restriction;
    }

    public static Restriction.Item.Equip registerItemEquipRestriction(Identifier name, Restriction.Item.Equip restriction) {
        RESTRICTIONS_ITEM_EQUIP.put(name, restriction);
        ArrayList<Restriction.Item> list = RESTRICTIONS_ITEM_GMO.getOrDefault(restriction.item(), new ArrayList<>());
        list.add(restriction);
        RESTRICTIONS_ITEM_GMO.put(restriction.item(), list);
        return restriction;
    }

    public static Restriction.Item.Craft registerItemCraftRestriction(Identifier name, Restriction.Item.Craft restriction) {
        RESTRICTIONS_ITEM_CRAFT.put(name, restriction);
        ArrayList<Restriction.Item> list = RESTRICTIONS_ITEM_GMO.getOrDefault(restriction.item(), new ArrayList<>());
        list.add(restriction);
        RESTRICTIONS_ITEM_GMO.put(restriction.item(), list);
        return restriction;
    }

    public static Restriction.Block registerBlockRestriction(Identifier name, Restriction.Block restriction) {
        RESTRICTIONS_BLOCK.put(name, restriction);
        ArrayList<Restriction.Block> list = RESTRICTIONS_BLOCK_GMO.getOrDefault(restriction.block(), new ArrayList<>());
        list.add(restriction);
        RESTRICTIONS_BLOCK_GMO.put(restriction.block(), list);
        return restriction;
    }

    public static Restriction.Item.Use getItemUseRestriction(Identifier name) {
        return RESTRICTIONS_ITEM_USE.get(name);
    }

    public static Restriction.Item.Equip getItemEquipRestriction(Identifier name) {
        return RESTRICTIONS_ITEM_EQUIP.get(name);
    }

    public static Restriction.Item.Craft getItemCraftRestriction(Identifier name) {
        return RESTRICTIONS_ITEM_CRAFT.get(name);
    }

    public static Restriction.Block getBlockRestriction(Identifier name) {
        return RESTRICTIONS_BLOCK.get(name);
    }

    public static ArrayList<Restriction.Block> getBlockRestrictions(Block block) {
        return RESTRICTIONS_BLOCK_GMO.getOrDefault(block, new ArrayList<>());
    }

    public static ArrayList<Restriction.Item> getItemRestrictions(Item item) {
        return RESTRICTIONS_ITEM_GMO.getOrDefault(item, new ArrayList<>());
    }
}
