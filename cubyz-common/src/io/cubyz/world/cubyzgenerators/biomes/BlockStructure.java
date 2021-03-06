package io.cubyz.world.cubyzgenerators.biomes;

import java.util.Random;

import io.cubyz.blocks.Block;

// Stores the vertical structure of a biome from top to bottom.
// TODO: Randomly variable structure(like top-block is either ice or snow, or there are 4-7 sand blocks on top).

public class BlockStructure {
	private final BlockStack[] structure;
	public BlockStructure(BlockStack ... blocks) {
		structure = blocks;
	}
	public int addSubTerranian(Block[][][] chunk, byte[][][] data, int depth, int x, int z, int highResDepth, Random rand) {
		int startingDepth = depth;
		for(int i = 0; i < structure.length; i++) {
			for(int j = 0; j < structure[i].min; j++) {
				chunk[x][z][depth--] = structure[i].block;
				data[x][z][depth+1] = structure[i].block.mode.getNaturalStandard();
				if(i == 0 && j == 0 && structure[i].block.mode.getRegistryID().toString().equals("cubyz:stackable")) {
					data[x][z][depth+1] = (byte)highResDepth;
				}
				if(depth <= 0) return depth;
			}
			int variation = rand.nextInt(1 + structure[i].max - structure[i].min);
			for(int j = 0; j < variation; j++) {
				chunk[x][z][depth--] = structure[i].block;
				if(depth <= 0) return depth;
			}
		}
		if(depth == startingDepth) return depth;
		return depth + 1;
	}
	
	public static class BlockStack {
		private final Block block;
		private final int min;
		private final int max;
		public BlockStack(Block block, int min, int max) {
			this.block = block;
			this.min = min;
			this.max = max;
		}
	}
}
