package main;
import files.FileManager;
import files.impl.FileManagerImpl;
import streams.IOStreamManager;
import streams.impl.IOStreamManagerImpl;

import exceptions.FileExistsException;


import java.io.*;
import java.util.Scanner;

public class IOStreamMain {
    public static void main(String[] args) throws FileExistsException, IOException {
        File f = new File("C:\\Users\\user\\Desktop\\os-2021-vezhbi\\src\\random\\wowowo.txt");
        File f1 = new File("C:\\Users\\user\\Desktop\\os-2021-vezhbi\\src\\random\\wow.txt");
        IOStreamManager ioStreamManager = new IOStreamManagerImpl();
        FileManager fileManager = new FileManagerImpl();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
        BufferedReader bufferedReader = new BufferedReader((new FileReader(f)));
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out);

        String pomos = null;
        // pomos = scanner.nextLine();
        //stringBuilder.append(pomos);

        //fileManager.createNewFile("C:\\Users\\user\\Desktop\\os-2021-vezhbi\\src\\random\\wow.txt");
        //fileManager.createFolder("C:\\Users\\user\\Desktop\\os-2021-vezhbi\\src\\novo");

        ioStreamManager.writeToRandomAccessFile(f1);
        ioStreamManager.readFromRandomAccessFile(f1,printStream);
        //ioStreamManager.writeToTextFile(f,pomos,true);
       // ioStreamManager.printContentOfTxtFile(f,printStream);
       // ioStreamManager.readFileWithLineNumber(f,printStream);
       // bufferedWriter.write(stringBuilder.toString());
        //String s = null;
       // while((s = bufferedReader.readLine()) != null){
        //    printStream.println(s);
        //}


       // fileManager.printFileNames(new File(s),System.out);
        //fileManager.getLastModified(s);

       // f.getPath();
    }
}

