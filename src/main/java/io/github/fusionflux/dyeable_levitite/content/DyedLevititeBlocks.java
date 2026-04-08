package io.github.fusionflux.dyeable_levitite.content;

import io.github.fusionflux.dyeable_levitite.DyedLevitite;
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

public class DyedLevititeBlocks {
    public static final List<DyeColor> CREATIVE_TAB_COLOR_ORDER = List.of(
            DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BLACK,
            DyeColor.BROWN, DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW,
            DyeColor.LIME, DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE,
            DyeColor.BLUE, DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK
    );

    public static final ResourceLocation DYED_LEVITITE_TAB_SECTION = DyedLevitite.id("levitite");

    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(DyedLevitite.ID);
    public static final DeferredRegister.Items REGISTER_ITEMS = DeferredRegister.createItems(DyedLevitite.ID);

    public static final Map<DyeColor, DeferredBlock<Block>> DYED_LEVITITE_BLOCKS = Util.make(new EnumMap<>(DyeColor.class), map -> {
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
            LevititeSparklePartcleData particleData = new LevititeSparklePartcleData(color.getTextureDiffuseColor());
            Levitating levitating = new Levitating(0.93f, Optional.of(particleData));

            DeferredItem<Item> holder = REGISTER_ITEMS.register(color.getSerializedName() + "_levitite", () -> new BlockItem(
                    DYED_LEVITITE_BLOCKS.get(color).get(),
                    new Item.Properties().component(AeroDataComponents.LEVITATING, levitating)
            ));

            map.put(color, holder);
            SimulatedRegistrate.TAB_ITEMS.add(holder::value);
            SimulatedRegistrate.ITEM_TO_SECTION.put(holder.getId(), DYED_LEVITITE_TAB_SECTION);
        }
    });
}
