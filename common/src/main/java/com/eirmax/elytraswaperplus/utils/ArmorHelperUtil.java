package com.eirmax.elytraswaperplus.utils;


import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.concurrent.atomic.AtomicInteger;

public class ArmorHelperUtil {
    public static ItemStack getBestChestplate(Player player) {
        ItemStack best = null;
        int highestScore = -1;

        for (ItemStack stack : player.getInventory().items) {
            if (isChestplate(stack)) {
                int score = calculateScore(stack);
                if (score > highestScore) {
                    highestScore = score;
                    best = stack;
                }
            }
        }
        return best;
    }

    private static boolean isChestplate(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem &&
                ((ArmorItem) stack.getItem()).getEquipmentSlot().equals(EquipmentSlot.CHEST);
    }

    private static int calculateScore(ItemStack stack) {
        AtomicInteger score = new AtomicInteger();

        if (stack.getItem() == Items.NETHERITE_CHESTPLATE) score.addAndGet(3);
        else if (stack.getItem() == Items.DIAMOND_CHESTPLATE) score.addAndGet(2);
        else if (stack.getItem() == Items.IRON_CHESTPLATE) score.addAndGet(1);

        stack.getEnchantments().entrySet().forEach(e -> score.addAndGet(e.getIntValue()));
        return score.get();
    }
}