/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package com.jadarstudios.developercapes.cape;

import com.jadarstudios.developercapes.DevCapes;
import com.jadarstudios.developercapes.HDImageBuffer;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;

/**
 * Default Cape implementation
 * 
 * @author jadar
 */
public class StaticCape extends AbstractCape {

    private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");

    public StaticCape(String name, URL url) {
        this.setName(name);
        this.setURL(url);
    }

    public StaticCape(String name) {
        this(name, null);
    }

    @Override
    public void loadTexture(AbstractClientPlayer player) {
        ResourceLocation location = this.getLocation();
        //player.func_152121_a(MinecraftProfileTexture.Type.CAPE, location);
        // Reflection (May fail someday)
        try {
            Field playerInfo = ReflectionHelper.findField(AbstractClientPlayer.class, new String[] {"field_175157_a", "playerInfo"});
            playerInfo.setAccessible(true);
            NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo) playerInfo.get(player);

            Field playerTextures = ReflectionHelper.findField(NetworkPlayerInfo.class, new String[] {"field_178864_d", "playerTextures"});
            playerTextures.setAccessible(true);
            Map<MinecraftProfileTexture.Type, ResourceLocation> texture = (Map<MinecraftProfileTexture.Type, ResourceLocation>) playerTextures.get(networkPlayerInfo);
            texture.put(MinecraftProfileTexture.Type.CAPE, location);

            // TODO: Figure out how to create a elytra texture as well and parse it
            texture.put(MinecraftProfileTexture.Type.ELYTRA, TEXTURE_ELYTRA);

            playerInfo.setAccessible(false);
            playerTextures.setAccessible(false);
        } catch (IllegalAccessException e) {
            DevCapes.logger.error("Cannot access fields");
            e.printStackTrace();
        }

        Minecraft.getMinecraft().renderEngine.loadTexture(location, this.getTexture());
    }

    @Override
    public boolean isTextureLoaded(AbstractClientPlayer player) {
        ResourceLocation cape = player.getLocationCape();
        return cape != null;
    }

    public void setURL(URL url) {
        if (url == null) {
            this.texture = null;
            return;
        }
        this.texture = new ThreadDownloadImageData(null, url.toString(), null, new HDImageBuffer());
    }

    public void setName(String name) {
        this.name = name;
        this.location = new ResourceLocation("DevCapes/" + name);
    }
}