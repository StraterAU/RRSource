package com.arlania.world.content.combat.strategy.impl.zulrah;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.GameObject;
import com.arlania.model.Position;
import com.arlania.model.Projectile;
import com.arlania.model.RegionInstance;
import com.arlania.model.RegionInstance.RegionInstanceType;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.content.CustomObjects;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatHitTask;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class GreenZulrah implements CombatStrategy {
	
	public static NPC ZULRAH;
	public static NPC CLOUD1;
	public static NPC CLOUD2;
	public static NPC CLOUD3;
	public static NPC CLOUD4;
	public static NPC CLOUD5;
	public static NPC CLOUD6;
	public static NPC CLOUD7;
	public static NPC CLOUD8;
	
	static Player getPlayer;
	
	private int[][] toxicFumeLocations = { { 2263, 3076 }, { 2263, 3073 }, { 2263, 3070 }, { 2266, 3069 },
			{ 2269, 3069 }, { 2272, 3070 }, { 2273, 3073 }, { 2273, 3076 } };
	
	private NPC[] CloudTiles = {CLOUD1, CLOUD2, CLOUD3, CLOUD4, CLOUD5, CLOUD6, CLOUD7, CLOUD8};
	
	public void spawnCloudTiles(Character victim) { 
		for(int i = 0; i < 8; i++) {
			CloudTiles[i] = new NPC(0, new Position(toxicFumeLocations[i][0], toxicFumeLocations[i][1], victim.getIndex() * 4));
			World.register(CloudTiles[i]);
		}
	}
	public void despawnCloudTiles(Player player) { 
		for(int i = 0; i < 8; i++) {
			World.deregister(CloudTiles[i]);
		}
	}
	
	private static Animation rise = new Animation(5073);
	private static Animation dive = new Animation(5072);

	public int i = 1;
	public static int phase = 1;
	public static boolean isRespawning = false;
	public static boolean isSpawning = false;
	public static boolean cloudsSpawned = false;
	public static boolean removeHp = false;
	public static boolean stopEvent = false;
	public static int entityConstitution = 0;
	
	public static void spawn(Player player, int next, int constitution){
		getPlayer = player;
		System.out.println(constitution);
		if(next == 1) {
			player.setRegionInstance(new RegionInstance(player, RegionInstanceType.ZULRAHS_SHRINE));
			ZULRAH = new NPC(2043, new Position(2268, 3074, player.getIndex() * 4));
		} else if(next == 2) {
			System.out.println("POSITION 2 OWYE");
			ZULRAH = new NPC(2043, new Position(2277, 3074, player.getIndex() * 4));
		} else if(next == 3) {
			ZULRAH = new NPC(2043, new Position(2270, 3065, player.getIndex() * 4));
		} else if(next == 4) {
			ZULRAH = new NPC(2043, new Position(2268, 3074, player.getIndex() * 4));
		} else if(next == 5) {
			ZULRAH = new NPC(2043, new Position(2259, 3070, player.getIndex() * 4));
		} else if(next == 6) {
			ZULRAH = new NPC(2043, new Position(2277, 3074, player.getIndex() * 4));
		}
		World.register(ZULRAH);
		ZULRAH.performAnimation(rise);
		entityConstitution = 5000 - constitution;
		removeHp = true;
		phase = next;
	}
	@Override
	public boolean canAttack(Character entity, Character victim) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		if(removeHp) {
			entity.setConstitution(5000 - entityConstitution);
			removeHp = false;
		}
		if(isRespawning)
			return true;
		if(isSpawning)
			return true;
		if(phase == 1) {
			if(!cloudsSpawned) {
				spawnCloudTiles(victim);
				cloudsSpawned = true;
			} else {
				isSpawning = true;
				getPlayer.setCloudsSpawned(true);
				TaskManager.submit(new Task(1, true) {
					int tick;
					int cloud = 1;
					
					@Override
					public void execute() {
						if(entity.getConstitution() < 1 || entity == null) {
							stop();
						}
						for(int i = 1; i < 8; i++) {
							if(tick == i*4) {
								entity.getCombatBuilder().attack(CloudTiles[cloud]);
							}
							
							if(tick == i*4+2){
								entity.performAnimation(new Animation(5069));
								new Projectile(entity, CloudTiles[cloud], 1044, 44, 1, 43, 0, 0).sendProjectile();
							}
							
							if(tick == i*4+3){
								CustomObjects.zulrahToxicClouds(new GameObject(11700, CloudTiles[cloud].getPosition()), getPlayer, 40);
								cloud++;
							} 
							if(tick == 32) {
								entity.getCombatBuilder().attack(CloudTiles[4]);
							}
						}
						if(tick == 34) {
							TaskManager.submit(new Task(1, true) {
								int tick;
								@Override
								public void execute() {
									if(tick == 0) {
										entityConstitution = entity.getConstitution();
										ZULRAH.performAnimation(dive);
										TaskManager.submit(new Task(2, ZULRAH, false) {
											@Override
											public void execute() {
												World.deregister(ZULRAH);
												System.out.println("2nd zulrah despawn");
												stop();
											}
										});
										isSpawning = false;
										isRespawning = true;								
									}
									if(tick == 3) {
										cloudsSpawned = false;
										isRespawning = false;
										spawn(getPlayer, 2, entityConstitution);
										stop();
									}
									tick++;
								} 
							});
							stop();
						}
						tick++;
					}
				});
			}
		} else {
			int rand = Misc.getRandom(15);
			TaskManager.submit(new Task(2, entity, true) {
				int tick;
				@Override
				public void execute() {
					if(rand == 4) {
						System.out.println("EVENT STOPPED");
						stop();
					}
					if(tick == 0) {
						entity.performAnimation(new Animation(5069));
					}
					if(tick == 2) {
						new Projectile(entity, victim, 1044, 44, 1, 43, 43, 0).sendProjectile();
						new CombatHitTask(entity.getCombatBuilder(), new CombatContainer(entity, victim, 1, CombatType.RANGED, true)).handleAttack();
					}
					tick++;
				}
			});
			if(rand == 4) {
				TaskManager.submit(new Task(1, true) {
					int tick;
					@Override
					public void execute() {
						if(tick == 0) {
							entityConstitution = entity.getConstitution();
							ZULRAH.performAnimation(dive);
							TaskManager.submit(new Task(2, ZULRAH, false) {
								@Override
								public void execute() {
									World.deregister(ZULRAH);
									System.out.println("2nd zulrah despawn");
									stop();
								}
							});
							isRespawning = true;								
						}
						if(tick == 3) {
							System.out.println(phase);
							isRespawning = false;
							stopEvent = false;
							if(phase == 2)
								CrimsonZulrah.spawn(getPlayer, entityConstitution);
								stop();
							if(phase == 3)
								BlueZulrah.spawn(getPlayer, 2, entityConstitution);
								stop();
							if(phase == 4)
								spawn(getPlayer, 5, entityConstitution);
								stop();
							if(phase == 5)
								BlueZulrah.spawn(getPlayer, 3, entityConstitution);
								stop();
							if(phase == 6)
								BlueZulrah.spawn(getPlayer, 4, entityConstitution);
								stop();
						}
						tick++;
					} 
				});
			}
		}

		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int attackDistance(Character entity) {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public CombatType getCombatType() {
		// TODO Auto-generated method stub
		return null;
	}

}
