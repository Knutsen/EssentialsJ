package de.jonah.enchantments;// don´t look at this mess ~Jonah

import de.jonah.utils.FileConfig;
import de.jonah.utils.LocationConfig;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Objects;

public class CustomEnchantsListener implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Entity taker = event.getEntity();
            Player damagerPlayer = (Player) event.getDamager();
            double d = (damagerPlayer.getHealth() + event.getFinalDamage());
            if(damagerPlayer.getInventory().getItemInMainHand().getItemMeta() == null) return;
            if ((damagerPlayer.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.LIFESTEAL))) {
                if (d >= 20.0) {
                    damagerPlayer.setMaxHealth(d);
                    damagerPlayer.setHealthScale(damagerPlayer.getMaxHealth());
                }
                damagerPlayer.setHealth(d);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        player.setHealthScale(20);
        player.setMaxHealth(20);
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (player.getHealth() >= 20) {
            player.setMaxHealth(player.getHealth());
            player.setHealthScale(player.getHealth());
        } else {
            player.setMaxHealth(20);
            player.setHealthScale(20);
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity taker = event.getHitEntity();
        Block block = event.getHitBlock();
        Projectile projectile = event.getEntity();

        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player shooter = (Player) projectile.getShooter();
        if (shooter == null) return;
        if (taker == null) {
            if (Objects.requireNonNull(shooter.getInventory().getItemInMainHand().getItemMeta()).hasEnchant(CustomEnchants.ARROWHOOK)) {
                FileConfig projectilehitblock = new FileConfig("locations.yml");
                projectilehitblock.set("BlockHitLoc", LocationConfig.loc2str(block.getLocation().add(0, 1, 0)));
                projectilehitblock.saveConfig();
                LocationConfig.teleport(shooter, LocationConfig.str2loc(Objects.requireNonNull(projectilehitblock.getString("BlockHitLoc"))));
            } else if (Objects.requireNonNull(shooter.getInventory().getItemInMainHand().getItemMeta()).hasEnchant(CustomEnchants.EXPLOSIVESHOT)) {
                FileConfig projectilehitblock = new FileConfig("locations.yml");
                projectilehitblock.set("BlockHitLoc", LocationConfig.loc2str(block.getLocation()));
                projectilehitblock.saveConfig();
                shooter.getWorld().spawnEntity(LocationConfig.str2loc(Objects.requireNonNull(projectilehitblock.getString("BlockHitLoc"))), EntityType.PRIMED_TNT);
                projectile.remove();
            }
        } else if (shooter.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEPORT)) {
            FileConfig projectilehitentity = new FileConfig("locations.yml");
            projectilehitentity.set("EntityHitLoc", LocationConfig.loc2str(taker.getLocation()));
            projectilehitentity.saveConfig();
            LocationConfig.teleport(shooter, LocationConfig.str2loc(Objects.requireNonNull(projectilehitentity.getString("EntityHitLoc"))));
        }
    }

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent event) {
        if (event.getEntity() instanceof TNTPrimed) {
                final TNTPrimed primed = (TNTPrimed) event.getEntity();
                primed.setFuseTicks(1);
        }
    }
}