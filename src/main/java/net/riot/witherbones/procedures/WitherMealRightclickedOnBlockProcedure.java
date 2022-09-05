package net.riot.witherbones.procedures;

import net.riot.witherbones.init.WitherBonesModItems;
import net.riot.witherbones.WitherBonesMod;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

import java.util.Map;
import java.util.HashMap;

public class WitherMealRightclickedOnBlockProcedure {
	public WitherMealRightclickedOnBlockProcedure() {
		UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("world", level);
			dependencies.put("entity", player);
			dependencies.put("x", player.getX());
			dependencies.put("y", player.getY());
			dependencies.put("z", player.getZ());
			execute(dependencies);
			return InteractionResult.PASS;
		});
	}

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WitherBonesMod.LOGGER.warn("Failed to load dependency world for procedure WitherMealRightclickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WitherBonesMod.LOGGER.warn("Failed to load dependency x for procedure WitherMealRightclickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WitherBonesMod.LOGGER.warn("Failed to load dependency y for procedure WitherMealRightclickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WitherBonesMod.LOGGER.warn("Failed to load dependency z for procedure WitherMealRightclickedOnBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WitherBonesMod.LOGGER.warn("Failed to load dependency entity for procedure WitherMealRightclickedOnBlock!");
			return;
		}
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double posX = 0;
		double posY = 0;
		double posZ = 0;
		double randomChance = 0;
		double randomFlower = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == WitherBonesModItems.WITHER_MEAL) {
			if (!world.isClientSide()) {
				posX = x - 2;
				posY = y - 2;
				posZ = z - 2;
				for (int index0 = 0; index0 < (int) (5); index0++) {
					for (int index1 = 0; index1 < (int) (5); index1++) {
						for (int index2 = 0; index2 < (int) (5); index2++) {
							randomChance = Math.random();
							if (randomChance >= 0.5
									&& ((world.getBlockState(new BlockPos(posX, posY, posZ))).getBlock() == Blocks.AIR
											|| (world.getBlockState(new BlockPos(posX, posY, posZ))).getBlock() == Blocks.CAVE_AIR)
									&& (world.getBlockState(new BlockPos(posX, posY - 1, posZ))).getBlock() == Blocks.SOUL_SAND) {
								if (entity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
								randomFlower = Math.random();
								if (randomFlower >= 0.45) {
									world.setBlock(new BlockPos(posX, posY, posZ), Blocks.NETHER_WART.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										_level.sendParticles(ParticleTypes.COMPOSTER, (posX + 0.25), (posY + 0.25), (posZ + 0.25), 5, 0.5, 0.5, 0.5,
												0.025);
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, new BlockPos(posX, posY, posZ),
													new SoundEvent(new ResourceLocation("item.bone_meal.use")), SoundSource.NEUTRAL, 1, 1);
										} else {
											_level.playLocalSound(posX, posY, posZ, new SoundEvent(new ResourceLocation("item.bone_meal.use")),
													SoundSource.NEUTRAL, 1, 1, false);
										}
									}
								}
							}
							posX = posX + 1;
						}
						posX = x - 2;
						posZ = posZ + 1;
					}
					posX = x - 2;
					posZ = z - 2;
					posY = posY + 1;
				}
			}
			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
					} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft
								.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(WitherBonesModItems.WITHER_MEAL);
					_setstack.setCount(
							(int) (((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)).getCount() - 1));
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
				.getItem() == WitherBonesModItems.WITHER_MEAL) {
			if (!world.isClientSide()) {
				posX = x - 2;
				posY = y - 2;
				posZ = z - 2;
				for (int index3 = 0; index3 < (int) (5); index3++) {
					for (int index4 = 0; index4 < (int) (5); index4++) {
						for (int index5 = 0; index5 < (int) (5); index5++) {
							randomChance = Math.random();
							if (randomChance >= 0.5
									&& ((world.getBlockState(new BlockPos(posX, posY, posZ))).getBlock() == Blocks.AIR
											|| (world.getBlockState(new BlockPos(posX, posY, posZ))).getBlock() == Blocks.CAVE_AIR)
									&& (world.getBlockState(new BlockPos(posX, posY - 1, posZ))).getBlock() == Blocks.SOUL_SAND) {
								if (entity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.OFF_HAND, true);
								randomFlower = Math.random();
								if (randomFlower >= 0.45) {
									world.setBlock(new BlockPos(posX, posY, posZ), Blocks.NETHER_WART.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										_level.sendParticles(ParticleTypes.COMPOSTER, (posX + 0.25), (posY + 0.25), (posZ + 0.25), 5, 0.5, 0.5, 0.5,
												0.025);
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, new BlockPos(posX, posY, posZ),
													new SoundEvent(new ResourceLocation("item.bone_meal.use")), SoundSource.NEUTRAL, 1, 1);
										} else {
											_level.playLocalSound(posX, posY, posZ, new SoundEvent(new ResourceLocation("item.bone_meal.use")),
													SoundSource.NEUTRAL, 1, 1, false);
										}
									}
								}
							}
							posX = posX + 1;
						}
						posX = x - 2;
						posZ = posZ + 1;
					}
					posX = x - 2;
					posZ = z - 2;
					posY = posY + 1;
				}
			}
			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
					} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft
								.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(WitherBonesModItems.WITHER_MEAL);
					_setstack.setCount(
							(int) (((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)).getCount() - 1));
					_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		}
	}
}
