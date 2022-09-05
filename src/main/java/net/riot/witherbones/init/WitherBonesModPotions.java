
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.riot.witherbones.init;

import net.riot.witherbones.WitherBonesMod;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class WitherBonesModPotions {
	public static Potion WITHER_POTIONS;

	public static void load() {
		WITHER_POTIONS = Registry.register(Registry.POTION, new ResourceLocation(WitherBonesMod.MODID, "wither_potions"),
				new Potion(new MobEffectInstance(MobEffects.WITHER, 200, 0, false, true)));
	}
}
