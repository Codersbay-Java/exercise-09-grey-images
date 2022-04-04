package application;

import java.awt.Color;

import picture.Picture;

public class Filter {
	private Picture picture;

	public Filter(Picture picture) {
		this.picture = picture;
	}

	/**
	 * The input picture should be converted to a grey scale. To achieve a grey
	 * image we need to sum up the red, green and blue value and divide it by 3.
	 * 
	 * @return converted picture
	 */
	public Picture greyScaleFilter() {
		for (int x = 0; x < picture.width(); x++) {
			for (int y = 0; y < picture.height(); y++) {
				int grayValue = (picture.get(x, y).getRed() + picture.get(x, y).getGreen()
						+ picture.get(x, y).getBlue()) / 3;
				Color gray = new Color(grayValue, grayValue, grayValue);
				picture.set(x, y, gray);
			}
		}
		return picture;
	}

	/**
	 * As we have a range until 255 inclusive getting the inverted image is easy.
	 * Just subtract the color value from 255.
	 * 
	 * @return converted picture
	 */
	public Picture revertColorFilter() {
		for (int x = 0; x < picture.width(); x++) {
			for (int y = 0; y < picture.height(); y++) {
				Color currentColor = picture.get(x, y);
				int r = 255 - currentColor.getRed();
				int g = 255 - currentColor.getGreen();
				int b = 255 - currentColor.getBlue();

				Color inverted = new Color(r, g, b);
				picture.set(x, y, inverted);
			}
		}
		return picture;
	}

	//@formatter:off
	/**
	 * A sepia filter is a little more difficult to calculate.
	 * to get the
	 * 
	 * sepia red: (current red * 0.393) + (current green * 0.769) + (current blue * 0.189)
	 * sepia green: (current red * 0.349) + (current green * 0.686) + (current blue * 0.168)
	 * sepia blue: (current red * 0.292) + (current green * 0.534) + (current blue * 0.131)
	 * 
	 * the new value is then Math.min(sepia red, 255) and so on.
	 * 
	 * @return converted picture
	 */
	//@formatter:on

	public Picture sepiaFilter() {
		for (int x = 0; x < picture.width(); x++) {
			for (int y = 0; y < picture.height(); y++) {
				Color currentColor = picture.get(x, y);
				//@formatter:off
				int sepiaRed = (int) ((currentColor.getRed() * 0.393) + (currentColor.getGreen() * 0.769) + (currentColor.getBlue() * 0.189));
				int sepiaGreen = (int) ((currentColor.getRed() * 0.349) + (currentColor.getGreen() * 0.686) + (currentColor.getBlue() * 0.168));
				int sepiaBlue = (int) ((currentColor.getRed() * 0.292) + (currentColor.getGreen() * 0.534) + (currentColor.getBlue() * 0.131));
				//@formatter:on

				Color inverted = new Color(Math.min(sepiaRed, 255), Math.min(sepiaGreen, 255),
						Math.min(sepiaBlue, 255));
				picture.set(x, y, inverted);
			}
		}
		return picture;
	}
}
