package moe.sqwatermark.scriptwrapper;

import moe.sqwatermark.scriptwrapper.api.level.entity.IEntity;
import moe.sqwatermark.scriptwrapper.api.level.entity.ILivingEntity;
import moe.sqwatermark.scriptwrapper.wrapper.EntityWrapper;
import moe.sqwatermark.scriptwrapper.wrapper.LivingEntityWrapper;
import moe.sqwatermark.scriptwrapper.wrapper.MobWrapper;
import moe.sqwatermark.scriptwrapper.wrapper.PlayerWrapper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public class WrapperCreator {

    public static IEntity<?> entity(Entity entity) {
        if (entity instanceof LivingEntity living) {
            return livingEntity(living);
        }
        return new EntityWrapper<>(entity);
    }

    public static ILivingEntity<?> livingEntity(LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            return new PlayerWrapper(player);
        } else if (entity instanceof Mob mob) {
            return new MobWrapper<>(mob);
        }
        return new LivingEntityWrapper<>(entity);
    }
}
