package io.cubyz.blocks;

public class Water extends Block {

	public Water() {
		setTexture("water");
		setID("cubyz:water");
		setSelectable(false);
		setSolid(false);
		transparent = true;
	}
	
}