package com.itachi1706.Forge.CheesecakeCapes;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Created by Kenneth on 17/7/2015.
 * for CheesecakeCapes in com.itachi1706.Forge.CheesecakeCapes
 */

//@Mod(modid=CheesecakeCapes.MOD_ID, name=CheesecakeCapes.MOD_NAME, version=CheesecakeCapes.VERSION)
@Mod(CheesecakeCapes.MOD_ID)
public class CheesecakeCapes {

    //Essential variables for Mod info
    public static final String MOD_ID = "cheesecakecapes";
    public static final String VERSION = "1.12.2-1.8";
    public static final String MOD_NAME = "Cheesecake Capes Mod";

    //@SidedProxy(clientSide = "com.itachi1706.Forge.CheesecakeCapes.CheesecakeClientProxy")
    public static CheesecakeCommonProxy proxy;

    public CheesecakeCapes() {
        proxy = DistExecutor.runForDist(()-> CheesecakeClientProxy::new, ()-> CheesecakeCommonProxy::new);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);
    }

    //@Mod.EventHandler
    private void clientInit(FMLClientSetupEvent event){
        proxy.initCapes();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, com.itachi1706.Forge.CheesecakeCapes.ModConfig.spec);
    }



}
