package codyhuh.ambientadditions.registry;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.items.*;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AAItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AmbientAdditions.MOD_ID);

    // Buckets & Catching Items
    public static final RegistryObject<Item> CRATE = ITEMS.register("crate", () -> new CrateItem(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> BLOWGUN = ITEMS.register("blowgun", () -> new BlowgunItem(new Item.Properties().stacksTo(1).durability(72)));
    public static final RegistryObject<Item> DART = ITEMS.register("dart", () -> new DartItem(new Item.Properties()));

    // Drops & Materials

    // Gear

    // Spawn Eggs
    public static final RegistryObject<Item> CHAMELEON_SPAWN_EGG = ITEMS.register("chameleon_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.CHAMELEON, 0x1ccf3d, 0xfffa45, new Item.Properties()));

    // BlockItems

}