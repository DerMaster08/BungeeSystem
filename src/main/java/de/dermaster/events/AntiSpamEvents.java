/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import java.util.HashMap;
/*    */ import java.util.UUID;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.event.ChatEvent;
/*    */ import net.md_5.bungee.api.event.PlayerDisconnectEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class AntiSpamEvents
/*    */   implements Listener
/*    */ {
/* 17 */   private HashMap<UUID, Long> times = new HashMap<>();
/*    */   
/*    */   @EventHandler
/*    */   public void onChat(ChatEvent e) {
/* 21 */     ProxiedPlayer p = (ProxiedPlayer)e.getSender();
/* 22 */     if (e.getMessage().startsWith("/"))
/* 23 */       return;  if (Proxy.lastMessagesSpam.containsKey(p.getUniqueId()) && ((String)Proxy.lastMessagesSpam.get(p.getUniqueId())).equalsIgnoreCase(e.getMessage())) {
/* 24 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("NoRepitions")));
/* 25 */       e.setCancelled(true);
/*    */       return;
/*    */     } 
/* 28 */     if (this.times.containsKey(p.getUniqueId()) && System.currentTimeMillis() - ((Long)this.times.get(p.getUniqueId())).longValue() < 1000L) {
/* 29 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("WriteToFast")));
/* 30 */       e.setCancelled(true);
/*    */       return;
/*    */     } 
/* 33 */     Proxy.lastMessagesSpam.put(p.getUniqueId(), e.getMessage());
/* 34 */     this.times.put(p.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onQuit(PlayerDisconnectEvent e) {
/* 39 */     ProxiedPlayer p = e.getPlayer();
/* 40 */     if (Proxy.lastMessagesSpam.containsKey(p.getUniqueId())) {
/* 41 */       Proxy.lastMessagesSpam.remove(p.getUniqueId());
/*    */     }
/* 43 */     if (this.times.containsKey(p.getUniqueId()))
/* 44 */       this.times.remove(p.getUniqueId()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\AntiSpamEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */