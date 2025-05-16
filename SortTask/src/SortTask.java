import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortTask {
    
    public static void main(String[] args) {
        Patient[] patients = new Patient[]{
                new Patient("Patient 1", 5, LocalDateTime.of(2025, 1, 1, 17, 15)),
                new Patient("Patient 2", 3, LocalDateTime.of(2025, 1, 1, 17, 30)),
                new Patient("Patient 3", 5, LocalDateTime.of(2025, 1, 1, 17, 15)),
                new Patient("Patient 4", 1, LocalDateTime.of(2025, 1, 1, 18, 0)),
                new Patient("Patient 5", 2, LocalDateTime.of(2025, 1, 1, 17, 45)),
                new Patient("Patient 6", 5, LocalDateTime.of(2025, 1, 1, 17, 30)),
                new Patient("Patient 7", 3, LocalDateTime.of(2025, 1, 1, 17, 30)),
                new Patient("Patient 8", 4, LocalDateTime.of(2025, 1, 1, 18, 0)),
                new Patient("Patient 9", 2, LocalDateTime.of(2025, 1, 1, 17, 45)),
                new Patient("Patient 10", 1, LocalDateTime.of(2025, 1, 1, 18, 15))
        };
        
        System.out.println("Patients before sorting:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }

        long startTime = System.nanoTime();
        mergeSort(patients, 0, patients.length - 1);
        long endTime = System.nanoTime();
        System.out.println("\nTime: " + (endTime - startTime) / 1000 + " microseconds");
        
        System.out.println("\nPatients after merge sort:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }

        System.out.println("\nPatients after comparator sort:");
        Comparator<Patient> comp = Comparator.comparing(Patient::urgencyLevel).thenComparing(Patient::timeOfArrival);
        List<Patient> sortedList = Arrays.stream(patients).sorted(comp).toList();
        sortedList.forEach(System.out::println);
    }
    
    private static void mergeSort(Patient[] patients, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;
            
            // Sort first and second halves
            mergeSort(patients, left, mid);
            mergeSort(patients, mid + 1, right);
            
            // Merge the sorted halves
            merge(patients, left, mid, right);
        }
    }

    private static void merge(Patient[] patients, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Create temp arrays
        Patient[] leftArray = new Patient[n1];
        Patient[] rightArray = new Patient[n2];
        
        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = patients[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = patients[mid + 1 + j];
        }
        
        // Merge the temp arrays
        
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        
        // Initial index of merged subarray
        int k = left;
        while (i < n1 && j < n2) {
            // Compare patients by urgency level (higher urgency level has priority)
            if (leftArray[i].urgencyLevel() > rightArray[j].urgencyLevel()) {
                patients[k] = leftArray[i];
                i++;
            } else if (leftArray[i].urgencyLevel() < rightArray[j].urgencyLevel()) {
                patients[k] = rightArray[j];
                j++;
            } else {
                // If urgency levels are equal, compare by arrival time (earlier time has priority)
                if (leftArray[i].timeOfArrival().isBefore(rightArray[j].timeOfArrival()) || leftArray[i].timeOfArrival().isEqual(rightArray[j].timeOfArrival())) {
                    patients[k] = leftArray[i];
                    i++;
                } else {
                    patients[k] = rightArray[j];
                    j++;
                }
            }
            k++;
        }
        
        // Copy remaining elements of leftArray[] if any
        while (i < n1) {
            patients[k] = leftArray[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray[] if any
        while (j < n2) {
            patients[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
