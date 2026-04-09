package io.github.fusionflux.dyeable_components.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.fusionflux.dyeable_components.DyeableComponents;
import io.github.fusionflux.dyeable_components.content.DyeableComponentsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DyeableLanguageProvider extends LanguageProvider {

    public DyeableLanguageProvider(PackOutput output) {
        super(output, DyeableComponents.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        loadSourceJson();

        createDyedTireTranslations(DyeableComponentsBlocks.DYED_SMALL_TIRE_ITEMS, "Small Tire");
        createDyedTireTranslations(DyeableComponentsBlocks.DYED_TIRE_ITEMS, "Tire");
        createDyedTireTranslations(DyeableComponentsBlocks.DYED_LARGE_TIRE_ITEMS, "Large Tire");
        createDyedTireTranslations(DyeableComponentsBlocks.DYED_MONSTROUS_TIRE_ITEMS, "Monstrous Tire");
    }

    private void createDyedTireTranslations(Map<DyeColor, DeferredItem<Item>> items, String name) {
        items.forEach((dye, item) -> {
            add(item.get(), prettyName(dye.getSerializedName()) + " " + name);
        });
    }

    /**Convert snake_case to Title Case*/
    private String prettyName(String serializedName) {
        String[] parts = serializedName.split("_");
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            builder.append(part.substring(0, 1).toUpperCase()).append(part.substring(1)).append(" ");
        }
        return builder.toString().trim();
    }

    private void loadSourceJson() {
        Path sourcePath = Paths.get("../src/main/resources/assets/" + DyeableComponents.ID + "/lang/source/en_us.json");

        if (sourcePath.toFile().exists()) {
            try (FileReader reader = new FileReader(sourcePath.toFile())) {
                JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);

                for (String key : jsonObject.keySet()) {
                    add(key, jsonObject.get(key).getAsString());
                }
                DyeableComponents.LOGGER.info("Loaded {} translations from source language file.", jsonObject.size());
            } catch (Exception e) {
                throw new RuntimeException("Failed to read source language file!", e);
            }
        } else {
            System.out.println("Warning: No source lang file found at " + sourcePath.toAbsolutePath());
        }
    }
}
