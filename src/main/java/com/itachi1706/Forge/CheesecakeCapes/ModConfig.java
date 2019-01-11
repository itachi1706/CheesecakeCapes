package com.itachi1706.Forge.CheesecakeCapes;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Kenneth on 11/1/2019.
 * for com.itachi1706.Forge.CheesecakeCapes in CheesecakeCapes
 */
@Config(modid = CheesecakeCapes.MOD_ID)
@Config.LangKey("cheesecakecapes.config.title")
public class ModConfig {

    @Config.Comment("Load custom skin on Single Player Worlds")
    public static boolean skinLoadOnSP = false;

    @Config.Comment("Load custom skin on Multiplayer Servers")
    public static boolean skinLoadOnMP = true;

    @Mod.EventBusSubscriber(modid = CheesecakeCapes.MOD_ID)
    private static class EventHandler {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(CheesecakeCapes.MOD_ID)) {
                ConfigManager.sync(CheesecakeCapes.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
