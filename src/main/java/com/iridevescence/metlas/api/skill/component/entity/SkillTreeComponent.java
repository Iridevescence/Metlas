package com.iridevescence.metlas.api.skill.component.entity;

import java.util.ArrayList;

import com.iridevescence.metlas.api.skill.tree.Unlockable;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.nbt.NbtCompound;

public class SkillTreeComponent implements PlayerComponent<SkillTreeComponent>, AutoSyncedComponent {
    private final ArrayList<Unlockable> unlocks = new ArrayList<>();

    @Override
    public void readFromNbt(NbtCompound tag) {
        // TODO Auto-generated method stub
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        // TODO Auto-generated method stub
        
    }
    
}
