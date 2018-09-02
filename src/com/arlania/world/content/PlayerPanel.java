package com.arlania.world.content;

import com.arlania.GameLoader;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.minigames.impl.Nomad;
import com.arlania.world.content.minigames.impl.RecipeForDisaster;
import com.arlania.world.entity.impl.player.Player;

public class PlayerPanel {

	public static final String LINE_START = "   > ";

	public static void refreshPanel(Player player) {

		int counter = 39159;
		player.getPacketSender().sendString(counter++, "@or3@-@whi@ Tools");
		player.getPacketSender().sendString(counter++, LINE_START.replace(">", "*") + "@or1@Staff Online");
		player.getPacketSender().sendString(counter++, LINE_START.replace(">", "*") + "@or1@Player Panel");
		player.getPacketSender().sendString(counter++, LINE_START.replace(">", "*") + "@or1@Kill Log");
		player.getPacketSender().sendString(counter++, LINE_START.replace(">", "*") + "@or1@Drop Log");

		player.getPacketSender().sendString(counter++, "");

		player.getPacketSender().sendString(counter++, "@or3@-@whi@ Worldwide Information");
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Evil Tree: @yel@"+(EvilTrees.getLocation() != null ? EvilTrees.getLocation().playerPanelFrame : "N/A"));
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Well of Goodwill: @yel@"+(WellOfGoodwill.isActive() ? WellOfGoodwill.getMinutesRemaining() + " mins" : "N/A"));
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Crashed Star: @yel@"+(ShootingStar.getLocation() != null ?ShootingStar.getLocation().playerPanelFrame : "N/A"));
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Bonus: @yel@"+GameLoader.getSpecialDay());

		player.getPacketSender().sendString(counter++, "");

		player.getPacketSender().sendString(counter++, "@or3@-@whi@ General Information");
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Server Time: @yel@"+Misc.getCurrentServerTime());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Time Played: @yel@"+Misc.getHoursPlayed((player.getTotalPlayTime() + player.getRecordedLogin().elapsed())));
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Username: @yel@"+player.getUsername());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Rank: @yel@"+player.getRights().toString());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Donated: @yel@"+player.getAmountDonated());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Exp Lock: @yel@"+(player.experienceLocked() ? "Locked" : "Unlocked"));
		
		player.getPacketSender().sendString(counter++, "");
		
		player.getPacketSender().sendString(counter++, "@or3@-@whi@ Player Statistics");
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Loyalty Points:@yel@ "+player.getPointsHandler().getLoyaltyPoints());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Donation Points:@yel@ "+player.getPointsHandler().getDonationPoints());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Voting Points:@yel@ "+player.getPointsHandler().getVotingPoints());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Trivia Points:@yel@ "+player.getPointsHandler().getTriviaPoints());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Donation Points:@yel@ "+player.getPointsHandler().getDonationPoints());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Commendations:@yel@ "+player.getPointsHandler().getCommendations());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Dung. Tokens:@yel@ "+player.getPointsHandler().getDungeoneeringTokens());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Boss Points:@yel@ "+player.getBossPoints());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Slayer Points:@yel@ "+player.getPointsHandler().getSlayerPoints());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Pk Points:@yel@ "+player.getPointsHandler().getPkPoints());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Wilderness Killstreak:@yel@ "+player.getPlayerKillingAttributes().getPlayerKillStreak());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Wilderness Kills:@yel@ "+player.getPlayerKillingAttributes().getPlayerKills());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Wilderness Deaths:@yel@ "+player.getPlayerKillingAttributes().getPlayerDeaths());
//		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Arena Victories:@yel@ "+player.getDueling());
//		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Arena Losses:@yel@ "+player.getPointsHandler().getLoyaltyPoints());

		player.getPacketSender().sendString(counter++, "");

		player.getPacketSender().sendString(counter++, "-@whi@ Slayer Information");
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Master: @yel@"+player.getSlayer().getSlayerMaster());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Task: @yel@"+player.getSlayer().getSlayerTask());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Task Amount: @yel@"+player.getSlayer().getAmountToSlay());
		player.getPacketSender().sendString(counter++, LINE_START + "@or1@Task Streak: @yel@"+player.getSlayer().getTaskStreak());

		player.getPacketSender().sendString(counter++, "");
		player.getPacketSender().sendString(3211, ""+player.getMorytaniaPoints());
		player.getPacketSender().sendString(counter++, "-@whi@ Quests");
		player.getPacketSender().sendString(counter++,
				LINE_START + RecipeForDisaster.getQuestTabPrefix(player) + "Recipe For Disaster ");
		player.getPacketSender().sendString(counter++,
				LINE_START + Nomad.getQuestTabPrefix(player) + "Nomad's Requiem ");

		player.getPacketSender().sendString(counter++, "");
		player.getPacketSender().sendString(counter++, "");
		player.getPacketSender().sendString(counter++, "");
		player.getPacketSender().sendString(counter++, "");
		player.getPacketSender().sendString(counter++, "");
		player.getPacketSender().sendString(counter++, "");

		/**
		 * General info
		 *
		 * player.getPacketSender().sendString(39159, "@or3@ - @whi@ General
		 * Information");
		 * 
		 * if(ShootingStar.CRASHED_STAR == null) {
		 * player.getPacketSender().sendString(26623, "@or2@Crashed
		 * star: @gre@N/A"); } else { player.getPacketSender().sendString(26623,
		 * "@or2@Crashed
		 * star: @gre@"+ShootingStar.CRASHED_STAR.getStarLocation().playerPanelFrame+"");
		 * }
		 * 
		 * if(EvilTrees.SPAWNED_TREE == null) {
		 * player.getPacketSender().sendString(26625, "@or2@Evil
		 * Tree: @gre@N/A"); } else { player.getPacketSender().sendString(26625,
		 * "@or2@Evil
		 * Tree: @gre@"+EvilTrees.SPAWNED_TREE.getTreeLocation().playerPanelFrame+"");
		 * }
		 * 
		 * if(GameLoader.getSpecialDay() != null) {
		 * player.getPacketSender().sendString(26626, "@or2@Bonus: @gre@"+
		 * GameLoader.getSpecialDay()); } else { if(GameLoader.getSpecialDay()
		 * != null) { return; } }
		 * 
		 * if(WellOfGoodwill.isActive()) {
		 * player.getPacketSender().sendString(26622, "@or2@Well of
		 * Goodwill: @gre@Active"); } else {
		 * player.getPacketSender().sendString(26622, "@or2@Well of
		 * Goodwill: @gre@N/A"); }
		 * 
		 * /** Account info
		 * 
		 * player.getPacketSender().sendString(39165, "@or3@ - @whi@ Account
		 * Information"); player.getPacketSender().sendString(39167,
		 * "@or2@Username: @yel@"+player.getUsername());
		 * player.getPacketSender().sendString(39168,
		 * "@or2@Claimed: @yel@$"+player.getAmountDonated());
		 * player.getPacketSender().sendString(39169,
		 * "@or2@Rank: @yel@"+Misc.formatText(player.getRights().toString().toLowerCase()));
		 * player.getPacketSender().sendString(39170,
		 * "@or2@Email: @yel@"+(player.getEmailAddress() == null ||
		 * player.getEmailAddress().equals("null") ? "-" :
		 * player.getEmailAddress()));
		 * player.getPacketSender().sendString(39171,
		 * "@or2@Music: @yel@"+(player.musicActive() ? "On" : "Off")+"");
		 * player.getPacketSender().sendString(39172,
		 * "@or2@Sounds: @yel@"+(player.soundsActive() ? "On" : "Off")+"");
		 * player.getPacketSender().sendString(26721, "@or2@Exp
		 * Lock: @gre@"+(player.experienceLocked() ? "Locked" : "Unlocked")+"");
		 * 
		 * /** Points
		 * 
		 * player.getPacketSender().sendString(39174, "@or3@ - @whi@
		 * Statistics"); player.getPointsHandler().refreshPanel();
		 * 
		 * /** Slayer
		 * 
		 * player.getPacketSender().sendString(39189, "@or3@ - @whi@ Slayer");
		 * player.getPacketSender().sendString(39190, "@or2@Open Kills
		 * Tracker"); player.getPacketSender().sendString(39191, "@or2@Open Drop
		 * Log"); player.getPacketSender().sendString(26716,
		 * "@or2@Master: @gre@"+Misc.formatText(player.getSlayer().getSlayerMaster().toString().toLowerCase().replaceAll("_",
		 * " "))); if(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK)
		 * player.getPacketSender().sendString(26717,
		 * "@or2@Task: @gre@"+Misc.formatText(player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_",
		 * " "))+""); else player.getPacketSender().sendString(26717,
		 * "@or2@Task: @gre@"+Misc.formatText(player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_",
		 * " "))+"s"); player.getPacketSender().sendString(26718, "@or2@Task
		 * Streak: @gre@"+player.getSlayer().getTaskStreak()+"");
		 * player.getPacketSender().sendString(26719, "@or2@Task
		 * Amount: @gre@"+player.getSlayer().getAmountToSlay()+"");
		 * if(player.getSlayer().getDuoPartner() != null)
		 * player.getPacketSender().sendString(26720, "@or2@Duo
		 * Partner: @gre@"+player.getSlayer().getDuoPartner()+""); else
		 * player.getPacketSender().sendString(26720, "@or2@Duo
		 * Partner: @gre@None");
		 * 
		 * /** Quests
		 * 
		 * player.getPacketSender().sendString(26722, "@or3@ - @whi@ Quests");
		 * player.getPacketSender().sendString(26723,
		 * RecipeForDisaster.getQuestTabPrefix(player) + "Recipe For Disaster");
		 * player.getPacketSender().sendString(26724,
		 * Nomad.getQuestTabPrefix(player) + "Nomad's Requeim");
		 * 
		 * /** Links
		 * 
		 * player.getPacketSender().sendString(39202, "@or3@ - @whi@ Links");
		 * player.getPacketSender().sendString(39203, "@or2@Forum");
		 * player.getPacketSender().sendString(39204, "@or2@Rules");
		 * player.getPacketSender().sendString(39205, "@or2@Store");
		 * player.getPacketSender().sendString(39206, "@or2@Vote");
		 * player.getPacketSender().sendString(39207, "@or2@Hiscores");
		 * player.getPacketSender().sendString(39208, "@or2@Report");
		 */
	}

}