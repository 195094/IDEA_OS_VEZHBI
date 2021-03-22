package main.LABSOS2;

/*Извршете го примерот од TwoThreads. Потоа, модифицирајте ја програмата така што
ќе користите само една класа за нитки, ThreadClassLettersNumbers. Во конструкторот на
класата ќе се предадe листа која соодветната инстанца треба да ја отпечати.
Нитката не треба да ја наследува класата Thread.
Однесувањето на новата програма треба да биде исто како на оригиналната,
односно повторно треба да имате две нитки кои ќе го извршуваат посебно методот run():
едната нитка ќе ги печати првите десет букви од англиската азбука,
 потоа другата ќе ги печати првите десет броеви.*/


import java.util.ArrayList;
import java.util.List;

public class zadaca1 {


    public static void main(String[] args) throws InterruptedException {
        ThreadClassLetters letters = new ThreadClassLetters();
        ThreadClassNumbers numbers = new ThreadClassNumbers();
       // letters.start();
        //letters.join();
        //numbers.start();
       // numbers.join();
        List<String>lista = new ArrayList<String>();
        String str = null;
        for(int i=0;i<10;i++){
            lista.add("Element "+i);
        }
        ThreadClassLettersNumbers threadClassLettersNumbers= new ThreadClassLettersNumbers(lista);

    }



}

class ThreadClassNumbers extends Thread {

    @Override
    public void run() {
        for(int i = 0; i<10;i++) System.out.println(i);
    }
}


class ThreadClassLetters extends Thread {

    @Override
    public void run() {
        for(int i = 0; i<10;i++) System.out.println((char)(i + 65));
    }
}

class ThreadClassLettersNumbers{
    ThreadClassLettersNumbers(List list){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
        ThreadClassLetters threadClassLetters = new ThreadClassLetters();
        ThreadClassNumbers threadClassNumbers = new ThreadClassNumbers();
        try {
            threadClassLetters.start();
            threadClassLetters.join();
            threadClassNumbers.start();
            threadClassNumbers.join();
        }catch (InterruptedException ex){}
    }
}