package moe.sqwatermark.scriptwrapper.api.level.entity;

import net.minecraft.world.entity.player.Player;

public interface IPlayer<T extends Player> extends ILivingEntity<T> {

    String getDisplayName();

    int getFoodLevel();

    void setFoodLevel(int level);

    void message(String message);

    int getGameMode();

    void setGameMode(int type);

}
