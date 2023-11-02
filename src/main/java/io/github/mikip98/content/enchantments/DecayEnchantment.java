package io.github.mikip98.content.enchantments;

import io.github.mikip98.HumilitySE;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class DecayEnchantment extends Enchantment {

    public DecayEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 25;
    } // unable to get this enchantment from enchanting table
    @Override
    public int getMaxPower(int level) {
        return 50;
    } // unable to get this enchantment from enchanting table

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canAccept(Enchantment other){
        return super.canAccept(other);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(HumilitySE.DECAY_STATUS_EFFECT, 40, 1, false, false, false));

            if(((LivingEntity) target).isUndead()) {
                ((LivingEntity) target).heal(1);
            }

            // If entity on fire, extinguish it
            if(target.isOnFire()) {
                target.extinguish();
            }
        }

        super.onTargetDamaged(user, target, level);
    }

    public boolean isTreasure() {
        return true;
    }

    public boolean isCursed() {
        return true;
    }
}
