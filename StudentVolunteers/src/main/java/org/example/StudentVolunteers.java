package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentVolunteers {

    static List<String> findWithIncompleteEvents(
            List<String> students,
            Map<String, List<String>> attendeesMapping) {

       // Keep track of how many events each student has attended, creating a map
        Map<String, Integer> studentsEventCount = students.stream()
                .collect(Collectors.toMap(s -> s, n -> 0));

        // Walk through attendee list at each event
        attendeesMapping.values()
                // Stream through each attendee list
                .forEach(list -> list.stream()
                        // Filter for the students that are keys in studentEventCountMap
                        // to avoid errors from students who attended but are not in student list
                        .filter(studentsEventCount::containsKey)
                        .forEach(filteredStudent -> studentsEventCount
                                .put(filteredStudent, studentsEventCount
                                        // For each student we kept, increment count value by 1
                                        .get(filteredStudent) + 1)));

        // Iterate through studentEventCount Map and return students who attended less than 2
        return studentsEventCount.entrySet().stream()
                .filter(map -> map.getValue() < 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<String> students = List.of("Sally", "Polly", "Molly",
                "Tony", "Harry");

        Map<String, List<String>> attendeesMapping =
                Map.of("Farmer's Market",
                        List.of("Sally", "Polly"),
                        "Car Wash Fundraiser",
                        List.of("Molly", "Tony", "Polly"),
                        "Cooking Workshop",
                        List.of("Sally", "Molly", "Polly"),
                        "Midnight Breakfast",
                        List.of("Polly", "Molly"));

        System.out.println(findWithIncompleteEvents(students,
                attendeesMapping));
    }

}
