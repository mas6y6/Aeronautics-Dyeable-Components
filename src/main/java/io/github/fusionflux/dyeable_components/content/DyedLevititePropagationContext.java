package io.github.fusionflux.dyeable_components.content;


import dev.eriksonn.aeronautics.api.levitite_blend_crystallization.CrystalPropagationContext;
import dev.eriksonn.aeronautics.content.blocks.levitite.LevititeCrystalPropagationContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DyedLevititePropagationContext extends LevititeCrystalPropagationContext {
    private final DeferredBlock<Block> block;

    public DyedLevititePropagationContext(DeferredBlock<Block> block) {
        this.block = block;
    }

    @Override
    public BlockState getCrystalBlockState(Level level, BlockPos pos) {
        return this.block.value().defaultBlockState();
    }

    @Override
    public CrystalPropagationContext getContextForSpread(Level level, BlockPos pos) {
        return this;
    }
}
