package com.fusionflux.dyedlevitite.mixin;

import com.fusionflux.dyedlevitite.montent.DyedLevititeBlendContextRegister;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import dev.eriksonn.aeronautics.api.levitite_blend_crystallization.CrystalPropagationContext;
import dev.eriksonn.aeronautics.network.packets.LevititeCatalystCrystallizationPacket;
import foundry.veil.api.network.handler.ServerPacketContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevititeCatalystCrystallizationPacket.class)
public class LevititeCatalystCrystallizationPacketMixin {

    @Shadow
    @Final
    private InteractionHand hand;

    @ModifyReceiver(
            method = "handle",
            at = @At(
                    value = "INVOKE",
                    target = "Ldev/eriksonn/aeronautics/api/levitite_blend_crystallization/CrystalPropagationContext;getContextForSpread(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Ldev/eriksonn/aeronautics/api/levitite_blend_crystallization/CrystalPropagationContext;"
            )
    )
    private CrystalPropagationContext dyeContext(CrystalPropagationContext instance, Level level, BlockPos blockPos, ServerPacketContext packetContext){
        InteractionHand offhand = this.hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack itemInOffHand = packetContext.player().getItemInHand(offhand);
        if(itemInOffHand.getItem() instanceof DyeItem dyeItem){
            DyeColor dyeColor = dyeItem.getDyeColor();
            return DyedLevititeBlendContextRegister.DYED_CONTEXT.get(dyeColor).value();
        }
        return instance;
    }

}
