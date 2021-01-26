package com.johnny.pack.age;

import java.io.*;

public class WriteBinaryFile {

    public static void main(String[] args) {
	// write your code here
        Movie[] movies = getMovies();
        DataOutputStream out = openOutputStream(Constant.MOVIE_FILE);
        for(Movie m : movies){
            writeMovie(m, out);
        }
        closeFile(out);

        ReadBinaryFile.readInstance().readFile();
    }

    private static Movie[] getMovies() {
        Movie[] movies = new Movie[4];
        movies[0] = new Movie("It's a Wonderful Life", 1946, 14.95);
        movies[1] = new Movie("Young Frankenstein", 1974, 16.95);
        movies[2] = new Movie("Star Wars", 1977, 17.95);
        movies[3] = new Movie("Deadpool II", 2018, 24.95);
        return movies;
    }

    private static DataOutputStream openOutputStream(String name) {
        DataOutputStream out = null;
        try{
            File file = new File(name);
            out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)));
            return out;
        } catch (IOException e){
            System.out.println("I/O Exception opening file!");
            System.exit(0);
        }
        return out;
    }

    private static void writeMovie(Movie m, DataOutputStream out) {
        try{
            out.writeUTF(m.title);
            out.writeInt(m.year);
            out.writeDouble(m.price);
        } catch (IOException e){
            System.out.println("I/O Exception writing data!");
            System.exit(0);
        }
    }

    private static void closeFile(DataOutputStream out) {
        try{
            out.close();
        } catch (IOException e){
            System.out.println("I/O Exception closing file.");
        }
    }
}
