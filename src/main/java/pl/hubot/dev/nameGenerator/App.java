package pl.hubot.dev.nameGenerator;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        NameGenerator nameGenerator = new NameGenerator();
        nameGenerator.generate();
    }
}
