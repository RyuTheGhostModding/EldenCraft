package me.ryutheghost.eldencraftmod.eldencraft.mixin;

import me.ryutheghost.eldencraftmod.eldencraft.Eldencraft;
import me.ryutheghost.eldencraftmod.eldencraft.config.AutoSidedConfig;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Item.class)
public class ItemMixin
{
    @Inject(method = "getMaxStackSize", at = @At("RETURN"), cancellable = true)
    private void biggerMaxStackSize(CallbackInfoReturnable<Integer> returnInfo)
    {
        //if whitelist is enabled and the item isn't whitelisted, don't increase its stack size
        if (AutoSidedConfig.isUsingWhitelist() && !((Item) (Object) this).getDefaultInstance()
                                                                         .is(Eldencraft.WHITELIST_TAG))
            return;
            //check if this item has the blacklist tag, and if it does, don't increase its stack size
        else if (((Item) (Object) this).getDefaultInstance().is(Eldencraft.BLACKLIST_TAG))
            return;

        if (returnInfo.getReturnValue() != 1)
        {
            returnInfo.cancel();
            returnInfo.setReturnValue(AutoSidedConfig.getMaxStackSize());
        }
    }
}
