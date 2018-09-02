package com.arlania.world.content.combat.strategy.impl.zulrah;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.GameObject;
import com.arlania.model.Position;
import com.arlania.model.Projectile;
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

public class BlueZulrah implements CombatStrategy {
	
	public static NPC ZULRAH;
	public static NPC CLOUD1;
	public static NPC CLOUD2;
	public static NPC CLOUD3;
	public static NPC CLOUD4;
	
	static Player getPlayer;
	
	private int[][] toxicFumeLocations = { { 2263, 3070 }, { 2266, 3069 }, { 2269, 3069 }, { 2272, 3070 } };
	
	private NPC[] CloudTiles = {CLOUD1, CLOUD2, CLOUD3, CLOUD4};
	
	public void spawnCloudTiles(Character victim) { 
		for(int i = 0; i < 4; i++) {
			CloudTiles[i] = new NPC(1, new Position(toxicFumeLocations[i][0], toxicFumeLocations[i][1], victim.getIndex() * 4));
			World.register(CloudTiles[i]);
		}
	}
	public void despawnCloudTiles(Player player) { 
		for(int i = 0; i < 4; i++) {
			World.deregister(CloudTiles[i]);
		}
	}

	private static Animation rise = new Animation(5073);
	private static Animation dive = new Animation(5072);

	public static int phase;
	public static boolean isRespawning = false;
	public static boolean isSpawning = false;
	public static boolean cloudsSpawned = false;
	public static boolean removeHp = false;
	public static boolean spawningEnded = false;
	public static int entityConstitution = 0;
	
	public static void spawn(Player player, int next, int constitution){
		getPlayer = player;
		if(next == 1) {
			ZULRAH = new NPC(2042, new Position(2259, 3070, player.getIndex() * 4));
		} else if(next == 2) {
			ZULRAH = new NPC(2042, new Position(2277, 3074, player.getIndex() * 4));
		} else if(next == 3) {
			ZULRAH = new NPC(2042, new Position(2268, 3074, player.getIndex() * 4));
		}  else if(next == 4) {
			ZULRAH = new NPC(2042, new Position(2268, 3074, player.getIndex() * 4));
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
		int rand = RandomUtility.getRandom(15);
		if(phase == 2) {
			if(!cloudsSpawned) {
				spawnCloudTiles(victim);
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
						for(int i = 1; i < 4; i++) {
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
							if(tick == 24) {
								entity.getCombatBuilder().attack(getPlayer);
								cloudsSpawned = true;
								isSpawning = false;
							}
						}
						tick++;
					}
				});
			}
		}
		if(rand == 10) {
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
								cloudsSpawned = false;
								stop();
							}
						});
						isRespawning = true;								
					}
					if(tick == 3) {
						isRespawning = false;
						if(phase == 1)
							GreenZulrah.spawn(getPlayer, 3, entityConstitution);
						if(phase == 2)
							GreenZulrah.spawn(getPlayer, 4, entityConstitution);
						if(phase == 3)
							GreenZulrah.spawn(getPlayer, 6, entityConstitution);
						if(phase == 4)
							GreenZulrah.spawn(getPlayer, 1, entityConstitution);
						stop();
					}
					tick++;
				} 
			});
		} else {
			entity.performAnimation(new Animation(5069));
			new CombatHitTask(entity.getCombatBuilder(), new CombatContainer(entity, victim, 1, CombatType.MAGIC, true)).handleAttack();
		}
		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		// TODO Auto-generated method stub
		return 4;
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

