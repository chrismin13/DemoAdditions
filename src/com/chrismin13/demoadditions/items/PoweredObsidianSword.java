package com.chrismin13.demoadditions.items;

import org.bukkit.Material;

import com.chrismin13.additionsapi.enums.ToolType;
import com.chrismin13.additionsapi.items.textured.CustomTexturedTool;

import us.fihgu.toolbox.item.DamageableItem;

/**
 * The class of the PoweredObsidianSword. It extends CustomTexturedTool, which
 * means that this will be of type Custom Textured Tool.
 * 
 * @author chrismin13
 *
 */
public class PoweredObsidianSword extends CustomTexturedTool {

	// This is required.
	public PoweredObsidianSword() {
		/*
		 * Original values of the Custom Items. I will base it on the Diamond
		 * Sword as it has a lot of durability points free. Next is the ID Name.
		 * This is just a unique identifier for the Custom Item. It is of the
		 * form "plugin_name:item_name". THe first part of it will be used to
		 * get the folder for the texture from the Resource Pack.
		 * "obsidian_sword_1" is the default texture that will be used from the
		 * Resource Pack. This will be the texture that you'll get when you
		 * craft the item.
		 */
		super(DamageableItem.DIAMOND_SWORD, "demo_additions:powered_obsidian_sword", "obsidian_sword_1");

		/*
		 * Add all the textures required for the Custom Item so that we can use
		 * them later.
		 */
		addTexture("obsidian_sword_2");
		addTexture("obsidian_sword_3");
		addTexture("obsidian_sword_4");
		addTexture("obsidian_sword_5");
		addTexture("obsidian_sword_6");

		/*
		 * Setting some Required values
		 */
		setUnbreakable(true); // Required, so we can set Custom Fake Durability
								// later
		setUnbreakableVisibility(false); // Hides the "Unbreakable" text found
											// when the item is unbreakable.
		addAttackDamage(4.0); // This will set the Attack Damage to 4.
		addAttackSpeed(1.6); // This will set the Attack Speed to 1.6.
		setToolLikeAttributes(true); // This will add Lore Text for Attack Speed
										// and Damage
		setAttributeVisibility(false); // This will hide the extra Attack Speed
										// and Attack Damage text

		setDisplayName("Powered Obsidian Sword"); // This will set the name.
		setFakeDurability(6247); // This will set the maximum durability

		// This will get the Crafting Recipe for the Sword and set the materials
		// for crafting to Obsidian and Redstone Torch.
		addAllCustomRecipes(ToolType.SWORD.getCustomShapedRecipe(Material.OBSIDIAN, Material.REDSTONE_TORCH_ON));
	}

}
