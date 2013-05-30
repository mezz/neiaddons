/**
 * Copyright (c) bdew, 2013
 * https://github.com/bdew/neiaddons
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/neiaddons/master/MMPL-1.0.txt
 */

package net.bdew.neiaddons.forestry;

import net.bdew.neiaddons.BaseAddon;
import net.bdew.neiaddons.NEIAddons;
import net.bdew.neiaddons.forestry.bees.BeeHelper;
import net.bdew.neiaddons.forestry.butterflies.ButterflyHelper;
import net.bdew.neiaddons.forestry.trees.TreeHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = NEIAddons.modid + "|Forestry", name = "NEI Addons: Forestry", version = "@@VERSION@@", dependencies = "after:NEIAddons;after:Forestry")
public class AddonForestry extends BaseAddon {
    public static boolean showSecret;
    public static boolean addBees;
    public static boolean addCombs;
    public static boolean addSaplings;
    public static boolean addPollen;
    public static boolean loadBlacklisted;

    @Instance(NEIAddons.modid + "|Forestry")
    public static AddonForestry instance;

    @Override
    public String getName() {
        return "Forestry";
    }

    @Override
    public boolean checkSide(Side side) {
        return side.isClient();
    }

    @Override
    public String[] getDependencies() {
        return new String[] { "Forestry@[2.2.6.0,)" };
    }

    @Override
    @PreInit
    public void preInit(FMLPreInitializationEvent ev) {
        doPreInit(ev);
    }

    @Override
    public void init(Side side) throws Exception {
        showSecret = NEIAddons.config.get(getName(), "Show Secret Mutations", false, "Set to true to show secret mutations").getBoolean(false);
        addBees = NEIAddons.config.get(getName(), "Add Bees to Search", true, "Set to true to add all bees to NEI search").getBoolean(false);
        addCombs = NEIAddons.config.get(getName(), "Add Combs to Search", false, "Set to true to add all combs that are produced by bees to NEI search").getBoolean(false);
        addSaplings = NEIAddons.config.get(getName(), "Add Saplings to Search", true, "Set to true to add all saplings to NEI search").getBoolean(false);
        addPollen = NEIAddons.config.get(getName(), "Add Pollen to Search", true, "Set to true to add all pollen types to NEI search").getBoolean(false);
        loadBlacklisted = NEIAddons.config.get(getName(), "Load blacklisted", false, "Set to true to load blacklisted species and alleles, it's dangerous and (mostly) useless").getBoolean(false);
        active = true;
    }

    public void registerWithNEIPlugins(String name, String id) {
        FMLInterModComms.sendRuntimeMessage(this, "NEIPlugins", "register-crafting-handler", String.format("Forestry Genetics@%s@%s", name, id));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void loadClient() {
        BeeHelper.setup();
        TreeHelper.setup();
        ButterflyHelper.setup();
    }
}
