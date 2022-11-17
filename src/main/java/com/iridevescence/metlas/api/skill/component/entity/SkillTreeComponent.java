package com.iridevescence.metlas.api.skill.component.entity;

import java.util.ArrayList;

import com.iridevescence.metlas.Metlas;
import com.iridevescence.metlas.api.skill.tree.Unlockable;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Identifier;

public class SkillTreeComponent implements PlayerComponent<SkillTreeComponent>, AutoSyncedComponent {
    private final ArrayList<Unlockable> unlocks = new ArrayList<>();

    private final PlayerEntity owner;

    public SkillTreeComponent(PlayerEntity owner) {
        this.owner = owner;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        if (tag.get("SkillTree") instanceof NbtList list) {
            list.forEach(identifier -> {
                if (identifier instanceof NbtString) {
                    this.unlocks.add(new Unlockable(new Identifier(identifier.toString())));
                }
            });
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        NbtList list = new NbtList();
        this.unlocks.forEach(unlock -> {
            list.add(NbtString.of(unlock.identifier().toString()));
        });
        tag.put("SkillTree", list);
    }

    public boolean isUnlocked(Unlockable checked) {
        return this.unlocks.contains(checked);
    }

    public void unlock(Unlockable target) {
        this.unlocks.add(target);
        Metlas.SKILL_TREE.sync(this.owner, Metlas.SKILL_TREE.get(this.owner));
    }
}
