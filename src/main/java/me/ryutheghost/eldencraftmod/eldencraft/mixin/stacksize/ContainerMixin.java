package me.ryutheghost.eldencraftmod.eldencraft.mixin.stacksize;

import me.ryutheghost.eldencraftmod.eldencraft.config.AutoSidedConfig;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Container.class)
public interface ContainerMixin extends Container
{
    @Override
    default int getMaxStackSize()
    {
        return AutoSidedConfig.getMaxStackSize();
    }
}
