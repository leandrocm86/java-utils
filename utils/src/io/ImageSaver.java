package io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSaver {
	
	public static void salvaImagem(BufferedImage img, CharSequence path) {
		File arquivo = new File(path.toString());
        try {
			ImageIO.write(img, "png", arquivo);
		} catch (IOException e) {
			throw new IllegalStateException("Nao foi possivel salvar aquivo de imagem!", e);
		}
	}

}
