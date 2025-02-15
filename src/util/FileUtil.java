package util;

import model.Employee;
import java.io.*;
import java.util.*;

public class FileUtil {

    public static List<Employee> loadEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                employees.add(new Employee(data[0].trim(), data[1].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Map<String, String> loadPreviousAssignments(String filePath) {
        Map<String, String> assignments = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                assignments.put(data[1].trim(), data[3].trim()); // (Giver Email -> Recipient Email)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public static void saveAssignments(String filePath, Map<Employee, Employee> assignments) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Employee_Name,Employee_EmailID,Secret_Child_Name,Secret_Child_EmailID\n");
            for (Map.Entry<Employee, Employee> entry : assignments.entrySet()) {
                bw.write(entry.getKey().getName() + "," + entry.getKey().getEmail() + ","
                        + entry.getValue().getName() + "," + entry.getValue().getEmail() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
