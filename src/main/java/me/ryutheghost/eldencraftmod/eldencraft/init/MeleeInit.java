package me.ryutheghost.eldencraftmod.eldencraft.init;

import me.ryutheghost.eldencraftmod.eldencraft.Eldencraft;
import me.ryutheghost.eldencraftmod.eldencraft.melee.Swords;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MeleeInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Eldencraft.MOD_ID);

    public static final RegistryObject<Item> SHORT_SWORD = ITEMS.register("short_sword",
            () -> new Swords.ShortSword(Tiers.IRON, 6, 7.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
