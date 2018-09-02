package com.arlania.world.content.dialogue;

import com.arlania.engine.task.impl.BonusExperienceTask;
import com.arlania.model.Animation;
import com.arlania.model.GameMode;
import com.arlania.model.GameObject;
import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.Skill;
import com.arlania.model.Locations.Location;
import com.arlania.model.container.impl.Shop.ShopManager;
import com.arlania.model.input.impl.BuyShards;
import com.arlania.model.input.impl.ChangePassword;
import com.arlania.model.input.impl.DonateToWell;
import com.arlania.model.input.impl.SellShards;
import com.arlania.model.input.impl.SetEmail;
import com.arlania.model.movement.MovementQueue;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.Artifacts;
import com.arlania.world.content.BankPin;
import com.arlania.world.content.CustomObjects;
import com.arlania.world.content.Effigies;
import com.arlania.world.content.Lottery;
import com.arlania.world.content.LoyaltyProgramme;
import com.arlania.world.content.MemberScrolls;
import com.arlania.world.content.PkSets;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.content.Scoreboards;
import com.arlania.world.content.WellOfGoodwill;
import com.arlania.world.content.Achievements.AchievementData;
import com.arlania.world.content.Gambling.FlowersData;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.dialogue.impl.AgilityTicketExchange;
import com.arlania.world.content.dialogue.impl.Mandrith;
import com.arlania.world.content.minigames.impl.Graveyard;
import com.arlania.world.content.minigames.impl.Nomad;
import com.arlania.world.content.minigames.impl.RecipeForDisaster;
import com.arlania.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.arlania.world.content.skill.impl.dungeoneering.DungeoneeringFloor;
import com.arlania.world.content.skill.impl.herblore.Decanting;
import com.arlania.world.content.skill.impl.mining.Mining;
import com.arlania.world.content.skill.impl.slayer.Slayer;
import com.arlania.world.content.skill.impl.slayer.SlayerDialogues;
import com.arlania.world.content.skill.impl.slayer.SlayerMaster;
import com.arlania.world.content.skill.impl.summoning.CharmingImp;
import com.arlania.world.content.skill.impl.summoning.SummoningTab;
import com.arlania.world.content.transportation.JewelryTeleporting;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.content.transportation.TeleportType;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.npc.NpcAggression;
import com.arlania.world.entity.impl.player.Player;

import mysql.MySQLController;

public class DialogueOptions {

	//Last id used = 78

	public static void handle(Player player, int id) {
		if(player.getRights() == PlayerRights.DEVELOPER) {
			player.getPacketSender().sendMessage("Dialogue button id: "+id+", action id: "+player.getDialogueActionId()).sendConsoleMessage("Dialogue button id: "+id+", action id: "+player.getDialogueActionId());
		}
		if(Effigies.handleEffigyAction(player, id)) {
			return;
		}
		if(id == FIRST_OPTION_OF_FIVE) {
			switch(player.getDialogueActionId()) {
			case 79:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers = FlowersData.generate2();
				final GameObject flower = new GameObject(flowers.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower, 90);
				player.setPositionToFace(flower.getPosition());
				player.getClickDelay().reset();

				break;
                               case 77:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers3 = FlowersData.generate10();
				final GameObject flower3 = new GameObject(flowers3.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower3);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower3, 90);
				player.setPositionToFace(flower3.getPosition());
				player.getClickDelay().reset();

				break;
			case 78:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers2 = FlowersData.generate6();
				final GameObject flower2 = new GameObject(flowers2.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower2);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower2, 90);
				player.setPositionToFace(flower2.getPosition());
				player.getClickDelay().reset();
                break;
			case 1:
				TeleportHandler.teleportPlayer(player, new Position(3420, 3510), player.getSpellbook().getTeleportType());
				break;
			case 2:
				TeleportHandler.teleportPlayer(player, new Position(3235, 3295), player.getSpellbook().getTeleportType());
				break;
			case 9:
				DialogueManager.start(player, 16);
				break;
			case 10:
				Artifacts.sellArtifacts(player);
				break;
			case 11:
				Scoreboards.open(player, Scoreboards.TOP_KILLSTREAKS);
				break;
			case 12:
				TeleportHandler.teleportPlayer(player, new Position(3087, 3517), player.getSpellbook().getTeleportType());
				break;
			/*case 13:
				player.setDialogueActionId(78);
				DialogueManager.start(player, 124);
				break;*/ // prestiging
			case 14:
				TeleportHandler.teleportPlayer(player, new Position(3097 + Misc.getRandom(1), 9869 + Misc.getRandom(1)), player.getSpellbook().getTeleportType());
				break;
			case 15:
				player.getPacketSender().sendInterfaceRemoval();
				int total = player.getSkillManager().getMaxLevel(Skill.ATTACK) + player.getSkillManager().getMaxLevel(Skill.STRENGTH);
				boolean has99 = player.getSkillManager().getMaxLevel(Skill.ATTACK) >= 99 || player.getSkillManager().getMaxLevel(Skill.STRENGTH) >= 99;
				if(total < 130 && !has99) {
					player.getPacketSender().sendMessage("");
					player.getPacketSender().sendMessage("You do not meet the requirements of a Warrior.");
					player.getPacketSender().sendMessage("You need to have a total Attack and Strength level of at least 140.");
					player.getPacketSender().sendMessage("Having level 99 in either is fine aswell.");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(2855, 3543), player.getSpellbook().getTeleportType());
				break;
			case 17:
				player.setInputHandling(new SetEmail());
				player.getPacketSender().sendEnterInputPrompt("Enter your email address:");
				break;
			case 29:
				SlayerMaster.changeSlayerMaster(player, SlayerMaster.VANNAKA);
				break;
			case 36:
				TeleportHandler.teleportPlayer(player, new Position(2871, 5318, 2), player.getSpellbook().getTeleportType());
				break;
			case 38:
				TeleportHandler.teleportPlayer(player, new Position(2273, 4681), player.getSpellbook().getTeleportType());
				break;
			case 40:
				TeleportHandler.teleportPlayer(player, new Position(3476, 9502), player.getSpellbook().getTeleportType());
				break;
			case 41:
				TeleportHandler.teleportPlayer(player, new Position(2886, 4376), player.getSpellbook().getTeleportType());
				break;
				//corp
			case 48:
				JewelryTeleporting.teleport(player, new Position(3088, 3506));
				break;
			case 59:
				if(player.getClickDelay().elapsed(1500)) {
					PkSets.buySet(player, PkSets.PURE_SET);
				}
				break;
			case 60:
				player.setDialogueActionId(61);
				DialogueManager.start(player, 102);
				break;
			case 67:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
					if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner().getUsername().equals(player.getUsername())) {
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().setDungeoneeringFloor(DungeoneeringFloor.FIRST_FLOOR);
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().sendMessage("The party leader has changed floor.");
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().refreshInterface();
					}
				}
				break;
			case 68:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
					if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner().getUsername().equals(player.getUsername())) {
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().setComplexity(1);
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().sendMessage("The party leader has changed complexity.");
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().refreshInterface();
					}
				}
				break;
			}
		} else if(id == SECOND_OPTION_OF_FIVE) {
			switch(player.getDialogueActionId()) {
			case 0:
				TeleportHandler.teleportPlayer(player, new Position(3557 + (Misc.getRandom(2)), 9946 + Misc.getRandom(2)), player.getSpellbook().getTeleportType());
				break;
			case 1:
				TeleportHandler.teleportPlayer(player, new Position(2933, 9849), player.getSpellbook().getTeleportType());
				break;
			case 2:
				TeleportHandler.teleportPlayer(player, new Position(2802, 9148), player.getSpellbook().getTeleportType());
				break;
			case 9:
				Lottery.enterLottery(player);
				break;
			case 10:
				player.setDialogueActionId(59);
				DialogueManager.start(player, 100);
				break;
			case 78:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers2 = FlowersData.generate7();
				final GameObject flower2 = new GameObject(flowers2.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower2);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower2, 90);
				player.setPositionToFace(flower2.getPosition());
				player.getClickDelay().reset();

				break;
			case 11:
				Scoreboards.open(player, Scoreboards.TOP_PKERS);
				break;
			case 12:
				TeleportHandler.teleportPlayer(player, new Position(2980 + Misc.getRandom(3), 3596 + Misc.getRandom(3)), player.getSpellbook().getTeleportType());
				break;
			case 79:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers = FlowersData.generate3();
				final GameObject flower = new GameObject(flowers.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower, 90);
				player.setPositionToFace(flower.getPosition());
				player.getClickDelay().reset();

				break;
			case 13:
				player.getPacketSender().sendInterfaceRemoval();
				for(AchievementData d : AchievementData.values()) {
					if(!player.getAchievementAttributes().getCompletion()[d.ordinal()]) {
						player.getPacketSender().sendMessage("You must have completed all achievements in order to buy this cape.");
						return;
					}
				}
				boolean usePouch = player.getMoneyInPouch() >= 100000000;
				if(!usePouch && player.getInventory().getAmount(995) < 100000000) {
					player.getPacketSender().sendMessage("You do not have enough coins.");
					return;
				}
				if(usePouch) {
					player.setMoneyInPouch(player.getMoneyInPouch() - 100000000);
					player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
				} else
					player.getInventory().delete(995, 100000000);
				player.getInventory().add(14022, 1);
				player.getPacketSender().sendMessage("You've purchased an Completionist cape.");
				break;
			case 14:
				TeleportHandler.teleportPlayer(player, new Position(3184 + Misc.getRandom(1), 5471 + Misc.getRandom(1)), player.getSpellbook().getTeleportType());
				break;
			case 15:
				TeleportHandler.teleportPlayer(player, new Position(2663 + Misc.getRandom(1), 2651 + Misc.getRandom(1)), player.getSpellbook().getTeleportType());
				break;
			case 17:
				if(player.getBankPinAttributes().hasBankPin()) {
					DialogueManager.start(player, 12);
					player.setDialogueActionId(8);
				} else {
					BankPin.init(player, false);
				}
				break;
			case 29:
				SlayerMaster.changeSlayerMaster(player, SlayerMaster.DURADEL);
				break;
			case 36:
				TeleportHandler.teleportPlayer(player, new Position(1908, 4367), player.getSpellbook().getTeleportType());
				break;
			case 38:
				DialogueManager.start(player, 70);
				player.setDialogueActionId(39);
				break;
			case 40:
				TeleportHandler.teleportPlayer(player, new Position(2839, 9557), player.getSpellbook().getTeleportType());
				break;
			case 41:
				TeleportHandler.teleportPlayer(player, new Position(2903, 5203), player.getSpellbook().getTeleportType());	
			break;
			case 48:
				JewelryTeleporting.teleport(player, new Position(3213, 3423));
				break;
			case 59:
				if(player.getClickDelay().elapsed(1500)) {
					PkSets.buySet(player, PkSets.MELEE_MAIN_SET);
				}
				break;
			case 60:
				player.setDialogueActionId(62);
				DialogueManager.start(player, 102);
				break;
			case 68:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
					if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner().getUsername().equals(player.getUsername())) {
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().setComplexity(2);
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().sendMessage("The party leader has changed complexity.");
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().refreshInterface();
					}
				}
				break;
			}
		} else if(id == THIRD_OPTION_OF_FIVE) {
			switch(player.getDialogueActionId()) {
			case 0:
				TeleportHandler.teleportPlayer(player, new Position(3204 + (Misc.getRandom(2)), 3263 + Misc.getRandom(2)), player.getSpellbook().getTeleportType());
				break;
			case 1:
				TeleportHandler.teleportPlayer(player, new Position(2480, 5175), player.getSpellbook().getTeleportType());
				break;
			case 2:
				TeleportHandler.teleportPlayer(player, new Position(2793, 2773), player.getSpellbook().getTeleportType());
				break;
			case 9:
				DialogueManager.start(player, Lottery.Dialogues.getCurrentPot(player));
				break;
			case 10:
				DialogueManager.start(player, Mandrith.getDialogue(player));
				break;
				
			case 78:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers2 = FlowersData.generate8();
				final GameObject flower2 = new GameObject(flowers2.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower2);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower2, 90);
				player.setPositionToFace(flower2.getPosition());
				player.getClickDelay().reset();

				break;
			case 79:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers = FlowersData.generate4();
				final GameObject flower = new GameObject(flowers.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower, 90);
				player.setPositionToFace(flower.getPosition());
				player.getClickDelay().reset();

				break;
			case 11:
				Scoreboards.open(player, Scoreboards.TOP_TOTAL_EXP);
				break;
			case 12:
				TeleportHandler.teleportPlayer(player, new Position(3239 + Misc.getRandom(2), 3619 + Misc.getRandom(2)), player.getSpellbook().getTeleportType());
				break;
			case 13:
				player.getPacketSender().sendInterfaceRemoval();
				
				
				player.getPacketSender().sendMessage("Speak To Morytania to obtain this rank..");
				//DialogueManager.start(player, 122);
				//player.setDialogueActionId(76);
				break;
			case 14:
				TeleportHandler.teleportPlayer(player, new Position(2713, 9564), player.getSpellbook().getTeleportType());
				break;
			case 15:
				TeleportHandler.teleportPlayer(player, new Position(3368 + Misc.getRandom(5), 3267+ Misc.getRandom(3)), player.getSpellbook().getTeleportType());
				player.getPacketSender().sendMessage("Don't Lose all of your items, Goodluck!");
				break;
			case 17:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getBankPinAttributes().hasBankPin()) {
					player.getPacketSender().sendMessage("Please visit the nearest bank and enter your pin before doing this.");
					return;
				}
				if(player.getSummoning().getFamiliar() != null) {
					player.getPacketSender().sendMessage("Please dismiss your familiar first.");
					return;
				}
				if(player.getGameMode() == GameMode.NORMAL) {
					DialogueManager.start(player, 83);
				} else {
					player.setDialogueActionId(46);
					DialogueManager.start(player, 84);
				}
				break;
			case 29:
				SlayerMaster.changeSlayerMaster(player, SlayerMaster.KURADEL);
				break;
			case 36:
				player.setDialogueActionId(37);
				DialogueManager.start(player, 70);
				break;
			case 38:
				TeleportHandler.teleportPlayer(player, new Position(2547, 9448), player.getSpellbook().getTeleportType());
				break;
			case 40:
				TeleportHandler.teleportPlayer(player, new Position(2891, 4767), player.getSpellbook().getTeleportType());
				break;
			case 41:
				TeleportHandler.teleportPlayer(player, new Position(3236, 3941), player.getSpellbook().getTeleportType());
				break;
				//scorp
			case 48:
				JewelryTeleporting.teleport(player, new Position(3368, 3267));
				break;
			case 59:
				if(player.getClickDelay().elapsed(1500)) {
					PkSets.buySet(player, PkSets.RANGE_MAIN_SET);
				}
				break;
			case 60:
				player.setDialogueActionId(63);
				DialogueManager.start(player, 102);
				break;
			case 68:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
					if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner().getUsername().equals(player.getUsername())) {
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().setComplexity(3);
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().sendMessage("The party leader has changed complexity.");
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().refreshInterface();
					}
				}
				break;
			}
		} else if(id == FOURTH_OPTION_OF_FIVE) {
			switch(player.getDialogueActionId()) {
			case 0:
				TeleportHandler.teleportPlayer(player, new Position(3173 - (Misc.getRandom(2)), 2981 + Misc.getRandom(2)), player.getSpellbook().getTeleportType());
				break;
			case 1:
				TeleportHandler.teleportPlayer(player, new Position(3279, 2964), player.getSpellbook().getTeleportType());
				break;
			case 2:
				TeleportHandler.teleportPlayer(player, new Position(3085, 9672), player.getSpellbook().getTeleportType());
				break;
			case 9:
				DialogueManager.start(player, Lottery.Dialogues.getLastWinner(player));
				break;
			case 10:
				ShopManager.getShops().get(26).open(player);
				break;
			case 11:
				Scoreboards.open(player, Scoreboards.TOP_ACHIEVER);
				break;
			case 12:
				TeleportHandler.teleportPlayer(player, new Position(3329 + Misc.getRandom(2), 3660 + Misc.getRandom(2), 0), player.getSpellbook().getTeleportType());
				break;
			case 78:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers2 = FlowersData.generate9();
				final GameObject flower2 = new GameObject(flowers2.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower2);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower2, 90);
				player.setPositionToFace(flower2.getPosition());
				player.getClickDelay().reset();

				break;
			case 79:
				if(!player.getClickDelay().elapsed(2000))
					return;
				for(NPC npc : player.getLocalNpcs()) {
					if(npc != null && npc.getPosition().equals(player.getPosition())) {
						player.getPacketSender().sendMessage("You cannot plant a seed right here.");
						return;
					}
				}
				if(CustomObjects.objectExists(player.getPosition().copy())) {
					player.getPacketSender().sendMessage("You cannot plant a seed right here.");
					return;
				}
				FlowersData flowers = FlowersData.generate5();
				final GameObject flower = new GameObject(flowers.objectId, player.getPosition().copy());
				player.getMovementQueue().reset();
				player.getInventory().delete(299, 1);
				player.performAnimation(new Animation(827));
				player.getPacketSender().sendMessage("You plant the seed..");
				player.getMovementQueue().reset();
				player.setDialogueActionId(42);
				player.setInteractingObject(flower);
				DialogueManager.start(player, 78);
				MovementQueue.stepAway(player);
				CustomObjects.globalObjectRemovalTask(flower, 90);
				player.setPositionToFace(flower.getPosition());
				player.getClickDelay().reset();

				break;
			case 13:
				player.getPacketSender().sendInterfaceRemoval();
				if(!player.getUnlockedLoyaltyTitles()[LoyaltyProgramme.LoyaltyTitles.MAXED.ordinal()]) {
					player.getPacketSender().sendMessage("You must have unlocked the 'Maxed' Loyalty Title in order to buy this cape.");
					return;
				}
				boolean usePouch = player.getMoneyInPouch() >= 50000000;
				if(!usePouch && player.getInventory().getAmount(995) < 50000000) {
					player.getPacketSender().sendMessage("You do not have enough coins.");
					return;
				}
				if(usePouch) {
					player.setMoneyInPouch(player.getMoneyInPouch() - 50000000);
					player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
				} else
					player.getInventory().delete(995, 50000000);
				player.getInventory().add(14019, 1);
				player.getPacketSender().sendMessage("You've purchased a Max cape.");
				break;
			case 14:
				TeleportHandler.teleportPlayer(player, new Position(2884, 9797), player.getSpellbook().getTeleportType());
				break;
			case 15:
				TeleportHandler.teleportPlayer(player, new Position(3565, 3313), player.getSpellbook().getTeleportType());
				break;
			case 17:
				player.setInputHandling(new ChangePassword());
				player.getPacketSender().sendEnterInputPrompt("Enter a new password:");
				break;
			case 29:
				SlayerMaster.changeSlayerMaster(player, SlayerMaster.SUMONA);
				break;
			case 36:
				TeleportHandler.teleportPlayer(player, new Position(2717, 9805), player.getSpellbook().getTeleportType());
				break;
			case 38:
				TeleportHandler.teleportPlayer(player, new Position(1891, 3177), player.getSpellbook().getTeleportType());
				break;
			case 40:
				TeleportHandler.teleportPlayer(player, new Position(3050, 9573), player.getSpellbook().getTeleportType());
				break;
			case 41:
				TeleportHandler.teleportPlayer(player, new Position(3350, 3734), player.getSpellbook().getTeleportType());
				break;
			case 48:
				JewelryTeleporting.teleport(player, new Position(2447, 5169));
				break;
			case 59:
				if(player.getClickDelay().elapsed(1500)) {
					PkSets.buySet(player, PkSets.MAGIC_MAIN_SET);
				}
				break;
			case 60:
				player.setDialogueActionId(64);
				DialogueManager.start(player, 102);
				break;
			case 68:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
					if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner().getUsername().equals(player.getUsername())) {
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().setComplexity(4);
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().sendMessage("The party leader has changed complexity.");
						player.getMinigameAttributes().getDungeoneeringAttributes().getParty().refreshInterface();
					}
				}
				break;
			}
		} else if(id == FIFTH_OPTION_OF_FIVE) {
			switch(player.getDialogueActionId()) {
			case 0:
				player.setDialogueActionId(1);
				DialogueManager.next(player);
				break;
			case 1:
				player.setDialogueActionId(2);
				DialogueManager.next(player);
				break;
			case 2:
				player.setDialogueActionId(0);
				DialogueManager.start(player, 0);
				break;
			case 9:
			case 10:
			case 11:
			case 13:
			case 17:
			case 29:
			case 48:
			case 60:
			case 67:
			case 68:
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 12:
				int random = Misc.getRandom(4);
				switch(random) {
				case 0:
					TeleportHandler.teleportPlayer(player, new Position(3035, 3701, 0), player.getSpellbook().getTeleportType());
					break;
				case 1:
					TeleportHandler.teleportPlayer(player, new Position(3036, 3694, 0), player.getSpellbook().getTeleportType());
					break;
				case 2:
					TeleportHandler.teleportPlayer(player, new Position(3045, 3697, 0), player.getSpellbook().getTeleportType());
					break;
				case 3:
					TeleportHandler.teleportPlayer(player, new Position(3043, 3691, 0), player.getSpellbook().getTeleportType());
					break;
				case 4:
					TeleportHandler.teleportPlayer(player, new Position(3037, 3687, 0), player.getSpellbook().getTeleportType());
					break;
				}
				break;
			case 14:
				DialogueManager.start(player, 23);
				player.setDialogueActionId(14);
				break;
			case 79:
				DialogueManager.start(player, 1);
				player.setDialogueActionId(78);
				break;
                        case 78:
				DialogueManager.start(player, 2);
				player.setDialogueActionId(77);
				break;
                        case 77:
				DialogueManager.start(player, 0);
				player.setDialogueActionId(79);
				break;
			case 15:
				DialogueManager.start(player, 32);
				player.setDialogueActionId(18);
				break;
			case 36:
				DialogueManager.start(player, 66);
				player.setDialogueActionId(38);
				break;
			case 38:
				DialogueManager.start(player, 68);
				player.setDialogueActionId(40);
				break;
			case 40:
				DialogueManager.start(player, 69);
				player.setDialogueActionId(41);
				break;
			case 41:
				DialogueManager.start(player, 65);
				player.setDialogueActionId(36);
				break;
			case 59:
				if(player.getClickDelay().elapsed(1500)) {
					PkSets.buySet(player, PkSets.HYBRIDING_MAIN_SET);
				}
				break;
			}
		} else if(id == FIRST_OPTION_OF_FOUR) {
			switch(player.getDialogueActionId()) {
			case 101:
				player.getPlayerOwnedShopManager().open();
				break;
			case 5:
				player.getPacketSender().sendInterfaceRemoval();
//				MySQLController.getStore().claim(player);
				break;
			case 8:
				ShopManager.getShops().get(27).open(player);
				break;
			case 9:
				TeleportHandler.teleportPlayer(player, new Position(2440, 3090), player.getSpellbook().getTeleportType());
				break;
			case 13:
				player.getPacketSender().sendInterfaceRemoval();
				for(AchievementData d : AchievementData.values()) {
					if(!player.getAchievementAttributes().getCompletion()[d.ordinal()]) {
						player.getPacketSender().sendMessage("You must have completed all achievements in order to buy this cape.");
						return;
					}
				}
				boolean usePouch = player.getMoneyInPouch() >= 100000000;
				if(!usePouch && player.getInventory().getAmount(995) < 100000000) {
					player.getPacketSender().sendMessage("You do not have enough coins.");
					return;
				}
				if(usePouch) {
					player.setMoneyInPouch(player.getMoneyInPouch() - 100000000);
					player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
				} else
					player.getInventory().delete(995, 100000000);
				player.getInventory().add(14022, 1);
				player.getPacketSender().sendMessage("You've purchased an Completionist cape.");
				break;
			case 14:
				TeleportHandler.teleportPlayer(player, new Position(2871, 5318, 2), player.getSpellbook().getTeleportType());
				break;
			case 18:
				TeleportHandler.teleportPlayer(player, new Position(2439 + Misc.getRandom(2), 5171 + Misc.getRandom(2)), player.getSpellbook().getTeleportType());
				break;
			case 26:
				TeleportHandler.teleportPlayer(player, new Position(2480, 3435), player.getSpellbook().getTeleportType());
				break;
			case 27:
				ClanChatManager.createClan(player);
				break;
			case 28:
				player.setDialogueActionId(29);
				DialogueManager.start(player, 62);
				break;
			case 30:
				player.getSlayer().assignTask();
				break;
			case 31:
				DialogueManager.start(player, SlayerDialogues.findAssignment(player));
				break;
			case 41:
				DialogueManager.start(player, 76);
				break;
			case 45:
				GameMode.set(player, GameMode.NORMAL, false);
				break;
			}
		} else if(id == SECOND_OPTION_OF_FOUR) {
			switch(player.getDialogueActionId()) {
			case 101:
				player.getPlayerOwnedShopManager().openEditor();
				break;
			case 5:
				DialogueManager.start(player, MemberScrolls.getTotalFunds(player));
				break;
			case 8:
				LoyaltyProgramme.open(player);
				break;
			case 9:
				DialogueManager.start(player, 14);
				break;
			case 13:
				player.getPacketSender().sendInterfaceRemoval();
				
				
				player.getPacketSender().sendMessage("Speak To Morytania to obtain this rank..");
				//DialogueManager.start(player, 122);
				//player.setDialogueActionId(76);
				break;
			case 14:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getSkillManager().getCurrentLevel(Skill.SLAYER) < 50) {
					player.getPacketSender().sendMessage("You need a Slayer level of at least 50 to visit this dungeon.");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(2731, 5095), player.getSpellbook().getTeleportType());
				break;
			case 18:
				TeleportHandler.teleportPlayer(player, new Position(2399, 5177), player.getSpellbook().getTeleportType());
				break;
			case 26:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getSkillManager().getMaxLevel(Skill.AGILITY) < 35) {
					player.getPacketSender().sendMessage("You need an Agility level of at least level 35 to use this course.");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(2552, 3556), player.getSpellbook().getTeleportType());
				break;
			case 27:
				ClanChatManager.clanChatSetupInterface(player, true);
				break;
			case 28:
				if(player.getSlayer().getSlayerMaster().getPosition() != null) {
					TeleportHandler.teleportPlayer(player, new Position(player.getSlayer().getSlayerMaster().getPosition().getX(), player.getSlayer().getSlayerMaster().getPosition().getY(), player.getSlayer().getSlayerMaster().getPosition().getZ()), player.getSpellbook().getTeleportType());
					if(player.getSkillManager().getCurrentLevel(Skill.SLAYER) <= 50)
						player.getPacketSender().sendMessage("").sendMessage("You can train Slayer with a friend by using a Slayer gem on them.").sendMessage("Slayer gems can be bought from all Slayer masters.");;
				}
				break;
			case 31:
				DialogueManager.start(player, SlayerDialogues.resetTaskDialogue(player));
				break;
			case 41:
				WellOfGoodwill.lookDownWell(player);
				break;
			case 45:
				GameMode.set(player, GameMode.IRONMAN, false);
				break;
			}
		} else if(id == THIRD_OPTION_OF_FOUR) {
			switch(player.getDialogueActionId()) {
			case 101:
				player.getPlayerOwnedShopManager().claimEarnings();
				break;
			case 5:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getRights() == PlayerRights.PLAYER) {
					player.getPacketSender().sendMessage("You need to be a member to teleport to this zone.").sendMessage("To become a member, visit www.Morytania.com and purchase a scroll.");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(3424, 2919), player.getSpellbook().getTeleportType());
				break;
			case 8:
				LoyaltyProgramme.reset(player);
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 9:
				ShopManager.getShops().get(41).open(player);
				break;
			case 14:
				TeleportHandler.teleportPlayer(player, new Position(1745, 5325), player.getSpellbook().getTeleportType());
				break;
			case 13:
				player.getPacketSender().sendInterfaceRemoval();
				if(!player.getUnlockedLoyaltyTitles()[LoyaltyProgramme.LoyaltyTitles.MAXED.ordinal()]) {
					player.getPacketSender().sendMessage("You must have unlocked the 'Maxed' Loyalty Title in order to buy this cape.");
					return;
				}
				boolean usePouch = player.getMoneyInPouch() >= 50000000;
				if(!usePouch && player.getInventory().getAmount(995) < 50000000) {
					player.getPacketSender().sendMessage("You do not have enough coins.");
					return;
				}
				if(usePouch) {
					player.setMoneyInPouch(player.getMoneyInPouch() - 50000000);
					player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
				} else
					player.getInventory().delete(995, 50000000);
				player.getInventory().add(14019, 1);
				player.getPacketSender().sendMessage("You've purchased a Max cape.");
				break;
			case 18:
				TeleportHandler.teleportPlayer(player, new Position(3503, 3562), player.getSpellbook().getTeleportType());
				break;
			case 26:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getSkillManager().getMaxLevel(Skill.AGILITY) < 55) {
					player.getPacketSender().sendMessage("You need an Agility level of at least level 55 to use this course.");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(2998, 3914), player.getSpellbook().getTeleportType());
				break;
			case 27:
				ClanChatManager.deleteClan(player);
				break;
			case 28:
				TeleportHandler.teleportPlayer(player, new Position(3427, 3537, 0), player.getSpellbook().getTeleportType());
				break;
			case 31:
				DialogueManager.start(player, SlayerDialogues.totalPointsReceived(player));
				break;
			case 41:
				player.setInputHandling(new DonateToWell());
				player.getPacketSender().sendInterfaceRemoval().sendEnterAmountPrompt("How much money would you like to contribute with?");
				break;
			case 45:
				GameMode.set(player, GameMode.HARDCORE_IRONMAN, false);
				break;
			}
		} else if(id == FOURTH_OPTION_OF_FOUR) {
			switch(player.getDialogueActionId()) {
			case 101:
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 5:
			case 8:
			case 9:
			case 13:
			case 26:
			case 27:
			case 28:
			case 41:
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 14:
				player.setDialogueActionId(14);
				DialogueManager.start(player, 22);
				break;
			case 18:
				DialogueManager.start(player, 25);
				player.setDialogueActionId(15);
				break;
			case 30:
			case 31:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getSlayer().getDuoPartner() != null) {
					Slayer.resetDuo(player, World.getPlayerByName(player.getSlayer().getDuoPartner()));
				}
				break;
			case 45:
				player.getPacketSender().sendString(1, "www.Morytania.com");
				break;
			}
		} else if(id == FIRST_OPTION_OF_TWO) {
			switch(player.getDialogueActionId()) {
			case 3:
				ShopManager.getShops().get(22).open(player);
				break;
			case 4:
				SummoningTab.handleDismiss(player, true);
				break;
			case 709:
				player.getPacketSender().sendInterfaceRemoval();
				Decanting.checkRequirements(player);
				break;
			case 7:
				BankPin.init(player, false);
				break;
			case 8:
				BankPin.deletePin(player);
				break;
			case 16:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getMinigameAttributes().getBarrowsMinigameAttributes().getKillcount() < 5) {
					player.getPacketSender().sendMessage("You must have a killcount of at least 5 to enter the tunnel.");
					return;
				}
				player.moveTo(new Position(3552, 9692));
				break;
			case 20:
				player.getPacketSender().sendInterfaceRemoval();
				DialogueManager.start(player, 39);
				player.getMinigameAttributes().getRecipeForDisasterAttributes().setPartFinished(0, true);
				PlayerPanel.refreshPanel(player);
				break;
			case 23:
				DialogueManager.start(player, 50);
				player.getMinigameAttributes().getNomadAttributes().setPartFinished(0, true);
				player.setDialogueActionId(24);
				PlayerPanel.refreshPanel(player);
				break;
			case 24:
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 33:
				if(player.getPointsHandler().getSlayerPoints() <= 0) {
				     player.getPacketSender().sendMessage("You need at least 5 points to reset your task!");
				     return;
							} else {
							player.getPacketSender().sendInterfaceRemoval();
							player.getSlayer().resetSlayerTask();
							}
							break;
			case 34:
				player.getPacketSender().sendInterfaceRemoval();
				player.getSlayer().handleInvitation(true);
				break;
			case 37:
				TeleportHandler.teleportPlayer(player, new Position(2961, 3882), player.getSpellbook().getTeleportType());
				break;
			case 39:
				TeleportHandler.teleportPlayer(player, new Position(3281, 3914), player.getSpellbook().getTeleportType());
				break;
			
			case 42:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getInteractingObject() != null && player.getInteractingObject().getDefinition() != null && player.getInteractingObject().getDefinition().getName().equalsIgnoreCase("flowers")) {
					if(CustomObjects.objectExists(player.getInteractingObject().getPosition())) {
						player.getInventory().add(FlowersData.forObject(player.getInteractingObject().getId()).itemId, 1);
						CustomObjects.deleteGlobalObject(player.getInteractingObject());
						player.setInteractingObject(null);
					}
				}
				break;
			case 44:
				player.getPacketSender().sendInterfaceRemoval();
				player.getMinigameAttributes().getGodwarsDungeonAttributes().setHasEnteredRoom(true);
				player.moveTo(new Position(2911, 5203));
				player.getPacketSender().sendMessage("You enter Nex's lair..");
				NpcAggression.target(player);
				break;
			case 46:
				player.getPacketSender().sendInterfaceRemoval();
				DialogueManager.start(player, 82);
				player.setPlayerLocked(true).setDialogueActionId(45);
				break;
			case 57:
				Graveyard.start(player);
				break;
			case 66:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getLocation() == Location.DUNGEONEERING && player.getMinigameAttributes().getDungeoneeringAttributes().getParty() == null) {
					if(player.getMinigameAttributes().getDungeoneeringAttributes().getPartyInvitation() != null) {
						player.getMinigameAttributes().getDungeoneeringAttributes().getPartyInvitation().add(player);
					}
				}
				player.getMinigameAttributes().getDungeoneeringAttributes().setPartyInvitation(null);
				break;
			case 71:
				if(player.getClickDelay().elapsed(1000)) {
					if(Dungeoneering.doingDungeoneering(player)) {
						Dungeoneering.leave(player, false, true);
						player.getClickDelay().reset();
					}
				}
				break;
			case 72:
				if(player.getClickDelay().elapsed(1000)) {
					if(Dungeoneering.doingDungeoneering(player)) {
						Dungeoneering.leave(player, false, player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner() == player ? false : true);
						player.getClickDelay().reset();
					}
				}
				break;
			case 73:
				player.getPacketSender().sendInterfaceRemoval();
				player.moveTo(new Position(3653, player.getPosition().getY()));
				break;
			case 74:
				player.getPacketSender().sendMessage("The ghost teleports you away.");
				player.getPacketSender().sendInterfaceRemoval();
				player.moveTo(new Position(3651, 3486));
				break;
			case 76:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getRights().isStaff()) {
					player.getPacketSender().sendMessage("You cannot change your rank.");
					return;
				}
				player.setRights(PlayerRights.YOUTUBER);
				player.getPacketSender().sendRights();
				break;
			case 78:
				player.getPacketSender().sendString(38006, "Select a skill...").sendString(38090, "Which skill would you like to prestige?");
				player.getPacketSender().sendInterface(38000);
				player.setUsableObject(new Object[2]).setUsableObject(0, "prestige");
				break;
			}
		} else if(id == SECOND_OPTION_OF_TWO) {
			switch(player.getDialogueActionId()) {
			case 3:
				ShopManager.getShops().get(23).open(player);
				break;
			case 4:
			case 16:
			case 20:
			case 23:
			case 33:
			case 37:
			case 39:
			case 42:
			case 44:
			case 46:
			case 57:
			case 71:
			case 72:
			case 73:
			case 74:
			case 76:
			case 78:
			case 709:
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 7:
			case 8:
				player.getPacketSender().sendInterfaceRemoval();
				player.getBank(player.getCurrentBankTab()).open();
				break;
			case 24:
				Nomad.startFight(player);
				break;
			case 34:
				player.getPacketSender().sendInterfaceRemoval();
				player.getSlayer().handleInvitation(false);
				break;
			case 66:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getMinigameAttributes().getDungeoneeringAttributes().getPartyInvitation() != null && player.getMinigameAttributes().getDungeoneeringAttributes().getPartyInvitation().getOwner() != null)
					player.getMinigameAttributes().getDungeoneeringAttributes().getPartyInvitation().getOwner().getPacketSender().sendMessage(""+player.getUsername()+" has declined your invitation.");
				player.getMinigameAttributes().getDungeoneeringAttributes().setPartyInvitation(null);
				break;
			}
		} else if(id == FIRST_OPTION_OF_THREE) {
			switch(player.getDialogueActionId()) {
			case 5:
				DialogueManager.start(player, MemberScrolls.getTotalFunds(player));
				break;
			case 15:
				DialogueManager.start(player, 35);
				player.setDialogueActionId(19);
				break;
			case 19:
				DialogueManager.start(player, 33);
				player.setDialogueActionId(21);
				break;
			case 21:
				TeleportHandler.teleportPlayer(player, new Position(3080, 3498), player.getSpellbook().getTeleportType());
				break;
			case 22:
				TeleportHandler.teleportPlayer(player, new Position(1891, 3177), player.getSpellbook().getTeleportType());
				break;
			case 25:
				TeleportHandler.teleportPlayer(player, new Position(2589, 4319), TeleportType.PURO_PURO);
				break;
			case 35:
				player.getPacketSender().sendEnterAmountPrompt("How many shards would you like to buy? (You can use K, M, B prefixes)");
				player.setInputHandling(new BuyShards());
				break;
			case 41:
				TeleportHandler.teleportPlayer(player, new Position(2884 + Misc.getRandom(1), 4374 + Misc.getRandom(1)), player.getSpellbook().getTeleportType());
				break;
			case 43:
				TeleportHandler.teleportPlayer(player,new Position(2773, 9195), player.getSpellbook().getTeleportType());
				break;
			case 47:
				TeleportHandler.teleportPlayer(player,new Position(2911, 4832), player.getSpellbook().getTeleportType());
				break;
			case 48: 
				if(player.getInteractingObject() != null) {
					Mining.startMining(player, new GameObject(24444, player.getInteractingObject().getPosition()));
				}
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 56:
				TeleportHandler.teleportPlayer(player, new Position(2717, 3499), player.getSpellbook().getTeleportType());
				break;
			case 58:
				DialogueManager.start(player, AgilityTicketExchange.getDialogue(player));
				break;
			case 61:
				CharmingImp.changeConfig(player, 0, 0);
				break;
			case 62:
				CharmingImp.changeConfig(player, 1, 0);
				break;
			case 63:
				CharmingImp.changeConfig(player, 2, 0);
				break;
			case 64:
				CharmingImp.changeConfig(player, 3, 0);
				break;
			case 65:
				player.getPacketSender().sendInterfaceRemoval();
				if (player.getSlayer().getDuoPartner() != null) {
					player.getPacketSender().sendMessage(
							"You already have a duo partner.");
					return;
				}
				player.getPacketSender().sendMessage("<img=10> To do Social slayer, simply use your Slayer gem on another player.");
				break;
			case 69:
				ShopManager.getShops().get(44).open(player);
				player.getPacketSender().sendMessage("<img=10> <col=660000>You currently have "+player.getPointsHandler().getDungeoneeringTokens()+" Dungeoneering tokens.");
				break;
			case 70:			
			case 71:
				if(player.getInventory().contains(19670) && player.getClickDelay().elapsed(700)) {
					final int amt = player.getDialogueActionId() == 70 ? 1 : player.getInventory().getAmount(19670);
					player.getPacketSender().sendInterfaceRemoval();
					player.getInventory().delete(19670, amt);
					player.getPacketSender().sendMessage("You claim the "+(amt > 1 ? "scrolls" : "scroll")+" and receive your reward.");
					int minutes = player.getGameMode() == GameMode.NORMAL ? 10 : 5;
					BonusExperienceTask.addBonusXp(player, minutes * amt);
					player.getClickDelay().reset();
				}
				break;
			}
		} else if(id == SECOND_OPTION_OF_THREE) {
			switch(player.getDialogueActionId()) {
			case 15:
				DialogueManager.start(player, 25);
				player.setDialogueActionId(15);
				break;
			case 5:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getRights() == PlayerRights.PLAYER) {
					player.getPacketSender().sendMessage("You need to be a member to teleport to this zone.").sendMessage("To become a member, visit www.Morytania.com and purchase a scroll.");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(3424, 2919), player.getSpellbook().getTeleportType());
				break;
			case 21:
				RecipeForDisaster.openQuestLog(player);
				break;
			case 19:
				DialogueManager.start(player, 33);
				player.setDialogueActionId(22);
				break;
			case 22:
				Nomad.openQuestLog(player);
				break;
			case 25:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getSkillManager().getCurrentLevel(Skill.HUNTER) < 23) {
					player.getPacketSender().sendMessage("You need a Hunter level of at least 23 to visit the hunting area.");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(2922, 2885), player.getSpellbook().getTeleportType());
				break;
			case 35:
				player.getPacketSender().sendEnterAmountPrompt("How many shards would you like to sell? (You can use K, M, B prefixes)");
				player.setInputHandling(new SellShards());
				break;
			case 41:
				TeleportHandler.teleportPlayer(player, new Position(2903, 5204), player.getSpellbook().getTeleportType());
				break;
			case 43:
				TeleportHandler.teleportPlayer(player,new Position(2577, 9881), player.getSpellbook().getTeleportType());
				break;
			case 47:
				TeleportHandler.teleportPlayer(player, new Position(3023, 9740), player.getSpellbook().getTeleportType());
				break;
			case 48: 
				if(player.getInteractingObject() != null) {
					Mining.startMining(player, new GameObject(24445, player.getInteractingObject().getPosition()));
				}
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 56:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getSkillManager().getCurrentLevel(Skill.WOODCUTTING) < 60) {
					player.getPacketSender().sendMessage("You need a Woodcutting level of at least 60 to teleport there.");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(2711, 3463), player.getSpellbook().getTeleportType());
				break;
			case 58:
				ShopManager.getShops().get(39).open(player);
				break;
			case 61:
				CharmingImp.changeConfig(player, 0, 1);
				break;
			case 62:
				CharmingImp.changeConfig(player, 1, 1);
				break;
			case 63:
				CharmingImp.changeConfig(player, 2, 1);
				break;
			case 64:
				CharmingImp.changeConfig(player, 3, 1);
				break;
			case 65:
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getSlayer().getDuoPartner() != null) {
					Slayer.resetDuo(player, World.getPlayerByName(player.getSlayer().getDuoPartner()));
				}
				break;
			case 69:
				if(player.getClickDelay().elapsed(1000)) {
					Dungeoneering.start(player);
				}
				break;
			case 70:
			case 71:
				final boolean all = player.getDialogueActionId() == 71;
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getInventory().getFreeSlots() == 0) {
					player.getPacketSender().sendMessage("You do not have enough free inventory space to do that.");
					return;
				}
				if(player.getInventory().contains(19670) && player.getClickDelay().elapsed(700)) {
					int amt = !all ? 1 : player.getInventory().getAmount(19670);
					player.getInventory().delete(19670, amt);
					player.getPointsHandler().incrementVotingPoints(amt);
					player.getPointsHandler().refreshPanel();
					if(player.getGameMode() == GameMode.NORMAL) {
						player.getInventory().add(995, 1000000 * amt);
					} else {
						player.getInventory().add(995, 150000 * amt);
					}
					player.getPacketSender().sendMessage("You claim the "+(amt > 1 ? "scrolls" : "scroll")+" and receive your reward.");
					player.getClickDelay().reset();
				}
				break;
			}
		} else if(id == THIRD_OPTION_OF_THREE) {
			switch(player.getDialogueActionId()) {
			case 5:
			case 10:
			case 15:
			case 19:
			case 21:
			case 22:
			case 25:
			case 35:
			case 47:
			case 48:
			case 56:
			case 58:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65:
			case 69:
			case 70:
			case 71:
			case 77:
				player.getPacketSender().sendInterfaceRemoval();
				break;
			case 43: 
				DialogueManager.start(player, 65);
				player.setDialogueActionId(36);
				break;
			case 41:
				player.setDialogueActionId(36);
				DialogueManager.start(player, 65);
				break;
			}
		}
	}

	public static int FIRST_OPTION_OF_FIVE = 2494;
	public static int SECOND_OPTION_OF_FIVE = 2495;
	public static int THIRD_OPTION_OF_FIVE = 2496;
	public static int FOURTH_OPTION_OF_FIVE = 2497;
	public static int FIFTH_OPTION_OF_FIVE = 2498;

	public static int FIRST_OPTION_OF_FOUR = 2482;
	public static int SECOND_OPTION_OF_FOUR = 2483;
	public static int THIRD_OPTION_OF_FOUR = 2484;
	public static int FOURTH_OPTION_OF_FOUR = 2485;


	public static int FIRST_OPTION_OF_THREE = 2471;
	public static int SECOND_OPTION_OF_THREE = 2472;
	public static int THIRD_OPTION_OF_THREE = 2473;

	public static int FIRST_OPTION_OF_TWO = 2461;
	public static int SECOND_OPTION_OF_TWO = 2462;

}
