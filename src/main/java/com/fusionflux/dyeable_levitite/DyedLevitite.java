package com.fusionflux.dyeable_levitite;

import com.fusionflux.dyeable_levitite.montent.DyedLevititeBlendContextRegister;
import com.fusionflux.dyeable_levitite.montent.DyedLevititeBlockRegister;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.eriksonn.aeronautics.registry.AeroRegistrate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DyedLevitite.ID)
public class DyedLevitite {



    public static final String ID = "dyeable_levitite";

    private static final NonNullSupplier<AeroRegistrate> REGISTRATE = NonNullSupplier.lazy(() -> (AeroRegistrate)(new AeroRegistrate(id(ID), ID)).defaultCreativeTab((ResourceKey)null));


    public DyedLevitite(IEventBus bus) {
        DyedLevititeBlendContextRegister.REGISTER.register(bus);
        DyedLevititeBlockRegister.REGISTER.register(bus);
        DyedLevititeBlockRegister.REGISTER_ITEMS.register(bus);
        DyedLevititeBlockRegister. CREATIVE_MODE_TABS.register(bus);
    }


    public static ResourceLocation id(String path){
        return ResourceLocation.fromNamespaceAndPath(ID,path);
    }
}
