package io.github.fusionflux.dyeable_levitite;

import io.github.fusionflux.dyeable_levitite.content.DyedLevititeBlocks;
import io.github.fusionflux.dyeable_levitite.content.DyedLevititeSpriteShifts;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(value = DyedLevitite.ID, dist = Dist.CLIENT)
public class DyedLevititeClient {
    public DyedLevititeClient(IEventBus bus) {
        bus.addListener(DyedLevititeClient::onInit);
    }

    public static void onInit(FMLCommonSetupEvent event) {
        for (DyeColor color : DyeColor.values()) {
            DeferredBlock<Block> block = DyedLevititeBlocks.DYED_LEVITITE_BLOCKS.get(color);
            CTSpriteShiftEntry entry = DyedLevititeSpriteShifts.DYED_SPRITE_SHIFTS.get(color);
            registerCT(block.get(), entry);
        }
    }

    private static void registerCT(Block block, CTSpriteShiftEntry entry) {
        CreateRegistrate.connectedTextures(() -> new SimpleCTBehaviour(entry)).accept(block);
    }
}
