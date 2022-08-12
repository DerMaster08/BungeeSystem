/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import de.dermaster.utils.FriendUtils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.event.PlayerDisconnectEvent;
/*    */ import net.md_5.bungee.api.event.PostLoginEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class FriendEvents
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onJoin(PostLoginEvent e) throws IOException {
/* 23 */     ProxiedPlayer p = e.getPlayer();
/* 24 */     File friends = new File(FriendUtils.PATH, p.getUniqueId() + ".yml");
/* 25 */     if (!friends.exists()) {
/* 26 */       friends.createNewFile();
/*    */     }
/* 28 */     UUIDEvents.onJoin(e);
/*    */     
/* 30 */     ArrayList<String> onlineFriends = FriendUtils.getOnlineFriends(p.getUniqueId().toString());
/* 31 */     if (onlineFriends.size() == 0) {
/* 32 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("0FriendsOnline")));
/* 33 */     } else if (onlineFriends.size() == 1) {
/* 34 */       ProxiedPlayer other = ProxyServer.getInstance().getPlayer(UUID.fromString(onlineFriends.get(0)));
/* 35 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("1FriendsOnline").replace("<Friend1>", other.getName())));
/* 36 */     } else if (onlineFriends.size() == 2) {
/* 37 */       ProxiedPlayer other = ProxyServer.getInstance().getPlayer(UUID.fromString(onlineFriends.get(0)));
/* 38 */       ProxiedPlayer other2 = ProxyServer.getInstance().getPlayer(UUID.fromString(onlineFriends.get(1)));
/* 39 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("2FriendsOnline").replace("<Friend1>", other.getName())
/* 40 */             .replace("<Friend2>", other2.getName())));
/*    */     } else {
/* 42 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("ManyFriendsOnline").replace("<FriendCount>", String.valueOf(onlineFriends.size()))));
/*    */     } 
/*    */     
/* 45 */     for (String current : FriendUtils.getFriendsSorted(FriendUtils.getUUIDFromName(p.getName()))) {
/* 46 */       if (FriendUtils.isOnline(current)) {
/* 47 */         ProxyServer.getInstance().getPlayer(FriendUtils.getNameFromUUID(current)).sendMessage(
/* 48 */             TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendWentOnline").replace("<PlayerName>", p.getName())));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onQuit(PlayerDisconnectEvent e) throws IOException {
/* 55 */     ProxiedPlayer p = e.getPlayer();
/* 56 */     for (String uuid : FriendUtils.getFriendsSorted(FriendUtils.getUUIDFromName(p.getName()))) {
/* 57 */       if (FriendUtils.isOnline(uuid))
/* 58 */         ProxyServer.getInstance().getPlayer(FriendUtils.getNameFromUUID(uuid)).sendMessage(
/* 59 */             TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendWentOffline").replace("<PlayerName>", p.getName()))); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\FriendEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */