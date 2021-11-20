package org.bleachhack.module.mods;


import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import org.bleachhack.event.events.EventSendPacket;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.util.PlayerInteractEntityC2SUtils;

public class MountBypass extends Module {

    /**
     * i got this code from <a>https://github.com/CUPZYY/BleachHack-CupEdition/blob/master/CupEdition-1.17/src/main/java/bleach/hack/module/mods/MountBypass.java</a>.
     */

    public boolean dontCancel = false;

    public MountBypass() {
        super("MountBypass", KEY_UNBOUND, ModuleCategory.EXPLOITS, "Bypasses illegalstack on non bungeecord servers");
    }

    @Subscribe
    public void onPacket(EventSendPacket event) {
        if (dontCancel)
            return;

        if (event.getPacket() instanceof PlayerInteractEntityC2SPacket
                && PlayerInteractEntityC2SUtils.getEntity(
                (PlayerInteractEntityC2SPacket) event.getPacket()) instanceof AbstractDonkeyEntity
                && PlayerInteractEntityC2SUtils.getInteractType(
                (PlayerInteractEntityC2SPacket) event.getPacket()) == PlayerInteractEntityC2SUtils.InteractType.INTERACT_AT) {
            event.setCancelled(true);
        }
    }
}