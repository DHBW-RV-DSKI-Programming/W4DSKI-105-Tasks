import java.time.LocalDateTime;

record Patient(String patientName, int urgencyLevel, LocalDateTime timeOfArrival) {}
