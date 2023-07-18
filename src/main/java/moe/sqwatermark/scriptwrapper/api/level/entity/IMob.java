package moe.sqwatermark.scriptwrapper.api.level.entity;

import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.Nullable;

public interface IMob<T extends Mob> extends ILivingEntity<T> {

    boolean isNavigating();

    void navigateTo(double x, double y, double z, double speed);

    void clearNavigation();

    void jump();

    @Nullable
    ILivingEntity<?> getTarget();

    void setTarget(@Nullable ILivingEntity<?> target);

}
