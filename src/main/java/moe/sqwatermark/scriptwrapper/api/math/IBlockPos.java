package moe.sqwatermark.scriptwrapper.api.math;

import net.minecraft.core.BlockPos;

public interface IBlockPos {

    BlockPos getMCBlockPos();

    int getX();

    int getY();

    int getZ();

    IBlockPos offset(int dx, int dy, int dz);

    IBlockPos above();

    IBlockPos above(int distance);

    IBlockPos below();

    IBlockPos below(int distance);

    IBlockPos north();

    IBlockPos north(int distance);

    IBlockPos south();

    IBlockPos south(int distance);

    IBlockPos west();

    IBlockPos west(int distance);

    IBlockPos east();

    IBlockPos east(int distance);

    double distanceTo(IBlockPos pos);

}
