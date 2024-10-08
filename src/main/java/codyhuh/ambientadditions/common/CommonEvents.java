package codyhuh.ambientadditions.common;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.*;
import codyhuh.ambientadditions.registry.AAEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = AmbientAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public static void registerCommon(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(AAEntities.CHAMELEON.get(), Chameleon.createAttributes().build());
    }

    @SubscribeEvent
    public static void spawnPlacements(SpawnPlacementRegisterEvent e) {
        e.register(AAEntities.CHAMELEON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
