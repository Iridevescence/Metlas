package com.iridevescence.metlas.api.skill;

import com.iridevescence.metlas.Metlas;
import com.iridevescence.metlas.api.skill.tree.Unlockable;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.player.PlayerEntity;

import java.util.concurrent.atomic.AtomicBoolean;

public interface Restriction {
    default boolean hasUnlocked(PlayerEntity player) {
        return Metlas.SKILL_TREE.get(player).isUnlocked(this.requirement()) || player.isCreative();
    }

    Unlockable requirement();

    interface Item extends Restriction {
        record Use(net.minecraft.item.Item item, Unlockable requirement) implements Restriction, Item {}

        record Craft(net.minecraft.item.Item item, Unlockable requirement) implements Restriction, Item {}

        record Equip(net.minecraft.item.Item item, Unlockable requirement) implements Restriction, Item {}
    }

    record Block(net.minecraft.block.Block block, Unlockable requirement) implements Restriction {}

    record Generic(Unlockable requirement) implements Restriction {}

    static void registerCallbacks() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            net.minecraft.block.Block block = state.getBlock();

            AtomicBoolean canBreak = new AtomicBoolean(true);
            MetlasRegistry.getBlockRestrictions(block).forEach(restriction -> {
                if (!restriction.hasUnlocked(player)) {
                    canBreak.set(false);
                }
            });
            return canBreak.get();
        });
    }
}
