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

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;

/**
 * Default Cape implementation
 * 
 * @author jadar
 */
public class StaticCape extends AbstractCape {

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
        // Reflection (May fail someday)
        //player.func_152121_a(MinecraftProfileTexture.Type.CAPE, location);
        try {
            Field playerInfo = AbstractClientPlayer.class.getDeclaredField("playerInfo");
            playerInfo.setAccessible(true);
            NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo) playerInfo.get(player);

            Field playerTextures = NetworkPlayerInfo.class.getDeclaredField("playerTextures");
            playerTextures.setAccessible(true);
            Map<MinecraftProfileTexture.Type, ResourceLocation> texture = (Map<MinecraftProfileTexture.Type, ResourceLocation>) playerTextures.get(networkPlayerInfo);
            texture.put(MinecraftProfileTexture.Type.CAPE, location);
            texture.put(MinecraftProfileTexture.Type.ELYTRA, location);
            playerTextures.set(networkPlayerInfo, texture);

            // TODO: Test
            ResourceLocation skin = player.getLocationSkin();
            ResourceLocation elytra = player.getLocationElytra();
            ResourceLocation cape = player.getLocationCape();

            /*Method load = NetworkPlayerInfo.class.getDeclaredMethod("loadPlayerTextures");
            load.setAccessible(true);
            load.invoke(null);
            load.setAccessible(false);*/
            //networkPlayerInfo.playerTextures.put(MinecraftProfileTexture.Type.CAPE, location);


            playerInfo.setAccessible(false);
            playerTextures.setAccessible(false);
        } catch (NoSuchFieldException e) {
            DevCapes.logger.error("Cannot find fields. Mappings may have changed!");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            DevCapes.logger.error("Cannot access fields");
            e.printStackTrace();
        }/* catch (NoSuchMethodException e) {
            DevCapes.logger.error("Cannot find method. Mappings may have changed!");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            DevCapes.logger.error("Cannot invoke method. Mappings may have changed!");
            e.printStackTrace();
        }*/

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