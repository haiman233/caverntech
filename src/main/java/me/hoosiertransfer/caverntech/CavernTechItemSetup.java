package me.hoosiertransfer.caverntech;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.RadioactiveItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.hoosiertransfer.caverntech.items.CavernTechItems;
import me.hoosiertransfer.caverntech.machines.CobbleGen;
import me.hoosiertransfer.caverntech.machines.IngotExtractor;
import me.hoosiertransfer.caverntech.machines.PressurizedCarbonPress;
import me.hoosiertransfer.caverntech.machines.SaltProducer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class CavernTechItemSetup {
    private static final NestedItemGroup caverntech = new NestedItemGroup(
            new NamespacedKey(CavernTech.getInstance(), "caverntech"),
            new CustomItemStack(Material.END_CRYSTAL, "&6Cavern&9Tech")
    );

    private static final ItemGroup materials = new SubItemGroup(
            new NamespacedKey(CavernTech.getInstance(), "materials"), caverntech,
            new CustomItemStack(Material.NETHERITE_INGOT, "&4Materials"), 1
    );

    private static final ItemGroup machines = new SubItemGroup(
            new NamespacedKey(CavernTech.getInstance(), "machines"), caverntech,
            new CustomItemStack(Material.FURNACE, "&cMachines"), 2
    );

    private static final ItemGroup multiblocks = new SubItemGroup(
            new NamespacedKey(CavernTech.getInstance(), "multiblocks"), caverntech,
            new CustomItemStack(Material.DISPENSER, "&6Multiblocks"), 3
    );
    private static final ItemGroup generators = new SubItemGroup(
            new NamespacedKey(CavernTech.getInstance(), "generators"), caverntech,
            new CustomItemStack(Material.DAYLIGHT_DETECTOR, "&eGenerators"), 4
    );
    private static final ItemGroup chambers = new SubItemGroup(
            new NamespacedKey(CavernTech.getInstance(), "chambers"), caverntech,
            new CustomItemStack(Material.SPAWNER, "&2Chambers"), 5
    );
    private static final ItemGroup magic = new SubItemGroup(
            new NamespacedKey(CavernTech.getInstance(), "magic"), caverntech,
            new CustomItemStack(Material.STICK, "&aMagic"), 6
    );
    private static final ItemGroup gadgets = new SubItemGroup(
            new NamespacedKey(CavernTech.getInstance(), "gadgets"), caverntech,
            new CustomItemStack(Material.FEATHER, "&bGadgets"), 7
    );

    public static void setup(CavernTech plugin) {
        new UnplaceableBlock(materials, CavernTechItems.BORON_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.STEEL_INGOT, SlimefunItems.CARBON, SlimefunItems.IRON_DUST,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.FERROBORON_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.FERROSILICON, CavernTechItems.BORON_INGOT, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.HARD_CARBON_ALLOY, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.CARBON_CHUNK, new ItemStack(Material.DIAMOND), new ItemStack(Material.IRON_INGOT),
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.ELECTROLYTE_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                new ItemStack(Material.GOLD_INGOT), SlimefunItems.SILVER_INGOT, SlimefunItems.MAGNESIUM_DUST,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.TOUGH_ALLOY, RecipeType.SMELTERY, new ItemStack[]{
                CavernTechItems.FERROBORON_INGOT, CavernTechItems.ELECTROLYTE_INGOT, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.EXTREME_ALLOY, RecipeType.SMELTERY, new ItemStack[]{
                CavernTechItems.TOUGH_ALLOY, CavernTechItems.HARD_CARBON_ALLOY, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.THERMOCONDUCTING_ALLOY, RecipeType.SMELTERY, new ItemStack[]{
                CavernTechItems.EXTREME_ALLOY, CavernTechItems.BORON_INGOT, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.BISMUTH, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.SULFATE, CavernTechItems.HARD_CARBON_ALLOY, null,
                null, null, null,
                null, null, null
        }, new SlimefunItemStack(CavernTechItems.BISMUTH, 2)).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.PLATINUM, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT, CavernTechItems.EXTREME_ALLOY, SlimefunItems.REINFORCED_ALLOY_INGOT,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.SOULARIUM, RecipeType.SMELTERY, new ItemStack[]{
                CavernTechItems.BISMUTHPLATES, SlimefunItems.REDSTONE_ALLOY, CavernTechItems.REFINED_SOUL,
                new ItemStack(Material.SOUL_SAND, 4), null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.BERYLLIUM_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.ZINC_DUST, new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.VINE, 6), null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.FLEXIUM_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                new SlimefunItemStack(CavernTechItems.BISMUTH, 64), new SlimefunItemStack(CavernTechItems.FERROBORON_INGOT, 64), new SlimefunItemStack(CavernTechItems.HARD_CARBON_ALLOY, 64),
                new SlimefunItemStack(CavernTechItems.ELECTROLYTE_INGOT, 64), new SlimefunItemStack(CavernTechItems.TOUGH_ALLOY, 64), new SlimefunItemStack(CavernTechItems.EXTREME_ALLOY, 64),
                new SlimefunItemStack(CavernTechItems.THERMOCONDUCTING_ALLOY, 64), new SlimefunItemStack(CavernTechItems.PLATINUM, 64), new SlimefunItemStack(CavernTechItems.SOULARIUM, 64)
        }).register(plugin);
        new RadioactiveItem(materials, Radioactivity.HIGH, CavernTechItems.RADIUM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.NEPTUNIUM, CavernTechItems.ELECTROLYTE_INGOT, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.EMPTY_RESERVOIR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_PLATE, new ItemStack(Material.SMOOTH_STONE_SLAB), SlimefunItems.STEEL_PLATE,
                new ItemStack(Material.GLASS), null, new ItemStack(Material.GLASS),
                SlimefunItems.STEEL_PLATE, new ItemStack(Material.SMOOTH_STONE_SLAB), SlimefunItems.STEEL_PLATE
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.WATER_RESERVOIR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.WATER_BUCKET), CavernTechItems.EMPTY_RESERVOIR, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.LAVA_RESERVOIR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.LAVA_BUCKET), CavernTechItems.EMPTY_RESERVOIR, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new SlimefunItem(materials, CavernTechItems.BISMUTHPLATES, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                CavernTechItems.BISMUTH, CavernTechItems.BISMUTH, CavernTechItems.BISMUTH,
                CavernTechItems.BISMUTH, SlimefunItems.HARDENED_METAL_INGOT, CavernTechItems.BISMUTH,
                CavernTechItems.BISMUTH, CavernTechItems.BISMUTH, CavernTechItems.BISMUTH
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.MACHINE_MOTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, SlimefunItems.ELECTRO_MAGNET, null,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD
        }).register(plugin);
        new SlimefunItem(materials, CavernTechItems.MACHINEPLATING, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_ALLOY_INGOT,
                CavernTechItems.BISMUTHPLATES, CavernTechItems.PLATINUM, CavernTechItems.BISMUTHPLATES,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_ALLOY_INGOT
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.MACHINE_FRAME, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.IRON_BLOCK), CavernTechItems.PLATINUM, new ItemStack(Material.IRON_BLOCK),
                CavernTechItems.PLATINUM, CavernTechItems.MACHINEPLATING, CavernTechItems.PLATINUM,
                new ItemStack(Material.IRON_BLOCK), CavernTechItems.PLATINUM, new ItemStack(Material.IRON_BLOCK)
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.SOUL_FRAGMENT, new RecipeType(new NamespacedKey(CavernTech.getInstance(), "soul_harvester"), CavernTechItems.SOUL_HARVESTER, "", "&7Generates 1 Soul Fragment", "&5&oevery 666 seconds."), new ItemStack[]{
                null, null, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, CavernTechItems.REFINED_SOUL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                CavernTechItems.SOUL_FRAGMENT, CavernTechItems.SOUL_FRAGMENT, null,
                CavernTechItems.SOUL_FRAGMENT, CavernTechItems.SOUL_FRAGMENT, null,
                null, null, null
        }).register(plugin);
        // Machines
        new PressurizedCarbonPress(machines, CavernTechItems.PRESSURIZED_CARBON_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ELECTRO_MAGNET, SlimefunItems.CARBONADO_EDGED_FURNACE, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBON_PRESS, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.CARBONADO, CavernTechItems.MACHINE_FRAME, SlimefunItems.CARBONADO
        }).setCapacity(600)
                .setEnergyConsumption(200)
                .setProcessingSpeed(1)
                .register(plugin);
        new SaltProducer(machines, CavernTechItems.SALT_PRODUCER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HEATING_COIL, new ItemStack(Material.CAULDRON), SlimefunItems.HEATING_COIL,
                new ItemStack(Material.IRON_BLOCK), SlimefunItems.SALT, new ItemStack(Material.IRON_BLOCK),
                new ItemStack(Material.IRON_BLOCK), CavernTechItems.MACHINE_MOTOR, new ItemStack(Material.IRON_BLOCK)
        }).setCapacity(96)
                .setEnergyConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);
        new CobbleGen(machines, CavernTechItems.COBBLE_GEN, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, new ItemStack(Material.DIAMOND_PICKAXE), null,
                new ItemStack(Material.LAVA_BUCKET), SlimefunItems.PROGRAMMABLE_ANDROID_MINER, new ItemStack(Material.WATER_BUCKET),
                SlimefunItems.ELECTRIC_MOTOR, CavernTechItems.MACHINEPLATING, SlimefunItems.ELECTRIC_MOTOR
        }).setCapacity(360)
                .setEnergyConsumption(48)
                .setProcessingSpeed(1)
                .register(plugin);
        new IngotExtractor(machines, CavernTechItems.INGOTEXTRACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, new ItemStack(Material.DIAMOND_PICKAXE), null,
                new ItemStack(Material.LAVA_BUCKET), SlimefunItems.PROGRAMMABLE_ANDROID_MINER, new ItemStack(Material.WATER_BUCKET),
                SlimefunItems.ELECTRIC_MOTOR, CavernTechItems.MACHINEPLATING, SlimefunItems.ELECTRIC_MOTOR
        }).setCapacity(360)
                .setEnergyConsumption(48)
                .setProcessingSpeed(1)
                .register(plugin);
    }
}
