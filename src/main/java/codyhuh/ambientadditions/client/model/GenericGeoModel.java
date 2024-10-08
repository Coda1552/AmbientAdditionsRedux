package codyhuh.ambientadditions.client.model;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.util.IFlopper;
import codyhuh.ambientadditions.common.entities.util.ISwimmer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GenericGeoModel<E extends LivingEntity & GeoEntity> extends GeoModel<E> {
    private final String model;
    private final String texture;
    private final String anim;

    public GenericGeoModel(String name) {
        this(name, name, name);
    }

    public GenericGeoModel(String model, String texture, String anim) {
        this.model = model;
        this.texture = texture;
        this.anim = anim;
    }

    @Override
    public ResourceLocation getModelResource(E object) {
        return AmbientAdditions.rl("geo/entity/" + model + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(E object) {
        return AmbientAdditions.rl("textures/entity/" + texture + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(E object) {
        return AmbientAdditions.rl("animations/entity/" + anim + ".animation.json");
    }

    @Override
    public void setCustomAnimations(E entity, long instanceId, AnimationState<E> customPredicate) {
        super.setCustomAnimations(entity, instanceId, customPredicate);

        CoreGeoBone root = getAnimationProcessor().getBone("root");

        if (entity instanceof IFlopper) {
            if (!entity.isInWater()) {
                root.setRotZ(1.5708F);
            }
            else {
                root.setRotZ(0.0F);
            }
        }
        if (entity instanceof ISwimmer) {
            if (entity.isInWater()) {

                EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

                root.setRotX(extraData.headPitch() * ((float)Math.PI / 180F));
                root.setRotY(extraData.netHeadYaw() * ((float)Math.PI / 180F));
            }
            else {
                root.setRotX(0.0F);
                root.setRotY(0.0F);
            }
        }
    }
}