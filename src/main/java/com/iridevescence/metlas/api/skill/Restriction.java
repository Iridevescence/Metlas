package com.iridevescence.metlas.api.skill;

import com.iridevescence.metlas.Metlas;
import com.iridevescence.metlas.api.skill.tree.Unlockable;

import net.minecraft.entity.player.PlayerEntity;

public interface Restriction {
    public enum Type {
        USE_ITEM,
        CRAFT_ITEM,
        EQUIP_ITEM,
        BREAK_BLOCK
    }

    default boolean unlocked(PlayerEntity player) {
        return Metlas.SKILL_TREE.get(player).isUnlocked(this.requirement());
    };

    Type type();
    Unlockable requirement();

    public class Item {
        public record Use(net.minecraft.item.Item item, Unlockable requirement) implements Restriction {
            @Override
            public Type type() {
                return Type.USE_ITEM;
            }
        }

        public record Craft(net.minecraft.item.Item item, Unlockable requirement) implements Restriction {
            @Override
            public Type type() {
                return Type.CRAFT_ITEM;
            }
        }


        public record Equip(net.minecraft.item.Item item, Unlockable requirement) implements Restriction {
            @Override
            public Type type() {
                return Type.EQUIP_ITEM;
            }
        }
    }

    public record Block(Block block, Unlockable requirement) implements Restriction {
        @Override
        public Type type() {
            return Type.BREAK_BLOCK;
        }
    }
}
