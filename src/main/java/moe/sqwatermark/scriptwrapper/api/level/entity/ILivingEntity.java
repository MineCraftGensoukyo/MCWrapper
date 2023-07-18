package moe.sqwatermark.scriptwrapper.api.level.entity;

import moe.sqwatermark.scriptwrapper.api.level.item.IItemStack;
import net.minecraft.world.entity.LivingEntity;

public interface ILivingEntity<T extends LivingEntity> extends IEntity<T> {

    float getHealth();

    void setHealth(float health);

    float getMaxHealth();

    void setMaxHealth(float health);

    IItemStack getMainHandItem();

    void setMainHandItem(IItemStack item);

    IItemStack getOffhandItem();

    void setOffhandItem(IItemStack item);

}
