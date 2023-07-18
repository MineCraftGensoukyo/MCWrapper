package moe.sqwatermark.scriptwrapper.wrapper;

import com.google.common.collect.Multimap;
import com.google.gson.JsonParseException;
import moe.sqwatermark.scriptwrapper.api.level.entity.ILivingEntity;
import moe.sqwatermark.scriptwrapper.api.level.entity.IMob;
import moe.sqwatermark.scriptwrapper.api.level.item.IItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ItemStackWrapper implements IItemStack {

    @NotNull
    ItemStack stack;

    public ItemStackWrapper(@NotNull ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int getCount() {
        return stack.getCount();
    }

    @Override
    public void setCount(int count) {
        stack.setCount(count);
    }

    @Override
    public int getMaxStackSize() {
        return stack.getMaxStackSize();
    }

    @Override
    public String[] getLore() {
        CompoundTag compound = stack.getTagElement("display");
        if (compound != null && compound.getTagType("Lore") == Tag.TAG_LIST) {
            ListTag listTag = compound.getList("Lore", Tag.TAG_LIST);
            if (listTag.isEmpty()) {
                return new String[0];
            } else {
                List<String> lore = new ArrayList<>();
                for(int i = 0; i < listTag.size(); ++i) {
                    lore.add(listTag.getString(i));
                }
                return lore.toArray(new String[0]);
            }
        } else {
            return new String[0];
        }
    }

    @Override
    public void setLore(String[] lore) {
        CompoundTag compound = stack.getOrCreateTagElement("display");
        if (lore != null && lore.length != 0) {
            ListTag listTag = new ListTag();
            for (String s : lore) {
                try {
                    Component.Serializer.fromJson(s);
                } catch (JsonParseException e) {
                    s = Component.Serializer.toJson(Component.translatable(s));
                }
                listTag.add(StringTag.valueOf(s));
            }
            compound.put("Lore", listTag);
        } else {
            compound.remove("Lore");
        }
    }

    @Override
    public boolean isBlock() {
        Block block = Block.byItem(stack.getItem());
        return block != Blocks.AIR;
    }

    @Override
    public boolean isBook() {
        // TODO
        return false;
    }

    @Override
    public boolean isDamageable() {
        return stack.isDamageableItem();
    }

    @Override
    public int getDamage() {
        return stack.getDamageValue();
    }

    @Override
    public void setDamage(int value) {
        stack.setDamageValue(value);
    }

    @Override
    public void damageItem(int damage, @Nullable IMob<?> living) {
        if (living != null) {
            stack.hurtAndBreak(damage, living.getMCEntity(), (e) -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        } else if (stack.isDamageableItem()) {
            if (stack.getDamageValue() <= damage) {
                stack.shrink(1);
                stack.setDamageValue(0);
            } else {
                stack.setDamageValue(stack.getDamageValue() - damage);
            }
        }
    }

    @Override
    public int getMaxDamage() {
        return stack.getMaxDamage();
    }

    @Override
    public double getAttackDamage() {
        Multimap<Attribute, AttributeModifier> map = stack.getAttributeModifiers(EquipmentSlot.MAINHAND);
        double damage = 0.0;

        for (Map.Entry<Attribute, AttributeModifier> entry : map.entries()) {
            if (entry.getKey() == Attributes.ATTACK_DAMAGE) {
                AttributeModifier mod = entry.getValue();
                damage = mod.getAmount();
            }
        }

        return damage + (double) EnchantmentHelper.getDamageBonus(stack, MobType.UNDEFINED);
    }

    @Override
    public int getNutrition() {
        return stack.getItem().getFoodProperties() != null ? stack.getItem().getFoodProperties().getNutrition() : 0;
    }

    @Override
    public int getNutrition(ILivingEntity<?> entity) {
        FoodProperties foodProperties = stack.getItem().getFoodProperties(stack, entity.getMCEntity());
        if (foodProperties != null) {
            return foodProperties.getNutrition();
        } else {
            return 0;
        }
    }

    @Override
    public IItemStack copy() {
        return new ItemStackWrapper(stack.copy());
    }

    @Override
    public boolean hasCustomName() {
        return stack.hasCustomHoverName();
    }

    @Override
    public void setCustomName(String name) {
        stack.setHoverName(Component.translatable(name));
    }

    @Override
    public String getDisplayName() {
        return stack.getHoverName().getString();
    }

    @Override
    public String getItemName() {
        return stack.getItem().getName(stack).getString();
    }

    @Override
    public String getName() {
        return ForgeRegistries.ITEMS.getKey(stack.getItem()).toString();
    }

    @Override
    public ItemStack getMCItemStack() {
        return stack;
    }

    public static ItemStackWrapper create(ItemStack stack) {
        return new ItemStackWrapper(Objects.requireNonNullElse(stack, ItemStack.EMPTY));
    }

}
