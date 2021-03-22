package main.LABSOS2;

/*Со помош на синхронизациските методи да се реши проблемотза определување на бројот на
појавувања на буквата E во  стрингот и негово запишување во глобална променлива count.

Секвенцијалното решение не е прифатливо поради тоа што трае многу долго време
(поради големината на стрингот). За таа цел, потребно е да се паралелизира овој процес,
при што треба да се напише метода која ќе ги брои појавувањата на буквата E во помал фрагмент
од стрингот, при што резултатот повторно се чува во глобалната заедничка променлива count.

Напомена: Почетниот код е даден во почетниот код CountLetter.
Задачата да се тестира над стринг од минимум 1000 карактери.*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class zadaca2 {

    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na bukvata E
     */
    static int count = 0;
    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */

    public void init() {
        Semaphore s = new Semaphore(count);
    }

    static class CountLetter extends Thread {

        public void count(String data) throws InterruptedException {
            // da se implementira
            Lock lock = new ReentrantLock();
            for(int i=0;i<data.length();i++){
                lock.lock();
                if(data.charAt(i)=='E'){
                    count++;
                }
                lock.unlock();
            }
        }
        private String data;

        public CountLetter(String data) {
            this.data = data;
        }


        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            //CountLetter environment = new CountLetter();
           // environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String pom = bf.readLine();
        String [] data = pom.split("");

        for(int i = 0; i< data.length; i++) {
            CountLetter c = new CountLetter(data[i]);
            threads.add(c);
        }


        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(count);


    }
}