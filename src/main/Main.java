package main;

import service.SecretSantaService;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <employees.csv> <previous_assignments.csv> <output.csv>");
            return;
        }

        String employeesFile = args[0];
        String previousAssignmentsFile = args[1];
        String outputFile = args[2];

        SecretSantaService secretSanta = new SecretSantaService(employeesFile, previousAssignmentsFile);
        secretSanta.run(outputFile);
    }
}
