/*     */ package de.dermaster.utils;
/*     */ 
/*     */ import de.dermaster.Proxy;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ 
/*     */ 
/*     */ public class Config
/*     */ {
/*  13 */   private static HashMap<String, String> loaded = new HashMap<>();
/*     */   
/*  15 */   private static String[] keys = new String[] { "Prefix", "NoPermMessage", "AdminPerm", 
/*  16 */       "DiscordLink", "BuilderPerm", "BauserverName", "TestserverName", "DevPerm", "Maintenance", "JoinMePerm", 
/*  17 */       "PlayerPerm", "StaffPerm", "LobbyServer", "Beschimpfungen", "DisallowedServersForJoinMe", "BroadcastPrefix", 
/*  18 */       "ReportPrefix", "JoinMePrefix", "PartyPrefix", "FriendPrefix", "TeamChatPrefix", "YTRequirements", 
/*  19 */       "VoteMessage", "MaintenancePerm", "BauserverSuccess", "CommandOnlyIngame", 
/*  20 */       "BroadcastHelp", "ClearChat1", "ClearChat2", "ClearChat3", "FL-Help", "FriendHelp", "SelfFriendRequest", "AlreadyFriend", 
/*  21 */       "PlayerNotOnline", "FriendRequestAdded", "FriendRequestSent", "FriendRequestReceived", "FriendAcceptHover", "FriendDeclineHover", 
/*  22 */       "FriendRequestAlreadySent", "FriendAccepted", "NoFriendRequestSent", "FriendRequestDeclined", "FriendRequestDeclinedOther", 
/*  23 */       "NotFriendsYet", "FriendDeclined", "FriendDeclinedOther", 
/*  24 */       "JoinMeWait", "NoJoinMe", "JoinMeHelp", "JoinMeDisallowedOnThisServer", "JoinMeSent", "ClickJoinMe", "HoverJoinMe", 
/*  25 */       "JoinMeXYPlaysZ", "MaintenanceOn", "MaintenanceOff", "MsgHelp", "MsgNoFriend", "OnlineTime", "NoRepitions", "NoCurseWords", 
/*  26 */       "MaintenanceKickMessage", "SpyUserUsedCommand", "PartyLeftSelf", "PartyJoinedServer", "FriendWentOffline", "FriendWentOnline", 
/*  27 */       "0FriendsOnline", "1FriendsOnline", "2FriendsOnline", "ManyFriendsOnline", "XYLeftParty", "XYClosedParty", "XYKickedFromParty", 
/*  28 */       "XYPromotedToPartyLeader", "NotInParty", "PartyHelp", "PingHelp", "PingSelf", "PingOther", "ProxyHelp", "ProxyConfigReloadSuccess", 
/*  29 */       "NoMessageToAnswer", "RHelp", "PlayerNotReported", "ReportHandle", "SelfReport", "AlreadyReported", "ReportHelp", "ReportHelpStaff", 
/*  30 */       "SpyHelp", "SpyOn", "SpyOff", "TeamChatHelp", "TestserverSuccess", "FailMessage", "WriteToFast", "AntiCommandBypassPerm", 
/*  31 */       "ServerStarting" };
/*     */   
/*  33 */   private static String[] defaultValues = new String[] { "&8[&aServer&8] ", "&cDazu hast du keine Berechtigung!", 
/*  34 */       "perm.rank.admin", "DC-Link", "perm.rank.builder", "bauserver", "testserver", "perm.rank.dev", 
/*  35 */       "false", "perm.joinme.*", "perm.rank.player", "perm.rank.staff", "lobby", 
/*  36 */       "hs#hurensohn#fuck#fk#dummkopf#hacker#arschloch#wixer#wichser#schlampe#hure#arsch#fotze", 
/*  37 */       "lobby#bauserver#testserver", "&8[&6&lBroadcast&8]&e", "&8[&aReportSystem&8] ", "&8[&eJoinMe&8] ", 
/*  38 */       "&8[&5Party&8] ", "&8[&eFreundeSystem&8] ", "&8[&6&lTeamChat&8] &e", 
/*  39 */       "&aAlle Infos, um ein YouTuber zu werden: \n&6100 Abonennten &aund &6regelmäßige Videos &aüber den Server", 
/*  40 */       "&aVotet für &6ServerName &ahttps://...", "perm.maintenance.*", 
/*  41 */       "&aDu wurdest zum Bauserver gesendet!", "&cDer Command kann nur Ingame verwendet werden!", 
/*  42 */       "&c/Broadcast [Nachricht]", "&8-------------------------------------", 
/*  43 */       "&aDer Spieler &6<PlayerName> &ahat den Chat geleert!", "&8-------------------------------------", 
/*  44 */       "&cBenutzung von /FL\n     /fl\n     /fl [SeitenZahl]", 
/*  45 */       "&cBenutzung von /Friend\n     /Friend [help/list]\n     /Friend list [SeitenZahl]\n     /Friend add [Spieler]\n     /Friend accept [Spieler]\n     /Friend decline [Spieler]\n     /Friend remove [Spieler]", 
/*     */       
/*  47 */       "&cDu kannst dir nicht selber eine Freundschaftsanfrage senden!", "&cDer Spieler ist bereits mit dir befreundet!", 
/*  48 */       "&cDer Spieler ist nicht online!", "&aDu bist nun mit &6<PlayerName>&a befreundet!", 
/*  49 */       "&aDu hast &6<PlayerName>&a eine Freundschaftsanfrage gesendet!", 
/*  50 */       "&6<PlayerName>&a hat dir eine Freundschaftsanfrage gesendet!", 
/*  51 */       "&aFreundschaftsanfrage annehmen!", "&aFreundschaftsanfrage §cablehnen§a!", 
/*  52 */       "&cDu hast diesem Spieler bereits eine Freundschaftsanfrage gesendet!", 
/*  53 */       "&aDu bist nun mit &6<PlayerName>&a befreundet!", 
/*  54 */       "&cDer Spieler hat dir keine Freundschaftsanfrage gesendet!", 
/*  55 */       "&aDu hast die Freundschaftsanfrage abgelehnt!", 
/*  56 */       "&aDer Spieler &6<PlayerName>&a hat deine Freundschaftsanfrage abgelehnt!", 
/*  57 */       "&cDu bist nicht mit dem Spieler &6<PlayerName>&c befreundet!", 
/*  58 */       "&aDu hast die Freundschaft mit &6<PlayerName>&a beendet!", 
/*  59 */       "&aDer Spieler &6<PlayerName>&a hat die Freundschaft mit dir beendet!", 
/*  60 */       "&cDu musst 1 Minute zwischen deinen JoinMe's warten!", 
/*  61 */       "&cDer Spieler hat kein JoinMe erstellt\n&coder es ist bereits abgelaufen!", 
/*  62 */       "&cBenutzung von /joinme\n     &6/joinme\n     &c/joinme [playerName]", 
/*  63 */       "&cAuf diesem Server darfst du kein JoinMe erstellen!", "&aDas JoinMe wurde gesendet!", 
/*  64 */       "&aKlicke hier, um ihm nachzujoinen!", "&6Klicke um beizutreten", 
/*  65 */       "&aDer Spieler &6<PlayerName>&a spielt &6<ServerName>", 
/*  66 */       "&aDu hast den Server wieder eröffnet!", "&aDu hast den Server in Wartungsarbeiten gesetzt!", 
/*  67 */       "&c/Msg [SpielerName] [Nachricht]", 
/*  68 */       "&cDu musst mit &6<PlayerName> &cbefreundet sein, um ihm private Nachrichten schreiben zu können!", 
/*  69 */       "&aDeine Spielzeit beträgt: &6<Ping>", "&cBitte wiederhole dich nicht!", "&cBitte benutze keine Schimpfwörter!", 
/*  70 */       "&cWartungsarbeiten!", "&6<PlayerName> &ahat einen Command ausgeführt:\n&a<Message>", 
/*  71 */       "&aDu hast die Party verlassen!", "&aDie Party ist dem Server &6<ServerName> &abeigetreten!", 
/*  72 */       "&cDein Freund &6<PlayerName>&c ist nun offline!", "&aDein Freund &6<PlayerName>&a ist nun online!", 
/*  73 */       "&aEs ist im Moment &ckein &aFreund online!", 
/*  74 */       "&aIm Moment ist nur &6<Friend1> &avon deinen Freunden online!", 
/*  75 */       "&aIm Moment sind &6<Friend1> &aund &6<Friend2> &avon deinen Freunden online!", 
/*  76 */       "&aEs sind im Moment &6<FriendCount> &aFreunde online!", 
/*  77 */       "&aDer Spieler &6<PlayerName> &ahat die Party verlassen!", 
/*  78 */       "&aDer Spieler &6<PlayerName> &ahat die Party geschlossen!", 
/*  79 */       "&aDer Spieler &6<PlayerName> &awurde aus der Party geschmissen!", 
/*  80 */       "&aDer Spieler &6<PlayerName> &awurde zum PartyOwner gemacht!", 
/*  81 */       "&cDu bist in keiner Party!", 
/*  82 */       "&cBenutze:\n     /party [help/list/leave/close/summon]\n     /party invite [SpielerName]\n     /party accept [SpielerName]\n     /party deny [SpielerName]\n     /party kick [SpielerName]\n     /party promote [SpielerName]\n     /party chat [Message]", 
/*     */ 
/*     */       
/*  85 */       "&c/Ping oder /Ping [SpielerName]", 
/*  86 */       "&aDein Ping beträgt: &6<Ping>", 
/*  87 */       "&aDer Ping von &6<PlayerName> &abeträgt: &6<Ping>", 
/*  88 */       "&c/proxy [reload/rl]", 
/*  89 */       "&aDu hast die Config des BungeeCordSystems reloadet!", 
/*  90 */       "&cDir hat niemand eine private Nachricht geschrieben, der du antworten könntest!", 
/*  91 */       "&c/R [Nachricht]", "&cDer Spieler wurde nicht reported!", "&aDu bearbeitest nun den Report!", 
/*  92 */       "&cDu kannst dich nicht selber reporten!", "&cDu hast den Spieler bereits reported!", 
/*  93 */       "&cBenutze: /report [SpielerName] [Cheating|Skin|Chat|Custom]", 
/*  94 */       "     &cBenutze: /report list\n     &cBenutze: /report handle [SpielerName]", 
/*  95 */       "&c/Spy [SpielerName]", "&aDu spionierst nun &6<PlayerName> &anach!", "&aDu spionierst nun &6<PlayerName> &a nicht mehr nach!", 
/*  96 */       "&c/TeamChat [Nachricht]", "&aDu wurdest zum Testserver gesendet!", "&6:(", 
/*  97 */       "&cDu schreibst zu schnell!", "AntiCommand.bypass", 
/*  98 */       "&aDer <ServerName> startet!" };
/*     */   
/*     */   public static void loadConfigs() throws IOException {
/* 101 */     (new File(String.valueOf(Proxy.getInstance().getDataFolder().getPath()) + "/playerStats")).mkdirs();
/*     */     
/* 103 */     (new File(String.valueOf(Proxy.getInstance().getDataFolder().getPath()) + "/friends")).mkdirs();
/*     */     
/* 105 */     File antiCommandFile = new File(Proxy.getInstance().getDataFolder().getPath(), "AntiCommand.yml");
/* 106 */     if (!antiCommandFile.exists()) {
/* 107 */       antiCommandFile.createNewFile();
/* 108 */       String msg = "plugins?&cDazu hast du keine Berechtigung!";
/* 109 */       FileWriter writer = new FileWriter(String.valueOf(Proxy.getInstance().getDataFolder().getPath()) + "/AntiCommand.yml", true);
/* 110 */       writer.write(msg);
/* 111 */       writer.close();
/*     */     } 
/* 113 */     File config = new File(Proxy.getInstance().getDataFolder().getPath(), "config.yml");
/* 114 */     File automatedMessages = new File(Proxy.getInstance().getDataFolder().getPath(), "AutomatedMessages.yml");
/* 115 */     File playerData = new File(Proxy.getInstance().getDataFolder().getPath(), "PlayerData.yml");
/* 116 */     if (!config.exists()) {
/* 117 */       createDefaultConfig(config);
/*     */     } else {
/* 119 */       updateDefaultConfig(config);
/*     */     } 
/* 121 */     if (!automatedMessages.exists()) {
/* 122 */       createDefaultAutomatedMessagesConfig(automatedMessages);
/*     */     }
/* 124 */     if (!playerData.exists()) {
/* 125 */       createDefaultPlayerDataConfig(playerData);
/*     */     }
/*     */     
/* 128 */     for (int i = 0; i < keys.length; i++) {
/* 129 */       loaded.put(keys[i], FileHelper.getString(config, keys[i]));
/*     */     }
/* 131 */     Proxy.PREFIX = ChatColor.translateAlternateColorCodes('&', getSetting("Prefix"));
/* 132 */     Proxy.REPORT_PREFIX = ChatColor.translateAlternateColorCodes('&', getSetting("ReportPrefix"));
/* 133 */     Proxy.JOINME_PREFIX = ChatColor.translateAlternateColorCodes('&', getSetting("JoinMePrefix"));
/* 134 */     Proxy.PARTY_PREFIX = ChatColor.translateAlternateColorCodes('&', getSetting("PartyPrefix"));
/* 135 */     Proxy.FRIEND_PREFIX = ChatColor.translateAlternateColorCodes('&', getSetting("FriendPrefix"));
/* 136 */     Proxy.TEAMCHAT_PREFIX = ChatColor.translateAlternateColorCodes('&', getSetting("TeamChatPrefix"));
/* 137 */     loadAutomatedMessages(automatedMessages);
/*     */   }
/*     */   
/*     */   private static void createDefaultConfig(File config) {
/*     */     try {
/* 142 */       config.createNewFile();
/* 143 */     } catch (IOException e) {
/* 144 */       e.printStackTrace();
/*     */     } 
/* 146 */     for (int i = 0; i < keys.length; i++) {
/* 147 */       FileHelper.saveString(config, keys[i], defaultValues[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void updateDefaultConfig(File config) {
/* 152 */     for (int i = 0; i < keys.length; i++) {
/* 153 */       if (!FileHelper.contains(config, keys[i])) {
/* 154 */         FileHelper.saveString(config, keys[i], defaultValues[i]);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void createDefaultAutomatedMessagesConfig(File automatedMessages) {
/*     */     try {
/* 161 */       automatedMessages.createNewFile();
/* 162 */     } catch (IOException e) {
/* 163 */       e.printStackTrace();
/*     */     } 
/* 165 */     FileHelper.saveInteger(automatedMessages, "AutomatedMessagesCount", 3);
/* 166 */     FileHelper.saveString(automatedMessages, "Message1", "&aJoine unserem Discord: &6https://discord.gg/?");
/* 167 */     FileHelper.saveString(automatedMessages, "Message2", "&aBenutze &6/ping&a, um deinen Ping zu sehen");
/* 168 */     FileHelper.saveString(automatedMessages, "Message3", "&aFolge uns auf Youtube: &6https://youtube.com/?");
/*     */   }
/*     */   
/*     */   private static void createDefaultPlayerDataConfig(File playerData) {
/*     */     try {
/* 173 */       playerData.createNewFile();
/* 174 */     } catch (IOException e) {
/* 175 */       e.printStackTrace();
/*     */     } 
/* 177 */     FileHelper.saveInteger(playerData, "Count", 0);
/*     */   }
/*     */   
/*     */   private static void loadAutomatedMessages(File automatedMessages) {
/* 181 */     int count = FileHelper.getInteger(automatedMessages, "AutomatedMessagesCount");
/* 182 */     loaded.put("MessagesCount", String.valueOf(count));
/* 183 */     for (int i = 1; i <= count; i++) {
/* 184 */       loaded.put("Messages" + i, FileHelper.getString(automatedMessages, "Message" + i));
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getSetting(String key) {
/* 189 */     if (loaded.containsKey(key)) {
/* 190 */       return ChatColor.translateAlternateColorCodes('&', loaded.get(key));
/*     */     }
/* 192 */     return "§cNULL";
/*     */   }
/*     */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\telle\\utils\Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */