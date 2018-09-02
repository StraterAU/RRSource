package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.model.Locations;
import com.arlania.model.Position;
import com.arlania.model.Projectile;
import com.arlania.util.Misc;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class LizardMan implements CombatStrategy {

	@Override
	public boolean canAttack(Character entity, Character victim) {
		return true;
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		return null;
	}

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		NPC lizardman = (NPC)entity;
		if(lizardman.isChargingAttack() || victim.getConstitution() <= 0) {
			return true;
		}
		if(Misc.getRandom(15) <= 2){
			int hitAmount = 1;
			lizardman.performGraphic(new Graphic(69));
			lizardman.setConstitution(lizardman.getConstitution() + hitAmount);
			//((Player)victim).getPacketSender().sendMessage(MessageType.NPC_ALERT, "lizardman absorbs his next attack, healing himself a bit.");
		}
		if(Locations.goodDistance(lizardman.getPosition().copy(), victim.getPosition().copy(), 3) && Misc.getRandom(5) <= 3) {
			lizardman.performAnimation(new Animation(lizardman.getDefinition().getAttackAnimation()));
			lizardman.getCombatBuilder().setContainer(new CombatContainer(lizardman, victim, 1, 1, CombatType.MELEE, true));
			if(Misc.getRandom(5) <= 2) {
				victim.moveTo(new Position(2718 + Misc.getRandom(6), 9818 + Misc.getRandom(6)));
				lizardman.performAnimation(new Animation(7192));
				victim.performAnimation(new Animation(534));
				//((Player)victim).getPacketSender().sendMessage(MessageType.NPC_ALERT, "You have been knocked back!");
			}
		} else {
			lizardman.setChargingAttack(true);
			lizardman.performAnimation(new Animation(7193));
			lizardman.getCombatBuilder().setContainer(new CombatContainer(lizardman, victim, 1, 3, CombatType.MAGIC, true));
			TaskManager.submit(new Task(1, lizardman, false) {
				int tick = 0;
				@Override
				public void execute() {
					if(tick == 0) {
						new Projectile(lizardman, victim, 69, 44, 3, 41, 31, 0).sendProjectile();
					} else if(tick == 1) {
						lizardman.setChargingAttack(false);
						stop();
					}
					tick++;
				}
			});
		}
		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 5;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.MIXED;
	}
}