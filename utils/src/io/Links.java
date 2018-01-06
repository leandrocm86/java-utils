package io;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import utils.Str;

public class Links {
	
	public static String getRealTargetPath(Str linkPath) {
		if (linkPath == null || !linkPath.contains(".lnk"))
			throw new IllegalArgumentException("Tentativa invalida de ler link do windows!");
		Str parentPath = linkPath.ateUltimo("\\", false);
		Str fileName = linkPath.desdeUltimo("\\", false).ate(" - ", false);
		
		while(parentPath.contains("\\")) {
			Collection<File> files = FileUtils.listFiles(new File(parentPath.val()), new IOFileFilter() {
				@Override
				public boolean accept(File arg0, String arg1) {
					return arg0.getName().contains(fileName) && !arg0.getName().contains(".lnk");
				}
				@Override
				public boolean accept(File arg0) {
					return arg0.getName().contains(fileName) && !arg0.getName().contains(".lnk");
				}
			}, TrueFileFilter.INSTANCE);
			
			if (files.size() > 0) {
				return files.iterator().next().getAbsolutePath();
			}
			
			parentPath.val(parentPath.ateUltimo("\\", false));
		}
		
		throw new IllegalArgumentException("Nao foi possivel encontrar diretorio real do link: " + linkPath);
	}
}
