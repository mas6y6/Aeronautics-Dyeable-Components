package com.fusionflux.dyedlevitite.montent;

import com.fusionflux.dyedlevitite.DyedLevitite;
import dev.eriksonn.aeronautics.api.levitite_blend_crystallization.CrystalPropagationContext;
import dev.eriksonn.aeronautics.index.AeroRegistries;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;

public class DyedLevititeBlendContextRegister {

    public static final DeferredRegister<CrystalPropagationContext> REGISTER = DeferredRegister.create(AeroRegistries.Keys.LEVITITE_CRYSTAL_PROPAGATION_CONTEXT, DyedLevitite.ID);

    public static final Map<DyeColor, DeferredHolder<CrystalPropagationContext,DyedLevititeCrystalPropogationContext>> DYED_CONTEXT = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : DyeColor.values()) {
            DeferredHolder<CrystalPropagationContext, DyedLevititeCrystalPropogationContext> context = REGISTER.register(
                    color.getSerializedName() + "_crystal_context",
                    () -> new DyedLevititeCrystalPropogationContext(DyedLevititeBlockRegister.DYED_LEVITITE.get(color))
            );
            map.put(color, context);
        }
    });

}
