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

public class Corrupter extends AContainer implements RecipeDisplayItem {

    public Corrupter(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(5, new ItemStack(Material.COBBLESTONE, 16), new ItemStack(Material.NETHERRACK, 8));
        registerRecipe(3, new ItemStack(Material.SAND, 16), new ItemStack(Material.SOUL_SAND));
        registerRecipe(3, SlimefunItems.STRANGE_NETHER_GOO, new ItemStack(Material.QUARTZ));
        registerRecipe(5, new ItemStack(Material.COBBLESTONE, 16), new ItemStack(Material.NETHERRACK, 8));
        registerRecipe(2, new ItemStack(Material.COPPER_BLOCK), new ItemStack(Material.EXPOSED_COPPER));
        registerRecipe(5, new ItemStack(Material.EXPOSED_COPPER), new ItemStack(Material.WEATHERED_COPPER));
        registerRecipe(2, new ItemStack(Material.EXPOSED_COPPER), new ItemStack(Material.WEATHERED_COPPER));
        registerRecipe(2, new ItemStack(Material.WEATHERED_COPPER), new ItemStack(Material.OXIDIZED_COPPER));
        registerRecipe(2, new ItemStack(Material.GRASS_BLOCK), new ItemStack(Material.PODZOL));
        registerRecipe(2, new ItemStack(Material.PODZOL), new ItemStack(Material.MYCELIUM));
        registerRecipe(2, new ItemStack(Material.STONE), new ItemStack(Material.BLACKSTONE));
        registerRecipe(2, new ItemStack(Material.COD, 64), new ItemStack(Material.TROPICAL_FISH_BUCKET)); //TODO: Find out the time for this
        registerRecipe(2, new ItemStack(Material.JUNGLE_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.CHERRY_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.DARK_OAK_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.MANGROVE_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.ACACIA_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.FLOWERING_AZALEA_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.OAK_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.SPRUCE_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.BIRCH_LEAVES, 16), new ItemStack(Material.DIRT));
        registerRecipe(2, new ItemStack(Material.AZALEA_LEAVES, 16), new ItemStack(Material.DIRT));




    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    }

    @Override
    @Nonnull
    public String getMachineIdentifier() {
        return "SALT_PRODUCER";
    }
}
