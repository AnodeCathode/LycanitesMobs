package lycanite.lycanitesmobs.arcticmobs.item;

import java.util.List;

import lycanite.lycanitesmobs.AssetManager;
import lycanite.lycanitesmobs.LycanitesMobs;
import lycanite.lycanitesmobs.api.entity.EntityProjectileBase;
import lycanite.lycanitesmobs.arcticmobs.ArcticMobs;
import lycanite.lycanitesmobs.arcticmobs.entity.EntityFrostbolt;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFrostboltCharge extends Item {
	public static String itemName = "frostboltcharge";
	
	// ==================================================
	//                   Constructor
	// ==================================================
    public ItemFrostboltCharge() {
        super();
        setMaxStackSize(64);
        setCreativeTab(LycanitesMobs.itemsTab);
        setUnlocalizedName("frostboltcharge");
    }
    
    
	// ==================================================
	//                      Info
	// ==================================================
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
    	par3List.add("\u00a7a" + "Can be used to throw a");
    	par3List.add("\u00a7a" + "single frostbolt or");
    	par3List.add("\u00a7a" + "fired from a dispenser");
    	par3List.add("\u00a7a" + "for a frostbolt blast!");
    	super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    }
    
    
    // ==================================================
 	//                    Item Use
 	// ==================================================
     public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
         if(!par3EntityPlayer.capabilities.isCreativeMode) {
             --par1ItemStack.stackSize;
         }
         
         if(!par2World.isRemote) {
         	EntityThrowable projectile = new EntityFrostbolt(par2World, par3EntityPlayer);
             par2World.spawnEntityInWorld(projectile);
             par2World.playSoundAtEntity(par3EntityPlayer, ((EntityProjectileBase)projectile).getLaunchSound(), 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
         }

         return par1ItemStack;
     }
    
    
	// ==================================================
	//                      Visuals
	// ==================================================
    // ========== Get Icon ==========
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1) {
        return AssetManager.getIcon(itemName);
    }
    
    // ========== Register Icons ==========
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        AssetManager.addIcon(itemName, ArcticMobs.domain, "frostbolt", iconRegister);
    }
}
