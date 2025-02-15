package service;

import model.Employee;
import util.FileUtil;

import java.util.*;

public class SecretSantaService {
    private List<Employee> employees;
    private Map<String, String> previousAssignments;
    private Map<Employee, Employee> assignments;

    public SecretSantaService(String employeesFile, String previousAssignmentsFile) {
        this.employees = FileUtil.loadEmployees(employeesFile);
        this.previousAssignments = FileUtil.loadPreviousAssignments(previousAssignmentsFile);
        this.assignments = new HashMap<>();
    }

    public boolean generateAssignments() {
        Collections.shuffle(employees); // Randomize the order
        Set<Employee> remainingRecipients = new HashSet<>(employees);

        for (Employee giver : employees) {
            List<Employee> validRecipients = new ArrayList<>();
            for (Employee recipient : remainingRecipients) {
                if (!giver.equals(recipient) &&
                        !previousAssignments.getOrDefault(giver.getEmail(), "").equals(recipient.getEmail())) {
                    validRecipients.add(recipient);
                }
            }

            if (validRecipients.isEmpty()) {
                return false; // No valid match found
            }

            Employee selectedRecipient = validRecipients.get(new Random().nextInt(validRecipients.size()));
            assignments.put(giver, selectedRecipient);
            remainingRecipients.remove(selectedRecipient);
        }
        return true;
    }

    public void run(String outputFile) {
        for (int i = 0; i < 10; i++) {
            if (generateAssignments()) {
                FileUtil.saveAssignments(outputFile, assignments);
                System.out.println("Assignments successfully generated!");
                return;
            }
        }
        System.out.println("Failed to generate valid assignments after multiple attempts.");
    }
}
