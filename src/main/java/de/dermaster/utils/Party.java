/*     */ package de.dermaster.utils;
/*     */ 
/*     */ import de.dermaster.Proxy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
/*     */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*     */ 
/*     */ 
/*     */ public class Party
/*     */ {
/*     */   private UUID partyOwner;
/*  14 */   private ArrayList<UUID> partyMembers = new ArrayList<>();
/*     */   
/*     */   public Party(ProxiedPlayer player) {
/*  17 */     this.partyOwner = player.getUniqueId();
/*     */   }
/*     */   public Party(ProxiedPlayer partyOwner, ProxiedPlayer partyMember) {
/*  20 */     this.partyOwner = partyOwner.getUniqueId();
/*     */   }
/*     */   
/*     */   public void addPlayerToParty(ProxiedPlayer player) {
/*  24 */     this.partyMembers.add(player.getUniqueId());
/*     */   }
/*     */   public void removePlayerFromParty(ProxiedPlayer p) {
/*  27 */     if (p.getUniqueId().equals(this.partyOwner)) {
/*  28 */       closeParty(p);
/*     */     } else {
/*  30 */       this.partyMembers.remove(p.getUniqueId());
/*  31 */       sendMessageToEveryBodyInTheParty(Config.getSetting("XYLeftParty").replace("<PlayerName>", p.getName()), p);
/*     */     } 
/*     */   }
/*     */   public void closeParty(ProxiedPlayer p) {
/*  35 */     PartyUtils.partys.remove(this);
/*  36 */     sendMessageToEveryBodyInTheParty(Config.getSetting("XYClosedParty").replace("<PlayerName>", p.getName()), p);
/*  37 */     System.gc();
/*     */   }
/*     */   
/*     */   public void joinServerWithParty() {}
/*     */   
/*     */   public void sendMessageToEveryBodyInTheParty(String msg, ProxiedPlayer p) {
/*  43 */     for (ProxiedPlayer proxiedPlayer : getPartyMembers()) {
/*  44 */       if (!proxiedPlayer.equals(p)) {
/*  45 */         proxiedPlayer.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + msg));
/*     */       }
/*     */     } 
/*  48 */     ProxiedPlayer players = getPartyOwner();
/*  49 */     if (!players.equals(p))
/*  50 */       players.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + msg)); 
/*     */   }
/*     */   
/*     */   public void sendMessageToEveryBodyInTheParty(String msg, ProxiedPlayer p, ProxiedPlayer p2) {
/*  54 */     for (ProxiedPlayer proxiedPlayer : getPartyMembers()) {
/*  55 */       if (!proxiedPlayer.equals(p) && !proxiedPlayer.equals(p2)) {
/*  56 */         proxiedPlayer.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + msg));
/*     */       }
/*     */     } 
/*  59 */     ProxiedPlayer players = getPartyOwner();
/*  60 */     if (!players.equals(p))
/*  61 */       players.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + msg)); 
/*     */   }
/*     */   
/*     */   public void sendMessageToEveryBodyInTheParty(String msg) {
/*  65 */     for (ProxiedPlayer proxiedPlayer : getPartyMembers()) {
/*  66 */       proxiedPlayer.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + msg));
/*     */     }
/*  68 */     ProxiedPlayer players = getPartyOwner();
/*  69 */     players.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + msg));
/*     */   }
/*     */   public void kickPlayerFromParty(ProxiedPlayer p, ProxiedPlayer toKick) {
/*  72 */     this.partyMembers.remove(toKick.getUniqueId());
/*  73 */     sendMessageToEveryBodyInTheParty(Config.getSetting("XYKickedFromParty").replace("<PlayerName>", toKick.getName()), p, toKick);
/*     */   }
/*     */   public void promote(ProxiedPlayer p, ProxiedPlayer toPromote) {
/*  76 */     this.partyMembers.remove(toPromote.getUniqueId());
/*  77 */     this.partyMembers.add(p.getUniqueId());
/*  78 */     this.partyOwner = toPromote.getUniqueId();
/*  79 */     sendMessageToEveryBodyInTheParty(Config.getSetting("XYPromotedToPartyLeader").replace("<PlayerName>", toPromote.getName()), p, toPromote);
/*     */   }
/*     */   
/*     */   public ProxiedPlayer getPartyOwner() {
/*  83 */     return ProxyServer.getInstance().getPlayer(this.partyOwner);
/*     */   }
/*     */   public ArrayList<ProxiedPlayer> getPartyMembers() {
/*  86 */     ArrayList<ProxiedPlayer> list = new ArrayList<>();
/*  87 */     for (UUID uuid : this.partyMembers) {
/*  88 */       list.add(ProxyServer.getInstance().getPlayer(uuid));
/*     */     }
/*  90 */     return list;
/*     */   }
/*     */   public boolean isPartyOwner(ProxiedPlayer p) {
/*  93 */     return p.equals(getPartyOwner());
/*     */   }
/*     */   public ProxiedPlayer isInParty(String playerName) {
/*  96 */     ProxiedPlayer p = ProxyServer.getInstance().getPlayer(playerName);
/*  97 */     if (p != null && 
/*  98 */       getPartyMembers().contains(p)) {
/*  99 */       return p;
/*     */     }
/*     */     
/* 102 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\telle\\utils\Party.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */