package me.ryutheghost.eldencraftmod.eldencraft;

import me.ryutheghost.eldencraftmod.eldencraft.config.ClientConfig;
import me.ryutheghost.eldencraftmod.eldencraft.config.LocalConfig;
import me.ryutheghost.eldencraftmod.eldencraft.config.ServerConfig;
import me.ryutheghost.eldencraftmod.eldencraft.init.MeleeInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.text.DecimalFormat;
import java.util.ArrayList;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Eldencraft.MOD_ID)
public class Eldencraft {

    public static final ArrayList<Class<?>> IGNORED_CLASSES = new ArrayList();
    public static final TagKey<Item> BLACKLIST_TAG = ItemTags.create(new ResourceLocation("eldencraft", "blacklist"));
    public static final TagKey<Item> WHITELIST_TAG = ItemTags.create(new ResourceLocation("eldencraft", "whitelist"));
    private static final DecimalFormat TOOLTIP_NUMBER_FORMAT = new DecimalFormat("###,###,###,###,###,###");

    public static final String MOD_ID = "eldencraft";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // Add a comment
    public Eldencraft() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC, "eldencraft-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LocalConfig.INSTANCE.SPEC, "eldencraft-local.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.INSTANCE.SPEC, "eldencraft-server.toml");
        MeleeInit.register(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        GeckoLib.initialize();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event){

    }

    private void setup(final FMLCommonSetupEvent event) {
        
    }

    @SubscribeEvent(
            priority = EventPriority.LOWEST
    )
    public void showExactItemStackCount(ItemTooltipEvent event) {
        if ((Boolean) ClientConfig.enableNumberShortening.get()) {
            ItemStack stack = event.getItemStack();
            if (stack.getMaxStackSize() > 1000) {
                event.getToolTip().add(1, (new TranslatableComponent("eldencraft.exact.count", new Object[]{(new TextComponent(TOOLTIP_NUMBER_FORMAT.format((long)stack.getCount()))).withStyle(ChatFormatting.DARK_AQUA)})).withStyle(ChatFormatting.GRAY));
            }

        }
    }

    static {
        IGNORED_CLASSES.add(ClientboundMoveEntityPacket.Rot.class);
        IGNORED_CLASSES.add(ClientboundMoveEntityPacket.PosRot.class);
        IGNORED_CLASSES.add(ClientboundMoveEntityPacket.Pos.class);
        IGNORED_CLASSES.add(ClientboundTeleportEntityPacket.class);
        IGNORED_CLASSES.add(ClientboundEntityEventPacket.class);
        IGNORED_CLASSES.add(ClientboundRotateHeadPacket.class);
        IGNORED_CLASSES.add(ClientboundSetEntityMotionPacket.class);
        IGNORED_CLASSES.add(ClientboundLevelChunkWithLightPacket.class);
        IGNORED_CLASSES.add(ClientboundSetEntityDataPacket.class);
        IGNORED_CLASSES.add(ClientboundBlockUpdatePacket.class);
        IGNORED_CLASSES.add(ClientboundSetTimePacket.class);
        IGNORED_CLASSES.add(ClientboundRemoveEntitiesPacket.class);
        IGNORED_CLASSES.add(ServerboundMovePlayerPacket.class);
        IGNORED_CLASSES.add(net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.Pos.class);
        IGNORED_CLASSES.add(net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.PosRot.class);
        IGNORED_CLASSES.add(net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.Rot.class);
        IGNORED_CLASSES.add(ServerboundMovePlayerPacket.StatusOnly.class);
        IGNORED_CLASSES.add(ClientboundAddMobPacket.class);
        IGNORED_CLASSES.add(ClientboundSectionBlocksUpdatePacket.class);
        IGNORED_CLASSES.add(ClientboundUpdateAttributesPacket.class);
        IGNORED_CLASSES.add(ClientboundSetChunkCacheCenterPacket.class);
        IGNORED_CLASSES.add(ClientboundLightUpdatePacket.class);
        IGNORED_CLASSES.add(ClientboundKeepAlivePacket.class);
        IGNORED_CLASSES.add(ServerboundKeepAlivePacket.class);
        IGNORED_CLASSES.add(ClientboundForgetLevelChunkPacket.class);
    }
}
