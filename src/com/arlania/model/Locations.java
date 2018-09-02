package com.arlania.model;

import com.arlania.GameSettings;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.PlayerPunishment.Jail;
import com.arlania.world.content.combat.CombatFactory;
import com.arlania.world.content.combat.pvp.BountyHunter;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.droppreview.AVATAR;
import com.arlania.world.content.droppreview.BARRELS;
import com.arlania.world.content.droppreview.BORKS;
import com.arlania.world.content.droppreview.CERB;
import com.arlania.world.content.droppreview.CORP;
import com.arlania.world.content.droppreview.DAGS;
import com.arlania.world.content.droppreview.GLAC;
import com.arlania.world.content.droppreview.GWD;
import com.arlania.world.content.droppreview.KALPH;
import com.arlania.world.content.droppreview.KBD;
import com.arlania.world.content.droppreview.LIZARD;
import com.arlania.world.content.droppreview.NEXX;
import com.arlania.world.content.droppreview.PHEON;
import com.arlania.world.content.droppreview.SKOT;
import com.arlania.world.content.droppreview.SLASHBASH;
import com.arlania.world.content.droppreview.TDS;
import com.arlania.world.content.minigames.impl.Barrows;
import com.arlania.world.content.minigames.impl.FightCave;
import com.arlania.world.content.minigames.impl.FightPit;
import com.arlania.world.content.minigames.impl.FreeForAll;
import com.arlania.world.content.minigames.impl.Graveyard;
import com.arlania.world.content.minigames.impl.Nomad;
import com.arlania.world.content.minigames.impl.PestControl;
import com.arlania.world.content.minigames.impl.RecipeForDisaster;
import com.arlania.world.content.minigames.impl.WarriorsGuild;
import com.arlania.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.arlania.world.entity.Entity;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class Locations {

	public static void login(Player player) {
		player.setLocation(Location.getLocation(player));
		player.getLocation().login(player);
		player.getLocation().enter (player);
	}

	
	public static void logout(Player player) {
		if (player.inFFA)
			FreeForAll.leaveGame(player);
		player.getLocation().logout(player);
		if(player.getRegionInstance() != null)
			player.getRegionInstance().destruct();
		if(player.getLocation() != Location.GODWARS_DUNGEON) {
			player.getLocation().leave(player);
		}
	}

	public static int PLAYERS_IN_WILD;
	public static int PLAYERS_IN_DUEL_ARENA;

	public enum Location {
		DUNGEONEERING(new int[]{3433, 3459, 2421, 2499}, new int[]{3694, 3729, 4915, 4990}, true, false, true, false, true, false) {
			@Override
			public void login(Player player) {
				player.getPacketSender().sendDungeoneeringTabIcon(true).sendTabInterface(GameSettings.QUESTS_TAB, 26600).sendTab(GameSettings.QUESTS_TAB);
			}
			
			@Override
			public void logout(Player player) {
				if(Dungeoneering.doingDungeoneering(player)) {
				player.getInventory().resetItems();
				player.getEquipment().resetItems();
				player.moveTo(GameSettings.DEFAULT_POSITION.copy());
				}
			}

			@Override
			public void leave(Player player) {
				Dungeoneering.leave(player, true, true);
			}

			@Override
			public void enter(Player player) {
				player.getPacketSender().sendDungeoneeringTabIcon(true).sendTabInterface(GameSettings.QUESTS_TAB, 27224).sendTab(GameSettings.QUESTS_TAB);
				DialogueManager.start(player, 104);
			}

			@Override
			public void onDeath(Player player) {
				if(Dungeoneering.doingDungeoneering(player)) {
					Dungeoneering.handlePlayerDeath(player);
				}
			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC npc) {
				if(Dungeoneering.doingDungeoneering(killer)) {
					Dungeoneering.handleNpcDeath(killer, npc);
					return true;
				}
				return false;
			}

			@Override
			public void process(Player player) {
				if(Dungeoneering.doingDungeoneering(player)) {
					
					
				
				} else {
					player.sendParallellInterfaceVisibility(37500, false);
				}
			}
		},
		MEMBER_ZONE(new int[]{3415, 3435}, new int[]{2900, 2926}, false, true, true, false, false, true) {
		},
		DONATOR_ZONE(new int[]{2290, 2400}, new int[]{9750, 9999}, false, true, true, false, false, true) {
		},
		VARROCK(new int[]{3167, 3272}, new int[]{3376, 3504}, false, true, true, true, true, true) {
		},
		GAMBLING(new int[]{2835, 2855}, new int[]{5130, 5170}, false, true, true, true, true, true) {
		},
		BANK(new int[]{3090, 3099, 3089, 3090, 3248, 3258, 3179, 3191, 2944, 2948, 2942, 2948, 2944, 2950, 3008, 3019, 3017, 3022, 3203, 3213, 3212, 3215, 3215, 3220, 3220, 3227, 3227, 3230, 3226, 3228, 3227, 3229}, new int[]{3487, 3500, 3492, 3498, 3413, 3428, 3432, 3448, 3365, 3374, 3367, 3374, 3365, 3370, 3352, 3359, 3352, 3357, 3200, 3237, 3200, 3235, 3202, 3235, 3202, 3229, 3208, 3226, 3230, 3211, 3208, 3226}, false, true, true, false, false, true) {
		},
		EDGEVILLE(new int[]{3073, 3134}, new int[]{3457, 3518}, false, true, true, false, false, true) {
		},
		LUMBRIDGE(new int[]{3175, 3238}, new int[]{3179, 3302}, false, true, true, true, true, true) {
		},
		KING_BLACK_DRAGON(new int[]{2251, 2292}, new int[]{4673, 4717}, true, true, true, true, true, true) {
		
			/*@Override
			public void enter(Player player) {
				
				KBD.startPreview(player);
			}			
			@Override
			public void leave(Player player) {
				KBD.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				KBD.closeInterface(player);
			}*/
		},
		UNHOLY_CURSEBEARER(new int[]{3047, 3070}, new int[]{4390, 4370}, true, true, true, true, true, true) {
		},
		BORK(new int[]{3080, 3120}, new int[]{5520, 5550}, true, true, true, true, true, true) {
		
			/*@Override
			public void enter(Player player) {
				
				BORKS.startPreview(player);
			}		
			@Override
			public void leave(Player player) {
				BORKS.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				BORKS.closeInterface(player);
			}*/
		},
		LIZARDMAN(new int[]{2700, 2730}, new int[]{9800, 9829}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
				LIZARD.startPreview(player);
			}			
			@Override
			public void leave(Player player) {
				LIZARD.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				LIZARD.closeInterface(player);
			}*/
		},
		BARRELCHESTS(new int[]{2960, 2990}, new int[]{9510, 9520}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
				BARRELS.startPreview(player);
			}			
			@Override
			public void leave(Player player) {
				BARRELS.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				BARRELS.closeInterface(player);
			}*/
		},
		SLASH_BASH(new int[]{2504, 2561}, new int[]{9401, 9473}, true, true, true, true, true, true) {

			/*@Override
			public void enter(Player player) {
				
				SLASHBASH.startPreview(player);
			}			
			@Override
			public void leave(Player player) {
				SLASHBASH.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				SLASHBASH.closeInterface(player);
			}*/
		},
		BANDOS_AVATAR(new int[]{2877, 2928}, new int[]{4734, 4787}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
				AVATAR.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				AVATAR.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				AVATAR.closeInterface(player);
			}*/
		},
		TORM_DEMONS(new int[]{2520, 2560}, new int[]{5730, 5799}, true, true, true, true, true, true) {
		
			/*@Override
			public void enter(Player player) {
				
			TDS.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				TDS.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				TDS.closeInterface(player);
			}*/
		
		},
		KALPHITE_QUEEN(new int[]{3464, 3500}, new int[]{9478, 9523}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
			KALPH.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				KALPH.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				KALPH.closeInterface(player);
			}*/
		
		},
		PHOENIX(new int[]{2824, 2862}, new int[]{9545, 9594}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
			PHEON.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				PHEON.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				PHEON.closeInterface(player);
			}*/
		
		},
		GLACORS(new int[]{3000, 3100}, new int[]{9500, 9600}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
			GLAC.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				GLAC.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				GLAC.closeInterface(player);
			}*/
		
		},
		SKOTIZO(new int[]{3350, 3390}, new int[]{9800, 9825}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
			SKOT.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				SKOT.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				SKOT.closeInterface(player);
			}*/
		
		},
		CERBERUS(new int[]{1215, 1265}, new int[]{1220, 1265}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
			CERB.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				CERB.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				CERB.closeInterface(player);
			}*/
		
		},
		NEX(new int[]{2900, 2945}, new int[]{5180, 5220}, true, true, true, true, true, true) {
			/*@Override
			public void enter(Player player) {
				
			NEXX.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				NEXX.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				NEXX.closeInterface(player);
			}*/
		
		},
		BANDIT_CAMP(new int[]{3020, 3150, 3055, 3195}, new int[]{3684, 3711, 2958, 3003}, true, true, true, true, true, true) {
		},
		ROCK_CRABS(new int[]{2689, 2727}, new int[]{3691, 3730}, true, true, true, true, true, true) {
		},
		ARMOURED_ZOMBIES(new int[]{3077, 3132}, new int[]{9657, 9680}, true, true, true, true, true, true) {
		},
		CORPOREAL_BEAST(new int[]{2879, 2964}, new int[]{4368, 4409}, true, true, true, false, true, true) {
			/*@Override
			public void enter(Player player) {
				
			CORP.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				CORP.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				CORP.closeInterface(player);
			}*/
		
		},
		DAGANNOTH_DUNGEON(new int[]{2886, 2938}, new int[]{4431, 4477}, true, true, true, false, true, true) {
			/*@Override
			public void enter(Player player) {
				
				DAGS.startPreview(player);
			
			}			
			@Override
			public void leave(Player player) {
				DAGS.closeInterface(player);
			}			
			@Override
			public void onDeath(Player player) {
				DAGS.closeInterface(player);
			}*/
		},
		ZULRAH_CLOUD_ONE(new int[]{2262, 2264}, new int[]{3074, 3076}, true, true, true, true, true, true) {
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
		},
		ZULRAH_CLOUD_TWO(new int[]{2262, 2264}, new int[]{3072, 3074}, true, true, true, true, true, true) {
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
		},
		ZULRAH_CLOUD_THREE(new int[]{2262, 2264}, new int[]{3069, 3071}, true, true, true, true, true, true) {
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
		},
		ZULRAH_CLOUD_FOUR(new int[]{2265, 2267}, new int[]{3068, 3070}, true, true, true, true, true, true) {
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
		},
		ZULRAH_CLOUD_FIVE(new int[]{2268, 2270}, new int[]{3068, 3070}, true, true, true, true, true, true) {
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
		},
		ZULRAH_CLOUD_SIX(new int[]{2271, 2273}, new int[]{3069, 3071}, true, true, true, true, true, true) {
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
		},
		ZULRAH_CLOUD_SEVEN(new int[]{2272, 2274}, new int[]{3072, 3074}, true, true, true, true, true, true) {
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
		},
		ZULRAH_CLOUD_EIGHT(new int[]{2265, 2264}, new int[]{3069, 3071}, true, true, true, true, true, true) {
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
		},
		ZULRAH(new int[]{2257, 2281}, new int[]{3063, 3083}, true, true, true, true, true, true){
			
			@Override
			public boolean canTeleport(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));
				return true;
			}
			@Override
			public void logout(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

				
			}
			@Override
			public void onDeath(Player player) {
				World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ZULRAH, player.getPosition().getZ()));

			}
			
		},
		WILDERNESS(new int[]{2941, 3392, 2986, 3012, 3653, 3710, 3650, 3653}, new int[]{3523, 3968, 10338, 10366, 3441, 3538, 3457, 3472}, false, true, true, true, true, true) {
			@Override
			public void process(Player player) {
				int x = player.getPosition().getX();
				int y = player.getPosition().getY();

				boolean ghostTown = x >= 3650 && y <= 3538;
				if(ghostTown) {
					
					player.setWildernessLevel(60);
					
				} else {
					player.setWildernessLevel(((((y > 6400 ? y - 6400 : y) - 3520) / 8)+1));
				}
				player.getPacketSender().sendString(25352, ""+player.getWildernessLevel());
				player.getPacketSender().sendString(25355, "Levels: "+CombatFactory.getLevelDifference(player, false) +" - "+CombatFactory.getLevelDifference(player, true));
				BountyHunter.process(player);
			}

			@Override
			public void leave(Player player) {
				if(player.getLocation() != this) {
					player.getPacketSender().sendString(19000, "Combat level: " + player.getSkillManager().getCombatLevel());
					player.getUpdateFlag().flag(Flag.APPEARANCE);
				}
				PLAYERS_IN_WILD--;
			}

			@Override
			public void enter(Player player) {
				player.getPacketSender().sendInteractionOption("Attack", 2, true);
				player.sendParallellInterfaceVisibility(25347, true);

				player.getPacketSender().sendString(19000, "Combat level: " + player.getSkillManager().getCombatLevel());
				player.getUpdateFlag().flag(Flag.APPEARANCE);
				PLAYERS_IN_WILD++;
			}

			@Override
			public boolean canTeleport(Player player) {
				if(player.getWildernessLevel() > 20) {
					if(player.getRights() == PlayerRights.OWNER || player.getRights() == PlayerRights.DEVELOPER) {
						player.getPacketSender().sendMessage("@red@You've teleported out of deep Wilderness, logs have been written.");
						return true;
					}
					player.getPacketSender().sendMessage("Teleport spells are blocked in this level of Wilderness.");
					player.getPacketSender().sendMessage("You must be below level 20 of Wilderness to use teleportation spells.");
					return false;
				}
				return true;
			}

			@Override
			public void login(Player player) {
				player.performGraphic(new Graphic(2000, 8));
			}

			@Override
			public boolean canAttack(Player player, Player target) {
				int combatDifference = CombatFactory.combatLevelDifference(player.getSkillManager().getCombatLevel(), target.getSkillManager().getCombatLevel());
				if (combatDifference > player.getWildernessLevel() || combatDifference > target.getWildernessLevel()) {
					player.getPacketSender().sendMessage("Your combat level difference is too great to attack that player here.");
					player.getMovementQueue().reset();
					return false;
				}
				if(target.getLocation() != Location.WILDERNESS && !player.inFFA) {
					player.getPacketSender().sendMessage("That player cannot be attacked, because they are not in the Wilderness.");
					player.getMovementQueue().reset();
					return false;
				}
				if(Jail.isJailed(player)) {
					player.getPacketSender().sendMessage("You cannot do that right now.");
					return false;
				}
				if(Jail.isJailed(target)) {
					player.getPacketSender().sendMessage("That player cannot be attacked right now.");
					return false;
				}
				/*if(Misc.getMinutesPlayed(player) < 20) {
					player.getPacketSender().sendMessage("You must have played for at least 20 minutes in order to attack someone.");
					return false;
				}
				if(Misc.getMinutesPlayed(target) < 20) {
					player.getPacketSender().sendMessage("This player is a new player and can therefore not be attacked yet.");
					return false;
				}*/
				return true;
			}
		},
		CONSTRUCTION(new int[] {1880, 1951}, new int[] {5080, 5151}, false, true, true, false, true, true) {

			@Override
			public boolean canTeleport(Player player) {
				return true;
			}
			
			@Override
			public void login(Player player) {
				player.moveTo(GameSettings.DEFAULT_POSITION.copy());
			}

			@Override
			public void logout(Player player) {
				player.moveTo(GameSettings.DEFAULT_POSITION.copy());
			}
		},
		
		BARROWS(new int[] {3520, 3598, 3543, 3584, 3543, 3560}, new int[] {9653, 9750, 3265, 3314, 9685, 9702}, false, true, true, true, true, true) {
			@Override
			public void process(Player player) {
				if(player.getWalkableInterfaceId() != 37200)
					player.sendParallellInterfaceVisibility(37200,true);
			}

			@Override
			public boolean canTeleport(Player player) {
				return true;
			}

			@Override
			public void logout(Player player) {

			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC npc) {
				Barrows.killBarrowsNpc(killer, npc, true);
				return true;
			}
		},
		PEST_CONTROL_GAME(new int[]{2624, 2690}, new int[]{2550, 2619}, true, true, true, false, true, true) {
			@Override
			public void process(Player player) {
				if(player.getWalkableInterfaceId() != 21100)
					player.sendParallellInterfaceVisibility(21100,true);
			}

			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("Teleport spells are blocked on this island. Wait for the game to finish!");
				return false;
			}

			@Override
			public void leave(Player player) {
				PestControl.leave(player, true);
			}

			@Override
			public void logout(Player player) {
				PestControl.leave(player, true);
			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC n) {
				return true;
			}

			@Override
			public void onDeath(Player player) {
				player.moveTo(new Position(2657, 2612, 0));
			}
		},
		PEST_CONTROL_BOAT(new int[]{2660, 2663}, new int[]{2638, 2643}, false, false, false, false, false, true) {
			@Override
			public void process(Player player) {
				if(!player.walkableInterfaceList.contains(21005))
					player.sendParallellInterfaceVisibility(21005, true);
			}

			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("You must leave the boat before teleporting.");
				return false;
			}

			@Override
			public void leave(Player player) {
				if(player.getLocation() != PEST_CONTROL_GAME) {
					PestControl.leave(player, true);
				}
			}

			@Override
			public void logout(Player player) {
				PestControl.leave(player, true);
			}
		},
		SOULWARS(new int[]{-1, -1}, new int[]{-1, -1}, true, true, true, false, true, true) {
			@Override
			public void process(Player player) {

			}

			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("If you wish to leave, you must use the portal in your team's lobby.");
				return false;
			}

			@Override
			public void logout(Player player) {

			}

			@Override
			public void onDeath(Player player) {

			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC npc) {

				return false;
			}

		},
		SOULWARS_WAIT(new int[]{-1, -1}, new int[]{-1, -1}, false, false, false, false, false, true) {
			@Override
			public void process(Player player) {}

			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("You must leave the waiting room before being able to teleport.");
				return false;
			}

			@Override
			public void logout(Player player) {}
		},
		FIGHT_CAVES(new int[]{2360, 2445}, new int[]{5045, 5125}, true, true, false, false, false, false) {
			@Override
			public void process(Player player) {}

			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("Teleport spells are blocked here. If you'd like to leave, use the north-east exit.");
				return false;
			}

			@Override
			public void login(Player player) {}

			@Override
			public void leave(Player player) {
				player.getCombatBuilder().reset(true);
				if(player.getRegionInstance() != null) {
					player.getRegionInstance().destruct();
				}
				player.moveTo(new Position(2439, 5169));
			}

			@Override
			public void onDeath(Player player) {
				FightCave.leaveCave(player, true);
			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC npc) {
				FightCave.handleJadDeath(killer, npc);
				return true;
			}
		},
		GRAVEYARD(new int[]{3485, 3517}, new int[]{3559, 3580}, true, true, false, true, false, false) {
			@Override
			public void process(Player player) {
			}

			@Override
			public boolean canTeleport(Player player) {
				if(player.getMinigameAttributes().getGraveyardAttributes().hasEntered()) {
					player.getPacketSender().sendInterfaceRemoval().sendMessage("A spell teleports you out of the graveyard.");
					Graveyard.leave(player);
					return false;
				}
				return true;
			}

			@Override
			public void logout(Player player) {
				if(player.getMinigameAttributes().getGraveyardAttributes().hasEntered()) {
					Graveyard.leave(player);
				}
			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC npc) {
				return killer.getMinigameAttributes().getGraveyardAttributes().hasEntered() && Graveyard.handleDeath(killer, npc);
			}

			@Override
			public void onDeath(Player player) {
				Graveyard.leave(player);
			}
		},
		FIGHT_PITS(new int[]{2370, 2425}, new int[]{5133, 5167}, true, true, true, false, false, true) {
			@Override
			public void process(Player player) {
				if(FightPit.inFightPits(player)) {
					FightPit.updateGame(player);
					if(player.getPlayerInteractingOption() != PlayerInteractingOption.ATTACK)
						player.getPacketSender().sendInteractionOption("Attack", 2, true);
				}
			}

			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("Teleport spells are blocked here. If you'd like to leave, use the northern exit.");
				return false;
			}

			@Override
			public void logout(Player player) {
				FightPit.removePlayer(player, "leave game");
			}

			@Override
			public void leave(Player player) {
				onDeath(player);
			}

			@Override
			public void onDeath(Player player) {
				if(FightPit.getState(player) != null) {
					FightPit.removePlayer(player, "death");
				}
			}

			@Override
			public boolean canAttack(Player player, Player target) {
				String state1 = FightPit.getState(player);
				String state2 = FightPit.getState(target);
				return state1 != null && state1.equals("PLAYING") && state2 != null && state2.equals("PLAYING");
			}
		},
		FIGHT_PITS_WAIT_ROOM(new int[]{2393, 2404}, new int[]{5168, 5176}, false, false, false, false, false, true) {
			@Override
			public void process(Player player) {
				FightPit.updateWaitingRoom(player);
			}

			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("Teleport spells are blocked here. If you'd like to leave, use the northern exit.");
				return false;
			}

			@Override
			public void logout(Player player) {
				FightPit.removePlayer(player, "leave room");
			}

			@Override
			public void leave(Player player) {
				if(player.getLocation() != FIGHT_PITS) {
					FightPit.removePlayer(player, "leave room");
				}
			}

		},
		DUEL_ARENA(new int[]{3322, 3394, 3311, 3323, 3331, 3391}, new int[]{3195, 3291, 3223, 3248, 3242, 3260}, false, false, false, false, false, false) {
			@Override
			public void process(Player player) {
				if(!player.walkableInterfaceList.contains(201))
					player.sendParallellInterfaceVisibility(201, true);
				if(player.getDueling().duelingStatus == 0) {
					if(player.getPlayerInteractingOption() != PlayerInteractingOption.CHALLENGE)
						player.getPacketSender().sendInteractionOption("Challenge", 2, false);
				} else if(player.getPlayerInteractingOption() != PlayerInteractingOption.ATTACK)
					player.getPacketSender().sendInteractionOption("Attack", 2, true);
			}

			@Override
			public void enter(Player player) {
				PLAYERS_IN_DUEL_ARENA++;
				player.getPacketSender().sendMessage("<img=10> <col=996633>Warning! Do not stake items which you are not willing to lose.");
			}

			@Override
			public boolean canTeleport(Player player) {
				if(player.getDueling().duelingStatus == 5) {
					player.getPacketSender().sendMessage("To forfiet a duel, run to the west and use the trapdoor.");
					return false;
				}
				return true;
			}

			@Override
			public void logout(Player player) {
				boolean dc = false;
				if(player.getDueling().inDuelScreen && player.getDueling().duelingStatus != 5) {
					player.getDueling().declineDuel(player.getDueling().duelingWith > 0 ? true : false);
				} else if(player.getDueling().duelingStatus == 5) {
					if(player.getDueling().duelingWith > -1) {
						Player duelEnemy = World.getPlayers().get(player.getDueling().duelingWith);
						if(duelEnemy != null) {
							duelEnemy.getDueling().duelVictory();
						} else {
							dc = true;
						}
					}
				}
				player.moveTo(new Position(3368, 3268));
				if(dc) {
					World.getPlayers().remove(player);
				}
			}

			@Override
			public void leave(Player player) {
				if(player.getDueling().duelingStatus == 5) {
					onDeath(player);
				}
				PLAYERS_IN_DUEL_ARENA--;
			}

			@Override
			public void onDeath(Player player) {
				if(player.getDueling().duelingStatus == 5) {
					if(player.getDueling().duelingWith > -1) {
						Player duelEnemy = World.getPlayers().get(player.getDueling().duelingWith);
						if(duelEnemy != null) {
							duelEnemy.getDueling().duelVictory();
							duelEnemy.getPacketSender().sendMessage("You won the duel! Congratulations!");
						}
					}
					player.getPacketSender().sendMessage("You've lost the duel.");
					player.getDueling().arenaStats[1]++; player.getDueling().reset();
				}
				player.moveTo(new Position(3368 + Misc.getRandom(5), 3267+ Misc.getRandom(3)));
				player.getDueling().reset();
			}

			@Override
			public boolean canAttack(Player player, Player target) {
				if(target.getIndex() != player.getDueling().duelingWith) {
					player.getPacketSender().sendMessage("That player is not your opponent!");
					return false;
				}
				if(player.getDueling().timer != -1) {
					player.getPacketSender().sendMessage("You cannot attack yet!");
					return false;
				}
				return player.getDueling().duelingStatus == 5 && target.getDueling().duelingStatus == 5;
			}
		},
		GODWARS_DUNGEON(new int[]{2800, 2950, 2858, 2943}, new int[]{5200, 5400, 5180, 5230}, true, true, true, false, true, true) {
			@Override
			public void process(Player player) {
			
				if(!player.walkableInterfaceList.contains(16210))
					player.sendParallellInterfaceVisibility(16210, true);
			}

			@Override
			public void enter(Player player) {
				DialogueManager.start(player, 110);
				player.getPacketSender().sendMessage("<img=10> If you die in a boss room, you will lose your items. You have been warned.");
				//GWD.startPreview(player);
			}

			@Override
			public boolean canTeleport(Player player) {
				return true;
			}

			@Override
			public void onDeath(Player player) {
				leave(player);
				//GWD.closeInterface(player);
			}

			@Override
			public void leave(Player p) {
				//GWD.closeInterface(p);

				for(int i = 0; i < p.getMinigameAttributes().getGodwarsDungeonAttributes().getKillcount().length; i++) {
					p.getMinigameAttributes().getGodwarsDungeonAttributes().getKillcount()[i] = 0;
					p.getPacketSender().sendString((16216+i), "0");
				}
				p.getMinigameAttributes().getGodwarsDungeonAttributes().setAltarDelay(0).setHasEnteredRoom(false);
				p.getPacketSender().sendMessage("Your Godwars dungeon progress has been reset.");
			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC n) {
				int index = -1;
				int npc = n.getId();
				if(npc == 6246 || npc == 6229 || npc == 6230 || npc == 6231) //Armadyl
					index = 0;
				else if(npc == 102 || npc == 3583 || npc == 115 || npc == 113 || npc == 6273 || npc == 6276 || npc == 6277 || npc == 6288) //Bandos
					index = 1;
				else if(npc == 6258 || npc == 6259 || npc == 6254 || npc == 6255 || npc == 6257 || npc == 6256) //Saradomin
					index = 2;
				else if(npc == 10216 || npc == 6216 || npc == 1220 || npc == 6007 || npc == 6219 ||npc == 6220 || npc == 6221 || npc == 49 || npc == 4418) //Zamorak
					index = 3;
				if(index != -1) {
					killer.getMinigameAttributes().getGodwarsDungeonAttributes().getKillcount()[index]++;
					killer.getPacketSender().sendString((16216+index), ""+killer.getMinigameAttributes().getGodwarsDungeonAttributes().getKillcount()[index]);
				}
				return false;
			}
		},
		NOMAD(new int[]{3342, 3377}, new int[]{5839, 5877}, true, true, false, true, false, true) {
			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("Teleport spells are blocked here. If you'd like to leave, use the southern exit.");
				return false;
			}

			@Override
			public void leave(Player player) {
				if(player.getRegionInstance() != null)
					player.getRegionInstance().destruct();
				player.moveTo(new Position(1890, 3177));
				player.restart();
			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC npc) {
				if(npc.getId() == 8528) {
					Nomad.endFight(killer, true);
					return true;
				}
				return false;
			}
		},
		RECIPE_FOR_DISASTER(new int[]{1885, 1913}, new int[]{5340, 5369}, true, true, false, false, false, false) {

			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("Teleport spells are blocked here. If you'd like to leave, use a portal.");
				return false;
			}

			@Override
			public boolean handleKilledNPC(Player killer, NPC npc) {
				RecipeForDisaster.handleNPCDeath(killer, npc);
				return true;
			}

			@Override
			public void leave(Player player) {
				if(player.getRegionInstance() != null)
					player.getRegionInstance().destruct();
				player.moveTo(new Position(3081, 3500));
			}

			@Override
			public void onDeath(Player player) {
				leave(player);
			}
		},
		FREE_FOR_ALL_ARENA(new int[]{2755, 2876}, new int[]{5512, 5627}, true, true, true, false, false, true) {
			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("Teleport spells are blocked here, if you wish to teleport, use the portal.");
				return false;
			}

			@Override
			public void onDeath(Player player) {
				player.moveTo(new Position(2815, 5511));
			}

			@Override
			public boolean canAttack(Player player, Player target) {
				if(target.getLocation() != FREE_FOR_ALL_ARENA) {
					player.getPacketSender().sendMessage("That player has not entered the dangerous zone yet.");
					player.getMovementQueue().reset();
					return false;
				}
				return true;
			}

			@Override
			public void enter(Player player) {
				if(player.getPlayerInteractingOption() != PlayerInteractingOption.ATTACK) {
					player.getPacketSender().sendInteractionOption("Attack", 2, true);
				}
			}

		},
		FREE_FOR_ALL_WAIT(new int[]{2755, 2876}, new int[]{5507, 5627}, false, false, true, false, false, true) {
			@Override
			public boolean canTeleport(Player player) {
				player.getPacketSender().sendMessage("Teleport spells are blocked here, if you wish to teleport, use the portal.");
				return false;
			}

			@Override
			public void onDeath(Player player) {
				player.moveTo(new Position(2815, 5511));
			}
		},
		COWS(new int[]{3239, 3270}, new int[]{3250, 3300}, false, true, true, false, false, true) {
			@Override
			public void logout(Player player) {
			player.moveTo(new Position(3094, 3503, 0));
				
			}
			@Override
			public void leave(Player player) {
			
			}
		},
		WARRIORS_GUILD(new int[]{2833, 2879}, new int[]{3531, 3559}, false, true, true, false, false, true) {
			@Override
			public void process(Player player) {
				int defender = WarriorsGuild.getDefender(player);
				if(player.getMinigameAttributes().getWarriorsGuildAttributes().enteredTokenRoom()) {
					player.sendParallellInterfaceVisibility(54500, true);
					player.getPacketSender().sendItemOnInterface(54503, defender, 1);
				} else {			
				player.sendParallellInterfaceVisibility(54500, true);
				player.getPacketSender().sendItemOnInterface(54503, 8851, 100);
				}
			}
			public void logout(Player player) {
				
				if(player.getMinigameAttributes().getWarriorsGuildAttributes().enteredTokenRoom()) {
					player.moveTo(new Position(2844, 3540, 2));
				}
				
			}
			@Override
			public void leave(Player player) {
				player.getMinigameAttributes().getWarriorsGuildAttributes().setEnteredTokenRoom(false);
				player.sendParallellInterfaceVisibility(54500, false);

			}
		},
		PURO_PURO(new int[]{2556, 2630}, new int[]{4281, 4354}, false, true, true, false, false, true) {
		},
		FLESH_CRAWLERS(new int[]{2033, 2049}, new int[]{5178, 5197}, false, true, true, false, true, true) {
		},
		DEFAULT(null, null, false, true, true, true, true, true) {

		};

		Location(int[] x, int[] y, boolean multi, boolean summonAllowed, boolean followingAllowed, boolean cannonAllowed, boolean firemakingAllowed, boolean aidingAllowed) {
			this.x = x;
			this.y = y;
			this.multi = multi;
			this.summonAllowed = summonAllowed;
			this.followingAllowed = followingAllowed;
			this.cannonAllowed = cannonAllowed;
			this.firemakingAllowed = firemakingAllowed;
			this.aidingAllowed = aidingAllowed;
		}

		private int[] x, y;
		private boolean multi;
		private boolean summonAllowed;
		private boolean followingAllowed;
		private boolean cannonAllowed;
		private boolean firemakingAllowed;
		private boolean aidingAllowed;

		public int[] getX() {
			return x;
		}

		public int[] getY() {
			return y;
		}

		public static boolean inMulti(Character gc) {
			if (gc.getLocation() == WILDERNESS) {
				int x = gc.getPosition().getX(), y = gc.getPosition().getY();
				if (x >= 3270 && x <= 3300 && y >= 3920 && y <= 3947) {
					return false;
				}
				if (x >= 3195 && x <= 3285 && y >= 3705 && y <= 3785 || x >= 3120 && x <= 3350 && y >= 3865 && y <= 3903
						|| x >= 3250 && x <= 3350 && y >= 3905 && y <= 3960 || x >= 3650 && y <= 3538
						|| x >= 3020 && x <= 3055 && y >= 3684 && y <= 3711
						|| x >= 3150 && x <= 3195 && y >= 2958 && y <= 3003)
					return true;

			}

			/*
			 * New in multi boolean coords for outsite wilderness
			 */
			int x = gc.getPosition().getX(), y = gc.getPosition().getY();
			if (x >= 3145 && x <= 3245 && y >= 3595 && y <= 3700 || x >= 2700 && x <= 2730 && y >= 9800 && y <= 9829
					|| x >= 3080 && x <= 3120 && y >= 5520 && y <= 5550) {
				return true;
			}

			return gc.getLocation().multi;
		}

		public boolean isSummoningAllowed() {
			return summonAllowed;
		}

		public boolean isFollowingAllowed() {
			return followingAllowed;
		}

		public boolean isCannonAllowed() {
			return cannonAllowed;
		}

		public boolean isFiremakingAllowed() {
			return firemakingAllowed;
		}

		public boolean isAidingAllowed() {
			return aidingAllowed;
		}

		public static Location getLocation(Entity gc) {
			for(Location location : Location.values()) {
				if(location != Location.DEFAULT)
					if(inLocation(gc, location))
						return location;
			}
			return Location.DEFAULT;
		}

		public static boolean inLocation(Entity gc, Location location) {
			if(location == Location.DEFAULT) {
				if(getLocation(gc) == Location.DEFAULT)
					return true;
				else
					return false;
			}
			/*if(gc instanceof Player) {
				Player p = (Player)gc;
				if(location == Location.TRAWLER_GAME) {
					String state = FishingTrawler.getState(p);
					return (state != null && state.equals("PLAYING"));
				} else if(location == FIGHT_PITS_WAIT_ROOM || location == FIGHT_PITS) {
					String state = FightPit.getState(p), needed = (location == FIGHT_PITS_WAIT_ROOM) ? "WAITING" : "PLAYING";
					return (state != null && state.equals(needed));
				} else if(location == Location.SOULWARS) {
					return (SoulWars.redTeam.contains(p) || SoulWars.blueTeam.contains(p) && SoulWars.gameRunning);
				} else if(location == Location.SOULWARS_WAIT) {
					return SoulWars.isWithin(SoulWars.BLUE_LOBBY, p) || SoulWars.isWithin(SoulWars.RED_LOBBY, p);
				}
			}
			 */
			return inLocation(gc.getPosition().getX(), gc.getPosition().getY(), location);
		}

		public static boolean inLocation(int absX, int absY, Location location) {
			int checks = location.getX().length - 1;
			for(int i = 0; i <= checks; i+=2) {
				if(absX >= location.getX()[i] && absX <= location.getX()[i + 1]) {
					if(absY >= location.getY()[i] && absY <= location.getY()[i + 1]) {
						return true;
					}
				}
			}
			return false;
		}

		public void process(Player player) {

		}

		public boolean canTeleport(Player player) {
			return true;
		}

		public void login(Player player) {

		}

		public void enter(Player player) {

		}

		public void leave(Player player) {

		}

		public void logout(Player player) {

		}

		public void onDeath(Player player) {

		}

		public boolean handleKilledNPC(Player killer, NPC npc) {
			return false;
		}

		public boolean canAttack(Player player, Player target) {
			return false;
		}

		/** SHOULD AN ENTITY FOLLOW ANOTHER ENTITY NO MATTER THE DISTANCE BETWEEN THEM? **/
		public static boolean ignoreFollowDistance(Character character) {
			Location location = character.getLocation();
			return location == Location.FIGHT_CAVES || location == Location.RECIPE_FOR_DISASTER || location == Location.NOMAD;
		}
	}

	public static void process(Character gc) {
		Location newLocation = Location.getLocation(gc);
		if(gc.getLocation() == newLocation) {
			if(gc.isPlayer()) {
				Player player = (Player) gc;
				gc.getLocation().process(player);
				if(Location.inMulti(player)) {
					if(player.getMultiIcon() != 1)
						player.getPacketSender().sendMultiIcon(1);
				} else if(player.getMultiIcon() == 1)
					player.getPacketSender().sendMultiIcon(0);
			}
		} else {
			Location prev = gc.getLocation();
			if(gc.isPlayer()) {
				Player player = (Player) gc;
				if(player.getMultiIcon() > 0)
					player.getPacketSender().sendMultiIcon(0);
				if(player.walkableInterfaceList.size() > 0)
					//player.getPacketSender().sendWalkableInterface(-1);
                                    player.resetInterfaces();
				if(player.getPlayerInteractingOption() != PlayerInteractingOption.NONE)
					player.getPacketSender().sendInteractionOption("null", 2, true);
			}
			gc.setLocation(newLocation);
			if(gc.isPlayer()) {
				prev.leave(((Player)gc));
				gc.getLocation().enter(((Player)gc));
			}
		}
	}

	public static boolean goodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		if (playerX == objectX && playerY == objectY)
			return true;
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX + i) == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if ((objectX - i) == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean goodDistance(Position pos1, Position pos2, int distanceReq) {
		if(pos1.getZ() != pos2.getZ())
			return false;
		return goodDistance(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY(), distanceReq);
	}

	public static int distanceTo(Position position, Position destination,
			int size) {
		final int x = position.getX();
		final int y = position.getY();
		final int otherX = destination.getX();
		final int otherY = destination.getY();
		int distX, distY;
		if (x < otherX)
			distX = otherX - x;
		else if (x > otherX + size)
			distX = x - (otherX + size);
		else
			distX = 0;
		if (y < otherY)
			distY = otherY - y;
		else if (y > otherY + size)
			distY = y - (otherY + size);
		else
			distY = 0;
		if (distX == distY)
			return distX + 1;
		return distX > distY ? distX : distY;
	}
}
