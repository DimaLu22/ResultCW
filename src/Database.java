package src;
import java.io.*;
import java.util.*;

public class Database {
    private final List<Animal> animals;
    private static final String FILE_PATH = "database.txt";

    public Database() {
        animals = new ArrayList<>();
        loadDatabase();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        saveDatabase();
    }

    public void displayAnimalCommands(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                animal.displayCommands();
                return;
            }
        }
        System.out.println("�������� � ������ " + name + " �� �������.");
    }



    public void teachNewCommand(String name, String command) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                String[] commands = command.split(",");
                for (int i = 0; i < commands.length; i++) {
                    String trimmedCommand = commands[i].trim();
                    commands[i] = trimmedCommand;
                }
                animal.teachNewCommand(command);
                saveDatabase();
                System.out.println("������� ������� ��������� ��� ���������.");
                return;
            }
        }
        System.out.println("�������� � ������ " + name + " �� �������.");
    }


    private void loadDatabase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String className = data[0];
                    String name = data[1];
                    String skills = String.join(",", Arrays.copyOfRange(data, 2, data.length));

                    Animal animal;
                    switch (className) {
                        case "Dog" -> animal = new Dog(name, skills);
                        case "Cat" -> animal = new Cat(name, skills);
                        case "Hamster" -> animal = new Hamster(name, skills);
                        case "Donkey" -> animal = new Donkey(name, skills);
                        case "Horse" -> animal = new Horse(name, skills);
                        default -> {
                            System.out.println("����������� ����� ���������: " + className);
                            continue;
                        }
                    }
                    animals.add(animal);
                } else {
                    System.out.println("������������ ������ � �����: " + line);
                }
            }
            System.out.println("���� ������ ������� ���������.");
        } catch (IOException e) {
            System.out.println("������ ��� ������ ���� ������: " + e.getMessage());
        }
    }


    public void displayAllAnimals() {
        try {
            File file = new File(FILE_PATH);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String animalData = fileScanner.nextLine();
                System.out.println(animalData);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("���� � ������� � �������� �� ������.");
        }
    }

    private void saveDatabase() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Animal animal : animals) {
                String className = animal.getClass().getSimpleName();
                String name = animal.getName();
                String skills = animal.getSkills().replaceAll(",\\s+", ",");

                String line = className + "," + name + "," + skills;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("���� ������ ������� ���������.");
        } catch (IOException e) {
            System.out.println("������ ��� ���������� ���� ������: " + e.getMessage());
        }
    }

}
