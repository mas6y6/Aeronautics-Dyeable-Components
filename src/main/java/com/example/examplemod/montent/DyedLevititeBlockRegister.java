package com.example.examplemod.montent;

import com.example.examplemod.DyedLevitite;
import dev.eriksonn.aeronautics.index.AeroBlocks;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;

public class DyedLevititeBlockRegister {

    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(DyedLevitite.ID);

    public static final Map<DyeColor, DeferredBlock<Block>> DYED_LEVITITE = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : DyeColor.values()) {
            DeferredBlock<Block> holder = REGISTER.register(color.getSerializedName() + "_levitite",()->{
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(AeroBlocks.LEVITITE.get());
                return new Block(properties);
            });
            map.put(color, holder);
        }
    });

}
