/*     */ package de.dermaster.commands;
/*     */ 
/*     */ import de.dermaster.Proxy;
/*     */ import de.dermaster.utils.Config;
/*     */ import de.dermaster.utils.Party;
/*     */ import de.dermaster.utils.PartyUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import net.md_5.bungee.api.CommandSender;
/*     */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*     */ import net.md_5.bungee.api.plugin.Command;
/*     */ 
/*     */ 
/*     */ public class PartyCommand
/*     */   extends Command
/*     */ {
/*     */   public PartyCommand() {
/*  24 */     super("Party", Config.getSetting("PlayerPerm"), new String[] { "P" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(CommandSender sender, String[] args) {
/*  29 */     if (sender instanceof ProxiedPlayer) {
/*  30 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/*  31 */       if (args.length == 1) {
/*  32 */         if (args[0].equalsIgnoreCase("summon")) {
/*  33 */           Party party = PartyUtils.getParty(p);
/*  34 */           if (party != null) {
/*  35 */             if (party.getPartyOwner().equals(p)) {
/*  36 */               for (ProxiedPlayer members : party.getPartyMembers()) {
/*  37 */                 members.connect(p.getServer().getInfo());
/*     */               }
/*  39 */               party.sendMessageToEveryBodyInTheParty("§aDu wurdest von §6" + p.getName() + " §agesendet!", p);
/*  40 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu hast die Party zu deinem Server gesendet!");
/*     */             } else {
/*  42 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu musst PartyOwner sein, um die Partymitglieder zu deinem Server zu senden!");
/*     */             } 
/*     */           } else {
/*  45 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("NotInParty"));
/*     */           } 
/*  47 */         } else if (args[0].equalsIgnoreCase("list")) {
/*  48 */           Party party = PartyUtils.getParty(p);
/*  49 */           if (party != null) {
/*  50 */             sendPartyList(p, party);
/*     */           } else {
/*  52 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("NotInParty"));
/*     */           } 
/*  54 */         } else if (args[0].equalsIgnoreCase("leave")) {
/*  55 */           Party party = PartyUtils.getParty(p);
/*  56 */           if (party != null) {
/*  57 */             party.removePlayerFromParty(p);
/*  58 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("PartyLeftSelf"));
/*  59 */             for (ArrayList<UUID> list : (Iterable<ArrayList<UUID>>)PartyUtils.partyRequests.values()) {
/*  60 */               if (list.contains(p.getUniqueId())) {
/*  61 */                 list.remove(p.getUniqueId());
/*     */               }
/*     */             } 
/*     */           } else {
/*  65 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("NotInParty"));
/*     */           } 
/*  67 */         } else if (args[0].equalsIgnoreCase("close")) {
/*  68 */           Party party = PartyUtils.getParty(p);
/*  69 */           if (party != null) {
/*  70 */             if (party.isPartyOwner(p)) {
/*  71 */               party.closeParty(p);
/*  72 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu hast die Party geschlossen!");
/*     */             } else {
/*  74 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu musst der PartyOwner sein, um die Party zu schließen!");
/*     */             } 
/*     */           } else {
/*  77 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("NotInParty"));
/*     */           } 
/*     */         } else {
/*  80 */           sendHelpMsg(p);
/*     */         } 
/*  82 */       } else if (args.length == 2) {
/*  83 */         if (args[0].equalsIgnoreCase("invite")) {
/*  84 */           if (args[1].equals(p.getName())) {
/*  85 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu kannst dich nicht selber in eine Party einladen!"));
/*     */             return;
/*     */           } 
/*  88 */           ProxiedPlayer toInvite = ProxyServer.getInstance().getPlayer(args[1]);
/*  89 */           if (toInvite != null) {
/*  90 */             if (PartyUtils.partyRequests.containsKey(toInvite.getUniqueId()) && (
/*  91 */               (ArrayList)PartyUtils.partyRequests.get(toInvite.getUniqueId())).contains(p.getUniqueId())) {
/*  92 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu hast den Spieler bereits in deine Party eingeladen!"));
/*     */               return;
/*     */             } 
/*  95 */             if (PartyUtils.getParty(p) != null && PartyUtils.getParty(p).getPartyMembers().contains(toInvite)) {
/*  96 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + "§cDer Spieler ist bereits in deiner Party!"));
/*     */               return;
/*     */             } 
/*  99 */             if (PartyUtils.getParty(p) != null && !PartyUtils.getParty(p).getPartyOwner().getUniqueId().equals(p.getUniqueId())) {
/* 100 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu darfst nur als PartyOwner Spieler in deine party einladen!"));
/*     */               return;
/*     */             } 
/* 103 */             if (PartyUtils.partyRequests.containsKey(toInvite.getUniqueId())) {
/* 104 */               ArrayList<UUID> list = (ArrayList<UUID>)PartyUtils.partyRequests.get(toInvite.getUniqueId());
/* 105 */               list.add(p.getUniqueId());
/* 106 */               PartyUtils.partyRequests.put(toInvite.getUniqueId(), list);
/*     */             } else {
/* 108 */               ArrayList<UUID> list = new ArrayList<>();
/* 109 */               list.add(p.getUniqueId());
/* 110 */               PartyUtils.partyRequests.put(toInvite.getUniqueId(), list);
/*     */             } 
/* 112 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu hast §6" + args[1] + " §azu deiner Party eingeladen!");
/*     */             
/* 114 */             TextComponent message = new TextComponent(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu wurdest von §6" + p.getName() + " §ain eine Party eingeladen!\n");
/* 115 */             TextComponent accept = new TextComponent(" §a[§aA§aN§aN§aE§aH§aM§aE§aN§a]");
/* 116 */             accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§aParty annehmen!")).create()));
/* 117 */             accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/party accept " + p.getName()));
/* 118 */             TextComponent deny = new TextComponent(" §c[§cA§cB§cL§cE§cH§cN§cE§cN§c]");
/* 119 */             deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§cParty ablehnen!")).create()));
/* 120 */             deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/party deny " + p.getName()));
/* 121 */             accept.addExtra((BaseComponent)deny);
/* 122 */             message.addExtra((BaseComponent)accept);
/* 123 */             toInvite.sendMessage((BaseComponent)message);
/*     */           } else {
/* 125 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("PlayerNotOnline"));
/*     */           } 
/* 127 */         } else if (args[0].equalsIgnoreCase("accept")) {
/* 128 */           ProxiedPlayer toAccept = ProxyServer.getInstance().getPlayer(args[1]);
/* 129 */           if (toAccept != null) {
/* 130 */             if (PartyUtils.partyRequests.containsKey(p.getUniqueId())) {
/* 131 */               if (((ArrayList)PartyUtils.partyRequests.get(p.getUniqueId())).contains(toAccept.getUniqueId())) {
/* 132 */                 ArrayList<UUID> list = (ArrayList<UUID>)PartyUtils.partyRequests.get(p.getUniqueId());
/* 133 */                 list.remove(toAccept.getUniqueId());
/* 134 */                 PartyUtils.partyRequests.put(p.getUniqueId(), list);
/*     */                 
/* 136 */                 Party party = PartyUtils.getParty(toAccept);
/* 137 */                 if (party == null) {
/* 138 */                   party = new Party(toAccept);
/* 139 */                   PartyUtils.partys.add(party);
/* 140 */                   toAccept.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu hast eine Party erstellt!");
/*     */                 } 
/* 142 */                 party.sendMessageToEveryBodyInTheParty("§aDer Spieler §6" + p.getName() + " §aist der Party beigetreten!");
/* 143 */                 party.addPlayerToParty(p);
/* 144 */                 p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu bist der Party von §6" + args[1] + " §abeigetreten!");
/*     */               } else {
/* 146 */                 p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDer Spieler hat dich in keine Party eingeladen!");
/*     */               } 
/*     */             } else {
/* 149 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDer Spieler hat dich in keine Party eingeladen!");
/*     */             } 
/*     */           } else {
/* 152 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("PlayerNotOnline"));
/*     */           } 
/* 154 */         } else if (args[0].equalsIgnoreCase("deny")) {
/* 155 */           ProxiedPlayer requester = ProxyServer.getInstance().getPlayer(args[1]);
/* 156 */           if (requester != null) {
/* 157 */             ArrayList<UUID> list = (ArrayList<UUID>)PartyUtils.partyRequests.get(p.getUniqueId());
/* 158 */             if (list.contains(requester.getUniqueId())) {
/* 159 */               list.remove(requester.getUniqueId());
/* 160 */               PartyUtils.partyRequests.put(p.getUniqueId(), list);
/* 161 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu hast die Partyeinladung von §6" + args[1] + " §aabgelehnt!");
/* 162 */               requester.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDer Spieler §6" + p.getName() + " §ahat deine Partyeinladung §cabgelehnt§a!");
/*     */             } else {
/* 164 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDer Spieler hat dir keine Partyeinladung gesendet!");
/*     */             } 
/*     */           } else {
/* 167 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("PlayerNotOnline"));
/*     */           } 
/* 169 */         } else if (args[0].equalsIgnoreCase("kick")) {
/* 170 */           Party party = PartyUtils.getParty(p);
/* 171 */           if (party != null) {
/* 172 */             if (party.isPartyOwner(p)) {
/* 173 */               if (args[1].equals(p.getName())) {
/* 174 */                 p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu kannst dich nicht selber aus einer Party kicken!");
/*     */                 return;
/*     */               } 
/* 177 */               ProxiedPlayer toKick = party.isInParty(args[1]);
/* 178 */               if (toKick == null) {
/* 179 */                 p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDer Spieler ist nicht in deiner Party!");
/*     */                 return;
/*     */               } 
/* 182 */               party.kickPlayerFromParty(p, toKick);
/* 183 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu hast §6" + args[1] + " §aaus der Party gekickt!");
/* 184 */               toKick.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu wurdest von §6" + p.getName() + " §aaus der Party gekickt!");
/*     */             } else {
/* 186 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu musst der PartyOwner sein, um einen Spieler aus der Party zu werfen!");
/*     */             } 
/*     */           } else {
/* 189 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu bist in keiner Party!");
/*     */           } 
/* 191 */         } else if (args[0].equalsIgnoreCase("promote")) {
/* 192 */           Party party = PartyUtils.getParty(p);
/* 193 */           if (party != null) {
/* 194 */             if (party.isPartyOwner(p)) {
/* 195 */               ProxiedPlayer toPromote = party.isInParty(args[1]);
/* 196 */               if (toPromote == null) {
/* 197 */                 p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDer Spieler ist nicht in deiner Party!");
/*     */                 return;
/*     */               } 
/* 200 */               party.promote(p, toPromote);
/* 201 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu hast §6" + args[1] + " §azum PartyOwner gemacht!");
/* 202 */               toPromote.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§aDu wurdest von §6" + p.getName() + " §azum PartyOwner gemacht!");
/*     */             } else {
/* 204 */               p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu musst der PartyOwner sein, um einen Spieler aus der Party zu promoten!");
/*     */             } 
/*     */           } else {
/* 207 */             p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu bist in keiner Party!");
/*     */           } 
/* 209 */         } else if (args[0].equalsIgnoreCase("chat") || args[0].equalsIgnoreCase("c")) {
/* 210 */           sendPartyChatMessage(p, args);
/*     */         } else {
/* 212 */           sendHelpMsg(p);
/*     */         } 
/* 214 */       } else if (args.length == 0) {
/* 215 */         sendHelpMsg(p);
/*     */       }
/* 217 */       else if (args[0].equalsIgnoreCase("chat") || args[0].equalsIgnoreCase("c")) {
/* 218 */         sendPartyChatMessage(p, args);
/*     */       } else {
/* 220 */         sendHelpMsg(p);
/*     */       } 
/*     */     } else {
/*     */       
/* 224 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sendPartyChatMessage(ProxiedPlayer p, String[] args) {
/* 229 */     Party party = PartyUtils.getParty(p);
/* 230 */     if (party != null) {
/* 231 */       String msg = "";
/* 232 */       for (int i = 1; i < args.length; i++) {
/* 233 */         if (msg.equals("")) {
/* 234 */           msg = String.valueOf(msg) + args[i];
/*     */         } else {
/* 236 */           msg = String.valueOf(msg) + " " + args[i];
/*     */         } 
/*     */       } 
/* 239 */       String PARTY_PREFIX = String.valueOf(Proxy.PARTY_PREFIX) + "§5" + p.getName() + " §8>> §5";
/* 240 */       party.sendMessageToEveryBodyInTheParty(String.valueOf(PARTY_PREFIX) + msg);
/*     */     } else {
/* 242 */       p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§cDu bist in keiner Party!");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sendHelpMsg(ProxiedPlayer p) {
/* 247 */     p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + Config.getSetting("PartyHelp"));
/*     */   }
/*     */   
/*     */   private void sendPartyList(ProxiedPlayer p, Party party) {
/* 251 */     p.sendMessage(String.valueOf(Proxy.PARTY_PREFIX) + "§ePartyOwner: " + party.getPartyOwner());
/* 252 */     if (party.getPartyMembers().size() > 0) {
/* 253 */       p.sendMessage("     §ePartyMembers:");
/* 254 */       for (ProxiedPlayer players : party.getPartyMembers()) {
/* 255 */         p.sendMessage("     §e" + players.getName());
/*     */       }
/*     */     } else {
/* 258 */       p.sendMessage("     §eKeine PartyMembers");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\PartyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */