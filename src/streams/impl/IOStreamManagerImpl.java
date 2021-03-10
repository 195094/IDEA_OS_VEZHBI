package streams.impl;


import streams.IOStreamManager;

import java.io.*;

public class IOStreamManagerImpl implements IOStreamManager {


    @Override
    public void copyFileByteByByte(File from, File to) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            inputStream = new FileInputStream(from);
            outputStream = new FileOutputStream(to);
            int c = -1;
            while((c=inputStream.read())!= -1){
                outputStream.write(c);
                outputStream.flush();
            }
        }catch (IOException ex){
            System.out.println("nesh nesh exc");
        }
        finally {
            if(inputStream==null)inputStream.close();
            if(outputStream==null)outputStream.close();
        }
    }

    @Override
    public void printContentOfTxtFile(File f, PrintStream printer) throws IOException {
           BufferedReader bufferedReader = null;
            if(!f.exists()){
               throw new FileNotFoundException();
           }
           try{
               bufferedReader = new BufferedReader(new FileReader(f));
               String s=null;
               while((s=bufferedReader.readLine()) != null){
                    printer.println(s);

               }
           }catch (IOException ex){
               System.out.println("IOException: printContentOfTxtFile()");
           }finally {
                if(bufferedReader==null) bufferedReader.close();
           }
    }

    @Override
    public void readContentFromStdInput(OutputStream to) throws IOException {
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            try{
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(to));
                String line = null;
                while((line = bufferedReader.readLine())!=null){
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }

            }catch (IOException ex){
                System.out.println("bkbblblb");
            }finally {
                if(bufferedReader == null) bufferedReader.close();
                if(bufferedWriter == null) bufferedWriter.close();
            }
    }

    @Override
    public void writeToTextFile(File to, String text, Boolean append) throws IOException {
            if(!to.exists()){
                throw new FileNotFoundException();
            }
                BufferedWriter bufferedWriter= null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(to, append));
                bufferedWriter.write(text+"\n");
            }catch (IOException ex){
                    System.out.println("smthssh");
            }finally {
                if(bufferedWriter!=null){
                    bufferedWriter.flush();
                    bufferedWriter.close();}
            }
    }

    @Override
    public void memoryUnsafeTextFileCopy(File from, File to) throws IOException {
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;
            StringBuilder stringBuilder = new StringBuilder();
            try{
                bufferedReader = new BufferedReader(new FileReader(from));
                bufferedWriter = new BufferedWriter(new FileWriter(to));
                String line =null;

                while((line=bufferedReader.readLine())!=null)
                    stringBuilder.append(line).append("\n");

                bufferedWriter.write(stringBuilder.toString());

            }catch (IOException ex){
                System.out.println("nesheshseens");
            }finally {
                if(bufferedReader != null) bufferedReader.close();
                if(bufferedWriter != null) bufferedWriter.flush(); bufferedWriter.close();
            }
    }

    @Override
    public void memorySafeTextFileCopy(File from, File to) throws IOException {
            BufferedReader bufferedReader =null;
            BufferedWriter bufferedWriter =null;
            try{
                 bufferedReader = new BufferedReader(new FileReader(from));
                 bufferedWriter = new BufferedWriter(new FileWriter(to));
                 String s = null;
                 while((s=bufferedReader.readLine())!=null){
                     bufferedWriter.write(s);
                     bufferedWriter.newLine();
                 }
            }catch (IOException ex){
                System.out.println("shshshsha");
            }finally {
                if(bufferedReader!=null) bufferedReader.close();
                if(bufferedWriter!=null) {
                    bufferedWriter.close();
                    bufferedWriter.close();
                }
            }
    }

    @Override
    public void readFileWithLineNumber(File from, OutputStream outputStream) throws IOException {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try{
          bufferedReader = new BufferedReader(new FileReader(from));
          printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
          int linenum = 1;
          String line = null;
          while((line=bufferedReader.readLine())!=null){
                printWriter.printf("%d: %s \n",linenum++,line);
          }
        }catch (IOException ex){
            System.out.println("Exception: readFileWithLineNumber");

        }finally {
            if(bufferedReader!=null) bufferedReader.close();
            if(printWriter!=null){
                printWriter.flush();
                printWriter.close();}
        }
    }

    @Override
    public void writeBinaryDataToBFile(File to, Object... objects) throws IOException {
            DataOutputStream dataOutputStream = null;
            try{
                dataOutputStream = new DataOutputStream(new FileOutputStream(to));
                for(Object o : objects){
                    if(o instanceof String){
                        dataOutputStream.writeUTF((String)o);
                    }else if(o instanceof Integer){
                        dataOutputStream.writeInt((Integer)o);
                    }else if(o instanceof Double){
                        dataOutputStream.writeDouble((Double)o);
                    }
                }
            }catch (IOException ex){
                System.out.println("Exception: writeBinaryDataToBFile");
            }finally {
                    if(dataOutputStream!=null){
                        dataOutputStream.flush();
                        dataOutputStream.close();
                    }
            }
    }

    @Override
    public void readBinaryDataFromBFile(File from, Object... objects) throws IOException {
            DataInputStream dataInputStream = null;
            try{
                dataInputStream = new DataInputStream(new FileInputStream(from));
                for(Object o : objects){
                    if(o instanceof  String){
                        o = dataInputStream.readUTF();
                    }else if (o instanceof  Integer){
                        o = dataInputStream.readInt();
                    }
                    else if(o instanceof Double){
                        o = dataInputStream.readDouble();
                    }

                System.out.println(o);
                }
            }catch (IOException ex){
                    System.out.println("Exception: readBinaryDataFromBFile");
            }finally {
                    if(dataInputStream!=null){
                        dataInputStream.close();
                    }
            }
    }

    @Override
    public void writeToRandomAccessFile(File from) throws IOException {
            RandomAccessFile randomAccessFile = null;
            try{
                randomAccessFile = new RandomAccessFile(from,"rw");
                for(int i=0;i<10;i++)
                    randomAccessFile.writeDouble(1.75*i);
                randomAccessFile.writeInt(100);
                randomAccessFile.writeUTF("THE END");

            }catch (IOException ex){
                System.out.println("Exception: writeToRandomAccessFile");
            }finally {
                if(randomAccessFile!=null)
                    randomAccessFile.close();
            }
    }

    @Override
    public void readFromRandomAccessFile(File from, PrintStream out) throws IOException {
            RandomAccessFile randomAccessFile = null;
            try{
                randomAccessFile = new RandomAccessFile(from,"r");
                for(int i=0;i<10;i++)
                    out.println(randomAccessFile.readDouble());
                out.println(randomAccessFile.readInt());
                out.println(randomAccessFile.readUTF());
            }catch (IOException ex){
                System.out.println("Exception: readFromRandomAccessFile");
            }finally {
                if(randomAccessFile!=null)
                    randomAccessFile.close();
            }
    }
}
