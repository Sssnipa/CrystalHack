package org.bleachhack.module.mods;

import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.item.Item;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import org.bleachhack.event.events.EventTick;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.module.setting.base.SettingToggle;
import org.bleachhack.module.setting.other.SettingItemList;

import java.util.List;

/**
 * Ported from meteor rejects
 */

public class AutoCraft extends Module {

    public AutoCraft(){
        super("AutoCraft", KEY_UNBOUND, ModuleCategory.MISC, "Automatically craft things.",
                new SettingItemList("Edit Items", "Items you want to craft.").withDesc("Edit crafting items."),
                new SettingToggle("Anti desync", false).withDesc("Try to prevent inventory desync."),
                new SettingToggle("Craft all", false).withDesc("Crafts maximum possible amount amount per craft (shift-clicking)"),
                new SettingToggle("Drop", false).withDesc("Automatically drops crafted items (useful for when not enough inventory space)"));
    }

    boolean antiDesync = getSetting(1).asToggle().state;
    boolean craftAll = getSetting(2).asToggle().state;
    boolean drop = getSetting(3).asToggle().state;
    //Set<Item> items = getSetting(0).asList(Item.class).getItems();

    @BleachSubscribe
    public void onTick(EventTick event) {
        if (mc.interactionManager == null) return;
        if (getSetting(0).asList(Item.class).contains(null)) return;

        if (!(mc.player.currentScreenHandler instanceof CraftingScreenHandler)) return;

        if (antiDesync) {
            mc.player.getInventory().updateItems();
        }
        CraftingScreenHandler currentScreenHandler = (CraftingScreenHandler) mc.player.currentScreenHandler;
        //List<Item> itemList = items.get();
        List<RecipeResultCollection> recipeResultCollectionList  = mc.player.getRecipeBook().getOrderedResults();
        for (RecipeResultCollection recipeResultCollection : recipeResultCollectionList) {
            for (Recipe<?> recipe : recipeResultCollection.getRecipes(true)) {
                if (!getSetting(0).asList(Item.class).contains(recipe.getOutput().getItem())) continue;
                    mc.interactionManager.clickRecipe(currentScreenHandler.syncId, recipe, craftAll);
                    mc.interactionManager.clickSlot(currentScreenHandler.syncId, 0, 1,
                            drop ? SlotActionType.THROW : SlotActionType.QUICK_MOVE, mc.player);
            }
        }
    }

}
