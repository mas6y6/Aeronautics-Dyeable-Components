package com.example.examplemod;

import com.example.examplemod.montent.DyedLevititeBlendContextRegister;
import com.example.examplemod.montent.DyedLevititeBlockRegister;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DyedLevitite.ID)
public class DyedLevitite {
    public static final String ID = "examplemod";

    public DyedLevitite(IEventBus bus) {
        DyedLevititeBlendContextRegister.REGISTER.register(bus);
        DyedLevititeBlockRegister.REGISTER.register(bus);
    }

    public static ResourceLocation id(String path){
        return ResourceLocation.fromNamespaceAndPath(ID,path);
    }
}
