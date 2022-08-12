/*     */ package de.dermaster.commands;
/*     */ 
/*     */ import de.dermaster.Proxy;
/*     */ import de.dermaster.utils.Config;
/*     */ import de.dermaster.utils.JoinMeInfos;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import net.md_5.bungee.api.CommandSender;
/*     */ import net.md_5.bungee.api.ProxyServer;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*     */ import net.md_5.bungee.api.plugin.Command;
/*     */ import net.md_5.bungee.api.plugin.Plugin;
/*     */ 
/*     */ public class JoinMeCommand
/*     */   extends Command
/*     */ {
/*     */   public JoinMeCommand() {
/*  24 */     super("JoinMe");
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(CommandSender sender, String[] args) {
/*  29 */     if (sender instanceof ProxiedPlayer) {
/*  30 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/*  31 */       if (args.length == 0) {
/*  32 */         if (p.hasPermission(Config.getSetting("JoinMePerm"))) {
/*  33 */           if (!alreadyCreatedJoinMe(p)) {
/*  34 */             createJoinMe(p);
/*     */           } else {
/*  36 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("JoinMeWait")));
/*     */           } 
/*     */         } else {
/*  39 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("NoPermMessage"))));
/*     */         } 
/*  41 */       } else if (args.length == 1) {
/*  42 */         ProxiedPlayer wantToJoinPlayer = ProxyServer.getInstance().getPlayer(args[0]);
/*  43 */         if (wantToJoinPlayer == null) {
/*  44 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("PlayerNotOnline")));
/*     */           return;
/*     */         } 
/*  47 */         if (alreadyCreatedJoinMe(wantToJoinPlayer)) {
/*  48 */           addPlayerToJoinMe(p, wantToJoinPlayer);
/*     */         } else {
/*  50 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("NoJoinMe")));
/*     */         } 
/*     */       } else {
/*  53 */         sendHelpMessage(p);
/*     */       } 
/*     */     } else {
/*  56 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void sendHelpMessage(ProxiedPlayer p) {
/*  61 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("JoinMeHelp")));
/*     */   }
/*     */   
/*     */   private boolean alreadyCreatedJoinMe(ProxiedPlayer p) {
/*  65 */     return Proxy.joinmes.containsKey(p.getUniqueId());
/*     */   }
/*     */   
/*     */   private void createJoinMe(ProxiedPlayer p) {
/*  69 */     String server = p.getServer().getInfo().getName();
/*  70 */     String[] disallowedServers = Config.getSetting("DisallowedServersForJoinMe").split("#"); byte b; int i; String[] arrayOfString1;
/*  71 */     for (i = (arrayOfString1 = disallowedServers).length, b = 0; b < i; ) { String s = arrayOfString1[b];
/*  72 */       if (server.equals(s)) {
/*  73 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("JoinMeDisallowedOnThisServer"))); return;
/*     */       } 
/*     */       b++; }
/*     */     
/*  77 */     Proxy.joinmes.put(p.getUniqueId(), new JoinMeInfos(server, "Egal"));
/*  78 */     sendMessages(p, server);
/*  79 */     removeJoinMe(p);
/*  80 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("JoinMeSent")));
/*     */   }
/*     */   
/*     */   private void sendMessages(ProxiedPlayer p, String server) {
/*  84 */     TextComponent tc = new TextComponent(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("ClickJoinMe"));
/*  85 */     tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Config.getSetting("HoverJoinMe"))).create()));
/*  86 */     tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/joinme " + p.getName()));
/*  87 */     for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
/*  89 */         all.sendMessage(TextComponent.fromLegacyText("§8§m--------------------------------------------"));
/*  90 */         all.sendMessage(TextComponent.fromLegacyText(" "));
/*  91 */         all.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.JOINME_PREFIX) + Config.getSetting("JoinMeXYPlaysZ").replace("<PlayerName>", p.getName())
/*  92 */               .replace("<ServerName>", server)));
/*  93 */         all.sendMessage((BaseComponent)tc);
/*  94 */         all.sendMessage(TextComponent.fromLegacyText(" "));
/*  95 */         all.sendMessage(TextComponent.fromLegacyText("§8§m--------------------------------------------"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addPlayerToJoinMe(ProxiedPlayer player, ProxiedPlayer wantToJoinPlayer) {
/* 101 */     String serverName = ((JoinMeInfos)Proxy.joinmes.get(wantToJoinPlayer.getUniqueId())).getServerName();
/* 102 */     sendPlayerToServer(player, serverName);
/*     */   }
/*     */   
/*     */   private void sendPlayerToServer(ProxiedPlayer p, String serverName) {
/* 106 */     p.connect(ProxyServer.getInstance().getServerInfo(serverName));
/*     */   }
/*     */   
/*     */   private void removeJoinMe(final ProxiedPlayer p) {
/* 110 */     ProxyServer.getInstance().getScheduler().schedule((Plugin)Proxy.getInstance(), new Runnable()
/*     */         {
/*     */           public void run() {
/* 113 */             Proxy.joinmes.remove(p.getUniqueId());
/*     */           }
/* 115 */         },  1L, TimeUnit.MINUTES);
/*     */   }
/*     */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\JoinMeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */