package com.fusionflux.dyedlevitite.montent;

import com.fusionflux.dyedlevitite.DyedLevitite;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;

import java.util.EnumMap;
import java.util.Map;

public class DyedLevititeSpriteShift {

    public static final Map<DyeColor, CTSpriteShiftEntry> DYED_SPRITE_SHIFTS = Util.make(new EnumMap<>(DyeColor.class), map -> {
        for (DyeColor color : DyeColor.values()) {
            CTSpriteShiftEntry omni = omni(color.getSerializedName() + "_levitite");
            map.put(color,omni);
        }
    });

    static CTSpriteShiftEntry omni(String name) {
        return CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL, DyedLevitite.id(name),  DyedLevitite.id(name + "_connected"));
    }


}
