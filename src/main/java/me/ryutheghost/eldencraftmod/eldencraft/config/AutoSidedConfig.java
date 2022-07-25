package me.ryutheghost.eldencraftmod.eldencraft.config;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.loading.FMLEnvironment;

/**
 * Provides the config values from LocalConfig if the player is playing on singleplayer,
 * or the values from ServerConfig if the player is playing on a remote server.
 * <p>
 * If the game instance is a dedicated server, it will use ServerConfig.
 */
public class AutoSidedConfig
{
    private AutoSidedConfig()
    {
    }

    public static boolean increaseTransferRate()
    {
        return getConfigInstance().increaseTransferRate.get();
    }

    public static int getMaxStackSize()
    {
        return getConfigInstance().maxStackCount.get();
    }

    public static boolean isUsingWhitelist()
    {
        return getConfigInstance().useWhitelistTag.get();
    }

    private static ServerConfig getConfigInstance()
    {
        if (isPlayingOnRemoteServer())
            return ServerConfig.INSTANCE;
        else
            return LocalConfig.INSTANCE;
    }

    private static boolean isPlayingOnRemoteServer()
    {
        if (FMLEnvironment.dist.isDedicatedServer())
            return true;
        else
            return !Minecraft.getInstance().hasSingleplayerServer();
    }
}
