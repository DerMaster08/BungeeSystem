/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import net.md_5.bungee.api.ProxyServer;
         import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.event.ChatEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class SpyEvents
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(ChatEvent e) {
/* 19 */     String[] args = e.getMessage().split(" ");
/* 20 */     ProxiedPlayer p = (ProxiedPlayer)e.getSender();
/* 21 */     if (args[0].startsWith("/"))
/* 22 */       for (UUID uuid : Proxy.spyPlayers.keySet()) {
/* 23 */         ArrayList<UUID> uuids = (ArrayList<UUID>)Proxy.spyPlayers.get(uuid);
/* 24 */         if (uuids.contains(p.getUniqueId())) {
/* 25 */           ProxiedPlayer spy = ProxyServer.getInstance().getPlayer(uuid);
/* 26 */           spy.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("SpyUserUsedCommand")
/* 27 */                 .replace("<PlayerName>", p.getName()).replace("<Message>", e.getMessage())));
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\SpyEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */