package com.johnny.pack.age;

import java.io.*;
import java.text.NumberFormat;

public class ReadBinaryFile {
    private static ReadBinaryFile instance = new ReadBinaryFile();

    public static ReadBinaryFile readInstance(){
        return instance;
    }

    public void readFile(){
        NumberFormat cf = NumberFormat.getCurrencyInstance();
        DataInputStream in = getInputStream(Constant.MOVIE_FILE);
        boolean eof = false;
        while(!eof){
            Movie movie = readMovie(in);
            if(movie == null){
                eof = true;
            } else {
                String msg = Integer.toString(movie.year);
                msg += ": " + movie.title;
                msg += " (" + cf.format(movie.price) + ")";
                System.out.println(msg);
            }
        }
        closeFile(in);
    }

    private DataInputStream getInputStream(String name) {
        DataInputStream in = null;
        try{
            File file = new File(name);
            in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)));
        } catch (FileNotFoundException e){
            System.out.println("The file doesn't exist");
            System.exit(0);
        }
        return in;
    }

    private Movie readMovie(DataInputStream in) {
        String title = "";
        int year = 0;
        double price = 0.0;
        try{
            title = in.readUTF();
            year = in.readInt();
            price = in.readDouble();
        } catch(EOFException e){
            return null;
        } catch(IOException e){
            System.out.println("I/O Exception");
            System.exit(0);
        }
        return new Movie(title, year, price);
    }

    private void closeFile(DataInputStream in) {
        try{
            in.close();
        } catch(IOException e){
            System.out.println("I/O Exception closing file");
            System.out.println();
        }
    }
}
