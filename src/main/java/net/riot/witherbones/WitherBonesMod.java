/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and the proxy files
 *    and they won't get overwritten. If you change your mod package or modid, you
 *    need to apply these changes to this file MANUALLY.
 *
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package net.riot.witherbones;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.riot.witherbones.init.WitherBonesModProcedures;
import net.riot.witherbones.init.WitherBonesModPotions;
import net.riot.witherbones.init.WitherBonesModItems;
import net.riot.witherbones.init.WitherBonesModBlocks;

import net.fabricmc.api.ModInitializer;

public class WitherBonesMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "wither_bones";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing WitherBonesMod");

		WitherBonesModPotions.load();

		WitherBonesModBlocks.load();
		WitherBonesModItems.load();

		WitherBonesModProcedures.load();

	}
}
