package com.fusionflux.dyeable_levitite;

import com.fusionflux.dyeable_levitite.content.DyedLevititeBlocks;
import dev.eriksonn.aeronautics.events.AeronauticsClientEvents;
import dev.eriksonn.aeronautics.index.client.AeroRenderTypes;
import foundry.veil.forge.event.ForgeVeilRegisterBlockLayersEvent;
import foundry.veil.forge.event.ForgeVeilRegisterFixedBuffersEvent;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = DyedLevitite.ID, value = Dist.CLIENT)
public class DyedLevititeClientEvents {
    @SubscribeEvent
    public static void preClientTick(ClientTickEvent.Pre event) {
        AeronauticsClientEvents.clientLevelTick(false);
    }

    @SubscribeEvent
    public static void postClientTick(ClientTickEvent.Post event) {
        AeronauticsClientEvents.clientLevelTick(true);
    }

    @EventBusSubscriber(modid = DyedLevitite.ID, value = Dist.CLIENT)
    public static class ModBusEvents {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            ChunkRenderTypeSet set = ChunkRenderTypeSet.of(RenderType.SOLID, AeroRenderTypes.levitite(), AeroRenderTypes.levititeGhosts());
            for (DyeColor color : DyeColor.values()) {
                setRenderTypes(DyedLevititeBlocks.DYED_LEVITITE_BLOCKS.get(color).get(), set);
            }
        }

        @SubscribeEvent
        public static void registerBlockLayersEvent(ForgeVeilRegisterBlockLayersEvent event) {
            event.registerBlockLayer(AeroRenderTypes.levitite());
            event.registerBlockLayer(AeroRenderTypes.levititeGhosts());
        }

        @SubscribeEvent
        public static void registerFixedBuffersEvent(ForgeVeilRegisterFixedBuffersEvent event) {
            event.register(RenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES, AeroRenderTypes.levitite());
            event.register(RenderLevelStageEvent.Stage.AFTER_WEATHER, AeroRenderTypes.levititeGhosts());
        }

        @SuppressWarnings("deprecation") // not really deprecated
        private static void setRenderTypes(Block block, ChunkRenderTypeSet set) {
            ItemBlockRenderTypes.setRenderLayer(block, set);
        }
    }
}
