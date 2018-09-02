package com.arlania.model.input.impl;

import com.arlania.model.input.EnterAmount;
import com.arlania.world.content.skill.impl.crafting.LeatherMaking;
import com.arlania.world.content.skill.impl.crafting.leatherData;
import com.arlania.world.entity.impl.player.Player;

public class EnterAmountOfLeatherToCraft extends EnterAmount {
	
	@Override
	public void handleAmount(Player player, int amount) {
		for (final leatherData l : leatherData.values()) {
			if (player.getSelectedSkillingItem() == l.getLeather()) {
				LeatherMaking.craftLeather(player, l, amount);
				break;
			}
		}
	}
}
