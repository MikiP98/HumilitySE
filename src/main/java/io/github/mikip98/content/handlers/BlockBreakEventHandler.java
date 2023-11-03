package io.github.mikip98.content.handlers;

import io.github.mikip98.HumilitySE;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;

import static com.mojang.text2speech.Narrator.LOGGER;

public class BlockBreakEventHandler {
    public static void init() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
            if (player != null) {
                if (player.isSneaking()) {
                    return true; // Event not handled
                }
                ItemStack stack = player.getMainHandStack();

                if (stack.getItem() instanceof PickaxeItem) {
                    byte level = (byte) EnchantmentHelper.getLevel(HumilitySE.DESTRUCTION, stack);
                    if (level > 0) {
                        int radiusY = Math.max(3, level);
                        int depth = level;

                        int startXOffset;
                        int endXOffset;
                        int startZOffset;
                        int endZOffset;
                        int startYOffset = (int) -Math.floor((double) (radiusY - 1) /2);
                        int endYOffset = -startYOffset;

                        if (player.getRotationVector().x > 0.5) { // If player looks east (positive x)
                            startXOffset = 0;
                            endXOffset = depth - 1;
                            startZOffset = startYOffset;
                            endZOffset = -startZOffset;
                        } else if (player.getRotationVector().x < -0.5) {  // If player looks west (negative x)
                            startXOffset = -depth + 1;
                            endXOffset = 0;
                            startZOffset = startYOffset;
                            endZOffset = -startZOffset;
                        } else if (player.getRotationVector().z > 0.5) {  // If player looks south (positive z)
                            startXOffset = startYOffset;
                            endXOffset = -startXOffset;
                            startZOffset = 0;
                            endZOffset = depth - 1;
                        } else if (player.getRotationVector().z < -0.5) {  // If player looks north (negative z)
                            startXOffset = startYOffset;
                            endXOffset = -startXOffset;
                            startZOffset = -depth + 1;
                            endZOffset = 0;
                        } else {
                            startXOffset = startYOffset;
                            endXOffset = -startXOffset;
                            startZOffset = startYOffset;
                            endZOffset = -startZOffset;
                        }

                        for (int x=startXOffset; x <= endXOffset; x++) {
                            for (int y = startYOffset; y <= endYOffset; y++) {
                                for (int z = startZOffset; z <= endZOffset; z++) {
                                    BlockPos targetPos = pos.add(x, y, z);
                                    LOGGER.info("BlockBreakEventHandler: targetPos = " + targetPos);

                                    if (world.isAir(targetPos)) {
                                        continue; // Skip air blocks
                                    }

                                    if (world.getBlockState(targetPos).getBlock() != Blocks.BEDROCK) {
                                        world.breakBlock(targetPos, false, player);
                                    }
                                }
                            }
                        }
//                        LOGGER.info("BlockBreakEventHandler: event handled");
                        return true; // Event handled
                    }
                }
            }
//            LOGGER.info("BlockBreakEventHandler: event not handled");
            return true; // Event not handled
        });
    }
}

