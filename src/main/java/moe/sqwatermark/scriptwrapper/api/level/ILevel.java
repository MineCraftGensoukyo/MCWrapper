package moe.sqwatermark.scriptwrapper.api.level;

import moe.sqwatermark.scriptwrapper.api.level.entity.IEntity;
import net.minecraft.server.level.ServerLevel;

import java.util.List;

public interface ILevel {

    ServerLevel getMCLevel();

    List<IEntity<?>> getEntitiesNearby(IEntity<?> entity, int range);

    void broadcast(String message);

}
