package moe.sqwatermark.scriptwrapper;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ScriptWrapper.MOD_ID)
public class ScriptWrapper {

    public static final String MOD_ID = "scriptwrapper";

    private static final Logger LOGGER = LogUtils.getLogger();

    public ScriptWrapper() {

    }

}
