/*
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 3.3.0.0
 */
package com.jadarstudios.developercapes;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

/**
 * This class is an implementation of {@link IImageBuffer} that allows capes to be in HD
 *
 * @author Jadar
 */
@OnlyIn(Dist.CLIENT)
public class HDImageBuffer implements IImageBuffer {

    private MinecraftProfileTexture.Type skinType;
    public HDImageBuffer(MinecraftProfileTexture.Type type) {
        skinType = type;
    }
    /*@Override
    public BufferedImage parseUserSkin(final BufferedImage texture) {
        if (texture == null)
            return null;
        int imageWidth = texture.getWidth(null) <= 64 ? 64 : texture.getWidth(null);
        int imageHeight = texture.getHeight(null) <= 32 ? 32 : texture.getHeight(null);

        BufferedImage capeImage = new BufferedImage(imageWidth, imageHeight, 2);

        Graphics graphics = capeImage.getGraphics();
        graphics.drawImage(texture, 0, 0, null);
        graphics.dispose();

        return capeImage;
    }*/

    /*
    ThreadDownloadImageData threaddownloadimagedata = new ThreadDownloadImageData(file2, profileTexture.getUrl(), DefaultPlayerSkin.getDefaultSkinLegacy(), new IImageBuffer() {
            public NativeImage parseUserSkin(NativeImage nativeImageIn) {
               return iimagebuffer != null ? iimagebuffer.parseUserSkin(nativeImageIn) : nativeImageIn;
            }

            public void skinAvailable() {
               if (iimagebuffer != null) {
                  iimagebuffer.skinAvailable();
               }

               if (skinAvailableCallback != null) {
                  skinAvailableCallback.onSkinTextureAvailable(textureType, resourcelocation, profileTexture);
               }

            }
         });
     */

    @Override
    @Nonnull
    public NativeImage parseUserSkin(@Nonnull NativeImage nativeImage) {
        final IImageBuffer iimagebuffer = skinType == MinecraftProfileTexture.Type.SKIN ? new ImageBufferDownload() : null;
        if (iimagebuffer != null) return iimagebuffer.parseUserSkin(nativeImage);
        return nativeImage;

        /*int imageWidth = nativeImage.getWidth() <= 64 ? 64 : nativeImage.getWidth();
        int imageHeight = nativeImage.getHeight() <= 32 ? 32 : nativeImage.getHeight();

        NativeImage capeImage = new NativeImage(imageWidth, imageHeight, true);

        capeImage.copyImageData(nativeImage);
        *//*Graphics graphics = capeImage.getFormat();
        graphics.drawImage(nativeImage, 0, 0, null);
        graphics.dispose();*//*

        return capeImage;*/
    }

    @Override
    public void skinAvailable() {}
}