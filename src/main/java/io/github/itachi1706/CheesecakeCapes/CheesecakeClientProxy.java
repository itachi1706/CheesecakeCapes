package io.github.itachi1706.CheesecakeCapes;

import com.jadarstudios.developercapes.DevCapes;

public class CheesecakeClientProxy extends CheesecakeCommonProxy {

	@Override
	public void initCapes()
	{
		//DevCapes.getInstance().registerConfig("https://dl.dropboxusercontent.com/u/46826040/McHost/DevCapesConfig.txt", "cheesecakecapes");
		DevCapes.getInstance().registerConfig("https://dl.dropboxusercontent.com/u/46826040/McHost/DevCapesConfig.txt");
	}
	
}
