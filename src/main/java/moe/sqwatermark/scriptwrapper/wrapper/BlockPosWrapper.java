package moe.sqwatermark.scriptwrapper.wrapper;

import moe.sqwatermark.scriptwrapper.api.math.IBlockPos;
import net.minecraft.core.BlockPos;

public class BlockPosWrapper implements IBlockPos {

    private final BlockPos blockPos;

    public BlockPosWrapper(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    @Override
    public BlockPos getMCBlockPos() {
        return blockPos;
    }

    @Override
    public int getX() {
        return blockPos.getX();
    }

    @Override
    public int getY() {
        return blockPos.getY();
    }

    @Override
    public int getZ() {
        return blockPos.getZ();
    }

    @Override
    public IBlockPos offset(int dx, int dy, int dz) {
        return new BlockPosWrapper(blockPos.offset(dx, dy, dz));
    }

    @Override
    public IBlockPos above() {
        return new BlockPosWrapper(blockPos.above());
    }

    @Override
    public IBlockPos above(int distance) {
        return new BlockPosWrapper(blockPos.above(distance));
    }

    @Override
    public IBlockPos below() {
        return new BlockPosWrapper(blockPos.below());
    }

    @Override
    public IBlockPos below(int distance) {
        return new BlockPosWrapper(blockPos.below(distance));
    }

    @Override
    public IBlockPos north() {
        return new BlockPosWrapper(blockPos.north());
    }

    @Override
    public IBlockPos north(int distance) {
        return new BlockPosWrapper(blockPos.north(distance));
    }

    @Override
    public IBlockPos south() {
        return new BlockPosWrapper(blockPos.south());
    }

    @Override
    public IBlockPos south(int distance) {
        return new BlockPosWrapper(blockPos.south(distance));
    }

    @Override
    public IBlockPos west() {
        return new BlockPosWrapper(blockPos.west());
    }

    @Override
    public IBlockPos west(int distance) {
        return new BlockPosWrapper(blockPos.west(distance));
    }

    @Override
    public IBlockPos east() {
        return new BlockPosWrapper(blockPos.east());
    }

    @Override
    public IBlockPos east(int distance) {
        return new BlockPosWrapper(blockPos.east(distance));
    }

    @Override
    public double distanceTo(IBlockPos pos) {
        double d0 = this.getX() - pos.getX();
        double d1 = this.getY() - pos.getY();
        double d2 = this.getZ() - pos.getZ();
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

}
