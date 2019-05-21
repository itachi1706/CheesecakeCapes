package com.itachi1706.Forge.CheesecakeCapes;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Created by Kenneth on 11/1/2019.
 * for com.itachi1706.Forge.CheesecakeCapes in CheesecakeCapes
 */
public class SkinHelper {

    @OnlyIn(Dist.CLIENT)
    public static boolean shouldLoadSkin() {
        boolean isSP = Minecraft.getInstance().isSingleplayer(); // User is in Single Player world or is host to a LAN world
        LogHelper.info("SP World: " + isSP);
        //LogHelper.info(">>> DEBUG: SP | MP: " + ModConfig.skinLoadOnSP + " | " + ModConfig.skinLoadOnMP);
        return (isSP) ? ModConfig.GENERAL.skinLoadOnSP.get() : ModConfig.GENERAL.skinLoadOnMP.get();
    }
}
