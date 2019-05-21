/*
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package com.jadarstudios.developercapes.cape;

import com.itachi1706.Forge.CheesecakeCapes.SkinHelper;
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

import static com.jadarstudios.developercapes.DevCapes.DEV_CAPE;

/**
 * Default Cape implementation
 * 
 * @author jadar
 */
public class StaticCape extends AbstractCape {

    private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");

    public StaticCape(String name, URL url, String type) {
        this.setName(name, type);
        this.setURL(url);
    }

    public StaticCape(String name) {
        this(name, null, DEV_CAPE);
    }

    private Map<MinecraftProfileTexture.Type, ResourceLocation> getTexture(AbstractClientPlayer player) {
        // player.func_152121_a(MinecraftProfileTexture.Type.CAPE, location);
        // Reflection (May fail someday)
        NetworkPlayerInfo networkPlayerInfo = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayer.class, player, "field_175157_a"); // field_175157_a -> playerInfo
        return ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, networkPlayerInfo, "field_187107_a"); // field_187107_a -> playerTextures
    }

    private MinecraftProfileTexture.Type getType(ResourceLocation location) {
        if (location.getResourcePath().contains("-" + DEV_CAPE)) return MinecraftProfileTexture.Type.CAPE;
        return MinecraftProfileTexture.Type.SKIN;
    }

    @Override
    public void loadTexture(AbstractClientPlayer player, ICape skin) {
        ResourceLocation location = this.getLocation();
        Map<MinecraftProfileTexture.Type, ResourceLocation> texture = getTexture(player);
        MinecraftProfileTexture.Type capeType = getType(location);
        texture.put(capeType, location);

        // TODO: Figure out how to create a elytra texture as well and parse it
        texture.put(MinecraftProfileTexture.Type.ELYTRA, TEXTURE_ELYTRA);

        // If skin then add
        if (skin != null && SkinHelper.shouldLoadSkin()) {
            MinecraftProfileTexture.Type skinType = getType(skin.getLocation());
            texture.put(skinType, skin.getLocation());
            Minecraft.getMinecraft().renderEngine.loadTexture(skin.getLocation(), skin.getTexture());
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

    public void setName(String name, String type) {
        this.name = name;
        this.location = new ResourceLocation("DevCapes/" + name + "-" + type);
    }
}