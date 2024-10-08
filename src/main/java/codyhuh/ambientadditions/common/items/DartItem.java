package codyhuh.ambientadditions.common.items;

import codyhuh.ambientadditions.common.entities.item.DartEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class DartItem extends Item {

    public DartItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    public DartEntity createArrow(Level level, LivingEntity owner) {
        DartEntity entity = new DartEntity(level, owner);
        entity.setBaseDamage(0.0);
        return entity;
    }
}
