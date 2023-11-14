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

public class SaltProducer extends AContainer implements RecipeDisplayItem {

    public SaltProducer(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(3, new ItemStack(Material.SAND, 2), new SlimefunItemStack(SlimefunItems.SALT, 1));
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
