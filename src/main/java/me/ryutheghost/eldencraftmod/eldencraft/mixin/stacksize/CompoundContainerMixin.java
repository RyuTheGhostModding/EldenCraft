package me.ryutheghost.eldencraftmod.eldencraft.mixin.stacksize;

import me.ryutheghost.eldencraftmod.eldencraft.config.AutoSidedConfig;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Mixin(CompoundContainer.class)
public class CompoundContainerMixin
{
    //@Inject(method = "getMaxStackSize", at = @At("RETURN"), cancellable = true)
    private void fixMaxStackSize(CallbackInfoReturnable<Integer> returnInfo)
    {
        if (returnInfo.getReturnValue() == 64)
        {
            returnInfo.cancel();
            returnInfo.setReturnValue(AutoSidedConfig.getMaxStackSize());
        }
    }
}
