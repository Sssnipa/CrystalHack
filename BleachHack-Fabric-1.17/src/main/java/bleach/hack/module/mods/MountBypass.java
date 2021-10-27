package bleach.hack.module.mods;

import bleach.hack.event.events.EventSendPacket;
import bleach.hack.module.Module;
import bleach.hack.module.ModuleCategory;
import bleach.hack.util.PlayerInteractEntityC2SUtils;
import bleach.hack.util.PlayerInteractEntityC2SUtils.InteractType;
import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

public class MountBypass extends Module {

    /**
     * i got this code from <a>https://github.com/CUPZYY/BleachHack-CupEdition/blob/master/CupEdition-1.17/src/main/java/bleach/hack/module/mods/MountBypass.java</a>.
     */

    public boolean dontCancel = false;

    public MountBypass() {
        super("MountBypass", KEY_UNBOUND, ModuleCategory.CRYSTALHACK, "Bypasses illegalstack on non bungeecord servers");
    }

    @Subscribe
    public void onPacket(EventSendPacket event) {
        if (dontCancel)
            return;

        if (event.getPacket() instanceof PlayerInteractEntityC2SPacket
                && PlayerInteractEntityC2SUtils.getEntity(
                (PlayerInteractEntityC2SPacket) event.getPacket()) instanceof AbstractDonkeyEntity
                && PlayerInteractEntityC2SUtils.getInteractType(
                (PlayerInteractEntityC2SPacket) event.getPacket()) == InteractType.INTERACT_AT) {
            event.setCancelled(true);
        }
    }
}