package moe.sqwatermark.scriptwrapper.api.level.block;

import net.minecraft.world.level.block.state.BlockState;

public interface IBlockState {

    BlockState getMCBlockState();

    boolean isAir();

}
