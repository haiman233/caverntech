package me.hoosiertransfer.caverntech.machines;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemState;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.operations.MiningOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class CobbleGen extends SlimefunItem implements RecipeDisplayItem, EnergyNetComponent, MachineProcessHolder<MiningOperation> {
    private static final int[] BORDER = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17};
    private static final int[] OUTPUT_SLOTS = {13};

    private int energyConsumedPerTick = -1;
    private int energyCapacity = -1;
    private int processingSpeed = -1;

    private final MachineProcessor<MiningOperation> processor = new MachineProcessor<>(this);

    private static final ItemStack NO_ENERGY = new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&cNot enough energy!");
    private static final ItemStack GENERATING = new CustomItemStack(Material.GREEN_STAINED_GLASS_PANE, "&aGenerating...");
    private static final ItemStack NO_ROOM = new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, "&6Not enough room!");

    public CobbleGen(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        addItemHandler(new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                CobbleGen.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
        addItemHandler(onBlockBreak());
    }

    @Override
    @Nonnull
    public MachineProcessor<MiningOperation> getMachineProcessor() {
        return processor;
    }
    @Override
    @Nonnull
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }
    public int getEnergyConsumption() {
        return energyConsumedPerTick;
    }

    public int getSpeed() {
        return processingSpeed;
    }

    public int getCapacity() {
        return energyCapacity;
    }

    public void register(@Nonnull SlimefunAddon addon) {
        this.addon = addon;

        if (getCapacity() <= 0) {
            warn("The capacity has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setEnergyCapacity(...)' before registering!");
        }

        if (getEnergyConsumption() <= 0) {
            warn("The energy consumption has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setEnergyConsumption(...)' before registering!");
        }

        if (getSpeed() <= 0) {
            warn("The processing speed has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setProcessingSpeed(...)' before registering!");
        }

        if (getCapacity() > 0 && getEnergyConsumption() > 0 && getSpeed() > 0) {
            super.register(addon);
        }
    }

    public final CobbleGen setCapacity(int capacity) {
        if (getState() == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the capacity after the Item was registered.");
        }
    }

    public final CobbleGen setProcessingSpeed(int speed) {
        this.processingSpeed = speed;
        return this;
    }

    public final CobbleGen setEnergyConsumption(int energyConsumption) {
//        Validate.isTrue(energyConsumption > 0, "The energy consumption must be greater than zero!");
//        Validate.isTrue(energyCapacity > 0, "You must specify the capacity before you can set the consumption amount.");
//        Validate.isTrue(energyConsumption <= energyCapacity, "The energy consumption cannot be higher than the capacity (" + energyCapacity + ')');

        this.energyConsumedPerTick = energyConsumption;
        return this;
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : BORDER) {
            preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(4, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
    }

    private BlockBreakHandler onBlockBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(@Nonnull Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), OUTPUT_SLOTS);
                }

                processor.endOperation(b);
            }
        };
    }

    @Override
    @Nonnull
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new LinkedList<>();
        displayRecipes.add(new CustomItemStack(Material.AIR));
        displayRecipes.add(new CustomItemStack(Material.COBBLESTONE, ChatColor.RESET + "Cobblestone"));
        return displayRecipes;
    }

    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }


            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return OUTPUT_SLOTS;
            }
        };
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        MiningOperation operation = processor.getOperation(b);

        if (operation != null) {
            if (!operation.isFinished()) {
                if (getCharge(b.getLocation()) < getEnergyConsumption()) {
                    inv.replaceExistingItem(4, NO_ENERGY);
                    return;
                }

                removeCharge(b.getLocation(), getEnergyConsumption());
                operation.addProgress(getSpeed());
            } else {
                inv.replaceExistingItem(4, GENERATING);
                inv.pushItem(operation.getResult(), OUTPUT_SLOTS);

                processor.endOperation(b);
            }
        } else {
            start(b, inv);
        }
    }

    private void start(Block b, BlockMenu inv) {
        if (!inv.fits(new ItemStack(Material.COBBLESTONE), OUTPUT_SLOTS)) {
            inv.replaceExistingItem(4, NO_ROOM);
            return;
        }
        processor.startOperation(b, new MiningOperation(new ItemStack(Material.COBBLESTONE), 1));
    }
}
