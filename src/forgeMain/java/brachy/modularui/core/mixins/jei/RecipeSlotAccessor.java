package brachy.modularui.core.mixins.jei;

import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.library.gui.ingredients.ICycler;
import mezz.jei.library.gui.ingredients.RecipeSlot;
import mezz.jei.library.gui.ingredients.RecipeSlotIngredients;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(value = RecipeSlot.class, remap = false)
public interface RecipeSlotAccessor {

    @Accessor("role")
    @Mutable
    void modularui$setRole(RecipeIngredientRole role);

    @Accessor("cycler")
    @Mutable
    void modularui$setCycler(ICycler cycler);

    @Accessor("tooltipCallbacks")
    List<IRecipeSlotRichTooltipCallback> modularui$getTooltipCallbacks();

    /**
     * JEI 15.55.0.201+ folded the mutable {@code allIngredients}/{@code displayIngredients} lists
     * into the immutable {@link RecipeSlotIngredients} holder. Swap the holder as a whole
     * (same wiring as the RecipeSlot constructor), then run the slot's own invalidation
     * ({@code onDisplayOverridesChanged}) so lazily cached candidates/tooltips/tag badge
     * don't render stale ingredients.
     */
    @Accessor("ingredients")
    @Mutable
    void modularui$setIngredients(RecipeSlotIngredients ingredients);

    @Invoker("onDisplayOverridesChanged")
    void modularui$onDisplayOverridesChanged();
}
