/*     */ package de.dermaster.commands;
/*     */ 
/*     */ import de.dermaster.Proxy;
/*     */ import de.dermaster.utils.Config;
/*     */ import de.dermaster.utils.FriendUtils;
/*     */ import de.dermaster.utils.MySQL;
import net.md_5.bungee.api.ChatColor;
/*     */ import net.md_5.bungee.api.CommandSender;
/*     */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*     */ import net.md_5.bungee.api.plugin.Command;

import java.sql.SQLException;

/*     */
/*     */ public class FriendCommand
/*     */   extends Command {
/*     */   public FriendCommand() {
/*  20 */     super("Friend", Config.getSetting("PlayerPerm"), new String[] { "F" });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(CommandSender sender, String[] args) {
/*  26 */     if (sender instanceof ProxiedPlayer) {
/*  27 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/*  28 */       if (args.length == 1) {
/*  29 */         if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l")) {
/*  30 */           FriendUtils.sendFriendList(p, 1);
/*     */         } else {
/*  32 */           sendHelpMsg((CommandSender)p);
/*     */         } 
/*  34 */       } else if (args.length == 2) {
/*  35 */         if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l")) {
/*  36 */           int side = 1;
/*     */           try {
/*  38 */             side = Integer.parseInt(args[1]);
/*  39 */           } catch (Exception exc) {
/*  40 */             sendHelpMsg((CommandSender)p);
/*     */             return;
/*     */           } 
/*  43 */           FriendUtils.sendFriendList(p, side);
/*  44 */         } else if (args[0].equalsIgnoreCase("add")) {
/*  45 */           if (args[1].equals(p.getName())) {
/*  46 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("SelfFriendRequest")));
/*     */             return;
/*     */           } 
/*  49 */           if (FriendUtils.hasFriend(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(args[1]))) {
/*  50 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("AlreadyFriend")));
/*     */             return;
/*     */           } 
/*  53 */           if (!FriendUtils.isOnline(FriendUtils.getUUIDFromName(args[1]))) {
/*  54 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("PlayerNotOnline")));
/*     */             
/*     */             return;
/*     */           } 
/*  58 */           if (FriendUtils.hasRequest(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(args[1]))) {
                    try {
                        MySQL.sendRequest(p.getName(), args[1]);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendRequestAdded").replace("<PlayerName>", args[1])));
/*  61 */             if (FriendUtils.isOnline(FriendUtils.getUUIDFromName(args[1]))) {
/*  62 */               ProxiedPlayer p2 = ProxyServer.getInstance().getPlayer(args[1]);
/*  63 */               p2.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendRequestAdded").replace("<PlayerName>", p.getName())));
/*     */             } 
/*     */           } else {
/*  66 */             ProxiedPlayer p2 = ProxyServer.getInstance().getPlayer(args[1]);
/*  67 */             if (!FriendUtils.hasRequest(FriendUtils.getUUIDFromName(p2.getName()), FriendUtils.getUUIDFromName(p.getName()))) {
/*  68 */               FriendUtils.requestFriend(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(p2.getName()));
/*  69 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendRequestSent").replace("<PlayerName>", args[1])));
/*  70 */               p2.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendRequestReceived").replace("<PlayerName>", p.getName())));
/*  71 */               TextComponent accept = new TextComponent(ChatColor.GREEN + "[ANNEHMEN] ");
/*  72 */               accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Config.getSetting("FriendAcceptHover"))).create()));
/*  73 */               accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend accept " + p.getName()));
/*  74 */               TextComponent deny = new TextComponent(ChatColor.RED + "[ABLEHNEN] ");
/*  75 */               deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Config.getSetting("FriendDeclineHover"))).create()));
/*  76 */               deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend decline " + p.getName()));
/*  77 */               accept.addExtra((BaseComponent)deny);
/*  78 */               p2.sendMessage((BaseComponent)accept);
/*     */             } else {
/*  80 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendRequestAlreadySent")));
/*     */             } 
/*     */           } 
/*  83 */         } else if (args[0].equalsIgnoreCase("accept")) {
/*  84 */           if (FriendUtils.hasRequest(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(args[1]))) {
/*  85 */             FriendUtils.acceptFriend(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(args[1]));
/*  86 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendAccepted").replace("<PlayerName>", args[1])));
/*  87 */             if (FriendUtils.isOnline(FriendUtils.getUUIDFromName(args[1]))) {
/*  88 */               ProxiedPlayer p2 = ProxyServer.getInstance().getPlayer(args[1]);
/*  89 */               p2.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendAccepted").replace("<PlayerName>", p.getName())));
/*     */             } 
/*     */           } else {
/*  92 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("NoFriendRequestSent")));
/*     */           } 
/*  94 */         } else if (args[0].equalsIgnoreCase("decline") || args[0].equalsIgnoreCase("deny")) {
/*  95 */           if (FriendUtils.hasRequest(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(args[1]))) {
/*  96 */             FriendUtils.declineFriend(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(args[1]));
/*  97 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendRequestDeclined")));
/*  98 */             if (FriendUtils.isOnline(FriendUtils.getUUIDFromName(args[1]))) {
/*  99 */               ProxiedPlayer p2 = ProxyServer.getInstance().getPlayer(args[1]);
/* 100 */               p2.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendRequestDeclinedOther")));
/*     */             } 
/*     */           } else {
/* 103 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("NoFriendRequestSent")));
/*     */           } 
/* 105 */         } else if (args[0].equalsIgnoreCase("remove")) {
/* 106 */           if (FriendUtils.hasFriend(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(args[1]))) {
/* 107 */             FriendUtils.removeFriend(FriendUtils.getUUIDFromName(p.getName()), FriendUtils.getUUIDFromName(args[1]));
/* 108 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendDeclined").replace("<PlayerName>", args[1])));
/* 109 */             if (FriendUtils.isOnline(FriendUtils.getUUIDFromName(args[1]))) {
/* 110 */               ProxiedPlayer p2 = ProxyServer.getInstance().getPlayer(args[1]);
/* 111 */               p2.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("FriendDeclinedOther").replace("<PlayerName>", p.getName())));
/*     */             } 
/*     */           } else {
/* 114 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("NotFriendsYet").replace("<PlayerName>", args[1])));
/*     */           } 
/*     */         } else {
/* 117 */           sendHelpMsg((CommandSender)p);
/*     */         } 
/*     */       } else {
/* 120 */         sendHelpMsg((CommandSender)p);
/*     */       } 
/*     */     } else {
/* 123 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("CommandOnlyIngame"))));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sendHelpMsg(CommandSender p) {
/* 128 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("FriendHelp"))));
/*     */   }
/*     */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\FriendCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */