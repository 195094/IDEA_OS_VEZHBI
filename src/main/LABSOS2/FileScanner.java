package main.LABSOS2;

/*Да се имплементира класа FileScanner која што ќе се однесува како thread. Во класата FileScanner
се чуваат податоци за : - името на директориумот што треба да се скенира - статичка променлива counter
 што ќе брои колку нишки од класата FileScanner ќе се креираат Во класата FileScanner да се
  имплементираа статички методот што ќе печати информации за некоја датотека од следниот формат:

dir: lab1 - reshenija 100 (dir за директориуми, името на директориумот и број на фајлови)

file: spisok.pdf 29198 (file за обични фајлови, име на фајл и големина)

Дополнително да се преоптовари методот run() од класата Thread, така што ќе печати информации за
директориумот за којшто е повикан. Доколку во директориумот има други под директориуми, да се креира
 нова нишка од тип FileScanner што ќе ги прави истите работи како и претходно за
 фајловите/директориумите што се наоѓаат во тие директориуми (рекурзивно).

На крај да се испечати вредноста на counter-от, односно колку вкупно нишки биле креирани.
Користете го следниот почетен код.*/

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class FileScanner extends Thread {

    private String fileToScan;
    //TODO: Initialize the start value of the counter
    private static Long counter=0L;
    Semaphore s1 = new Semaphore(1);


    public FileScanner (String fileToScan) {
        this.fileToScan=fileToScan;
        //TODO: Increment the counter on every creation of FileScanner object
        try {
            s1.acquire();
            counter++;
            s1.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printInfo(File file)  {

        /*
         * TODO: Print the info for the @argument File file, according to the requirement of the task
         * */
        if(file.exists()){
            if(file.isDirectory()){
                System.out.println("dir: "+file.getName()+" - "+file.list().length);
            }
            if(file.isFile()){
                System.out.println("file: "+file.getName()+" - "+file.length());
            }
        }

    }

    public static Long getCounter () {
        return counter;
    }


    public void run() {

        //TODO Create object File with the absolute path fileToScan.
        File file=new File(fileToScan);

        //TODO Create a list of all the files that are in the directory file.
        File [] files = file.listFiles();

        HashSet<Thread> threads = new HashSet<Thread>();
        for (File f : files) {

            /*
             * TODO If the File f is not a directory, print its info using the function printInfo(f)
             * */
                if(!f.isDirectory()){
                    printInfo(f);
                }else{
                    printInfo(f);
                    FileScanner thread1=new FileScanner(f.getAbsolutePath());
                    threads.add(thread1);
                    thread1.start();
                }
            /*
             * TODO If the File f is a directory, create a thread from type FileScanner and start it.
             * */
            for(Thread t1 : threads){
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //TODO: wait for all the FileScanner-s to finish
        }

    }

    public static void main (String [] args) throws InterruptedException {
        String FILE_TO_SCAN = "C:\\Users\\user\\Desktop\\TESTOSLAB2ZAD2";

        //TODO Construct a FileScanner object with the fileToScan = FILE_TO_SCAN
        FileScanner fileScanner = new FileScanner(FILE_TO_SCAN);

        //TODO Start the thread from type FileScanner
        fileScanner.start();
        //TODO wait for the fileScanner to finish
        fileScanner.join();
        //TODO print a message that displays the number of thread that were created
        System.out.println(FileScanner.getCounter());

    }
}
