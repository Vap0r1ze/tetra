package se.mickelus.tetra.items.sword;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import se.mickelus.tetra.TetraMod;
import se.mickelus.tetra.module.ItemModuleMajor;
import se.mickelus.tetra.module.ItemUpgradeRegistry;

public class BladeModule extends ItemModuleMajor {

    public static final String key = "basic_blade";
    public static final String materialKey = "basic_blade_material";

    public static BladeModule instance;

    public BladeModule() {
        instance = this;
        ItemUpgradeRegistry.instance.registerModule(key, this);
    }

    @Override
    public String getName(ItemStack itemStack) {
	    NBTTagCompound tag = itemStack.getTagCompound();
	    String materialName = tag.getString(materialKey);

	    switch (materialName) {
		    case "minecraft:planks":
		    	return "Sharpened Plank";
		    case "minecraft:cobblestone":
			    return "Sharpened Stone";
		    case "minecraft:iron_ingot":
			    return "Iron Blade";
		    case "minecraft:gold_ingot":
			    return "Golden Blade";
		    case "minecraft:diamond":
			    return "Diamond Blade";
	    }
	    return "Wooden Blade";
    }

    @Override
    public void addModule(ItemStack targetStack, ItemStack[] materials) {
        NBTTagCompound tag = targetStack.getTagCompound();
	    ResourceLocation resourcelocation = Item.REGISTRY.getNameForObject(materials[0].getItem());

        tag.setString(ItemSwordModular.bladeKey, key);
        tag.setString(materialKey, resourcelocation.toString());
    }

    @Override
    public ItemStack[] removeModule(ItemStack targetStack, ItemStack[] tools) {
        return new ItemStack[0];
    }

    @Override
    public double getDamageModifier(ItemStack itemStack) {
        NBTTagCompound tag = itemStack.getTagCompound();
        String materialName = tag.getString(materialKey);

        switch (materialName) {
            case "minecraft:planks":
                return 1;
            case "minecraft:cobblestone":
                return 2;
            case "minecraft:iron_ingot":
                return 3;
            case "minecraft:gold_ingot":
                return 4;
            case "minecraft:diamond":
                return 5;
        }
        return 1;
    }

	@Override
	public ResourceLocation[] getTextures(final ItemStack itemStack) {
		NBTTagCompound tag = itemStack.getTagCompound();
		String materialName = tag.getString(materialKey);
		ResourceLocation result = new ResourceLocation(TetraMod.MOD_ID, "sword_modular/basic_blade_oak");

		switch (materialName) {
			case "minecraft:planks":
				result = new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_oak");
				break;
			case "minecraft:cobblestone":
				result = new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_stone");
				break;
			case "minecraft:iron_ingot":
				result = new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_iron");
				break;
			case "minecraft:gold_ingot":
				result = new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_stone");
				break;
			case "minecraft:diamond":
				result = new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_diamond");
				break;
		}
		return new ResourceLocation[] {result};
	}

	@Override
	public ResourceLocation[] getAllTextures() {
		return new ResourceLocation[] {
			new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_oak"),
			new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_stone"),
			new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_iron"),
			new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_stone"),
			new ResourceLocation(TetraMod.MOD_ID, "items/sword_modular/basic_blade_diamond")
		};
	}
}
