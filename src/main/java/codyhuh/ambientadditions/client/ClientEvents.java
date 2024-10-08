package codyhuh.ambientadditions.client;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.client.model.TextureVariantModel;
import codyhuh.ambientadditions.client.particles.StunParticle;
import codyhuh.ambientadditions.client.particles.ZzzParticle;
import codyhuh.ambientadditions.client.renderer.GenericGeoRenderer;
import codyhuh.ambientadditions.client.renderer.item.DartRenderer;
import codyhuh.ambientadditions.common.entities.*;
import codyhuh.ambientadditions.common.items.CrateItem;
import codyhuh.ambientadditions.registry.AAEntities;
import codyhuh.ambientadditions.registry.AAItems;
import codyhuh.ambientadditions.registry.AAParticles;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AmbientAdditions.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(AAEntities.CHAMELEON.get(), (ctx) -> new GenericGeoRenderer<>(ctx, () -> {
            TextureVariantModel<Chameleon> model = new TextureVariantModel<>("veiled_chameleon");
            ArrayList<ResourceLocation> textures = new ArrayList<>();
            for (int i = 1; i <= 7; i++) {
                textures.add(AmbientAdditions.rl("textures/entity/veiled_chameleon/veiled_chameleon_" + i + ".png"));
            }
            model.setTextures(Chameleon::getVariant, textures);
            return model;
        }));

        EntityRenderers.register(AAEntities.DART.get(), DartRenderer::new);
        ItemProperties.register(AAItems.CRATE.get(), AmbientAdditions.rl("full"), (stack, world, player, i) -> !CrateItem.containsEntity(stack) ? 0.0F : 1.0F);
    }

    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(AAParticles.ZZZ.get(), ZzzParticle.Provider::new);
        event.registerSpriteSet(AAParticles.STUN.get(), StunParticle.Provider::new);
    }
}