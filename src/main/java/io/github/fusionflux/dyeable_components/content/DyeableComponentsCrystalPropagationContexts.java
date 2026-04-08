package io.github.fusionflux.dyeable_components.content;

import io.github.fusionflux.dyeable_components.DyeableComponents;
import dev.eriksonn.aeronautics.api.levitite_blend_crystallization.CrystalPropagationContext;
import dev.eriksonn.aeronautics.index.AeroRegistries;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;

public class DyeableComponentsCrystalPropagationContexts {
    public static final DeferredRegister<CrystalPropagationContext> REGISTER = DeferredRegister.create(AeroRegistries.Keys.LEVITITE_CRYSTAL_PROPAGATION_CONTEXT, DyeableComponents.ID);

    public static final Map<DyeColor, DeferredHolder<CrystalPropagationContext, DyedLevititePropagationContext>> DYED_CONTEXT = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : DyeColor.values()) {
            DeferredHolder<CrystalPropagationContext, DyedLevititePropagationContext> context = REGISTER.register(
                    color.getSerializedName() + "_crystal_context",
                    () -> new DyedLevititePropagationContext(DyeableComponentsBlocks.DYED_LEVITITE_BLOCKS.get(color))
            );
            map.put(color, context);
        }
    });
}
