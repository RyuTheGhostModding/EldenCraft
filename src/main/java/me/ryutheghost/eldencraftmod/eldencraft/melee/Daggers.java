package me.ryutheghost.eldencraftmod.eldencraft.melee;

import me.ryutheghost.eldencraftmod.eldencraft.Eldencraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import java.util.function.Consumer;

public class Daggers {

    public static final class Dagger extends SwordItem implements IAnimatable {

        public AnimationFactory factory = new AnimationFactory(this);

        public Dagger(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
            super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        }


        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            super.initializeClient(consumer);
            consumer.accept(new IItemRenderProperties() {
                private final BlockEntityWithoutLevelRenderer renderer = new DaggerRenderer();

                @Override
                public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                    return renderer;
                }
            });
        }

        @Override
        public void registerControllers(AnimationData data) {
            data.addAnimationController(new AnimationController(this, "controller",
                    0, this::predicate));
        }

        private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));

            return PlayState.CONTINUE;
        }

        @Override
        public AnimationFactory getFactory() {
            return this.factory;
        }
    }

    public static class DaggerModel extends AnimatedGeoModel<Dagger> {
        @Override
        public ResourceLocation getModelLocation(Dagger object) {
            return new ResourceLocation(Eldencraft.MOD_ID, "geo/dagger.geo.json");
        }

        @Override
        public ResourceLocation getTextureLocation(Dagger object) {
            return new ResourceLocation(Eldencraft.MOD_ID, "textures/item/daggers/dagger.png");
        }

        @Override
        public ResourceLocation getAnimationFileLocation(Dagger animatable) {
            return new ResourceLocation(Eldencraft.MOD_ID, "animations/dagger.animation.json");
        }
    }

    public static class DaggerRenderer extends GeoItemRenderer<Dagger> {
        public DaggerRenderer() {
            super(new DaggerModel());
        }
    }
}
