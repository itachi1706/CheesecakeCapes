package com.jadarstudios.developercapes;

import com.jadarstudios.developercapes.cape.ICape;
import com.jadarstudios.developercapes.user.User;
import com.jadarstudios.developercapes.user.UserManager;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author jadar
 */
public class RenderEventHandler {

    @SubscribeEvent
    public void renderPlayer(RenderPlayerEvent.Specials.Pre event) {
        AbstractClientPlayer player = (AbstractClientPlayer) event.getEntityPlayer();

        UserManager manager = UserManager.INSTANCE;
        User user = manager.getUser(player.getName());
        if (user == null) return;

        ICape cape = user.capes.get(0);
        if (cape == null) return;

        boolean flag = cape.isTextureLoaded(player);
        if (!flag) {
            cape.loadTexture(player);
        }
    }

}
