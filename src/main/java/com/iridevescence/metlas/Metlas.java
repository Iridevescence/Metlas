package com.iridevescence.metlas;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iridevescence.metlas.api.skill.component.entity.SkillTreeComponent;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class Metlas implements ModInitializer, EntityComponentInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Metlas");

    public static final ComponentKey<SkillTreeComponent> SKILL_TREE =
        ComponentRegistry.getOrCreate(new Identifier("metlas", "skill_tree"), SkillTreeComponent.class);

    public static final Path CONFIG_ROOT = Path.of(FabricLoader.getInstance().getConfigDir() + "/metlas/");

    @Override
    public void onInitialize() {

    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(SKILL_TREE, SkillTreeComponent::new);
    }
}