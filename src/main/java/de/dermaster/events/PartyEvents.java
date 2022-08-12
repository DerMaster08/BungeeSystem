/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import de.dermaster.utils.Party;
/*    */ import de.dermaster.utils.PartyUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.event.PlayerDisconnectEvent;
/*    */ import net.md_5.bungee.api.event.ServerSwitchEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class PartyEvents
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onServerChange(ServerSwitchEvent e) {
/* 21 */     ProxiedPlayer p = e.getPlayer();
/* 22 */     Party party = PartyUtils.getParty(p);
/* 23 */     if (party == null) {
/*    */       return;
/*    */     }
/* 26 */     String serverName = p.getServer().getInfo().getName();
/* 27 */     if (party.getPartyOwner().equals(p)) {
/* 28 */       for (ProxiedPlayer players : party.getPartyMembers()) {
/* 29 */         players.connect(p.getServer().getInfo());
/*    */       }
/* 31 */       party.sendMessageToEveryBodyInTheParty(Config.getSetting("PartyJoinedServer").replace("<ServerName>", serverName));
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onServerChange(PlayerDisconnectEvent e) {
/* 37 */     ProxiedPlayer p = e.getPlayer();
/* 38 */     Party party = PartyUtils.getParty(p);
/* 39 */     if (party != null) {
/* 40 */       party.removePlayerFromParty(p);
/* 41 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("PartyLeftSelf")));
/*    */     } 
/*    */     
/* 44 */     for (ArrayList<UUID> list : (Iterable<ArrayList<UUID>>)PartyUtils.partyRequests.values()) {
/* 45 */       if (list.contains(p.getUniqueId())) {
/* 46 */         list.remove(p.getUniqueId());
/*    */       }
/*    */     } 
/* 49 */     if (PartyUtils.partyRequests.containsKey(p.getUniqueId()))
/* 50 */       PartyUtils.partyRequests.remove(p.getUniqueId()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\PartyEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */