package com.arlania.world.content;

import com.arlania.model.PlayerRights;
import com.arlania.util.Misc;
import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.dialogue.DialogueExpression;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.dialogue.DialogueType;
import com.arlania.world.entity.impl.player.Player;

public class MemberScrolls {
	
	public static void checkForRankUpdate(Player player) {
		if(player.getRights().isStaff()) {
			return;
		}
		PlayerRights rights = null;
		if(player.getAmountDonated() >= 10)
			rights = PlayerRights.DONATOR;
		if(player.getAmountDonated() >= 25)
			rights = PlayerRights.SUPER_DONATOR;
		if(player.getAmountDonated() >= 50)
			rights = PlayerRights.EXTREME_DONATOR;
		if(player.getAmountDonated() >= 125)
			rights = PlayerRights.LEGENDARY_DONATOR;
		if(player.getAmountDonated() >= 200)
			rights = PlayerRights.UBER_DONATOR;
		if(rights != null && rights != player.getRights()) {
			player.getPacketSender().sendMessage("You've become a "+Misc.formatText(rights.toString().toLowerCase())+"! Congratulations!");
			player.setRights(rights);
			player.getPacketSender().sendRights();
		}
	}

	public static boolean handleScroll(Player player, int item) {
		switch(item) {
		case 15356:
		case 15355:
		case 15359:
		case 15358:
			int funds = item == 15356 ? 5 : item == 15355 ? 10 : item == 15359 ? 25 : item == 15358 ? 50 : -1;
			player.getInventory().delete(item, 1);
			player.incrementAmountDonated(funds);
			player.getPointsHandler().incrementDonationPoints(funds);
			player.getPacketSender().sendMessage("Your account has gained funds worth $"+funds+". Your total is now at $"+player.getAmountDonated()+".");
			checkForRankUpdate(player);
			PlayerPanel.refreshPanel(player);
			break;
		}
		return false;
	}
	
	public static Dialogue getTotalFunds(final Player player) {
		return new Dialogue() {

			@Override
			public DialogueType type() {
				return DialogueType.NPC_STATEMENT;
			}

			@Override
			public DialogueExpression animation() {
				return DialogueExpression.NORMAL;
			}
			
			@Override
			public int npcId() {
				return 4657;
			}

			@Override
			public String[] dialogue() {
				return player.getAmountDonated() > 0 ? new String[]{"Your account has claimed scrolls worth $"+player.getAmountDonated()+" in total.", "Thank you for supporting us!"} : new String[]{"Your account has claimed scrolls worth $"+player.getAmountDonated()+" in total."};
			}
			
			@Override
			public Dialogue nextDialogue() {
				return DialogueManager.getDialogues().get(5);
			}
		};
	}
}
