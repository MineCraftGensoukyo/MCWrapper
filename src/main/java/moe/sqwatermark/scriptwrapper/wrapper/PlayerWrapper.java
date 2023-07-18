package moe.sqwatermark.scriptwrapper.wrapper;

import moe.sqwatermark.scriptwrapper.api.level.entity.IPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

public class PlayerWrapper extends LivingEntityWrapper<ServerPlayer> implements IPlayer<ServerPlayer> {

    public PlayerWrapper(ServerPlayer player) {
        super(player);
    }

    @Override
    public String getDisplayName() {
        return entity.getDisplayName().getString();
    }

    @Override
    public int getFoodLevel() {
        return entity.getFoodData().getFoodLevel();
    }

    @Override
    public void setFoodLevel(int level) {
        entity.getFoodData().setFoodLevel(level);
    }

    @Override
    public void message(String message) {
        this.entity.sendSystemMessage(Component.literal(message));
    }

    @Override
    public int getGameMode() {
        return entity.gameMode.getGameModeForPlayer().getId();
    }

    @Override
    public void setGameMode(int type) {
        entity.setGameMode(GameType.byId(type));
    }

}
