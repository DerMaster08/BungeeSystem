/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.OnlinePlayer;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.event.PlayerDisconnectEvent;
/*    */ import net.md_5.bungee.api.event.PostLoginEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class OnlineTimeManager
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onJoin(PostLoginEvent e) {
/* 15 */     ProxiedPlayer p = e.getPlayer();
/* 16 */     Proxy.onlinePlayers.put(p.getUniqueId(), new OnlinePlayer(p));
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onLeave(PlayerDisconnectEvent e) {
/* 21 */     ProxiedPlayer p = e.getPlayer();
/* 22 */     (Proxy.onlinePlayers.get(p.getUniqueId())).saveOnlineTime();
/* 23 */     Proxy.onlinePlayers.remove(p.getUniqueId());
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\OnlineTimeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */