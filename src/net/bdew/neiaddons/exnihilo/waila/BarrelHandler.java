/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/neiaddons
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/neiaddons/master/MMPL-1.0.txt
 */

package net.bdew.neiaddons.exnihilo.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.text.DecimalFormat;
import java.util.List;

public class BarrelHandler implements IWailaDataProvider {
    private DecimalFormat decFormat = new DecimalFormat("0.0");

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        NBTTagCompound tag = accessor.getNBTData();

        float volume = tag.getFloat("volume");
        int timer = tag.getInteger("timer");
        Fluid fluid = FluidRegistry.getFluid(tag.getShort("fluid"));

        if (fluid != null && fluid.getID() == FluidRegistry.LAVA.getID() && volume > 0 && (accessor.getBlock().getMaterial().getCanBurn())) {
            currenttip.add(EnumChatFormatting.RED + I18n.format("bdew.exnihilo.barrel.burn", decFormat.format((400F - timer) / 20)) + EnumChatFormatting.RESET);
        }

        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }
}
