package gw.java.utils;

import gw.data.ChildName;
import gw.data.Gender;
import org.apache.commons.math3.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class JavaCode {
    private JavaCode() {
    }

    public static void ex() {
        List<String> names = Arrays.asList("Alice", "Bob", "Chad", "David", "Eve", "Frank", "Bernard", "Brandon");

        List<String> results2 = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("B")) {
                String newName = name.toUpperCase();
                results2.add(newName);
            }
        }
        System.out.println(results2);

        List<String> results = names.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.startsWith("B");
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.toUpperCase();
                    }
                })
                .collect(Collectors.toList());

        System.out.println(results);
    }

    public static List<String> helloWorldNonFunctionalJavaCode(List<String> names) {
        List<String> results = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("B")) {
                String newName = name.toUpperCase();
                results.add(newName);
            }
        }
        return results;
    }

    public static List<String> helloWorldJavaStreams(List<String> names) {
        return names.stream()
                .filter(name -> name.startsWith("B"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static Map<Gender, SortedSet<String>> sortedLongNamesByGender(List<ChildName> names) {
        return
                names.stream() //Stream<ChildName>
                        .filter(childName -> childName.getName().length() >= 5) //Stream<ChildName>
                        .collect(Collectors.groupingBy(ChildName::getGender)) //Map<Gender, List<ChildName>>
                        .entrySet().stream() //Stream<Map.Entry<Gender, List<ChildName>>>
                        .map(e -> new Pair<Gender, SortedSet<String>>(
                                e.getKey(),
                                e.getValue().stream() //Stream<ChildName>
                                        .map(ChildName::getName) //Stream<String>
                                        .collect(Collectors.toCollection(TreeSet::new))
                        )) // Stream<Pair<Gender, SortedSet<String>>>
                        .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    public static Map<Gender, Long> countLongNamesByGender(List<ChildName> names) {
        return names.stream()
                .filter(childName -> childName.getName().length() >= 5)
                .collect(Collectors.groupingBy(
                        ChildName::getGender,
                        Collectors.counting()
                ));
    }

}
