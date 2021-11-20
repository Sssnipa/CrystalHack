package org.bleachhack.module.mods;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.bleachhack.event.events.EventReadPacket;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.module.setting.base.SettingMode;
import org.bleachhack.util.io.BleachFileMang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AutoEz extends Module {

    /**
     * taken from BlaechHack-CupEdtion
     */


    private Random rand = new Random();
    private List<String> lines = new ArrayList<>();
    private int lineCount = 0;

    public AutoEz(){
        super("AutoEz", KEY_UNBOUND, ModuleCategory.MISC, "Sends a message when you kill someone.",
                new SettingMode("Msg", "Toxic", "Custom", "Friendly").withDesc("Send a chat message when you kill someone"),
                new SettingMode("Read", "Random", "Order").withDesc("How to read the custom ezmessage"));
    }

    @BleachSubscribe
    public void onEnable() {
        //super.onEnable();
        if (!BleachFileMang.fileExists("autoez.txt")) {
            BleachFileMang.createFile("autoez.txt");
            BleachFileMang.appendFile("$p Just got EZed by CrystalHack", "autoez.txt");
        }
        lines = BleachFileMang.readFileLines("autoez.txt");
        lineCount = 0;
    }


    @BleachSubscribe
    public void onPacketRead(EventReadPacket event) {
        if (event.getPacket() instanceof GameMessageS2CPacket) {
            String msg = ((GameMessageS2CPacket) event.getPacket()).getMessage().getString();
            if (msg.contains(mc.player.getName().getString()) && msg.contains("by")) {
                for (PlayerEntity e : mc.world.getPlayers()) {
                    if (e == mc.player)
                        continue;
                    List<String> list = new ArrayList<>(Arrays.asList(msg.split(" ")));
                    int index = list.indexOf("by");

                    if (mc.player.distanceTo(e) < 12 && msg.contains(e.getName().getString())
                            && !msg.contains("<" + e.getName().getString() + ">") && !msg.contains("<" + mc.player.getName().getString() + ">") && (list.get(index + 1).equals(mc.player.getName().getString()))) {
                        if (getSetting(0).asMode().mode == 0) {
                            mc.player.sendChatMessage(e.getName().getString() + " Just got EZed by CrystalHack");
                        } else if (getSetting(0).asMode().mode == 2) {
                            mc.player.sendChatMessage(e.getName().getString() + " is good but, " + "but CrystalHack is better!");
                        } else if (getSetting(0).asMode().mode == 1) {
                            if (getSetting(1).asMode().mode == 0) {
                                mc.player.sendChatMessage(lines.get(rand.nextInt(lines.size())).replace("$p", e.getName().getString()));
                            } else if (getSetting(1).asMode().mode == 1) {
                                mc.player.sendChatMessage(lines.get(lineCount).replace("$p", e.getName().getString()));
                            }

                            if (lineCount >= lines.size() - 1) {
                                lineCount = 0;
                            } else {
                                lineCount++;
                            }
                        }
                    }
                }
            }
        }
    }


}
