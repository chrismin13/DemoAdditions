package com.chrismin13.demoadditions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.chrismin13.demoadditions.listeners.PoweredObsidianSwordListener;

/**
 * The main class of the Demo Additions. This is just like any Bukkit Plugin.
 * 
 * @author chrismin13
 *
 */
public class DemoAdditions extends JavaPlugin {

	public static JavaPlugin instance; // Our Plugin's instance

	public void onEnable() {
		instance = this;

		PluginManager pm = Bukkit.getPluginManager();
		/*
		 * Here, we register the Class that contains the
		 * AdditionsAPIInitializationEvent Listener as well as any listeners
		 * that we may wish to have for our Custom Items.
		 */
		pm.registerEvents(new Items(), this);
		pm.registerEvents(new PoweredObsidianSwordListener(), this);
	}

}
