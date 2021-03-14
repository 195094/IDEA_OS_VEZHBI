package main.LABSOS1;

import java.io.*;


public class zadaca6 {

    public static void main(String[] args) throws IOException {
        File dest = new File("C:\\Users\\user\\Desktop\\destinacija.txt");
        File source = new File("C:\\Users\\user\\Desktop\\izvor.txt");
        BufferedWriter bufferedWriter= null;
        BufferedReader bufferedReader = null;
        if(!dest.exists() && !source.exists()){
            throw new FileNotFoundException();
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(dest, true));
            bufferedReader = new BufferedReader(new FileReader(source));
            String linija = null;
            int samoglaski = 0;
            while((linija = bufferedReader.readLine())!=null){
                for(int i =0;i<linija.length();i++){
                    char ch = linija.charAt(i);
                    if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                        ++samoglaski;
                    }
                }
                bufferedWriter.write(String.valueOf(samoglaski));
                bufferedWriter.newLine();
                samoglaski = 0;
            }
        }catch (IOException ex){
            System.out.println("IOException caught");
        }finally {
            if(bufferedReader!=null) bufferedReader.close();
            if(bufferedWriter!=null) {
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }

    }
}
