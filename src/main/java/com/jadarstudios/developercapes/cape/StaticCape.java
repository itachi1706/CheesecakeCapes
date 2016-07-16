/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package com.jadarstudios.developercapes.cape;

import com.jadarstudios.developercapes.HDImageBuffer;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

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
        NetworkPlayerInfo networkPlayerInfo = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayer.class, player, "field_175157_a", "playerInfo");

        Map<MinecraftProfileTexture.Type, ResourceLocation> texture = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, networkPlayerInfo, "field_187107_a", "playerTextures");
        texture.put(MinecraftProfileTexture.Type.CAPE, location);

        // TODO: Figure out how to create a elytra texture as well and parse it
        texture.put(MinecraftProfileTexture.Type.ELYTRA, TEXTURE_ELYTRA);

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