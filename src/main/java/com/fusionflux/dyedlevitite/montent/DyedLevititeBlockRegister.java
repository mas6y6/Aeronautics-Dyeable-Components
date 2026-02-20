package com.fusionflux.dyedlevitite.montent;

import com.fusionflux.dyedlevitite.DyedLevitite;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.eriksonn.aeronautics.content.components.Levitating;
import dev.eriksonn.aeronautics.content.particle.LevititeSparklePartcleData;
import dev.eriksonn.aeronautics.index.AeroBlocks;
import dev.eriksonn.aeronautics.index.AeroDataComponents;
import dev.eriksonn.aeronautics.index.AeroItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class DyedLevititeBlockRegister {

    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(DyedLevitite.ID);
    public static final DeferredRegister.Items REGISTER_ITEMS= DeferredRegister.createItems(DyedLevitite.ID);

    public static final Map<DyeColor, DeferredBlock<Block>> DYED_LEVITITE = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : DyeColor.values()) {
            DeferredBlock<Block> holder = REGISTER.register(color.getSerializedName() + "_levitite",()->{
                BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(AeroBlocks.LEVITITE.get());
                return new Block(properties);
            });
            map.put(color, holder);
        }
    });

    public static final Map<DyeColor, DeferredItem<Item>> DYED_LEVITITE_ITEMS = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : DyeColor.values()) {
            DeferredItem<Item> holder = REGISTER_ITEMS.register(color.getSerializedName() + "_levitite",() -> new BlockItem(DyedLevititeBlockRegister.DYED_LEVITITE.get(color).get(),new Item.Properties().component(AeroDataComponents.LEVITATING, new Levitating(0.93F, Optional.of(new LevititeSparklePartcleData(color.getTextureDiffuseColor()))))));
            map.put(color, holder);
        }
    });

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DyedLevitite.ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.dyeablelevitite")) //The language key for the title of your CreativeModeTab
            // .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> DyedLevititeBlockRegister.DYED_LEVITITE.get(DyeColor.PURPLE).get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (DyeColor color : DyeColor.values()) {
                    output.accept(DyedLevititeBlockRegister.DYED_LEVITITE.get(color).get());
                }
            }).build());

    public static final Levitating DYEDLEVITITE = new Levitating(0.93F, Optional.of(new LevititeSparklePartcleData(15521489)));



}
