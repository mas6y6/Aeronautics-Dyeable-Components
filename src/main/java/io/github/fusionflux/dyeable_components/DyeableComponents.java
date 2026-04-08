package io.github.fusionflux.dyeable_components;

import io.github.fusionflux.dyeable_components.content.DyeableComponentsCrystalPropagationContexts;
import io.github.fusionflux.dyeable_components.content.DyeableComponentsBlocks;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DyeableComponents.ID)
public class DyeableComponents {
    public static final String ID = "aeronautics_dyeable_components";

    public DyeableComponents(IEventBus bus) {
        DyeableComponentsCrystalPropagationContexts.REGISTER.register(bus);
        DyeableComponentsBlocks.REGISTER.register(bus);
        DyeableComponentsBlocks.REGISTER_ITEMS.register(bus);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}
