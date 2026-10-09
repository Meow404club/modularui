package brachy.modularui.integration.emi.recipe;

/**
 * The EMI recipe-page interaction seam (GT6 port card mattree-emi-panzoom): anything the
 * vendored {@code RecipeScreenMixin} may forward EMI's three undispatched recipe-page
 * events to implements this — the event types EMI 1.1.24 never delivers to api widgets
 * (RecipeScreen.mouseScrolled routes to sidebar/page flip; mouseDragged/mouseReleased
 * belong to the pressed-slot drag stack), so the mixin's HEAD injections are the only
 * delivery path.
 *
 * <p>History: the mixin gated on {@code instanceof ModularUIEmiRecipe.UIWrapperWidget}.
 * The gate widened to this interface so a third-party widget (GT6's material-tree nav
 * canvas) can receive the same forwards without the mixin learning any consumer type;
 * {@link ModularUIEmiRecipe.UIWrapperWidget} implements it with its existing methods, so
 * MUI's own behaviour is unchanged.
 *
 * <p>Contract: the mixin hands scroll coordinates GROUP-LOCAL (the same space
 * RecipeScreen.mouseClicked uses for {@code widget.getBounds().contains(ox, oy)} —
 * {@code mouseX - group.x()}), so an implementer can bound-gate against its own widget
 * bounds directly. Drag carries only the button and the raw deltas (no coordinates —
 * EMI's dispatch has none to give at this point); release carries only the button.
 * The scroll axes follow the per-leg vanilla Screen signature (double-axis on 1.21.1
 * neoforge, single amount on 1.20.1 forge), exactly as the mixin's {@code //?} legs do.
 */
public interface EmiInteractionSink {

    //? if neoforge {
    boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY);
    //?} else {
    /*boolean mouseScrolled(double mouseX, double mouseY, double amount);
     *///?}

    boolean mouseDragged(int button, double dragX, double dragY);

    boolean mouseReleased(int button);
}
