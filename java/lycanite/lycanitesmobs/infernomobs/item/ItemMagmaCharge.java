package lycanite.lycanitesmobs.infernomobs.item;

import java.util.List;

import lycanite.lycanitesmobs.AssetManager;
import lycanite.lycanitesmobs.LycanitesMobs;
import lycanite.lycanitesmobs.api.entity.EntityProjectileBase;
import lycanite.lycanitesmobs.infernomobs.InfernoMobs;
import lycanite.lycanitesmobs.infernomobs.entity.EntityMagma;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagmaCharge extends Item {
	public static String itemName = "magmacharge";
	
	// ==================================================
	//                   Constructor
	// ==================================================
    public ItemMagmaCharge() {
        super();
        setMaxStackSize(64);
        setCreativeTab(LycanitesMobs.itemsTab);
        setUnlocalizedName(itemName);
    }
    
    
	// ==================================================
	//                      Info
	// ==================================================
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
    	par3List.add("\u00a7a" + "Can be used to throw a");
    	par3List.add("\u00a7a" + "ball of molten lava!");
    	par3List.add("\u00a7a" + "Can be fired from a");
    	par3List.add("\u00a7a" + "dispenser! Take care!");
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
         	EntityThrowable projectile = new EntityMagma(par2World, par3EntityPlayer);
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
        AssetManager.addIcon(itemName, InfernoMobs.domain, "magma", iconRegister);
    }
}
