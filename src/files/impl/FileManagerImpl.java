package files.impl;

import exceptions.FileExistsException;
import files.FileManager;



import java.io.*;
import java.util.Date;


public class FileManagerImpl  implements FileManager {
    @Override
    public void createNewFile(String file) throws IOException, FileExistsException {
        File f = new File(file);
        if(f.exists())
            throw new FileExistsException();
        f.createNewFile();
    }

    @Override
    public File[] getFilesInFolder(File file) throws FileNotFoundException {
        if(!file.exists()) throw new FileNotFoundException();
        if(!file.isDirectory()) throw new FileNotFoundException();
        return file.listFiles();
    }

    @Override
    public void printFileNames(File file, PrintStream writer) throws FileNotFoundException {
        File [] files = getFilesInFolder(file);
        for(File f : files){
            writer.println(f.getName());
        }
    }

    @Override
    public String getAbsolutePath(String relativePath) throws FileNotFoundException {
        File file = new File(relativePath);
        if(file.exists()){
            return file.getAbsolutePath();
        }
        else{
            throw new FileNotFoundException();
        }
    }

    @Override
    public long getFileSize(String file) throws FileNotFoundException {
        File F = new File(file);
        if(!F.exists())
            throw new FileNotFoundException();
        return F.length();
    }

    @Override
    public void printFilePermissions(File f, PrintStream writer) throws FileNotFoundException {
        if(!f.exists())
            throw new FileNotFoundException();
        System.out.printf("Readable: %x"+f.canRead());
        System.out.printf("Writable: %x"+f.canWrite());
        System.out.printf("Executable: %x"+f.canExecute());
    }

    @Override
    public void createFolder(String folder) throws FileExistsException {
        File f = new File(folder);
        if(f.exists())
            throw new FileExistsException();
        f.mkdir();
    }

    @Override
    public void renameFile(File src, File dest) throws FileExistsException, FileNotFoundException {
            if(!src.exists())
                throw new FileNotFoundException();
            if(!dest.exists())
                throw new FileNotFoundException();
            src.renameTo(dest);
    }

    @Override
    public Date getLastModified(String filePath) throws FileNotFoundException {
        File F = new File(filePath);
        if(!F.exists())
            throw new FileNotFoundException();
        return new Date(F.lastModified());
    }

    @Override
    public boolean deleteFolder(File folder) throws FileNotFoundException {
        if(folder.isDirectory() && folder.exists()){
            File f[] = folder.listFiles();

            for(File fo : f){
                if(fo.isDirectory()){
                    deleteFolder(fo);
                }
                fo.delete();
            }
        }
        else{
            throw new FileNotFoundException();
        }
        return folder.delete();
    }

    @Override
    public void largestFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if(!f.exists()){
            throw new FileNotFoundException();
        }
        File [] files = f.listFiles();
        if(files!=null) {
            long maxSize = 0;
            String maxName = "";
            for (File f1 : files) {
                if (f1.length() > maxSize) {
                    maxSize = f1.length();
                    maxName = f1.getAbsolutePath();
                }
            }
            System.out.println(maxName+" "+maxSize);
        }
    }

    @Override
    public File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException {
        File f = new File(dirPath);
        if(!f.exists())
            throw new FileNotFoundException();
        if(!f.isDirectory())
            throw new FileNotFoundException();
        return f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".png")||name.endsWith(".jpg");
            }
        });
    }

    @Override
    public void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException();
        if(!file.isDirectory())
            throw new FileNotFoundException();
        File [] files = file.listFiles();

        for(File f:files){
            if(f.isDirectory())
                filterImagesFilesInDirRec(f,out);
            if (f.isFile() && (f.getName().endsWith(".png") || f.getName().endsWith(".jpg")))
                out.println(f.getAbsolutePath());
        }
    }


}
