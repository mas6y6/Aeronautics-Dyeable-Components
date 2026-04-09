package io.github.fusionflux.dyeable_components.data;

import dev.ryanhcode.offroad.Offroad;
import io.github.fusionflux.dyeable_components.DyeableComponents;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DyeableModelProvider extends ItemModelProvider {

    public DyeableModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DyeableComponents.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        createDyedTireVariants("small_tire");
        createDyedTireVariants("tire");
        createDyedTireVariants("large_tire");
        createDyedTireVariants("monstrous_tire");
    }

    private void createDyedTireVariants(String name) {
        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getSerializedName();

            this.withExistingParent("item/" + colorName + "_" + name, Offroad.path("item/" + name + "/item"))
                    .texture("tire_0", DyeableComponents.id("block/tire/tire_0_" + colorName))
                    .texture("tire_1", DyeableComponents.id("block/tire/tire_1_" + colorName));
        }
    }
}
