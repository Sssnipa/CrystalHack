package org.bleachhack.module.mods;


import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import org.bleachhack.event.events.EventReadPacket;
import org.bleachhack.event.events.EventTick;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.module.setting.base.SettingToggle;
import org.bleachhack.util.BleachLogger;

import java.util.HashMap;

public class TotemPop extends Module {

    /**
     * taken from BlaechHack-CupEdtion
     */

    private HashMap<String, Integer> pops = new HashMap<>();

    public boolean impact_toggle_state;

    public TotemPop() {
        super("PopCounter", KEY_UNBOUND, ModuleCategory.COMBAT, "Counts totem pops",
                new SettingToggle("Self", true).withDesc("Counts yourself too when you pop"),
                new SettingToggle("Public Chat", true).withDesc("Sends it the totem pops to the chat instead of client only"));
    }

    public void
    onDisable(){
        pops.clear();
    }

    @BleachSubscribe
    public void onReadPacket(EventReadPacket event){
        if(event.getPacket() instanceof EntityStatusS2CPacket)
        {
            EntityStatusS2CPacket pack = (EntityStatusS2CPacket) event.getPacket();

            if(pack.getStatus() == 35)
            {
                handlePop(pack.getEntity(mc.world));
            }
        }
    }

    @BleachSubscribe
    public void onTick(EventTick tick){
        if(mc.world == null)
            return;

        mc.world.getPlayers().forEach(player -> {
            if(player.getHealth() <= 0)
            {
                if(pops.containsKey(player.getEntityName()))
                {
                    if((!getSetting(0).asToggle().state) && (player == mc.player))
                        return;
                    if (!getSetting(1).asToggle().state) {
                        BleachLogger.info("\u00A7f" + player.getEntityName() + " \u00A79died after popping " + "\u00A7f" + pops.get(player.getEntityName()) + " \u00A79totems");
                    }
                    else{
                        assert mc.player != null;
                        mc.player.sendChatMessage(player.getEntityName() + " died after popping " + pops.get(player.getEntityName()) + " totems");
                    }
                    pops.remove(player.getEntityName(), pops.get(player.getEntityName()));
                }
            }
        });
    }

    private void
    handlePop(Entity entity){
        if(pops == null)
            pops = new HashMap<>();

        if((!getSetting(0).asToggle().state) && (entity == mc.player))
            return;

        if(pops.get(entity.getEntityName()) == null)
        {
            pops.put(entity.getEntityName(), 1);
            if (!getSetting(1).asToggle().state) {
                BleachLogger.info("\u00A7f" + entity.getEntityName() + " \u00A79popped "+ "\u00A7f" + "1 \u00A79totem");
            }
            else{
                assert mc.player != null;
                mc.player.sendChatMessage(entity.getEntityName() + " popped " + "1 totem");
            }
        }
        else if(!(pops.get(entity.getEntityName()) == null))
        {
            int popc = pops.get(entity.getEntityName());
            popc += 1;
            pops.put(entity.getEntityName(), popc);
            if (!getSetting(1).asToggle().state) {
                BleachLogger.info("\u00A7f" + entity.getEntityName() + " \u00A79popped "+ "\u00A7f" + popc + " \u00A79totems");
            }
            else{
                assert mc.player != null;
                mc.player.sendChatMessage(entity.getEntityName() + " popped " + popc + " totems");
            }
        }
    }

}
