package me.ryutheghost.eldencraftmod.eldencraft.mixin.stacksize;

import me.ryutheghost.eldencraftmod.eldencraft.config.AutoSidedConfig;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public class SlotMixin
{
    @Inject(method = "getMaxStackSize()I", at = @At("RETURN"), cancellable = true)
    private void fixMaxStackSize(CallbackInfoReturnable<Integer> returnInfo)
    {
        if (returnInfo.getReturnValue() == 64)
        {
            returnInfo.cancel();
            returnInfo.setReturnValue(AutoSidedConfig.getMaxStackSize());
        }
    }
}
