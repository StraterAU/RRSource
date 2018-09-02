package com.arlania.world.content;

import com.arlania.util.Misc;
import com.arlania.util.Stopwatch;
import com.arlania.world.World;

/*
 * @author Bas
 * www.Arlania.com
 */

public class Reminders {
	
	
    private static final int TIME = 900000; //10 minutes
	private static Stopwatch timer = new Stopwatch().reset();
	public static String currentMessage;
	
	/*
	 * Random Message Data
	 */
	private static final String[][] MESSAGE_DATA = { 
			{"@blu@[Server]@bla@ Join 'Help' CC For Help/Tips!"},
			{"@blu@[Server]@bla@ Do ::news For a Huge Announcement"},
			{"@blu@[Server]@bla@ Do ::refund to request a rank refund!"},
			{"@blu@[Server]@bla@ Do ::benefits To Check out donator Rank Benefits!"},
			{"@blu@[Server]@bla@ Donate to help the server grow! ::store"},
			{"@blu@[Server]@bla@ Use the command ::drop (npcname) for drop tables"},
			{"@blu@[Server]@bla@ Donate to help the server grow! ::store"},
			{"@blu@[Server]@bla@ Use the ::help command to ask staff for help"},
			{"@blu@[Server]@bla@ Make sure to read the forums for information www.Morytania.org/forum/"},
			{"@blu@[Server]@bla@ Donate to help the server grow! ::store"},
			{"@blu@[Server]@bla@ Remember to spread the word and invite your friends to play!"},
			{"@blu@[Server]@bla@ Donate to help the server grow! ::store"},
			{"@blu@[Server]@bla@ Use ::commands to find a list of commands"},
			{"@blu@[Server]@bla@ Donate to help the server grow! ::store"},
			{"@blu@[Server]@bla@ Toggle your client settings to your preference in the wrench tab!"},
			{"@blu@[Server]@bla@ Register and post on the forums to keep them active! ::Forum"},
			{"@blu@[Server]@bla@ Donate to help the server grow! ::store"},
			//{"@blu@[Server]@bla@ Did you know you can get paid to make videos? PM Owner"},
			{"@blu@[Server]@bla@ Donate to help the server grow! ::store"},
			
		
	};

	/*
	 * Sequence called in world.java
	 * Handles the main method
	 * Grabs random message and announces it
	 */
	public static void sequence() {
		if(timer.elapsed(TIME)) {
			timer.reset();
			{
				
			currentMessage = MESSAGE_DATA[Misc.getRandom(MESSAGE_DATA.length - 1)][0];
			World.sendMessage(currentMessage);
			World.savePlayers();
					
				}
				
			World.savePlayers();
			}
		

          }

}