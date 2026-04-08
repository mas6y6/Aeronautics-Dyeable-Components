package io.github.fusionflux.dyeable_components.content;

import io.github.fusionflux.dyeable_components.DyeableComponents;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;

import java.util.EnumMap;
import java.util.Map;

public class DyeableComponentsSpriteShifts {
    public static final Map<DyeColor, CTSpriteShiftEntry> DYED_SPRITE_SHIFTS = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : DyeColor.values()) {
            CTSpriteShiftEntry omni = omni("block/" + color.getSerializedName() + "_levitite");
            map.put(color, omni);
        }
    });

    static CTSpriteShiftEntry omni(String name) {
        return CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL, DyeableComponents.id(name),  DyeableComponents.id(name + "_connected"));
    }
}
