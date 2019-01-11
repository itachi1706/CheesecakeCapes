package com.itachi1706.Forge.CheesecakeCapes;

import com.jadarstudios.developercapes.DevCapes;

/**
 * Created by Kenneth on 17/7/2015.
 * for CheesecakeCapes in com.itachi1706.Forge.CheesecakeCapes
 */
public class CheesecakeClientProxy extends CheesecakeCommonProxy {

    private final String CAPES_URL_CONFIG = "http://itachi1706.com/mc/CheesecakeCapesConfig.txt";

    @Override
    public void initCapes()
    {
        LogHelper.info("Initializing Capes");
        LogHelper.info("Configuration: Retrieving from: " + CAPES_URL_CONFIG);
        DevCapes.getInstance().registerConfig(CAPES_URL_CONFIG);
        LogHelper.info("Initialization Completed");
    }

}
