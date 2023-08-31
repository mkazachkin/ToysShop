package view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import controller.CPrizeMachine;
import model.Toy;

public class PrizeMachineApp {
    private final CPrizeMachine prizeMachine = new CPrizeMachine();
    public void run (){
        boolean flag = false;
        while (!flag) {
            String cmd = strInput("""
                    Введите:
                        1. Добавить игрушки в выдачу призов
                        2. Убрать игрушки из выдачи призов
                        3. Получить приз
                        0. Выйти
                    """);
            switch (cmd) {
                case ("1") -> this.put();
                case ("2") -> this.del();
                case ("3") -> this.get();
                case ("0") -> flag = true;
            }
        }
    }
    public void put (){
        int id = intInput("Введите идентификатор игрушки");
        String name = strInput("Введите название игрушки");
        int weight = intInput("Введите условный вес в выдаче призов");
        int quantity = intInput("Введите количество игрушек");
        prizeMachine.addToys(new Toy (id, name, weight), quantity);
    }
    public void get () {
        if (this.prizeMachine.toysAvailable()) {
            Toy prize = prizeMachine.getPrize();
            System.out.println("Выдан приз: " + prize.getName() + ". См. файл.");
            Path path = Paths.get(System.getProperty("user.dir"), "prizes.txt");
            Charset charset = StandardCharsets.UTF_8;
            try (BufferedWriter bw = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND)) {
                PrintWriter pw = new PrintWriter(bw);
                pw.println(java.time.LocalDateTime.now() + "-> " + prize);
            }catch (IOException e) {
                System.out.println("Ошибка сохранения файла.");
            }
        } else {
            System.out.println("Выдача призов окончена. Игрушек больше нет.");
        }
    }
    public void del () {
        int id = intInput("Введите идентификатор игрушки");
        String name = strInput("Введите название игрушки");
        int weight = intInput("Введите условный вес в выдаче призов");
        int quantity = intInput("Введите количество игрушек");
        prizeMachine.delToys(new Toy (id, name, weight), quantity);
    }

    private String strInput (String message) {
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }
    private int intInput (String message) {
        while (true) {
            try {
                return Integer.parseInt(strInput(message));
            } catch(NumberFormatException e) {
                System.out.println("Ошибочный ввод. Попробуйте еще раз.");
            }
        }
    }
}
