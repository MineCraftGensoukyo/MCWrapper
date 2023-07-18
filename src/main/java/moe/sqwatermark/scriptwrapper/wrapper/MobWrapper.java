package moe.sqwatermark.scriptwrapper.wrapper;

import moe.sqwatermark.scriptwrapper.WrapperCreator;
import moe.sqwatermark.scriptwrapper.api.level.entity.ILivingEntity;
import moe.sqwatermark.scriptwrapper.api.level.entity.IMob;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.Nullable;

public class MobWrapper<T extends Mob> extends LivingEntityWrapper<T> implements IMob<T> {
    public MobWrapper(T entity) {
        super(entity);
    }

    @Override
    public boolean isNavigating() {
        return !entity.getNavigation().isDone();
    }

    @Override
    public void navigateTo(double x, double y, double z, double speed) {
        entity.getNavigation().stop();
        entity.getNavigation().moveTo(x, y, z, speed);
    }

    @Override
    public void clearNavigation() {
        entity.getNavigation().stop();
    }

    @Override
    public void jump() {
        this.entity.getJumpControl().jump();
    }

    @Nullable
    @Override
    public ILivingEntity<?> getTarget() {
        if (entity.getTarget() == null) {
            return null;
        }
        return WrapperCreator.livingEntity(entity.getTarget());
    }

    @Override
    public void setTarget(ILivingEntity<?> target) {
        if (target == null) {
            entity.setTarget(null);
        } else {
            entity.setTarget(target.getMCEntity());
        }
    }

}
