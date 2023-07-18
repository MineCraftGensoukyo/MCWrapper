package moe.sqwatermark.scriptwrapper.wrapper;

import moe.sqwatermark.scriptwrapper.WrapperCreator;
import moe.sqwatermark.scriptwrapper.api.level.ILevel;
import moe.sqwatermark.scriptwrapper.api.level.block.IBlockState;
import moe.sqwatermark.scriptwrapper.api.level.entity.IEntity;
import moe.sqwatermark.scriptwrapper.api.level.entity.IPlayer;
import moe.sqwatermark.scriptwrapper.api.math.IBlockPos;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LevelWrapper implements ILevel {

    private final ServerLevel level;

    public LevelWrapper(ServerLevel level) {
        this.level = level;
    }

    @Override
    public ServerLevel getMCLevel() {
        return level;
    }

    public boolean isDay() {
        return level.isDay();
    }

    public boolean isNight() {
        return level.isNight();
    }

    public long getDayTime() {
        return level.getDayTime();
    }

    public void setDayTime(long pTime) {
        level.setDayTime(pTime);
    }

    // TODO 封装计分板
    public ServerScoreboard getScoreboard() {
        return level.getScoreboard();
    }

    public void playSound(IBlockPos blockPos, String sound, float volume, float pitch) {
        // TODO soundevent需要注册了？
//        level.playSound(null, blockPos.getMCBlockPos(), new SoundEvent(new ResourceLocation(sound)), SoundSource.AMBIENT, volume, pitch);
    }

    // TODO MC粒子和effekseer粒子

    // ******************************* 实体相关 ******************************* //

    public List<IEntity<?>> getEntitiesNearby(IEntity<?> entity, int range) {
        AABB bb = (new AABB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)).move(entity.getBlockPos().getMCBlockPos()).inflate(range, range, range);
        List<Entity> entities = level.getEntities(entity.getMCEntity(), bb);
        return entities.stream().map(WrapperCreator::entity).collect(Collectors.toList());
    }

    // ******************************* 玩家相关 ******************************* //

    @Nullable
    public IPlayer<?> getPlayerByUUID(String uuid) {
        Player playerByUUID = level.getPlayerByUUID(UUID.fromString(uuid));
        if (playerByUUID != null) {
            return new PlayerWrapper((ServerPlayer) playerByUUID);
        } else {
            return null;
        }
    }

    @Nullable
    public IPlayer<?> getPlayerByName(String name) {
        return level.players().stream()
                .filter(player -> player.getName().getString().equals(name))
                .findFirst().map(PlayerWrapper::new).orElse(null);
    }

    @Override
    public void broadcast(String message) {
        Component text = Component.literal(message);
        for (ServerPlayer player : level.players()) {
            player.sendSystemMessage(text);
        }
    }

    // ******************************* 方块相关 ******************************* //

    public IBlockState getBlockState(int x, int y, int z) {
        return new BlockStateWrapper(level.getBlockState(new BlockPos(x, y, z)));
    }

    public IBlockState getBlockState(IBlockPos blockPos) {
        return new BlockStateWrapper(level.getBlockState(blockPos.getMCBlockPos()));
    }

    public void setBlockState(int x, int y, int z, IBlockState blockState) {
        level.setBlock(new BlockPos(x, y, z), blockState.getMCBlockState(), 2);
    }

    public void setBlockState(IBlockPos blockPos, IBlockState blockState) {
        level.setBlock(blockPos.getMCBlockPos(), blockState.getMCBlockState(), 2);
    }

    public void removeBlock(int x, int y, int z) {
        this.level.removeBlock(new BlockPos(x, y, z), false);
    }

    public void removeBlock(IBlockPos blockPos) {
        this.level.removeBlock(blockPos.getMCBlockPos(), false);
    }

}
