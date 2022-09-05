
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.riot.witherbones.init;

import net.riot.witherbones.item.WitherMealItem;
import net.riot.witherbones.item.WitherBoneItem;
import net.riot.witherbones.WitherBonesMod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class WitherBonesModItems {
	public static Item WITHER_MEAL;
	public static Item WITHER_BONE;
	public static Item WITHER_BONE_BLOCK;

	public static void load() {
		WITHER_MEAL = Registry.register(Registry.ITEM, new ResourceLocation(WitherBonesMod.MODID, "wither_meal"), new WitherMealItem());
		WITHER_BONE = Registry.register(Registry.ITEM, new ResourceLocation(WitherBonesMod.MODID, "wither_bone"), new WitherBoneItem());
		WITHER_BONE_BLOCK = Registry.register(Registry.ITEM, new ResourceLocation(WitherBonesMod.MODID, "wither_bone_block"),
				new BlockItem(WitherBonesModBlocks.WITHER_BONE_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	}
}
