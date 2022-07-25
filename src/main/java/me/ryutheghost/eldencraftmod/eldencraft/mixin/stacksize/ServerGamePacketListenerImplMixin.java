package me.ryutheghost.eldencraftmod.eldencraft.mixin.stacksize;

import me.ryutheghost.eldencraftmod.eldencraft.config.AutoSidedConfig;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin
{
    //for some reason it's hard coded to disallow giving more than 64 items in CREATIVE mode.
    @ModifyConstant(method = "handleSetCreativeModeSlot", constant = @Constant(intValue = 64))
    private int handleBiggerStackLimit(int value)
    {
        return AutoSidedConfig.getMaxStackSize();
    }
}
