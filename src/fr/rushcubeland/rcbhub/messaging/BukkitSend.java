package fr.rushcubeland.rcbhub.messaging;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.rushcubeland.rcbhub.RcbHub;
import org.bukkit.entity.Player;

public class BukkitSend {

    public static void CmdToProxy(Player player, String cmd){
        try {

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("CmdProxy");
            out.writeUTF(player.getName());
            out.writeUTF(cmd);
            player.sendPluginMessage(RcbHub.getInstance(), "rcbproxy:main", out.toByteArray());
        }
        catch (NullPointerException nullPointerException){
            nullPointerException.getStackTrace();
        }
    }
}
