package moe.sqwatermark.scriptwrapper.wrapper;

import moe.sqwatermark.scriptwrapper.api.math.IVec3;
import net.minecraft.world.phys.Vec3;

public class Vec3Wrapper implements IVec3 {

    Vec3 vec3;

    public Vec3Wrapper(Vec3 vec3) {
        this.vec3 = vec3;
    }

    public Vec3 getMCVec3() {
        return vec3;
    }
}
