package com.arlania.net.packet.impl;

import com.arlania.model.PlayerRights;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.entity.impl.player.Player;

public class ClickTextMenuPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {

		int interfaceId = packet.readShort();
		int menuId = packet.readByte();

		if(player.getRights() == PlayerRights.DEVELOPER) {
			player.getPacketSender().sendConsoleMessage("Clicked text menu: "+interfaceId+", menuId: "+menuId);
		}
		
		if(interfaceId >= 29344 && interfaceId <= 29443) { // Clan chat list
			int index = interfaceId - 29344;
			ClanChatManager.handleMemberOption(player, index, menuId);
		}
		
	}

	public static final int OPCODE = 222;
}
