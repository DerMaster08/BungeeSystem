/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class ClearChat
/*    */   extends Command {
/*    */   public ClearChat() {
/* 15 */     super("ClearChat", Config.getSetting("PlayerPerm"), new String[] { "CC" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 20 */     if (sender instanceof ProxiedPlayer) {
/* 21 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 22 */       if (p.hasPermission(Config.getSetting("StaffPerm"))) {
/* 23 */         for (ProxiedPlayer serverPlayers : p.getServer().getInfo().getPlayers()) {
/* 24 */           for (int i = 0; i < 200; i++) {
/* 25 */             serverPlayers.sendMessage((new ComponentBuilder(" ")).create());
/*    */           }
/* 27 */           serverPlayers.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("ClearChat1"))));
/* 28 */           serverPlayers.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("ClearChat2")).replace("<PlayerName>", p.getName())));
/* 29 */           serverPlayers.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("ClearChat3"))));
/*    */         } 
/*    */       } else {
/* 32 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("NoPermMessage"))));
/*    */       } 
/*    */     } else {
/* 35 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("CommandOnlyIngame"))));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\ClearChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */