package com.itachi1706.Forge.CheesecakeCapes;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Created by Kenneth on 11/1/2019.
 * for com.itachi1706.Forge.CheesecakeCapes in CheesecakeCapes
 */
public class ModConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();

    public static class General {
        public final ForgeConfigSpec.BooleanValue skinLoadOnSP;
        public final ForgeConfigSpec.BooleanValue skinLoadOnMP;

        public General(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            skinLoadOnSP = builder
                    .comment("Load custom skin on Single Player Worlds [false/true|default:false]")
                    .translation("enable.ocdtorcher.config")
                    .define("skinLoadOnSP", true);
            skinLoadOnMP = builder
                    .comment("Load custom skin on Multiplayer Servers [false/true|default:true]")
                    .translation("distance.ocdtorcher.config")
                    .define("skinLoadOnMP", false);
            builder.pop();
        }

    }

    /*@Mod.EventBusSubscriber(modid = CheesecakeCapes.MOD_ID)
    private static class EventHandler {

        *//**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         *//*
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(CheesecakeCapes.MOD_ID)) {
                ConfigManager.sync(CheesecakeCapes.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }*/
}
