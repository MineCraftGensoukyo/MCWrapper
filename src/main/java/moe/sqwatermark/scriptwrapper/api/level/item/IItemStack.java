package moe.sqwatermark.scriptwrapper.api.level.item;

import moe.sqwatermark.scriptwrapper.api.level.entity.ILivingEntity;
import moe.sqwatermark.scriptwrapper.api.level.entity.IMob;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface IItemStack {

    boolean isEmpty();

    int getCount();

    void setCount(int count);

    int getMaxStackSize();

    String[] getLore();

    void setLore(String[] lore);

    boolean isBlock();

    boolean isBook();

    boolean isDamageable();

    int getDamage();

    void setDamage(int value);

    void damageItem(int damage, @Nullable IMob<?> living);

    int getMaxDamage();

    double getAttackDamage();

    int getNutrition();

    int getNutrition(ILivingEntity<?> entity);

    IItemStack copy();

    boolean hasCustomName();

    void setCustomName(String name);

    String getDisplayName();

    String getItemName();

    String getName();

    ItemStack getMCItemStack();

}
