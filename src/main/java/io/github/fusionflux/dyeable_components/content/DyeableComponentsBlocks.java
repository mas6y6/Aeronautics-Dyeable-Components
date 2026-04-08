package io.github.fusionflux.dyeable_components.content;

import io.github.fusionflux.dyeable_components.DyeableComponents;
import dev.eriksonn.aeronautics.content.components.Levitating;
import dev.eriksonn.aeronautics.content.particle.LevititeSparklePartcleData;
import dev.eriksonn.aeronautics.index.AeroBlocks;
import dev.eriksonn.aeronautics.index.AeroDataComponents;
import dev.simulated_team.simulated.registrate.SimulatedRegistrate;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;

public class DyeableComponentsBlocks {
    public static final ResourceLocation DYED_LEVITITE_TAB_SECTION = DyeableComponents.id("levitite");

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DyeableComponents.ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DyeableComponents.ID);

    public static final Map<DyeColor, DeferredBlock<Block>> DYED_LEVITITE_BLOCKS = DyeableComponents.colorMap(
            color -> BLOCKS.register(color.getSerializedName() + "_levitite", () -> {
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(AeroBlocks.LEVITITE.get());
                return new Block(properties);
            })
    );

    public static final Map<DyeColor, DeferredItem<Item>> DYED_LEVITITE_ITEMS = DyeableComponents.colorMap(color -> {
        LevititeSparklePartcleData particleData = new LevititeSparklePartcleData(color.getTextureDiffuseColor());
        Levitating levitating = new Levitating(0.93f, Optional.of(particleData));

        DeferredItem<Item> holder = ITEMS.register(color.getSerializedName() + "_levitite", () -> new BlockItem(
                DYED_LEVITITE_BLOCKS.get(color).get(),
                new Item.Properties().component(AeroDataComponents.LEVITATING, levitating)
        ));

        SimulatedRegistrate.TAB_ITEMS.add(holder::value);
        SimulatedRegistrate.ITEM_TO_SECTION.put(holder.getId(), DYED_LEVITITE_TAB_SECTION);
        return holder;
    });
}
