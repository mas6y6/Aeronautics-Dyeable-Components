package com.fusionflux.dyeable_levitite.montent;

import com.fusionflux.dyeable_levitite.DyedLevitite;
import dev.eriksonn.aeronautics.content.components.Levitating;
import dev.eriksonn.aeronautics.content.particle.LevititeSparklePartcleData;
import dev.eriksonn.aeronautics.index.AeroBlocks;
import dev.eriksonn.aeronautics.index.AeroDataComponents;
import dev.simulated_team.simulated.registrate.SimulatedRegistrate;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;

public class DyedLevititeBlockRegister {
    public static final List<DyeColor> CREATIVE_TAB_COLOR_ORDER = List.of(
            DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BLACK,
            DyeColor.BROWN, DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW,
            DyeColor.LIME, DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE,
            DyeColor.BLUE, DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK
    );

    public static final ResourceLocation DYED_LEVITITE_TAB_SECTION = DyedLevitite.id("levitite");

    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(DyedLevitite.ID);
    public static final DeferredRegister.Items REGISTER_ITEMS = DeferredRegister.createItems(DyedLevitite.ID);

    public static final Map<DyeColor, DeferredBlock<Block>> DYED_LEVITITE = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : CREATIVE_TAB_COLOR_ORDER) {
            DeferredBlock<Block> holder = REGISTER.register(color.getSerializedName() + "_levitite",()->{
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(AeroBlocks.LEVITITE.get());
                return new Block(properties);
            });
            map.put(color, holder);
        }
    });

    public static final Map<DyeColor, DeferredItem<Item>> DYED_LEVITITE_ITEMS = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : CREATIVE_TAB_COLOR_ORDER) {
            DeferredItem<Item> holder = REGISTER_ITEMS.register(color.getSerializedName() + "_levitite",() -> new BlockItem(DyedLevititeBlockRegister.DYED_LEVITITE.get(color).get(),new Item.Properties().component(AeroDataComponents.LEVITATING, new Levitating(0.93F, Optional.of(new LevititeSparklePartcleData(color.getTextureDiffuseColor()))))));
            map.put(color, holder);

            SimulatedRegistrate.TAB_ITEMS.add(holder::value);
            SimulatedRegistrate.ITEM_TO_SECTION.put(holder.getId(), DYED_LEVITITE_TAB_SECTION);
        }
    });

    public static final Levitating DYEDLEVITITE = new Levitating(0.93F, Optional.of(new LevititeSparklePartcleData(15521489)));



}
