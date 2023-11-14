package me.hoosiertransfer.caverntech.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class PressurizedCarbonPress extends AContainer implements RecipeDisplayItem {
    public PressurizedCarbonPress(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(3, new ItemStack(Material.STRIPPED_MANGROVE_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_JUNGLE_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_CRIMSON_STEM, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_DARK_OAK_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_OAK_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.MANGROVE_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.ACACIA_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.DARK_OAK_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_MANGROVE_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.JUNGLE_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_CHERRY_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.CHERRY_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_CRIMSON_HYPHAE, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.BIRCH_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_BIRCH_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.OAK_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_JUNGLE_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.SPRUCE_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.WARPED_STEM, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_SPRUCE_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.OAK_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.CRIMSON_HYPHAE, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.JUNGLE_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.MANGROVE_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_WARPED_HYPHAE, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.BIRCH_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_WARPED_STEM, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_DARK_OAK_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.WARPED_HYPHAE, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_CHERRY_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_OAK_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.ACACIA_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_SPRUCE_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_ACACIA_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.CHERRY_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.SPRUCE_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.DARK_OAK_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_BIRCH_LOG, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.STRIPPED_ACACIA_WOOD, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
        registerRecipe(3, new ItemStack(Material.CRIMSON_STEM, 32), new SlimefunItemStack(SlimefunItems.CARBON, 2));
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.LAVA_BUCKET);
    }

    @Override
    public String getMachineIdentifier() {
        return "PRESSURIZED_CARBON_PRESS";
    }
}
