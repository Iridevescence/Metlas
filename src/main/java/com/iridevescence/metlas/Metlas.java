package com.iridevescence.metlas;

import java.nio.file.Path;
import java.util.HashMap;

import com.iridevescence.metlas.api.skill.MetlasRegistry;
import com.iridevescence.metlas.api.skill.Restriction;
import com.iridevescence.metlas.api.skill.tree.Unlockable;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
    public static final HashMap<ItemStack, PlayerEntity> ITEMSTACK_PLAYER_MAPPINGS = new HashMap<>();

    public static final Logger LOGGER = LogManager.getLogger("Metlas");

    public static final ComponentKey<SkillTreeComponent> SKILL_TREE =
        ComponentRegistry.getOrCreate(new Identifier("metlas", "skill_tree"), SkillTreeComponent.class);

    public static final Path CONFIG_ROOT = Path.of(FabricLoader.getInstance().getConfigDir() + "/metlas/");

    @Override
    public void onInitialize() {
        Restriction.registerCallbacks();

        MetlasRegistry.registerItemUseRestriction(new Identifier("metlas", "bow_test"), new Restriction.Item.Use(Items.BOW, new Unlockable(new Identifier("metlas", "test2"))));
        MetlasRegistry.registerBlockRestriction(new Identifier("metlas", "iron_ore_test"), new Restriction.Block(Blocks.IRON_ORE, new Unlockable(new Identifier("metlas", "test"))));
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(SKILL_TREE, SkillTreeComponent::new);
    }
}