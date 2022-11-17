package com.iridevescence.metlas.api.skill.tree;

import java.util.ArrayList;

import net.minecraft.util.Identifier;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;
import net.minecraft.nbt.NbtList;

public interface Unlockable {
    public Identifier identifier();

    public ArrayList<Unlockable> requirements();

    /**
     * Serializes this unlockable to a Named Binary Tag (NBT).
     * @implNote The default implementation is as follows:
     *      - Create an NbtList
     *      - Add this unlockable's identifier as the first (0th) element
     *      - Subsequent elements are all of this unlockable's requirements.
     * @return
     */
    default NbtElement serialize() {
        NbtList list = new NbtList();

        list.add(NbtString.of(this.identifier().toString()));
        this.requirements().forEach(requirement -> {
            list.add(NbtString.of(requirement.identifier().toString()));
        });

        return list;
    }
}
