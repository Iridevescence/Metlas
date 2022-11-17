package com.iridevescence.metlas.api.skill;

import com.iridevescence.metlas.api.skill.tree.Perk;

import net.minecraft.entity.player.PlayerEntity;

public interface Restriction {
    public enum Type {
        USE_ITEM,
        CRAFT_ITEM,
        EQUIP_ITEM,
        BREAK_BLOCK
    }

    default boolean unlocked(PlayerEntity player) {
        //TODO proper restriction unlock checks
        return false;
    };

    Type type();
    Perk perk();

    public class Item {
        public record Use(net.minecraft.item.Item item, Perk perk) implements Restriction {
            @Override
            public Type type() {
                return Type.USE_ITEM;
            }
        }

        public record Craft(net.minecraft.item.Item item, Perk perk) implements Restriction {
            @Override
            public Type type() {
                return Type.CRAFT_ITEM;
            }
        }


        public record Equip(net.minecraft.item.Item item, Perk perk) implements Restriction {
            @Override
            public Type type() {
                return Type.EQUIP_ITEM;
            }
        }
    }

    public record Block(Block block, Perk perk) implements Restriction {
        @Override
        public Type type() {
            return Type.BREAK_BLOCK;
        }
    }
}
