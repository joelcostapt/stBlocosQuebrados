package pt.joelcosta.stblocosquebrados.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static pt.joelcosta.stblocosquebrados.Main.*;

public class BlocosCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String string, String[] args) {

        if (!(s instanceof Player)) return false;
        Player p = (Player) s;
        if (c.getName().equalsIgnoreCase("blocos")){
            if (args.length == 0){
                p.sendMessage(" ");
                p.sendMessage("§7Blocos quebrados: " + getPlayerManager().getPlayer(p.getName()).getQuantidade());
                p.sendMessage(" ");
                return true;
            }
        }
        return false;
    }
}
