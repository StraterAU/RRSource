package mysql.impl;

import com.arlania.model.Position;
import com.arlania.model.Locations.Location;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.Achievements;
import com.arlania.world.content.PlayerLogs;
import com.arlania.world.content.Achievements.AchievementData;
import com.arlania.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

import mysql.motivote.MotivoteHandler;
import mysql.motivote.Vote;

public class Voting extends MotivoteHandler<Vote> {

	private static int VOTES;
	
	@Override
	public void onCompletion(Vote reward) {
		Player p = World.getPlayerByName(Misc.formatText(reward.username()));
		if (p != null) {
			
			if(Dungeoneering.doingDungeoneering(p)) {
				if(!p.voteMessageSent()) {
					p.getPacketSender().sendMessage("<col=900000>You will receive your vote reward once you're done Dungeoneering.");
					p.setVoteMessageSent(true);
				}
				return;
			}
			
			reward.complete();
			
		/*	if(reward.ip() != null && !reward.ip().equals(p.getHostAddress())) {
				p.getPacketSender().sendMessage("<col=900000>Warning! Our anti-cheat system has detected an invalid vote for your account.");
				PlayerLogs.log(p.getUsername(), "Anti-cheat system detected invalid vote attempt!");
			} else  {*/
				World.getVoteRewardingQueue().add(p); //Needs to be synchronized with game tick
		//	}
		}
	}
	public static void handleKilledVotingBoss(Player p) {
		p.getPacketSender().sendMessage("<img=0>You have killed the vote boss!");
		p.getInventory().add(19670, 5);//here
		TeleportHandler.teleportPlayer(p, new Position(3208, 3426), p.getSpellbook().getTeleportType());
		VOTES = 0;
			
		}
	public static void handleVotingBoss(Player p) {
		if (p.getLocation() == Location.DUNGEONEERING || p.getLocation() == Location.DUEL_ARENA) {
			p.getPacketSender().sendMessage("You Can't do that right now!");
			return;
		}
		if(VOTES >= 15) {
	
			p.getPacketSender().sendMessage("<col=900000>Welcome To The Voting Boss");
			TeleportHandler.teleportPlayer(p, new Position(3104, 5537), p.getSpellbook().getTeleportType());
			//VOTES = 0;
			
		} else if (VOTES <= 15) {
			p.getPacketSender().sendMessage("<col=900000>15 Votes Is Needed To Start This Event!");
		 }
		
		VOTES++; 
	}
   
	public static void handleQueuedReward(Player p) {
		p.setVoteMessageSent(false);
		p.getPacketSender().sendInterfaceRemoval().sendMessage("We've recorded a vote for your account, enjoy your reward!");
		p.getInventory().add(19670, 1);
			World.sendMessage("<img=10> <col=008FB2>[VOTING]"+p.getUsername()+" has claimed their vote reward! Type ::vote for yours.");

		Achievements.doProgress(p, AchievementData.VOTE_100_TIMES);
		PlayerLogs.log(p.getUsername(), "Player received vote reward!");
		if(VOTES == 15) {
			NPC npc = new NPC(72, new Position(3101, 5537, 0));
			World.register(npc);
		}
			if(VOTES == 15) {
			World.sendMessage("<img=10> <col=008FB2>The Vote Boss Has Just Been Activated! Do ::voteboss To Join The Fight!");
			World.sendMessage("<img=10> <col=008FB2>Another 15 votes have been claimed! Vote now using the ::vote command!");
			//VOTES = 0;
		}
		VOTES++;
	}
}