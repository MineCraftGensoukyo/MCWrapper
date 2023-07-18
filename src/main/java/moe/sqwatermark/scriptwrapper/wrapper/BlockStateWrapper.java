package moe.sqwatermark.scriptwrapper.wrapper;

import moe.sqwatermark.scriptwrapper.api.level.block.IBlockState;
import net.minecraft.world.level.block.state.BlockState;

public class BlockStateWrapper implements IBlockState {

    private final BlockState state;

    public BlockStateWrapper(BlockState state) {
        this.state = state;
    }

    @Override
    public BlockState getMCBlockState() {
        return state;
    }

    @Override
    public boolean isAir() {
        return state.isAir();
    }

}
