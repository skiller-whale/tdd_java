package com.example;

public class Main {
    public static void main(String[] args) {
        WordleApp app = new WordleApp("src/main/resources/words.txt");
        for (String line : app.run(args)) {
            System.out.println(line);
        }
    }
}
