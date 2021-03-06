package io.cubyz.world.cubyzgenerators;

import java.util.Random;

import io.cubyz.api.Resource;
import io.cubyz.blocks.Block;
import io.cubyz.math.CubyzMath;
import io.cubyz.world.cubyzgenerators.biomes.Biome;
import io.cubyz.world.cubyzgenerators.biomes.StructureModel;

public class StructureGenerator implements FancyGenerator {
	
	@Override
	public Resource getRegistryID() {
		return new Resource("cubyz", "lifeland_vegetation");
	}
	
	@Override
	public int getPriority() {
		return 131072; // Comes somewhere after cave generation.
	}

	@Override
	public void generate(long seed, int cx, int cz, Block[][][] chunk, boolean[][] vegetationIgnoreMap, float[][] heatMap, float[][] heightMap, Biome[][] biomeMap, byte[][][] blockData, int worldSize) {
		int wx = cx << 4;
		int wz = cz << 4;
		Random rand = new Random(seed + 3*(seed + 1 & Integer.MAX_VALUE));
		int rand1 = rand.nextInt() | 1;
		int rand2 = rand.nextInt() | 1;
		// Go through all positions in this and ±½ chunks to determine if there is a tree and if yes generate it.
		for(int px = 0; px < 32; px++) {
			for(int pz = 0; pz < 32; pz++) {
				if(!vegetationIgnoreMap[px][pz]) {
					rand.setSeed((CubyzMath.worldModulo((px-8+wx), worldSize)*rand1 << 32) ^ CubyzMath.worldModulo((pz-8+wz), worldSize)*rand2 ^ seed);
					float randomValue = rand.nextFloat();
					for(StructureModel model : biomeMap[px][pz].vegetationModels()) {
						if(model.getChance() > randomValue) {
							model.generate(px-8, pz-8, (int)heightMap[px][pz]+1, chunk, heightMap, rand);
							break;
						} else {
							randomValue = (randomValue - model.getChance())/(1 - model.getChance()); // Make sure that after the first one was considered all others get the correct chances.
						}
					}
				}
			}
		}
	}

}
