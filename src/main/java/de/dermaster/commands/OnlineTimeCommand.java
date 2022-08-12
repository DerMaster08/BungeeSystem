/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import de.dermaster.utils.OnlinePlayer;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class OnlineTimeCommand extends Command {
/*    */   public OnlineTimeCommand() {
/* 13 */     super("OnlineTime", Config.getSetting("PlayerPerm"), "OnlineZeit", "Spielzeit", "Playtime");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 18 */     if (sender instanceof ProxiedPlayer) {
/* 19 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 20 */       String onlineTime = ((OnlinePlayer)Proxy.onlinePlayers.get(p.getUniqueId())).getOnlineTime();
/* 21 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("OnlineTime").replace("<Ping>", onlineTime)));
/*    */     } else {
/* 23 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\OnlineTimeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */