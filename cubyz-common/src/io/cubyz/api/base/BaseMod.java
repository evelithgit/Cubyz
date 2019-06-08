package io.cubyz.api.base;

import io.cubyz.api.EventHandler;
import io.cubyz.api.Mod;
import io.cubyz.api.Registry;
import io.cubyz.blocks.Bedrock;
import io.cubyz.blocks.Block;
import io.cubyz.blocks.Cactus;
import io.cubyz.blocks.CoalOre;
import io.cubyz.blocks.CobbleStone;
import io.cubyz.blocks.DiamondOre;
import io.cubyz.blocks.Dirt;
import io.cubyz.blocks.EmeraldOre;
import io.cubyz.blocks.GoldOre;
import io.cubyz.blocks.Grass;
import io.cubyz.blocks.Ice;
import io.cubyz.blocks.IronOre;
import io.cubyz.blocks.OakLeaves;
import io.cubyz.blocks.OakLog;
import io.cubyz.blocks.RubyOre;
import io.cubyz.blocks.Sand;
import io.cubyz.blocks.SnowGrass;
import io.cubyz.blocks.Stone;
import io.cubyz.blocks.Water;
import io.cubyz.entity.EntityType;
import io.cubyz.entity.PlayerEntity;

/**
 * Mod adding Cubyz default content.
 */
@Mod(id = "cubyz", name = "Cubyz")
public class BaseMod {
	
	// Entities:
	static PlayerEntity player;
	
	// Blocks:
	static Bedrock bedrock;
	static Cactus cactus;
	static CobbleStone cobblestone;
	static Dirt dirt;
	static Grass grass;
	static Ice ice;
	static OakLeaves oakLeaves;
	static OakLog oakLog;
	static Sand sand;
	static SnowGrass snow;
	static Stone stone;
	
	// Ores:
	static CoalOre coal;
	static DiamondOre diamond;
	static EmeraldOre emerald;
	static GoldOre gold;
	static IronOre iron;
	static RubyOre ruby;
	
	// Fluid:
	static Water water;
	
	@EventHandler(type = "init")
	public void init() {
		System.out.println("Init!");
	}
	
	@EventHandler(type = "entity/register")
	public void registerEntities(Registry<EntityType> reg) {
		player = new PlayerEntity();
		
		reg.register(player);
	}
	
	@EventHandler(type = "block/register")
	public void registerBlocks(Registry<Block> reg) {
		
		// Normal
		bedrock = new Bedrock();
		cactus = new Cactus();
		cobblestone = new CobbleStone();
		dirt = new Dirt();
		grass = new Grass();
		ice = new Ice();
		oakLeaves = new OakLeaves();
		oakLog = new OakLog();
		sand = new Sand();
		snow = new SnowGrass();
		stone = new Stone();
		
		// Ores
		coal = new CoalOre();
		diamond = new DiamondOre();
		emerald = new EmeraldOre();
		gold = new GoldOre();
		iron = new IronOre();
		ruby = new RubyOre();
		
		
		// Fluids
		water = new Water();
		
		// Make some special block drops that cannot be done within the constructor due to uncertainty
		grass.setBlockDrop(dirt.getBlockDrop());
		snow.setBlockDrop(dirt.getBlockDrop());
		stone.setBlockDrop(cobblestone.getBlockDrop());
		
		// Register
		reg.registerAll(bedrock, cactus, cobblestone, dirt, grass, ice, oakLeaves, oakLog, sand, snow, stone, coal, diamond, emerald, gold, iron, ruby, water);
	}
	
}