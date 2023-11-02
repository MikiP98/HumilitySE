package io.github.mikip98.content.status_effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class DecayStatusEffect extends StatusEffect {

    public DecayStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x98D982); // whether beneficial or harmful for entities  AND  color in RGB
    }

    // This method is called every tick to check whether it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }

//    @Override
//    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
//        if (entity instanceof PlayerEntity) {
//            ((PlayerEntity) entity).addExperience(1 << amplifier); // Higher amplifier gives you EXP faster
//        }
//    }
}
