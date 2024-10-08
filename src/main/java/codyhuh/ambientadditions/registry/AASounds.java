package codyhuh.ambientadditions.registry;

import codyhuh.ambientadditions.AmbientAdditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AASounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AmbientAdditions.MOD_ID);

    //public static final RegistryObject<SoundEvent> ARMADILLO_DEATH = create("armadillo.death");
    //public static final RegistryObject<SoundEvent> ARMADILLO_HURT = create("armadillo.hurt");
    //public static final RegistryObject<SoundEvent> ARMADILLO_AMBIENT = create("armadillo.ambient");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AmbientAdditions.MOD_ID, name)));
    }
}