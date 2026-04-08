package io.github.fusionflux.dyeable_components;

import io.github.fusionflux.dyeable_components.content.DyeableComponentsCrystalPropagationContexts;
import io.github.fusionflux.dyeable_components.content.DyeableComponentsBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Mod(DyeableComponents.ID)
public class DyeableComponents {
    public static final String ID = "aeronautics_dyeable_components";

    public static final List<DyeColor> CREATIVE_TAB_COLOR_ORDER = List.of(
            DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BLACK,
            DyeColor.BROWN, DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW,
            DyeColor.LIME, DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE,
            DyeColor.BLUE, DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK
    );

    public DyeableComponents(IEventBus bus) {
        DyeableComponentsCrystalPropagationContexts.REGISTER.register(bus);
        DyeableComponentsBlocks.BLOCKS.register(bus);
        DyeableComponentsBlocks.ITEMS.register(bus);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    public static <T> Map<DyeColor, T> colorMap(Function<DyeColor, T> factory) {
        Map<DyeColor, T> map = new EnumMap<>(DyeColor.class);
        CREATIVE_TAB_COLOR_ORDER.forEach(color -> map.put(color, factory.apply(color)));
        return map;
    }
}
