package com.mayakplay.cscase.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Константин on 10.01.2016.
 */
public class ModelItemCase extends ModelBase {
    ModelRenderer _asing_middle_01;
    ModelRenderer _asing_middle_left;
    ModelRenderer _asing_middle_right;
    ModelRenderer Casing_vert_01;
    ModelRenderer Casing_vert_02;
    ModelRenderer Casing_vert_03;
    ModelRenderer Casing_vert_04;
    ModelRenderer Case;
    ModelRenderer _asing_lock_01;
    ModelRenderer _asing_lock_02;
    ModelRenderer Handles;

    public ModelItemCase()
    {
        this( 0.0f );
    }

    public ModelItemCase( float par1 )
    {
        _asing_middle_01 = new ModelRenderer( this, 72, 110 );
        _asing_middle_01.setTextureSize( 128, 128 );
        _asing_middle_01.addBox( -5.5F, -0.5F, -8.5F, 11, 1, 17);
        _asing_middle_01.setRotationPoint( 0F, 13F, 0F );
        _asing_middle_left = new ModelRenderer( this, 1, 72 );
        _asing_middle_left.setTextureSize( 128, 128 );
        _asing_middle_left.addBox( -2.5F, -0.5F, -8.5F, 5, 1, 17);
        _asing_middle_left.setRotationPoint( 13F, 13F, 0F );
        _asing_middle_right = new ModelRenderer( this, 0, 94 );
        _asing_middle_right.setTextureSize( 128, 128 );
        _asing_middle_right.addBox( -2.5F, -0.5F, -8.5F, 5, 1, 17);
        _asing_middle_right.setRotationPoint( -13F, 13F, 0F );
        Casing_vert_01 = new ModelRenderer( this, 1, 1 );
        Casing_vert_01.setTextureSize( 128, 128 );
        Casing_vert_01.addBox( -0.5F, -6.5F, -8.5F, 1, 13, 17);
        Casing_vert_01.setRotationPoint( 10F, 18F, 0F );
        Casing_vert_02 = new ModelRenderer( this, 81, 1 );
        Casing_vert_02.setTextureSize( 128, 128 );
        Casing_vert_02.addBox( -0.5F, -6.5F, -8.5F, 1, 13, 17);
        Casing_vert_02.setRotationPoint( -10F, 18F, 0F );
        Casing_vert_03 = new ModelRenderer( this, 41, 1 );
        Casing_vert_03.setTextureSize( 128, 128 );
        Casing_vert_03.addBox( -0.5F, -6.5F, -8.5F, 1, 13, 17);
        Casing_vert_03.setRotationPoint( 6F, 18F, 0F );
        Casing_vert_04 = new ModelRenderer( this, 79, 53 );
        Casing_vert_04.setTextureSize( 128, 128 );
        Casing_vert_04.addBox( -0.5F, -6.5F, -8.5F, 1, 13, 17);
        Casing_vert_04.setRotationPoint( -6F, 18F, 0F );
        Case = new ModelRenderer( this, 0, 35 );
        Case.setTextureSize( 128, 128 );
        Case.addBox( -15F, -7F, -8F, 30, 14, 16);
        Case.setRotationPoint( 0F, 18F, 0F );
        _asing_lock_01 = new ModelRenderer( this, 49, 92 );
        _asing_lock_01.setTextureSize( 128, 128 );
        _asing_lock_01.addBox( -1F, -1.5F, -8.5F, 2, 3, 17);
        _asing_lock_01.setRotationPoint( 8F, 14F, 0F );
        _asing_lock_02 = new ModelRenderer( this, 49, 70 );
        _asing_lock_02.setTextureSize( 128, 128 );
        _asing_lock_02.addBox( -1F, -1.5F, -8.5F, 2, 3, 17);
        _asing_lock_02.setRotationPoint( -8F, 14F, 0F );
        Handles = new ModelRenderer( this, 1, 118 );
        Handles.setTextureSize( 128, 128 );
        Handles.addBox( -15.5F, -1F, -2.5F, 31, 2, 5);
        Handles.setRotationPoint( 0F, 15F, 0F );
    }

    public void renderModel(float par7) {
        _asing_middle_01.rotateAngleX = 0F;
        _asing_middle_01.rotateAngleY = 0F;
        _asing_middle_01.rotateAngleZ = 0F;
        _asing_middle_01.renderWithRotation(par7);

        _asing_middle_left.rotateAngleX = 0F;
        _asing_middle_left.rotateAngleY = 0F;
        _asing_middle_left.rotateAngleZ = 0F;
        _asing_middle_left.renderWithRotation(par7);

        _asing_middle_right.rotateAngleX = 0F;
        _asing_middle_right.rotateAngleY = 0F;
        _asing_middle_right.rotateAngleZ = 0F;
        _asing_middle_right.renderWithRotation(par7);

        Casing_vert_01.rotateAngleX = 0F;
        Casing_vert_01.rotateAngleY = 0F;
        Casing_vert_01.rotateAngleZ = 0F;
        Casing_vert_01.renderWithRotation(par7);

        Casing_vert_02.rotateAngleX = 0F;
        Casing_vert_02.rotateAngleY = 0F;
        Casing_vert_02.rotateAngleZ = 0F;
        Casing_vert_02.renderWithRotation(par7);

        Casing_vert_03.rotateAngleX = 0F;
        Casing_vert_03.rotateAngleY = 0F;
        Casing_vert_03.rotateAngleZ = 0F;
        Casing_vert_03.renderWithRotation(par7);

        Casing_vert_04.rotateAngleX = 0F;
        Casing_vert_04.rotateAngleY = 0F;
        Casing_vert_04.rotateAngleZ = 0F;
        Casing_vert_04.renderWithRotation(par7);

        Case.rotateAngleX = 0F;
        Case.rotateAngleY = 0F;
        Case.rotateAngleZ = 0F;
        Case.renderWithRotation(par7);

        _asing_lock_01.rotateAngleX = 0F;
        _asing_lock_01.rotateAngleY = 0F;
        _asing_lock_01.rotateAngleZ = 0F;
        _asing_lock_01.renderWithRotation(par7);

        _asing_lock_02.rotateAngleX = 0F;
        _asing_lock_02.rotateAngleY = 0F;
        _asing_lock_02.rotateAngleZ = 0F;
        _asing_lock_02.renderWithRotation(par7);

        Handles.rotateAngleX = 0F;
        Handles.rotateAngleY = 0F;
        Handles.rotateAngleZ = 0F;
        Handles.renderWithRotation(par7);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        _asing_middle_01.rotateAngleX = 0F;
        _asing_middle_01.rotateAngleY = 0F;
        _asing_middle_01.rotateAngleZ = 0F;
        _asing_middle_01.renderWithRotation(par7);

        _asing_middle_left.rotateAngleX = 0F;
        _asing_middle_left.rotateAngleY = 0F;
        _asing_middle_left.rotateAngleZ = 0F;
        _asing_middle_left.renderWithRotation(par7);

        _asing_middle_right.rotateAngleX = 0F;
        _asing_middle_right.rotateAngleY = 0F;
        _asing_middle_right.rotateAngleZ = 0F;
        _asing_middle_right.renderWithRotation(par7);

        Casing_vert_01.rotateAngleX = 0F;
        Casing_vert_01.rotateAngleY = 0F;
        Casing_vert_01.rotateAngleZ = 0F;
        Casing_vert_01.renderWithRotation(par7);

        Casing_vert_02.rotateAngleX = 0F;
        Casing_vert_02.rotateAngleY = 0F;
        Casing_vert_02.rotateAngleZ = 0F;
        Casing_vert_02.renderWithRotation(par7);

        Casing_vert_03.rotateAngleX = 0F;
        Casing_vert_03.rotateAngleY = 0F;
        Casing_vert_03.rotateAngleZ = 0F;
        Casing_vert_03.renderWithRotation(par7);

        Casing_vert_04.rotateAngleX = 0F;
        Casing_vert_04.rotateAngleY = 0F;
        Casing_vert_04.rotateAngleZ = 0F;
        Casing_vert_04.renderWithRotation(par7);

        Case.rotateAngleX = 0F;
        Case.rotateAngleY = 0F;
        Case.rotateAngleZ = 0F;
        Case.renderWithRotation(par7);

        _asing_lock_01.rotateAngleX = 0F;
        _asing_lock_01.rotateAngleY = 0F;
        _asing_lock_01.rotateAngleZ = 0F;
        _asing_lock_01.renderWithRotation(par7);

        _asing_lock_02.rotateAngleX = 0F;
        _asing_lock_02.rotateAngleY = 0F;
        _asing_lock_02.rotateAngleZ = 0F;
        _asing_lock_02.renderWithRotation(par7);

        Handles.rotateAngleX = 0F;
        Handles.rotateAngleY = 0F;
        Handles.rotateAngleZ = 0F;
        Handles.renderWithRotation(par7);
    }
}
