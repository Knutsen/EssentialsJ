package de.jonah.enchantments;// don´t look at this mess ~Jonah

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants {
    public static final Enchantment TELEPATHY = new EnchantmentWrapper("telepathy", "Telepathy", 1);
    public static final Enchantment TELEPORT = new EnchantmentWrapper("teleport", "Teleport", 1);
    public static final Enchantment ARROWHOOK = new EnchantmentWrapper("arrowhook", "Arrowhook", 1);
    public static final Enchantment EXPLOSIVESHOT = new EnchantmentWrapper("explosiveshot", "Explosiveshot", 1);
    public static final Enchantment LIFESTEAL = new EnchantmentWrapper("lifesteal", "LIFESTEAL", 1);

    public static void register() {
        boolean telepathyregistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.TELEPATHY);
        boolean teleportregistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.TELEPORT);
        boolean arrowhookregistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.ARROWHOOK);
        boolean activeregistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.LIFESTEAL);
        boolean explosiveshotregistred = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.EXPLOSIVESHOT);


        if (!telepathyregistered) {
            registerEnchantments(TELEPATHY);
        }
        if (!teleportregistered) {
            registerEnchantments(TELEPORT);
        }
        if (!arrowhookregistered) {
            registerEnchantments(ARROWHOOK);
        }
        if (!activeregistered) {
            registerEnchantments(LIFESTEAL);
        }
        if (!explosiveshotregistred){
            registerEnchantments(EXPLOSIVESHOT);
        }

    }

    public static void registerEnchantments(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();

        }
        if (registered) {
            System.out.println("Enchantment is registered!");
        }
    }
}
