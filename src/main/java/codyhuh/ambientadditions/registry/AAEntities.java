package codyhuh.ambientadditions.registry;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.*;
import codyhuh.ambientadditions.common.entities.item.DartEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AAEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AmbientAdditions.MOD_ID);

    // Animals
    public static final RegistryObject<EntityType<Chameleon>> CHAMELEON = create("chameleon", EntityType.Builder.of(Chameleon::new, MobCategory.CREATURE).sized(0.65f, 0.55f));

    // Items
    public static final RegistryObject<EntityType<DartEntity>> DART = create("dart", EntityType.Builder.<DartEntity>of(DartEntity::new, MobCategory.MISC).sized(0.5f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(AmbientAdditions.MOD_ID + "." + name));
    }
}