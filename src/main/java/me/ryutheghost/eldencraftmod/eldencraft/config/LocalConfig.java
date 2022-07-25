package me.ryutheghost.eldencraftmod.eldencraft.config;

/**
 * Client side version of ServerConfig for use in single player.
 */
public class LocalConfig extends ServerConfig
{
    public final static LocalConfig INSTANCE = new LocalConfig();

    LocalConfig()
    {
        super(false);
    }
}
