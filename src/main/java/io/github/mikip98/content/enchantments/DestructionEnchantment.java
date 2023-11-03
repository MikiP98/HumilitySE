package io.github.mikip98.content.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;

public class DestructionEnchantment extends Enchantment {

    public DestructionEnchantment() {
        // Enchant for all tools
        super(Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 5 * level;
    }
    @Override
    public int getMaxPower(int level) {
        return 30;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canAccept(Enchantment other){
        if(other == Enchantments.SILK_TOUCH || other == Enchantments.FORTUNE)
            return false;
        return super.canAccept(other);
    }

    // When block is broken assure that it won't drop anything
    // and destroy blocks around it (and behind it according to level)
//    @Override
//    public void onBlockBreak(LivingEntity user, Entity target, int level) {
//        // Destroy blocks around
//        target.getWorld().breakBlock(target.getBlockPos().north(), false);
//        target.getWorld().breakBlock(target.getBlockPos().south(), false);
//        target.getWorld().breakBlock(target.getBlockPos().east(), false);
//        target.getWorld().breakBlock(target.getBlockPos().west(), false);
//    }
//    @Override
//    public void onBlockBreak(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
//        if (miner != null && miner.isPlayer()) {
//            int level = EnchantmentHelper.getLevel(ModEnchantments.DESTRUCTION, stack);
//            int radius = 3 * level;
//
//            for (int x = -radius; x <= radius; x++) {
//                for (int y = -radius; y <= radius; y++) {
//                    for (int z = -radius; z <= radius; z++) {
//                        BlockPos targetPos = pos.add(x, y, z);
//                        BlockState targetState = world.getBlockState(targetPos);
//                        Block targetBlock = targetState.getBlock();
//
//                        if (targetBlock != Blocks.BEDROCK) { // Ensure you can't break bedrock
//                            targetBlock.dropStacks(targetState, world, targetPos);
//                            world.removeBlock(targetPos, false);
//                        }
//                    }
//                }
//            }
//        }
//    }
}
