package lycanite.lycanitesmobs.demonmobs.entity;

import lycanite.lycanitesmobs.AssetManager;
import lycanite.lycanitesmobs.api.entity.EntityProjectileBase;
import lycanite.lycanitesmobs.demonmobs.DemonMobs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityDemonicBlast extends EntityProjectileBase {
	
	// Properties:
	public Entity shootingEntity;
	private float projectileWidth = 1f;
	private float projectileHeight = 1f;
	public int expireTime = 15;
	
	// Rapid Fire:
	private int rapidTicks = 0;
	private int rapidDelay = 5;
	
    // ==================================================
 	//                   Constructors
 	// ==================================================
    public EntityDemonicBlast(World par1World) {
        super(par1World);
        this.setSize(projectileWidth, projectileHeight);
    }

    public EntityDemonicBlast(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
        this.setSize(projectileWidth, projectileHeight);
    }

    public EntityDemonicBlast(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.setSize(projectileWidth, projectileHeight);
    }
    
    // ========== Setup Projectile ==========
    public void setup() {
    	this.entityName = "demonicblast";
    	this.mod = DemonMobs.instance;
    	this.setBaseDamage(8);
    	this.setProjectileScale(2.5F);
    }
	
    
    // ==================================================
 	//                   Update
 	// ==================================================
    @Override
    public void onUpdate() {
    	super.onUpdate();
    	if(!this.worldObj.isRemote) {
	    	if(rapidTicks % 5 == 0 && !isDead) {
	    		fireProjectile();
	    		fireProjectile();
	    	}
	    	if(rapidTicks == Integer.MAX_VALUE)
	    		rapidTicks = -1;
	    	rapidTicks++;
    	}
    	
    	if(this.posY > this.worldObj.getHeight() + 20)
    		this.setDead();
    	
    	if(this.ticksExisted >= this.expireTime * 20)
    		this.setDead();
    }
	
    
    // ==================================================
 	//                 Fire Projectile
 	// ==================================================
    public void fireProjectile() {
    	World world = this.worldObj;
    	
		IProjectile projectile;
		if(this.getThrower() != null) {
			projectile = (IProjectile) new EntityDemonicSpark(world, this.getThrower());
			if(projectile instanceof Entity) {
				((Entity)projectile).posX = this.posX;
				((Entity)projectile).posY = this.posY;
				((Entity)projectile).posZ = this.posZ;
			}
		}
		else
			projectile = (IProjectile) new EntityDemonicSpark(world, this.posX, this.posY, this.posZ);
		float velocity = 1.2F;
		double motionT = this.motionX + this.motionY + this.motionZ;
		if(this.motionX < 0) motionT -= this.motionX * 2;
		if(this.motionY < 0) motionT -= this.motionY * 2;
		if(this.motionZ < 0) motionT -= this.motionZ * 2;
        projectile.setThrowableHeading(this.motionX / motionT + (rand.nextGaussian() - 0.5D), this.motionY / motionT + (rand.nextGaussian() - 0.5D), this.motionZ / motionT + (rand.nextGaussian() - 0.5D), velocity, 0);
        
        if(projectile instanceof EntityProjectileBase)
        	this.playSound(((EntityProjectileBase)projectile).getLaunchSound(), 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
        
        world.spawnEntityInWorld((Entity)projectile);
    }
	
    
    // ==================================================
 	//                   Movement
 	// ==================================================
    // ========== Gravity ==========
    @Override
    protected float getGravityVelocity() {
        return 0.0001F;
    }
    
    
    // ==================================================
 	//                     Impact
 	// ==================================================
    //========== Entity Living Collision ==========
    @Override
    public boolean entityLivingCollision(EntityLivingBase entityLiving) {
    	entityLiving.addPotionEffect(new PotionEffect(Potion.wither.id, this.getEffectDuration(10), 0));
    	return true;
    }
    
    //========== On Impact Splash/Ricochet ==========
    @Override
    public void onImpact() {
    	for(int i = 0; i < 8; ++i)
    		fireProjectile();
    }
    
    //========== On Impact Particles/Sounds ==========
    @Override
    public void onImpactVisuals() {
    	for(int i = 0; i < 8; ++i)
    		this.worldObj.spawnParticle("reddust", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
    }
    
    
    // ==================================================
 	//                      Sounds
 	// ==================================================
    @Override
    public String getLaunchSound() {
    	return AssetManager.getSound("DemonicBlast");
    }
    
    
    // ==================================================
    //                   Brightness
    // ==================================================
    public float getBrightness(float par1) {
        return 1.0F;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        return 15728880;
    }
}
