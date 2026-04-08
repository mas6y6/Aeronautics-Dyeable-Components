package io.github.fusionflux.dyeable_levitite;

import io.github.fusionflux.dyeable_levitite.content.CrystalPropagationContexts;
import io.github.fusionflux.dyeable_levitite.content.DyedLevititeBlocks;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DyedLevitite.ID)
public class DyedLevitite {
    public static final String ID = "dyeable_levitite";

    public DyedLevitite(IEventBus bus) {
        CrystalPropagationContexts.REGISTER.register(bus);
        DyedLevititeBlocks.REGISTER.register(bus);
        DyedLevititeBlocks.REGISTER_ITEMS.register(bus);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}
