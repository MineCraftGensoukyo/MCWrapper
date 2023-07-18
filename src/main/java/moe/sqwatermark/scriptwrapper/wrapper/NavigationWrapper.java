package moe.sqwatermark.scriptwrapper.wrapper;

import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.Path;

public class NavigationWrapper {

    private PathNavigation navigation;

    public NavigationWrapper(PathNavigation navigation) {
        this.navigation = navigation;
    }

    public BlockPosWrapper getTargetPos() {
        return new BlockPosWrapper(navigation.getTargetPos());
    }

    public void setSpeedModifier(double pSpeed) {
        navigation.setSpeedModifier(pSpeed);
    }

    public boolean moveTo(double pX, double pY, double pZ, double pSpeed) {
        return navigation.moveTo(navigation.createPath(pX, pY, pZ, 1), pSpeed);
    }

    public boolean moveTo(EntityWrapper<?> pEntity, double pSpeed) {
        Path path = navigation.createPath(pEntity.getMCEntity(), 1);
        return path != null && navigation.moveTo(path, pSpeed);
    }

}
