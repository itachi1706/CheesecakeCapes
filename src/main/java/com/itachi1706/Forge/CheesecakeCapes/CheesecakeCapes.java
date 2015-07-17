package com.itachi1706.Forge.CheesecakeCapes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

/**
 * Created by Kenneth on 17/7/2015.
 * for CheesecakeCapes in com.itachi1706.Forge.CheesecakeCapes
 */

@Mod(modid=CheesecakeCapes.MOD_ID, name=CheesecakeCapes.MOD_NAME, version=CheesecakeCapes.VERSION)
public class CheesecakeCapes {

    //Essential variables for Mod info
    public static final String MOD_ID = "CheesecakeCapes";
    public static final String VERSION = "1.7.10-1.0_dev1";
    public static final String MOD_NAME = "Cheesecake Capes Mod";

    @SidedProxy(clientSide = "com.itachi1706.Forge.CheesecakeCapes.CheesecakeClientProxy")
    public static CheesecakeClientProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.initCapes();
    }



}
