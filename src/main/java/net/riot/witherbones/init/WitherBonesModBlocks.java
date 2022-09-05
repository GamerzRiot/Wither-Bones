
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.riot.witherbones.init;

import net.riot.witherbones.block.WitherBoneBlockBlock;
import net.riot.witherbones.WitherBonesMod;

import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class WitherBonesModBlocks {
	public static Block WITHER_BONE_BLOCK;

	public static void load() {
		WITHER_BONE_BLOCK = Registry.register(Registry.BLOCK, new ResourceLocation(WitherBonesMod.MODID, "wither_bone_block"),
				new WitherBoneBlockBlock());
	}

	public static void clientLoad() {
		WitherBoneBlockBlock.clientInit();
	}
}
