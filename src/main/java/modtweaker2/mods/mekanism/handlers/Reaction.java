package modtweaker2.mods.mekanism.handlers;

import static modtweaker2.helpers.InputHelper.toFluid;
import static modtweaker2.helpers.InputHelper.toStack;
import static modtweaker2.mods.mekanism.MekanismHelper.toGas;
import mekanism.api.PressurizedProducts;
import mekanism.api.PressurizedReactants;
import mekanism.api.PressurizedRecipe;
import mekanism.common.recipe.RecipeHandler.Recipe;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import modtweaker2.mods.mekanism.gas.IGasStack;
import modtweaker2.mods.mekanism.util.AddMekanismRecipe;
import modtweaker2.mods.mekanism.util.RemoveMekanismRecipe;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.mekanism.Reaction")
public class Reaction {
    @ZenMethod
    public static void addRecipe(IItemStack solid, ILiquidStack liquid, IGasStack gas, IItemStack outItem, IGasStack outGas, double energy, int ticks) {
        PressurizedReactants input = new PressurizedReactants(toStack(solid), toFluid(liquid), toGas(gas));
        PressurizedRecipe recipe = new PressurizedRecipe(input, energy, new PressurizedProducts(toStack(outItem), toGas(outGas)), ticks);
        MineTweakerAPI.apply(new AddMekanismRecipe("PRESSURIZED_REACTION_CHAMBER", Recipe.PRESSURIZED_REACTION_CHAMBER.get(), input, recipe));
    }

    @ZenMethod
    public static void removeRecipe(IItemStack outItem, IGasStack outGas) {
        PressurizedProducts output = new PressurizedProducts(toStack(outItem), toGas(outGas));
        MineTweakerAPI.apply(new RemoveMekanismRecipe("PRESSURIZED_REACTION_CHAMBER", Recipe.PRESSURIZED_REACTION_CHAMBER.get(), output));
    }
}