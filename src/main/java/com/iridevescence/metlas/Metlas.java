package com.iridevescence.metlas;

import java.nio.file.Path;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class Metlas implements ModInitializer {
    public static final Path CONFIG_ROOT = Path.of(FabricLoader.getInstance().getConfigDir() + "/metlas/");



    @Override
    public void onInitialize() {

    }
}