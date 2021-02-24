package elements;

import java.awt.Graphics;

@FunctionalInterface
public interface Renderable {
	public void render(Graphics graphics, int offsetX, int offsetY);
}
