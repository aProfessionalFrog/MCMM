package dev.doctor4t.trainmurdermystery.mixin.client;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameOptions.class)
public class GameOptionsMixin {

    @Shadow @Final private SimpleOption<Double> gamma;

    @Inject(method = "getGamma", at = @At("HEAD"))
    public void tmm$lockBrightnessToMoody(CallbackInfoReturnable<SimpleOption<Double>> cir) {
        gamma.setValue(0d);
    }
}
