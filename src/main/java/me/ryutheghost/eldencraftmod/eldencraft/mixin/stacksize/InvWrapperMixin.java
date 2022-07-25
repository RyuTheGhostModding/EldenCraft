package me.ryutheghost.eldencraftmod.eldencraft.mixin.stacksize;

import me.ryutheghost.eldencraftmod.eldencraft.config.AutoSidedConfig;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InvWrapper.class)
public class InvWrapperMixin
{
    @Redirect(method = "insertItem",
              at = @At(value = "INVOKE", target = "Lnet/minecraftforge/items/wrapper/InvWrapper;getSlotLimit(I)I"),
              remap = false)
    private int fixMaxStackSize(InvWrapper instance, int slot)
    {
        var value = instance.getSlotLimit(slot);

        if (value == 64)
        {
            return AutoSidedConfig.getMaxStackSize();
        }

        return value;
    }
}
