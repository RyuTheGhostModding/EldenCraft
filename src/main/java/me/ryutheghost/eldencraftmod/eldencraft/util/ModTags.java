package me.ryutheghost.eldencraftmod.eldencraft.util;

import me.ryutheghost.eldencraftmod.eldencraft.Eldencraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> SPIRIT_ASHES_ALLOWED_SPAWN_ON_BLOCKS
                = tag("spirit_ashes_allowed_spawn_on_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Eldencraft.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SPIRIT_ITEMS = forgeTag("spirit/spirititems");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Eldencraft.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
