package com.iridevescence.metlas.api.skill.tree;

import net.minecraft.util.Identifier;

import java.util.ArrayList;

import com.iridevescence.metlas.api.skill.Restriction;

public record Perk(Identifier skill, Identifier name, ArrayList<Restriction> unlocks, ArrayList<Unlockable> requirements) implements Unlockable {
    public ArrayList<Unlockable> requirements() {
        return this.requirements;
    }

    @Override
    public Identifier identifier() {
        return this.name;
    }
}
