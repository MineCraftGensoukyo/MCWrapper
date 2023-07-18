package moe.sqwatermark.scriptwrapper.wrapper;

import moe.sqwatermark.scriptwrapper.api.level.entity.ILivingEntity;
import moe.sqwatermark.scriptwrapper.api.level.item.IItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class LivingEntityWrapper<T extends LivingEntity> extends EntityWrapper<T> implements ILivingEntity<T> {
    public LivingEntityWrapper(T entity) {
        super(entity);
    }

    @Override
    public float getHealth() {
        return entity.getHealth();
    }

    @Override
    public void setHealth(float health) {
        entity.setHealth(health);
    }

    @Override
    public float getMaxHealth() {
        return entity.getMaxHealth();
    }

    @Override
    public void setMaxHealth(float health) {
        if (!(health < 0.0F)) {
            entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
        }
    }

    @Override
    public IItemStack getMainHandItem() {
        return ItemStackWrapper.create(entity.getMainHandItem());
    }

    @Override
    public void setMainHandItem(IItemStack item) {
        entity.setItemInHand(InteractionHand.MAIN_HAND, item == null ? ItemStack.EMPTY : item.getMCItemStack());
    }

    @Override
    public IItemStack getOffhandItem() {
        return ItemStackWrapper.create(entity.getOffhandItem());
    }

    @Override
    public void setOffhandItem(IItemStack item) {
        entity.setItemInHand(InteractionHand.OFF_HAND, item == null ? ItemStack.EMPTY : item.getMCItemStack());
    }

}
