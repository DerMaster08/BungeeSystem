/*    */ package de.dermaster.utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.UUID;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ 
/*    */ 
/*    */ public class PartyUtils
/*    */ {
/* 11 */   public static ArrayList<Party> partys = new ArrayList<>();
/* 12 */   public static HashMap<UUID, ArrayList<UUID>> partyRequests = new HashMap<>();
/*    */ 
/*    */   
/*    */   public static Party getParty(ProxiedPlayer p) {
/* 16 */     for (Party party : partys) {
/* 17 */       if (party != null) {
/* 18 */         if (party.getPartyOwner().equals(p))
/* 19 */           return party; 
/* 20 */         if (party.getPartyMembers().contains(p)) {
/* 21 */           return party;
/*    */         }
/*    */       } 
/*    */     } 
/* 25 */     return null;
/*    */   }
/*    */   
/*    */   public static boolean isInParty(ProxiedPlayer p) {
/* 29 */     return (getParty(p) != null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\telle\\utils\PartyUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */