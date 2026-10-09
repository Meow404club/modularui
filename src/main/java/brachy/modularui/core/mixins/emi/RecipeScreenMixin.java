package brachy.modularui.core.mixins.emi;

import brachy.modularui.integration.emi.recipe.EmiInteractionSink;

import dev.emi.emi.api.widget.Widget;
import dev.emi.emi.screen.RecipeScreen;
import dev.emi.emi.screen.WidgetGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = RecipeScreen.class, remap = false)
public class RecipeScreenMixin {

    @Shadow private List<WidgetGroup> currentPage;

    @Inject(method = "mouseScrolled", at = @At("HEAD"), remap = true, cancellable = true)
    //? if neoforge {
    private void modularui$mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY, CallbackInfoReturnable<Boolean> cir) {
    //?} else {
    /*    private void mui$mouseScrolled(double mouseX, double mouseY, double amount, CallbackInfoReturnable<Boolean> cir) {
     *///?}
        for (WidgetGroup group : currentPage) {
            for (Widget widget : group.widgets) {
                if (widget instanceof EmiInteractionSink sink) {
                    // scroll hands GROUP-LOCAL coords (the same space RecipeScreen.mouseClicked
                    // bounds-gates widgets in) so an implementer can gate against its own bounds;
                    // UIWrapperWidget ignores them, so its behaviour is what it always was
                    //? if neoforge {
                    if (sink.mouseScrolled(mouseX - group.x(), mouseY - group.y(), scrollX, scrollY)) {
                    //?} else {
                    /*                    if (sink.mouseScrolled(mouseX - group.x(), mouseY - group.y(), amount)) {
                     *///?}
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }

    @Inject(method = "mouseReleased", at = @At("HEAD"), remap = true, cancellable = true)
    //? if neoforge {
    private void modularui$mouseReleased(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
    //?} else {
    /*    private void mui$mouseReleased(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
     *///?}
        for (WidgetGroup group : currentPage) {
            for (Widget widget : group.widgets) {
                if (widget instanceof EmiInteractionSink sink) {
                    if (sink.mouseReleased(button)) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }

    @Inject(method = "mouseDragged", at = @At("HEAD"), remap = true, cancellable = true)
    //? if neoforge {
    private void modularui$mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY, CallbackInfoReturnable<Boolean> cir) {
    //?} else {
    /*    private void mui$mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY, CallbackInfoReturnable<Boolean> cir) {
     *///?}
        for (WidgetGroup group : currentPage) {
            for (Widget widget : group.widgets) {
                if (widget instanceof EmiInteractionSink sink) {
                    if (sink.mouseDragged(button, deltaX, deltaY)) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }

}
