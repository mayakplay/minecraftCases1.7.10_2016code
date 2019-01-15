package com.mayakplay.cscase.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Константин on 03.01.2016.
 */
public class ModelGuiCase extends ModelBase {
    ModelRenderer Fence;
    ModelRenderer Fence1;
    ModelRenderer Fence2;
    ModelRenderer Fence3;
    ModelRenderer Fence4;
    ModelRenderer Fence5;
    ModelRenderer Fence6;
    ModelRenderer Fence7;
    ModelRenderer Fence8;
    ModelRenderer Fence9;
    ModelRenderer Fence10;
    ModelRenderer Fence11;
    ModelRenderer Fence12;
    ModelRenderer Back;
    ModelRenderer Back1;
    ModelRenderer Back2;
    ModelRenderer Back3;
    ModelRenderer Back4;
    ModelRenderer Stay;
    ModelRenderer Stay1;
    ModelRenderer Block;

    public ModelGuiCase()
    {
        this( 0.0f );
    }

    public ModelGuiCase(float par1 ) {
        Fence = new ModelRenderer(this, 0, 0);
        Fence.setTextureSize(128, 128);
        Fence.addBox(-0.5F, -0.5F, -16F, 1, 1, 32);
        Fence.setRotationPoint(0F, 23.5F, 0F);
        Fence1 = new ModelRenderer(this, 0, 0);
        Fence1.setTextureSize(128, 128);
        Fence1.addBox(-0.5F, -0.5F, -16F, 1, 1, 32);
        Fence1.setRotationPoint(0F, 20.5F, 0F);
        Fence2 = new ModelRenderer(this, 0, 0);
        Fence2.setTextureSize(128, 128);
        Fence2.addBox(-0.5F, -0.5F, -16F, 1, 1, 32);
        Fence2.setRotationPoint(0F, 17.5F, 0F);
        Fence3 = new ModelRenderer(this, 0, 0);
        Fence3.setTextureSize(128, 128);
        Fence3.addBox(-0.5F, -0.5F, -16F, 1, 1, 32);
        Fence3.setRotationPoint(0F, 14.5F, 0F);
        Fence4 = new ModelRenderer(this, 0, 0);
        Fence4.setTextureSize(128, 128);
        Fence4.addBox(-0.5F, -0.5F, -16F, 1, 1, 32);
        Fence4.setRotationPoint(0F, 11.5F, 0F);
        Fence5 = new ModelRenderer(this, 0, 0);
        Fence5.setTextureSize(128, 128);
        Fence5.addBox(-0.5F, -0.5F, -16F, 1, 1, 32);
        Fence5.setRotationPoint(0F, 8.5F, 0F);
        Fence6 = new ModelRenderer(this, 17, 17);
        Fence6.setTextureSize(128, 128);
        Fence6.addBox(-0.5F, -0.5F, -7.5F, 1, 1, 15);
        Fence6.setRotationPoint(-0.13F, 16.5F, -15.5F);
        Fence7 = new ModelRenderer(this, 17, 17);
        Fence7.setTextureSize(128, 128);
        Fence7.addBox(-0.5F, -0.5F, -7.5F, 1, 1, 15);
        Fence7.setRotationPoint(-0.13F, 16.5F, 15.5F);
        Fence8 = new ModelRenderer(this, 17, 17);
        Fence8.setTextureSize(128, 128);
        Fence8.addBox(-0.5F, -0.5F, -7.5F, 1, 1, 15);
        Fence8.setRotationPoint(-0.13F, 16.5F, 0F);
        Fence9 = new ModelRenderer(this, 17, 17);
        Fence9.setTextureSize(128, 128);
        Fence9.addBox(-0.5F, -0.5F, -7.5F, 1, 1, 15);
        Fence9.setRotationPoint(-0.13F, 16.5F, 11F);
        Fence10 = new ModelRenderer(this, 17, 17);
        Fence10.setTextureSize(128, 128);
        Fence10.addBox(-0.5F, -0.5F, -7.5F, 1, 1, 15);
        Fence10.setRotationPoint(-0.13F, 16.5F, 5F);
        Fence11 = new ModelRenderer(this, 17, 17);
        Fence11.setTextureSize(128, 128);
        Fence11.addBox(-0.5F, -0.5F, -7.5F, 1, 1, 15);
        Fence11.setRotationPoint(-0.13F, 16.5F, -11F);
        Fence12 = new ModelRenderer(this, 17, 17);
        Fence12.setTextureSize(128, 128);
        Fence12.addBox(-0.5F, -0.5F, -7.5F, 1, 1, 15);
        Fence12.setRotationPoint(-0.13F, 16.5F, -5F);
        Back = new ModelRenderer(this, 0, 33);
        Back.setTextureSize(128, 128);
        Back.addBox(-0.5F, -8F, -16F, 1, 16, 32);
        Back.setRotationPoint(-17F, 16F, 0F);
        Back1 = new ModelRenderer(this, 0, 33);
        Back1.setTextureSize(128, 128);
        Back1.addBox(-0.5F, -8F, -16F, 1, 16, 32);
        Back1.setRotationPoint(-8.5F, 23.5F, 0F);
        Back2 = new ModelRenderer(this, 0, 33);
        Back2.setTextureSize(128, 128);
        Back2.addBox(-0.5F, -8F, -16F, 1, 16, 32);
        Back2.setRotationPoint(-8.5F, 8.5F, 0F);
        Back3 = new ModelRenderer(this, 15, 64);
        Back3.setTextureSize(128, 128);
        Back3.addBox(-8F, -8F, -0.5F, 16, 16, 1);
        Back3.setRotationPoint(-8.5F, 16.36F, -15.5F);
        Back4 = new ModelRenderer(this, 15, 64);
        Back4.setTextureSize(128, 128);
        Back4.addBox(-8F, -8F, -0.5F, 16, 16, 1);
        Back4.setRotationPoint(-8.5F, 16.36F, 15.5F);
        Stay = new ModelRenderer(this, 35, 0);
        Stay.setTextureSize(128, 128);
        Stay.addBox(-3F, -0.5F, -11F, 6, 1, 22);
        Stay.setRotationPoint(-9F, 18F, 0.6217537F);
        Stay1 = new ModelRenderer(this, 71, 4);
        Stay1.setTextureSize(128, 128);
        Stay1.addBox(-1F, -3.5F, -4.5F, 2, 7, 9);
        Stay1.setRotationPoint(-8.291135F, 22.32676F, 0.6217537F);
        Block = new ModelRenderer(this, 0, 0);
        Block.setTextureSize(128, 128);
        Block.addBox(-0.5F, -2F, -1.5F, 1, 4, 3);
        Block.setRotationPoint(-3F, 6F, -11F);
    }
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        Fence.rotateAngleX = 0F;
        Fence.rotateAngleY = 0F;
        Fence.rotateAngleZ = 0F;
        Fence.renderWithRotation(par7);

        Fence1.rotateAngleX = 0F;
        Fence1.rotateAngleY = 0F;
        Fence1.rotateAngleZ = 0F;
        Fence1.renderWithRotation(par7);

        Fence2.rotateAngleX = 0F;
        Fence2.rotateAngleY = 0F;
        Fence2.rotateAngleZ = 0F;
        Fence2.renderWithRotation(par7);

        Fence3.rotateAngleX = 0F;
        Fence3.rotateAngleY = 0F;
        Fence3.rotateAngleZ = 0F;
        Fence3.renderWithRotation(par7);

        Fence4.rotateAngleX = 0F;
        Fence4.rotateAngleY = 0F;
        Fence4.rotateAngleZ = 0F;
        Fence4.renderWithRotation(par7);

        Fence5.rotateAngleX = 0F;
        Fence5.rotateAngleY = 0F;
        Fence5.rotateAngleZ = 0F;
        Fence5.renderWithRotation(par7);

        Fence6.rotateAngleX = -1.570796F;
        Fence6.rotateAngleY = 0F;
        Fence6.rotateAngleZ = 0F;
        Fence6.renderWithRotation(par7);

        Fence7.rotateAngleX = -1.570796F;
        Fence7.rotateAngleY = 0F;
        Fence7.rotateAngleZ = 0F;
        Fence7.renderWithRotation(par7);

        Fence8.rotateAngleX = -1.570796F;
        Fence8.rotateAngleY = 0F;
        Fence8.rotateAngleZ = 0F;
        Fence8.renderWithRotation(par7);

        Fence9.rotateAngleX = -1.570796F;
        Fence9.rotateAngleY = 0F;
        Fence9.rotateAngleZ = 0F;
        Fence9.renderWithRotation(par7);

        Fence10.rotateAngleX = -1.570796F;
        Fence10.rotateAngleY = 0F;
        Fence10.rotateAngleZ = 0F;
        Fence10.renderWithRotation(par7);

        Fence11.rotateAngleX = -1.570796F;
        Fence11.rotateAngleY = 0F;
        Fence11.rotateAngleZ = 0F;
        Fence11.renderWithRotation(par7);

        Fence12.rotateAngleX = -1.570796F;
        Fence12.rotateAngleY = 0F;
        Fence12.rotateAngleZ = 0F;
        Fence12.renderWithRotation(par7);

        Back.rotateAngleX = 0F;
        Back.rotateAngleY = 0F;
        Back.rotateAngleZ = 0F;
        Back.renderWithRotation(par7);

        Back1.rotateAngleX = 0F;
        Back1.rotateAngleY = 0F;
        Back1.rotateAngleZ = -1.570796F;
        Back1.renderWithRotation(par7);

        Back2.rotateAngleX = 0F;
        Back2.rotateAngleY = 0F;
        Back2.rotateAngleZ = 1.570796F;
        Back2.renderWithRotation(par7);

        Back3.rotateAngleX = 0F;
        Back3.rotateAngleY = 0F;
        Back3.rotateAngleZ = -1.570796F;
        Back3.renderWithRotation(par7);

        Back4.rotateAngleX = 0F;
        Back4.rotateAngleY = 3.141593F;
        Back4.rotateAngleZ = -1.570796F;
        Back4.renderWithRotation(par7);

        Stay.rotateAngleX = 0F;
        Stay.rotateAngleY = 0F;
        Stay.rotateAngleZ = 0.3157104F;
        Stay.renderWithRotation(par7);

        Stay1.rotateAngleX = 0F;
        Stay1.rotateAngleY = 0F;
        Stay1.rotateAngleZ = 0F;
        Stay1.renderWithRotation(par7);

        Block.rotateAngleX = 0F;
        Block.rotateAngleY = 0F;
        Block.rotateAngleZ = 0F;
        Block.renderWithRotation(par7);
    }

    public void renderModel(float par7, float fencePos, float numPos)
    {
        GL11.glPushMatrix(); // -0.01F - -0.9F
        GL11.glTranslatef(0, fencePos ,0);
        Fence.rotateAngleX = 0F;
        Fence.rotateAngleY = 0F;
        Fence.rotateAngleZ = 0F;
        Fence.renderWithRotation(par7);

        Fence1.rotateAngleX = 0F;
        Fence1.rotateAngleY = 0F;
        Fence1.rotateAngleZ = 0F;
        Fence1.renderWithRotation(par7);

        Fence2.rotateAngleX = 0F;
        Fence2.rotateAngleY = 0F;
        Fence2.rotateAngleZ = 0F;
        Fence2.renderWithRotation(par7);

        Fence3.rotateAngleX = 0F;
        Fence3.rotateAngleY = 0F;
        Fence3.rotateAngleZ = 0F;
        Fence3.renderWithRotation(par7);

        Fence4.rotateAngleX = 0F;
        Fence4.rotateAngleY = 0F;
        Fence4.rotateAngleZ = 0F;
        Fence4.renderWithRotation(par7);

        Fence5.rotateAngleX = 0F;
        Fence5.rotateAngleY = 0F;
        Fence5.rotateAngleZ = 0F;
        Fence5.renderWithRotation(par7);

        Fence6.rotateAngleX = -1.570796F;
        Fence6.rotateAngleY = 0F;
        Fence6.rotateAngleZ = 0F;
        Fence6.renderWithRotation(par7);

        Fence7.rotateAngleX = -1.570796F;
        Fence7.rotateAngleY = 0F;
        Fence7.rotateAngleZ = 0F;
        Fence7.renderWithRotation(par7);

        Fence8.rotateAngleX = -1.570796F;
        Fence8.rotateAngleY = 0F;
        Fence8.rotateAngleZ = 0F;
        Fence8.renderWithRotation(par7);

        Fence9.rotateAngleX = -1.570796F;
        Fence9.rotateAngleY = 0F;
        Fence9.rotateAngleZ = 0F;
        Fence9.renderWithRotation(par7);

        Fence10.rotateAngleX = -1.570796F;
        Fence10.rotateAngleY = 0F;
        Fence10.rotateAngleZ = 0F;
        Fence10.renderWithRotation(par7);

        Fence11.rotateAngleX = -1.570796F;
        Fence11.rotateAngleY = 0F;
        Fence11.rotateAngleZ = 0F;
        Fence11.renderWithRotation(par7);

        Fence12.rotateAngleX = -1.570796F;
        Fence12.rotateAngleY = 0F;
        Fence12.rotateAngleZ = 0F;
        Fence12.renderWithRotation(par7);
        GL11.glPopMatrix();

        //RenderHelper.disableStandardItemLighting();
        Back.rotateAngleX = 0F;
        Back.rotateAngleY = 0F;
        Back.rotateAngleZ = 0F;
        Back.renderWithRotation(par7);
        RenderHelper.enableGUIStandardItemLighting();

        Back1.rotateAngleX = 0F;
        Back1.rotateAngleY = 0F;
        Back1.rotateAngleZ = -1.570796F;
        Back1.renderWithRotation(par7);

        Back2.rotateAngleX = 0F;
        Back2.rotateAngleY = 0F;
        Back2.rotateAngleZ = 1.570796F;
        Back2.renderWithRotation(par7);

        Back3.rotateAngleX = 0F;
        Back3.rotateAngleY = 0F;
        Back3.rotateAngleZ = -1.570796F;
        Back3.renderWithRotation(par7);

        Back4.rotateAngleX = 0F;
        Back4.rotateAngleY = 3.141593F;
        Back4.rotateAngleZ = -1.570796F;
        Back4.renderWithRotation(par7);

//        Stay.rotateAngleX = 0F;
//        Stay.rotateAngleY = 0F;
//        Stay.rotateAngleZ = 0.3157104F;
//        Stay.renderWithRotation(par7);
//
//        Stay1.rotateAngleX = 0F;
//        Stay1.rotateAngleY = 0F;
//        Stay1.rotateAngleZ = 0F;
//        Stay1.renderWithRotation(par7);

        RenderHelper.disableStandardItemLighting();
        GL11.glPushMatrix(); // 0.01F - 0.3F
        GL11.glTranslatef(0, numPos,0);
        Block.rotateAngleX = 0F;
        Block.rotateAngleY = 0F;
        Block.rotateAngleZ = 0F;
        Block.renderWithRotation(par7);
        GL11.glPopMatrix();
    }
}