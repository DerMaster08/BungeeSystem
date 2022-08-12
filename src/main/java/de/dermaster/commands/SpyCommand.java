/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class SpyCommand
/*    */   extends Command
/*    */ {
/*    */   public SpyCommand() {
/* 18 */     super("Spy");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 23 */     if (sender instanceof ProxiedPlayer) {
/* 24 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 25 */       if (p.hasPermission(Config.getSetting("AdminPerm"))) {
/* 26 */         if (args.length == 1) {
/* 27 */           ProxiedPlayer other = ProxyServer.getInstance().getPlayer(args[0]);
/* 28 */           if (other != null) {
/* 29 */             if (!Proxy.spyPlayers.containsKey(p.getUniqueId())) {
/* 30 */               ArrayList<UUID> uuids = new ArrayList<>();
/* 31 */               uuids.add(other.getUniqueId());
/* 32 */               Proxy.spyPlayers.put(p.getUniqueId(), uuids);
/* 33 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("SpyOn").replace("<PlayerName>", args[0])));
/* 34 */             } else if (!((ArrayList)Proxy.spyPlayers.get(p.getUniqueId())).contains(other.getUniqueId())) {
/* 35 */               ArrayList<UUID> uuids = (ArrayList<UUID>)Proxy.spyPlayers.get(p.getUniqueId());
/* 36 */               uuids.add(other.getUniqueId());
/* 37 */               Proxy.spyPlayers.put(p.getUniqueId(), uuids);
/* 38 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("SpyOn").replace("<PlayerName>", args[0])));
/*    */             } else {
/* 40 */               ArrayList<UUID> uuids = (ArrayList<UUID>)Proxy.spyPlayers.get(p.getUniqueId());
/* 41 */               if (uuids.size() == 1) {
/* 42 */                 Proxy.spyPlayers.remove(p.getUniqueId());
/*    */               } else {
/* 44 */                 uuids.remove(other.getUniqueId());
/* 45 */                 Proxy.spyPlayers.put(p.getUniqueId(), uuids);
/*    */               } 
/* 47 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("SpyOff").replace("<PlayerName>", args[0])));
/*    */             } 
/*    */           } else {
/* 50 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("PlayerNotOnline")));
/*    */           } 
/*    */         } else {
/* 53 */           sendHelpMsg((CommandSender)p);
/*    */         } 
/*    */       } else {
/* 56 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("NoPermMessage"))));
/*    */       } 
/*    */     } else {
/* 59 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendHelpMsg(CommandSender p) {
/* 64 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("SpyHelp")));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\SpyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */