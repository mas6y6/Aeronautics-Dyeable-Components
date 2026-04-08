package io.github.fusionflux.dyeable_components;

import io.github.fusionflux.dyeable_components.content.DyeableComponentsBlocks;
import io.github.fusionflux.dyeable_components.content.DyeableComponentsSpriteShifts;
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

@Mod(value = DyeableComponents.ID, dist = Dist.CLIENT)
public class DyeableComponentsClient {
    public DyeableComponentsClient(IEventBus bus) {
        bus.addListener(DyeableComponentsClient::onInit);
    }

    public static void onInit(FMLCommonSetupEvent event) {
        for (DyeColor color : DyeColor.values()) {
            DeferredBlock<Block> block = DyeableComponentsBlocks.DYED_LEVITITE_BLOCKS.get(color);
            CTSpriteShiftEntry entry = DyeableComponentsSpriteShifts.DYED_SPRITE_SHIFTS.get(color);
            registerCT(block.get(), entry);
        }
    }

    private static void registerCT(Block block, CTSpriteShiftEntry entry) {
        CreateRegistrate.connectedTextures(() -> new SimpleCTBehaviour(entry)).accept(block);
    }
}
