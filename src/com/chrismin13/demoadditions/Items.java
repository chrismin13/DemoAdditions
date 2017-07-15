package com.chrismin13.demoadditions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.chrismin13.additionsapi.events.AdditionsAPIInitializationEvent;
import com.chrismin13.demoadditions.items.PoweredObsidianSword;

/**
 * This Class will be the Listener of the AdditionsAPIInitializationEvent so
 * that all items can be added upon initialization.
 * 
 * @author chrismin13
 *
 */
public class Items implements Listener { // Class must implement Listener

	@EventHandler
	public void onInitialization(AdditionsAPIInitializationEvent event) {
		// Adds the Resource Pack from the plugin's jar. Don't forget to include the .zip extendsion!
		event.addResourcePackFromPlugin(DemoAdditions.instance, "DemoAdditions-v1.0.zip");

		// Adds a new Powered Obsidian Sword to be initialized.
		event.addCustomItem(new PoweredObsidianSword());
	}

}