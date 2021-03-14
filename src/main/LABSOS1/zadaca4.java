package main.LABSOS1;
import java.io.*;

import java.util.Scanner;

import java.util.concurrent.TimeUnit;


public class zadaca4 {
    public static void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException();
        if(!file.isDirectory())
            throw new FileNotFoundException();
        File [] files = file.listFiles();
        if(files!=null) {
            for (File f : files) {
                if (f.isDirectory())
                    filterImagesFilesInDirRec(f, out);
                if (f.isFile() && (f.getName().endsWith(".jpg") || f.getName().endsWith(".bmp"))){
                    long lastmod = f.lastModified();
                    long weekAgo = System.currentTimeMillis() - TimeUnit.DAYS.toDays(7);
                    if(lastmod<=weekAgo)
                        out.println(f.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        PrintStream printStream = new PrintStream(System.out);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        File F = new File(name);
        if(!F.exists()){
            throw new FileNotFoundException();
        }
        filterImagesFilesInDirRec(F,printStream);

    }
}
