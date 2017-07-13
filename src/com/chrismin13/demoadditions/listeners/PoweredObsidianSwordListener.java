package com.chrismin13.demoadditions.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.chrismin13.additionsapi.AdditionsAPI;
import com.chrismin13.additionsapi.items.CustomItemStack;
import com.chrismin13.demoadditions.items.PoweredObsidianSword;
import com.comphenix.attribute.Attributes;
import com.comphenix.attribute.Attributes.Attribute;
import com.comphenix.attribute.Attributes.AttributeType;
import com.comphenix.attribute.Attributes.Operation;

/**
 * Listener class for the Powered Obsidian Sword. Take a look here for how to
 * use Custom Items in other areas of the Bukkit API.
 * 
 * @author chrismin13
 *
 */
public class PoweredObsidianSwordListener implements Listener {

	/**
	 * This is the UUID that will be used for the Attribute that will contain
	 * the Extra Attack Damage
	 */
	public static final UUID EXTRA_ATTACK_UUID = UUID.fromString("73e975b-f742-484d-878f-1158f580dc65");

	/**
	 * Resets your Obsidian Sword when you get hit from an entity. It's just
	 * entities, I didn't want you to get reset due to Fall Damage or something.
	 */
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.isCancelled())
			return;
		// Check if the entity is a player
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			// Get every item in his inventory
			for (ItemStack item : player.getInventory()) {
				// Check if it's a CustomItem.
				if (AdditionsAPI.isCustomItem(item)) {
					// Make a Custom Item Stack - it contains the original Item
					// Stack, the Custom Item and a couple of useful methods.
					CustomItemStack cStack = new CustomItemStack(item);

					// Check if the Custom Item is of instance Powered Obsidian
					// Sword.
					if (cStack.getCustomItem() instanceof PoweredObsidianSword) {
						Attributes attributes = cStack.getAttributes();
						if (attributes.values() != null)
							// Scan through all of the Attributes
							for (Attribute attribute : attributes.values()) {
								// Remove those who are with the specified UUID.
								if (attribute.getUUID().equals(EXTRA_ATTACK_UUID)) {
									cStack.removeAttribute(attribute.getAttributeType(), attribute.getName(),
											attribute.getAmount(), EXTRA_ATTACK_UUID, EquipmentSlot.HAND,
											attribute.getOperation());
									cStack.setTexture("obsidian_sword_1");
								}
							}
					}
				}
			}

		}
	}

	/**
	 * Upgrades the Sword when an entity dies.
	 */
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		// Check that the last damage cause before death was from an entity
		// damaging an entity.
		if (!(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent))
			return;
		EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
		// Check if the damager is a player.
		if (!(damageEvent.getDamager() instanceof Player))
			return;
		Player player = (Player) damageEvent.getDamager();

		// Get the Item used to kill the entity
		ItemStack item = player.getInventory().getItemInMainHand();

		// Check if the Item was a Custom Item
		if (AdditionsAPI.isCustomItem(item)) {
			// Make a Custom Item Stack from the ItemStack.
			CustomItemStack cStack = new CustomItemStack(item);

			// Check if the Custom Item is of instance Powered Obsidian Sword
			if (cStack.getCustomItem() instanceof PoweredObsidianSword) {
				// Check if the Obsidian Sword is at full power
				if (!cStack.getTexture().equals("obsidian_sword_6")) {
					// Get all its attributes
					Attributes attributes = cStack.getAttributes();
					boolean changedTexture = false;
					for (Attribute attribute : attributes.values()) {
						if (attribute.getUUID().equals(EXTRA_ATTACK_UUID)) {
							// If it contains an Attribute with the UUID of the
							// Extra Attack, modify it and increase it by one.
							attribute.setAmount(attribute.getAmount() + 1);
							// Increase the texture name by one
							cStack.setTexture("obsidian_sword_" + Integer.toString((int) (attribute.getAmount() + 1)));
							// Update the lore to reflect the changes in attack
							// damage
							cStack.updateLore();
							// Confirm that there was a change.
							changedTexture = true;
						}
					}
					// If there was no change, it means that the Sword has not
					// been upgraded
					if (!changedTexture) {
						// Add the Extra Attack Attribute (lore is automatically
						// updated)
						cStack.addAttribute(AttributeType.GENERIC_ATTACK_DAMAGE, "Extra Attack Damage from Kill", 1,
								EXTRA_ATTACK_UUID, EquipmentSlot.HAND, Operation.ADD_NUMBER);
						// Set the texture.
						cStack.setTexture("obsidian_sword_2");
					}
				}
			}
		}
	}
}
