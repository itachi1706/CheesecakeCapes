package com.itachi1706.Forge.CheesecakeCapes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Kenneth on 17/7/2015.
 * for CheesecakeCapes in com.itachi1706.Forge.CheesecakeCapes
 */

@Mod(modid=CheesecakeCapes.MOD_ID, name=CheesecakeCapes.MOD_NAME, version=CheesecakeCapes.VERSION)
public class CheesecakeCapes {

    //Essential variables for Mod info
    public static final String MOD_ID = "CheesecakeCapes";
    public static final String VERSION = "1.10.2-1.4";
    public static final String MOD_NAME = "Cheesecake Capes Mod";

    @SidedProxy(clientSide = "com.itachi1706.Forge.CheesecakeCapes.CheesecakeClientProxy")
    public static CheesecakeClientProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.initCapes();
    }



}
