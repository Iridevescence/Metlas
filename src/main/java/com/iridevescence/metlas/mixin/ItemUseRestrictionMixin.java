package com.iridevescence.metlas.mixin;

import com.iridevescence.metlas.Metlas;
import com.iridevescence.metlas.api.skill.MetlasRegistry;
import com.iridevescence.metlas.api.skill.Restriction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemUseRestrictionMixin {
    @Shadow public abstract Item getItem();
    @Shadow public abstract int getMaxUseTime();

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void restrict(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        boolean canUse = true;
        for (Restriction.Item restriction : MetlasRegistry.getItemRestrictions(this.getItem())) {
            if (!restriction.hasUnlocked(user)) {
                canUse = false;
                break;
            }
        }
        if (!canUse) {
            cir.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)));
        }
    }

    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    public void restrict(World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (user instanceof PlayerEntity player) {
            boolean canUse = true;
            for (Restriction.Item restriction : MetlasRegistry.getItemRestrictions(this.getItem())) {
                if (!restriction.hasUnlocked(player) && restriction instanceof Restriction.Item.Use) {
                    canUse = false;
                    break;
                }
            }

            if (!canUse) {
                cir.setReturnValue((ItemStack) (Object) this);
            }
        }
    }

    @Inject(method = "onStoppedUsing", at = @At("HEAD"), cancellable = true)
    private void restrict(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        boolean canUse = true;
        if (user instanceof PlayerEntity player) {
            for (Restriction.Item restriction : MetlasRegistry.getItemRestrictions(this.getItem())) {
                if (!restriction.hasUnlocked(player) && restriction instanceof Restriction.Item.Use) {
                    canUse = false;
                    break;
                }
            }
        }

        if (!canUse) {
            ci.cancel();
        }
    }

//    @ModifyVariable(method = "onStoppedUsing", at = @At("HEAD"), argsOnly = true)
//    public int restrict(int value) {
//        PlayerEntity player = Metlas.ITEMSTACK_PLAYER_MAPPINGS.get((ItemStack) (Object) this);
//
//        return canUse ? value : this.getMaxUseTime();
//    }
}
