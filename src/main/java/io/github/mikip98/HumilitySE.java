package io.github.mikip98;

import io.github.mikip98.content.enchantments.DecayEnchantment;
import io.github.mikip98.content.enchantments.DestructionEnchantment;
import io.github.mikip98.content.enchantments.FrostEnchantment;
import io.github.mikip98.content.handlers.BlockBreakEventHandler;
import io.github.mikip98.content.status_effects.DecayStatusEffect;
import net.fabricmc.api.ModInitializer;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumilitySE implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "humility-se";
	public static final String MOD_NAME = "Humility SE";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static Enchantment FROST = new FrostEnchantment();

	// Decay curse (enchantment)
	public static final Enchantment DECAY = new DecayEnchantment();

	// Decay status effect
//	public static final StatusEffect DECAY_EFFECT = new DecayStatusEffect(); //delete
	public static StatusEffect DECAY_STATUS_EFFECT;

	// Destruction enchantment
	public static final Enchantment DESTRUCTION = new DestructionEnchantment();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info(MOD_NAME + " is initializing!");

		//Register frost enchantment
		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "frost"), FROST);

		//Register decay curse (enchantment)
		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "decay"), DECAY);

		//Register decay status effect
//		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "decay_effect"), DECAY_EFFECT);
		DECAY_STATUS_EFFECT = Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "decay_status_effect"), new DecayStatusEffect());

		// Register destruction enchantment
		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "destruction"), DESTRUCTION);

		BlockBreakEventHandler.init();
	}
}