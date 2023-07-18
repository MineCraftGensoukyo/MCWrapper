package moe.sqwatermark.scriptwrapper;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MCWrapper.MOD_ID)
public class MCWrapper {

    public static final String MOD_ID = "mcwrapper";

    private static final Logger LOGGER = LogUtils.getLogger();

    public MCWrapper() {

    }

}
