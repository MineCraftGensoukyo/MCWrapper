package moe.sqwatermark.scriptwrapper.wrapper;

import moe.sqwatermark.scriptwrapper.api.level.ILevel;
import moe.sqwatermark.scriptwrapper.api.level.entity.IEntity;
import moe.sqwatermark.scriptwrapper.api.math.IBlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

// TODO 骑乘、NBT
public class EntityWrapper<T extends Entity> implements IEntity<T> {

    protected T entity;

    public EntityWrapper(T entity) {
        this.entity = entity;
    }

    @Override
    public double getX() {
        return entity.getX();
    }

    @Override
    public double getY() {
        return entity.getY();
    }

    @Override
    public double getZ() {
        return entity.getZ();
    }

    @Override
    public void setPos(double x, double y, double z) {
        entity.setPos(x, y, z);
    }

    @Override
    public IBlockPos getBlockPos() {
        return new BlockPosWrapper(entity.blockPosition());
    }

    @Override
    public void setBlockPos(IBlockPos pos) {
        entity.setPos((float)pos.getX() + 0.5F, pos.getY(), (float)pos.getZ() + 0.5F);
    }

    @Override
    public double distanceTo(IEntity<?> target) {
        return this.entity.distanceTo(target.getMCEntity());
    }

    @Override
    public double getMovementX() {
        return entity.getDeltaMovement().x;
    }

    @Override
    public double getMovementY() {
        return entity.getDeltaMovement().y;
    }

    @Override
    public double getMovementZ() {
        return entity.getDeltaMovement().z;
    }

    @Override
    public void setMovement(double x, double y, double z) {
        entity.setDeltaMovement(x, y, z);
    }

    @Override
    public void setYaw(float rotation) {
        entity.setYRot(rotation);
    }

    @Override
    public float getYaw() {
        return entity.getYRot();
    }

    @Override
    public void setPitch(float rotation) {
        entity.setXRot(rotation);
    }

    @Override
    public float getPitch() {
        return entity.getXRot();
    }

    @Override
    public String getName() {
        return entity.getName().getString();
    }

    @Override
    public void setName(String name) {
        entity.setCustomName(Component.literal(name));
    }

    @Override
    public boolean hasCustomName() {
        return entity.hasCustomName();
    }

    @Override
    public ILevel getLevel() {
        return new LevelWrapper((ServerLevel) entity.level());
    }

    @Override
    public float getWidth() {
        return entity.getBbWidth();
    }

    @Override
    public float getHeight() {
        return entity.getBbHeight();
    }

    @Override
    public float getEyeHeight() {
        return entity.getEyeHeight();
    }

    @Override
    public long getAge() {
        return entity.tickCount;
    }

    @Override
    public boolean isAlive() {
        return entity.isAlive();
    }

    @Override
    public void damage(float amount) {
        entity.hurt(entity.damageSources().generic(), amount);
    }

    @Override
    public void kill() {
        entity.remove(Entity.RemovalReason.KILLED);
    }

    @Override
    public boolean isCrouching() {
        return entity.isCrouching();
    }

    @Override
    public boolean isSprinting() {
        return entity.isSprinting();
    }

    @Override
    public boolean isInvulnerable() {
        return entity.isInvulnerable();
    }

    @Override
    public void setInvulnerable(boolean invulnerable) {
        entity.setInvulnerable(invulnerable);
    }

    @Override
    public boolean inWater() {
        // TODO
        return false;
//        return entity.level().getBlockStates(entity.getBoundingBox()).anyMatch((state) -> state.getMaterial() == Material.WATER);
    }

    @Override
    public boolean inLava() {
        return false;
//        return entity.level().getBlockStates(entity.getBoundingBox()).anyMatch((state) -> state.getMaterial() == Material.LAVA);
    }

    @Override
    public boolean inFire() {
        return false;
//        return entity.level().getBlockStates(entity.getBoundingBox()).anyMatch((state) -> state.getMaterial() == Material.FIRE);
    }

    @Override
    public boolean isOnFire() {
        return entity.isOnFire();
    }

    @Override
    public void setOnFire(int ticks) {
        entity.setRemainingFireTicks(ticks);
    }

    @Override
    public void clearFire() {
        entity.clearFire();
    }

    @Override
    public int getId() {
        return entity.getId();
    }

    @Override
    public String getUUID() {
        return entity.getStringUUID();
    }

    @Override
    public T getMCEntity() {
        return entity;
    }
}
