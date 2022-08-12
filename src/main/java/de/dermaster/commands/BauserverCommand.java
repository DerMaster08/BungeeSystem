/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import eu.thesimplecloud.api.CloudAPI;
/*    */ import eu.thesimplecloud.api.player.ICloudPlayer;
/*    */ import eu.thesimplecloud.api.service.ICloudService;
/*    */ import eu.thesimplecloud.api.service.ServiceState;
/*    */ import eu.thesimplecloud.api.servicegroup.grouptype.ICloudServerGroup;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ 
/*    */ public class BauserverCommand
/*    */   extends Command
/*    */ {
/*    */   public BauserverCommand() {
/* 21 */     super("Bauserver");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 26 */     if (sender instanceof ProxiedPlayer) {
/* 27 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 28 */       if (p.hasPermission(Config.getSetting("BuilderPerm"))) {
/* 29 */         ICloudPlayer cloudPlayer = getCloudPlayer(p);
/* 30 */         ICloudServerGroup group = CloudAPI.getInstance().getCloudServiceGroupManager().getServerGroupByName(Config.getSetting("BauserverName"));
/* 31 */         ICloudService server = null;
/* 32 */         if (group.getAllServices().isEmpty()) {
/* 33 */           server = null;
/*    */         } else {
/* 35 */           server = group.getAllServices().get(0);
/*    */         } 
/*    */         
/* 38 */         if (server == null) {
/* 39 */           sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("ServerStarting").replace("<ServerName>", Config.getSetting("BauserverName"))));
/* 40 */           group.startNewService();
/* 41 */         } else if (server.getState().equals(ServiceState.CLOSED)) {
/* 42 */           sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("ServerStarting").replace("<ServerName>", Config.getSetting("BauserverName"))));
/* 43 */           server.start();
/*    */         } else {
/* 45 */           sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("BauserverSuccess")));
/* 46 */           cloudPlayer.connect(server);
/*    */         } 
/*    */       } else {
/* 49 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("NoPermMessage"))));
/*    */       } 
/*    */     } else {
/* 52 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("CommandOnlyIngame"))));
/*    */     } 
/*    */   }
/*    */   
/*    */   private ICloudPlayer getCloudPlayer(ProxiedPlayer p) {
/*    */     try {
/* 58 */       return (ICloudPlayer)CloudAPI.getInstance().getCloudPlayerManager().getCloudPlayer(p.getUniqueId()).get();
/* 59 */     } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
/* 60 */       e.printStackTrace();
/*    */       
/* 62 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\BauserverCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */