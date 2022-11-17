package com.iridevescence.metlas.api.skill.tree;

import java.util.ArrayList;

public interface Unlockable {
    public ArrayList<Unlockable> requirements();
}
