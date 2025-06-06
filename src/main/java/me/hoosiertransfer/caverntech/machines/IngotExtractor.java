package me.hoosiertransfer.caverntech.machines;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemState;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

public class IngotExtractor extends SlimefunItem implements EnergyNetComponent, MachineProcessHolder<CraftingOperation> {
    private static final int[] BORDER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
    private static final int[] BORDER_IN = { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] BORDER_OUT = { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };

    private final ItemStack[] ingots = new ItemStack[] {
            SlimefunItems.ALUMINUM_INGOT,
            SlimefunItems.GOLD_4K,
            SlimefunItems.COPPER_INGOT,
            SlimefunItems.TIN_INGOT,
            SlimefunItems.ZINC_DUST,
            SlimefunItems.MAGNESIUM_INGOT,
            SlimefunItems.LEAD_INGOT,
            SlimefunItems.SILVER_INGOT,
            new ItemStack(Material.IRON_INGOT)
    };

    private static final ItemStack NO_ENERGY = new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&c没有足够的能量!");
    private static final ItemStack PROCESSING = new CustomItemStack(Material.GREEN_STAINED_GLASS_PANE, "&a正在生产...");
    private static final ItemStack NO_ROOM = new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, "&6没有足够的空间!");
    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    private int energyConsumedPerTick = -1;
    private int energyCapacity = -1;
    private int processingSpeed = -1;
    public IngotExtractor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBlockBreak());

        addItemHandler(new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                IngotExtractor.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    protected BlockBreakHandler onBlockBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(@Nonnull Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }

                processor.endOperation(b);
            }

        };
    }

    public int[] getInputSlots() {
        return new int[] { 19, 20 };
    }

    public int[] getOutputSlots() {
        return new int[] { 24, 25 };
    }

    public final IngotExtractor setCapacity(int capacity) {
        if (getState() == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity;
            return this;
        } else {
            throw new IllegalStateException("你无法在物品注册后修改储电量");
        }
    }

    public final IngotExtractor setProcessingSpeed(int speed) {
        this.processingSpeed = speed;
        return this;
    }

    public final IngotExtractor setEnergyConsumption(int energyConsumption) {
        this.energyConsumedPerTick = energyConsumption;
        return this;
    }

    public int getCapacity() {
        return energyCapacity;
    }

    public int getEnergyConsumption() {
        return energyConsumedPerTick;
    }

    public int getSpeed() {
        return processingSpeed;
    }

    @Override
    @Nonnull
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    @Override
    @Nonnull
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public void register(@Nonnull SlimefunAddon addon) {
        this.addon = addon;

        if (getCapacity() <= 0) {
            warn("储电量未被正确配置，该物品已被禁用");
            warn("请确保 '" + getClass().getSimpleName() + "#setEnergyCapacity(...)' 在此之前注册!");
        }

        if (getEnergyConsumption() <= 0) {
            warn("能量消耗未被正确配置，该物品已被禁用");
            warn("请确保 '" + getClass().getSimpleName() + "#setEnergyConsumption(...)' 在此之前注册!");
        }

        if (getSpeed() <= 0) {
            warn("生产速度未被正确配置，该物品已被禁用");
            warn("请确保 '" + getClass().getSimpleName() + "#setProcessingSpeed(...)' 在此之前注册!");
        }

        if (getCapacity() > 0 && getEnergyConsumption() > 0 && getSpeed() > 0) {
            super.register(addon);
        }
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : BORDER) {
            preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : BORDER_IN) {
            preset.addItem(i, new CustomItemStack(Material.BLUE_STAINED_GLASS_PANE, "&9Input"), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : BORDER_OUT) {
            preset.addItem(i, new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, "&6Output"), ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "&8Idle."), ChestMenuUtils.getEmptyClickHandler());
    }

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
                if (flow == ItemTransportFlow.INSERT) {
                    return getInputSlots();
                } else {
                    return getOutputSlots();
                }
            }
        };
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        CraftingOperation currentOperation = processor.getOperation(b);

        if (currentOperation != null) {
            if (!currentOperation.isFinished()) {
                if (getCharge(b.getLocation()) < getEnergyConsumption()) {
                    inv.replaceExistingItem(22, NO_ENERGY);
                    return;
                }

                removeCharge(b.getLocation(), getEnergyConsumption());
                currentOperation.addProgress(getSpeed());
            } else {
                inv.replaceExistingItem(22, PROCESSING);

                for (ItemStack output : currentOperation.getResults()) {
                    inv.pushItem(output.clone(), getOutputSlots());
                }

                processor.endOperation(b);
            }
        } else {
            MachineRecipe next = findNextRecipe(inv);

            if (next != null) {
                currentOperation = new CraftingOperation(next);
                processor.startOperation(b, currentOperation);
            }
        }
    }

    protected MachineRecipe findNextRecipe(BlockMenu inv) {
        int input_slot = -1;

        for (int slot : getInputSlots()) {
            if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(slot), new ItemStack(Material.COBBLESTONE), true)) {
                input_slot = slot;
                break;
            }
        }
        if (input_slot != -1) {
            ItemStack output = getRandomIngot();
            if (!inv.fits(output, getOutputSlots())) {
                inv.replaceExistingItem(22, NO_ROOM);
                return null;
            }
            inv.consumeItem(input_slot);
            return new MachineRecipe(1, new ItemStack[]{new ItemStack(Material.COBBLESTONE)}, new ItemStack[] { getRandomIngot() });
        }
        return null;
    }

    public @Nonnull ItemStack getRandomIngot() {
        int index = ThreadLocalRandom.current().nextInt(ingots.length);
        return ingots[index].clone();
    }
}
