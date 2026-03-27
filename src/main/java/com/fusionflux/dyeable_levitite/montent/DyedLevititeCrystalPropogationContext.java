package com.fusionflux.dyeable_levitite.montent;


import dev.eriksonn.aeronautics.api.levitite_blend_crystallization.CrystalPropagationContext;
import dev.eriksonn.aeronautics.api.levitite_blend_crystallization.LevititeBlendHelper;
import dev.eriksonn.aeronautics.index.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DyedLevititeCrystalPropogationContext implements CrystalPropagationContext {

    private final DeferredBlock<Block> dyeable_levitite;

    public DyedLevititeCrystalPropogationContext(DeferredBlock<Block> dyeable_levitite) {
        this.dyeable_levitite = dyeable_levitite;
    }

    @Override
    public void onCrystallizationInitialize(Level level, BlockPos pos, boolean isDormant) {
        level.playSound((Player)null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.2F, 1.5F);
        if (!isDormant) {
            LevititeBlendHelper.spawnParticles(level, pos, ParticleTypes.FLAME, 20);
        }

        LevititeBlendHelper.spawnParticles(level, pos, ParticleTypes.SMOKE, 15);
    }

    @Override
    public void onCrystallize(Level level, BlockPos pos) {
        this.onDefaultCrystallize(level, pos);
        if (!level.isClientSide) {
            AeroSoundEvents.LEVITITE_BLEND_CRYSTALLIZE.play(level, (Player)null, pos, 1.0F, 1.0F);
            LevititeBlendHelper.spawnParticles(level, pos, ParticleTypes.FLAME, 30);
            AeroAdvancements.UNIDENTIFIED_FLOATING_OBJECT.awardToNearby(pos, level);
        }

    }
    @Override
    public void onCrystallizationFail(Level level, BlockPos pos, int attempts, boolean isDormant) {
        LevititeBlendHelper.spawnParticles(level, pos, ParticleTypes.SMOKE, 15);
    }

    @Override
    public BlockState getCrystalBlockState(Level level, BlockPos pos) {
        return dyeable_levitite.value().defaultBlockState();
    }

    @Override
    public boolean canSpreadTo(FluidState state) {
        return state.is(LevititeBlendHelper.getFluid());
    }



    @Override
    public CrystalPropagationContext getContextForSpread(Level level, BlockPos pos) {
        return this;
    }

    @Override
    public TagKey<Block> getCatalyzerTag() {
        return AeroTags.BlockTags.LEVITITE_CATALYZER;
    }
}
