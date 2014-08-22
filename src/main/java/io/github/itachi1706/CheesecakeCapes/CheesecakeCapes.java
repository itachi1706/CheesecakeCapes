package io.github.itachi1706.CheesecakeCapes;

import com.jadarstudios.developercapes.DevCapes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = CheesecakeCapes.MODID, version = CheesecakeCapes.VERSION)
public class CheesecakeCapes {
	
	public static final String MODID = "cheesecakecapes";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide="io.github.itachi1706.CheesecakeCapes.CheesecakeClientProxy")
    public static CheesecakeClientProxy proxy;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.initCapes();
    }

}
