package codyhuh.ambientadditions.common;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.data.SedationData;
import codyhuh.ambientadditions.data.SedationProvider;
import codyhuh.ambientadditions.registry.AAParticles;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AmbientAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void lowerStunWhenHit(LivingHurtEvent e) {
        LivingEntity living = e.getEntity();
        CompoundTag tag = living.getPersistentData();
        var cap = living.getCapability(SedationProvider.SEDATION_CAP);

        if (e.getAmount() > 0.0F && living.level() instanceof ServerLevel && living instanceof PathfinderMob mob) {
            if (cap.isPresent()) {
                var provider = cap.resolve().isPresent() ? cap.resolve().get() : null;

                if (provider != null) {
                    provider.setLevel(0);
                    tag.putBoolean("IsSedated", false);
                    mob.goalSelector.enableControlFlag(Goal.Flag.LOOK);
                    mob.goalSelector.enableControlFlag(Goal.Flag.MOVE);
                    mob.goalSelector.enableControlFlag(Goal.Flag.JUMP);
                }
            }
        }
    }

    @SubscribeEvent
    public static void attachCapabilitiesAnimal(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof PathfinderMob living) {
            if (!living.getCapability(SedationProvider.SEDATION_CAP).isPresent()) {
                e.addCapability(new ResourceLocation(AmbientAdditions.MOD_ID, "sedation"), new SedationProvider());
            }
        }
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent e) {
        e.register(SedationData.class);
    }

    @SubscribeEvent
    public static void entityJoinWorld(EntityJoinLevelEvent e) {
        if (e.getEntity() instanceof PathfinderMob living && living.getPersistentData().get("IsSedated") == null) {
            living.getPersistentData().putBoolean("IsSedated", false);
        }
    }

    @SubscribeEvent
    public static void entityTick(LivingEvent.LivingTickEvent e) {
        LivingEntity living = e.getEntity();

        CompoundTag tag = living.getPersistentData();
        var cap = living.getCapability(SedationProvider.SEDATION_CAP);

        if (living.level() instanceof ServerLevel serverLevel && living instanceof PathfinderMob mob) {
            if (cap.isPresent()) {
                var provider = cap.resolve().isPresent() ? cap.resolve().get() : null;

                if (provider != null) {
                    int i = provider.getTimer();

                    if (i == 0) {
                        provider.setLevel(0);
                        tag.putBoolean("IsSedated", false);
                        mob.goalSelector.enableControlFlag(Goal.Flag.LOOK);
                        mob.goalSelector.enableControlFlag(Goal.Flag.MOVE);
                        mob.goalSelector.enableControlFlag(Goal.Flag.JUMP);
                    }
                    else if (i > 0) {
                        provider.setTimer(i - 1);
                    }

                    if (tag.getBoolean("IsSedated")) {
                        zzzParticles(living, 30, serverLevel);
                        mob.getNavigation().stop();
                        mob.goalSelector.disableControlFlag(Goal.Flag.LOOK);
                        mob.goalSelector.disableControlFlag(Goal.Flag.MOVE);
                        mob.goalSelector.disableControlFlag(Goal.Flag.JUMP);
                    }
                }
            }
        }
    }

    private static void zzzParticles(LivingEntity entity, int amount, ServerLevel level) {
        if (entity.tickCount % amount == 0) {
            level.sendParticles(AAParticles.ZZZ.get(), getHeadOffset(entity).x(), getHeadOffset(entity).y(), getHeadOffset(entity).z(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }

    public static Vec3 getHeadOffset(LivingEntity entity) {
        return getYawVec(entity.getYRot(), 0.0F, entity.getEyeHeight() + (entity.getBbHeight() * 0.3F), entity.getBbWidth() * 0.5F).add(entity.position());
    }

    public static Vec3 getYawVec(float yaw, double xOffset, double yOffset, double zOffset) {
        return new Vec3(xOffset, yOffset, zOffset).yRot(-yaw * (Mth.PI / 180f));
    }
}
