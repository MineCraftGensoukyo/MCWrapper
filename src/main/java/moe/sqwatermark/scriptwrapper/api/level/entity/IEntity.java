package moe.sqwatermark.scriptwrapper.api.level.entity;

import moe.sqwatermark.scriptwrapper.api.level.ILevel;
import moe.sqwatermark.scriptwrapper.api.math.IBlockPos;
import net.minecraft.world.entity.Entity;

public interface IEntity<T extends Entity> {

    double getX();

    double getY();

    double getZ();

    void setPos(double x, double y, double z);

    IBlockPos getBlockPos();

    void setBlockPos(IBlockPos pos);

    double distanceTo(IEntity<?> target);

    double getMovementX();

    double getMovementY();

    double getMovementZ();

    void setMovement(double x, double y, double z);

    void setYaw(float rotation);

    float getYaw();

    void setPitch(float rotation);

    float getPitch();

    String getName();

    void setName(String name);

    boolean hasCustomName();

    ILevel getLevel();

    float getWidth();

    float getHeight();

    float getEyeHeight();

    long getAge();

    boolean isAlive();

    void damage(float amount);

    void kill();

    boolean isCrouching();

    boolean isSprinting();

    boolean isInvulnerable();

    void setInvulnerable(boolean invulnerable);

    boolean inWater();

    boolean inLava();

    boolean inFire();

    boolean isOnFire();

    void setOnFire(int ticks);

    void clearFire();

    int getId();

    String getUUID();

    T getMCEntity();

}
