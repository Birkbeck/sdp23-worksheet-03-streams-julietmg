import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Outline {

  public static List<String> getList() {
    return List.of("hi", "bat", "ear", "hello", "iguana",
        "beaver", "winterland", "elephant", "eye", "qi");
  }

  // Loop through the words and print each one on a separate line,
  // with two spaces in front of each word.
  public static void question1() {
    List<String> words = getList();
    System.out.println("1: ");

    for (int i = 0; i < words.size(); i++) {
      System.out.println("  " + words.get(i));
    }
  }

  // Repeat this problem but without two spaces in front of each word.
  // This should be trivial if you use the same approach as the previous
  // question; the point here is to make use of a method reference.
  public static void question2() {
    List<String> words = getList();
    System.out.println("2: ");

    for (int i = 0; i < words.size(); i++) {
      System.out.println(words.get(i));
    }
  }

  // For each of the following lambda expressions (see Question 5 in Worksheet 2),
  // produce the list that contains the elements of the original list
  // that satisfy the predicate defined by the lambda expression
  // (use the filter stream operation):
  // - s -> s.length() < 4 (strings with no more than 3 characters),
  // - s -> s.contains("b") (strings containing "b"),
  // s -> (s.length() % 2) == 0 (strings of even length).

  public static void question3() {
    List<String> words = getList();
    System.out.println("3:");

    // shortWords is a stream, created from the words stream
    // it is configured to filter out only the short words from the words stream
    // Note the evaluation happens only when the items are actually requested
    // and not when the stream is created.
    // So nothing will be printed out after this line.
    Stream<String> shortWords = words.stream().filter((String s) -> {
      System.out.println(s);
      return s.length() < 4;
    });
    System.out.println(shortWords.toList());

    List<String> bWords = words.stream().filter((String s) -> s.contains("b")).toList();
    System.out.println(bWords);

    List<String> evenWords = words.stream().filter((String s) -> s.length() % 2 == 0).toList();
    System.out.println(evenWords);

    for (int i = 0; i < words.size(); i++) {
      if (words.get(i).length() % 2 != 0) {
        continue;
      }
      System.out.println(words.get(i));
    }
  }

  // For each of the following lambda expressions (see Question 7 in Worksheet 2),
  // produce the list that contains the results of applying the function
  // defined by the lambda expression to each element of the original list
  // (use the map stream operation):
  // - s -> s + "!",
  // s -> s.replace("i", "eye"),
  // s -> s.toUpperCase().

  public static void question4() {
    List<String> words = getList();
    System.out.println("4:");
    List<String> exlamWords = words.stream().map((String s) -> s + "!").toList();
    System.out.println(exlamWords);

    List<String> iEyWords = words.stream().map((String s) -> s.replace("i", "eye")).toList();
    System.out.println(iEyWords);

    System.out.println(words.stream().map((String s) -> s.toUpperCase()).toList());
  }

  // (*) Turn the strings in the list into uppercase, keep only the
  // ones that are shorter than four characters, and, of what is remaining,
  // keep only the ones that contain "e", and print the first result.
  // Repeat the process, except checking for a "q" instead of an "e".

  public static void question5() {
    List<String> words = getList();
    System.out.println("5a:");
    System.out.println(words.stream()
        .map((String s) -> s.toUpperCase())
        .filter((String s) -> s.length() < 4)
        .filter((String s) -> s.contains("E"))
        .toList());

    System.out.println(words.stream()
        .map((s) -> s.toUpperCase())
        .filter((s) -> s.length() < 4)
        .filter((s) -> s.contains("Q"))
        .toList());

  }

  // (** ) The above example uses lazy evaluation, but it is not easy to see
  // that it is doing so. Create a variation of the above example that shows
  // that it is doing lazy evaluation. The simplest way is to track which
  // entries are turned into upper case.

  public static void question6() {
    List<String> words = getList();
    System.out.println("6:");
    System.out.println(words.stream()
        .map((String s) -> {
          System.out.println("Turning " + s + " into uppercase.");
          return s.toUpperCase();
        })
        .filter((String s) -> {
          System.out.println("Checking if " + s + " is short.");
          return s.length() < 4;
        })
        .filter((String s) -> {
          System.out.println("Checking if " + s + " contains E.");
          return s.contains("E");
        })
        .toList());
  }

  // (*) Produce a single String that is the result of concatenating the
  // uppercase versions of all the Strings.
  // For example, the result should be "HIHELLO...".
  // Hint: use a map operation that turns the words into upper case,
  // followed by a reduce operation that concatenates them.
  public static void question7() {
    List<String> words = getList();
    System.out.println("7:");
    System.out.println(words.stream()
        .map((String s) -> s.toUpperCase())
        .reduce("", (String acc, String elem) -> acc + elem));
  }

  // (*) Produce a single String that is the result of concatenating the
  // uppercase versions of all the Strings.
  // For example, the result should be "HIHELLO...".
  // Use a single reduce operation, without using map.

  public static void question8() {
    List<String> words = getList();
    System.out.println("8:");
    System.out.println(words.stream().reduce("", (String acc, String elem) -> acc + elem.toUpperCase()));
  }

  // (*) Produce a String that is all the words concatenated together, but
  // with commas in between. For example, the result should be "hi,hello,...".
  // Note that there is no comma at the beginning, before "hi", and also no comma
  // at the end, after the last word.

  public static void question9() {
    List<String> words = getList();
    System.out.println("9:");
    System.out.println(words.stream().reduce((String acc, String elem) -> acc + "," + elem).get());
  }

  // Use streams to filter the first two meat dishes.
  public static void question10() {
    List<Dish> menu = Dish.getMenu();
    System.out.println("10:");
    System.out.println(menu.stream().filter((Dish d) -> !d.vegetarian()).limit(2).toList());
  }

  // Count the number of dishes in a stream using the map and reduce methods.
  public static void question11() {
    List<Dish> menu = Dish.getMenu();
    System.out.println("11:");
    System.out.println(menu.stream()
        .map((Dish d) -> 1)
        .reduce(0, (Integer acc, Integer elem) -> acc + elem));

  }

  // Given a list of numbers, print out the list of the squares
  // of each number. For example, given `[1, 2, 3, 4, 5]` you should print `[1, 4,
  // 9, 16, 25]`.
  public static void question12() {
    System.out.println("12:");
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    System.out.println(numbers.stream().map((Integer x) -> x * x).toList());
  }

  // Given two lists of numbers, print out all pairs of numbers. For example,
  // given a list `[1, 2, 3]` and a list `[3, 4]` you should print:
  // `[[1, 3], [1, 4], [2, 3], [2, 4], [3, 3], [3, 4]]`.
  // For simplicity, you can represent each *pair* as a list with two elements.
  public static void question13() {
    System.out.println("13:");
    List<Integer> numbers = Arrays.asList(1, 2, 3);
    List<Integer> otherNumbers = Arrays.asList(3, 4);

    List<Integer[]> cartesian = numbers.stream()
        .flatMap(e1 -> otherNumbers.stream().map(e2 -> (new Integer[] { e1, e2 }))).toList();

    for (int i = 0; i < cartesian.size(); i++) {
      Integer[] elem = cartesian.get(i);
      System.out.println(elem[0] + " " + elem[1]);
    }
  }

  // Extend the previous example to return only pairs whose
  // sum is divisible by `3`. For example, `[2, 4]` and `[3, 3]` are valid
  public static void question14() {
    System.out.println("14:");
    List<Integer> numbers = Arrays.asList(1, 2, 3);
    List<Integer> otherNumbers = Arrays.asList(3, 4);

    List<Integer[]> cartesian = numbers.stream()
        .flatMap(e1 -> otherNumbers.stream().map(e2 -> (new Integer[] { e1, e2 }))).filter((elems) -> 
          (elems[0] + elems[1]) % 3 == 0
        ).toList();

    for (int i = 0; i < cartesian.size(); i++) {
      Integer[] elem = cartesian.get(i);
      System.out.println(elem[0] + " " + elem[1]);
    }
  }


  // Provide three ways to use streams to compute the sum of a list of
  // numbers.

  // First way:

  public static void question15() {
    System.out.println("15:");
    List<Integer> numbers = Arrays.asList(1, 5, 7, 9, 16);
    System.out.println(numbers.stream()
    .reduce(0, (acc, elem) -> acc + elem)); // Co tutaj jest nie tak?

  }

  // Second way:

  public static void question15b() {
    System.out.println("15b:");
    List<Integer> integers = Arrays.asList(1, 5, 7, 9, 16);
    System.out.println(integers.stream()
        .collect(Collectors.summingInt(Integer::intValue))); // To tez nie dziala
  }

  // Third way:

  public static void question15c() {
    System.out.println("15c:");
    List<Integer> num = Arrays.asList(1, 5, 7, 9, 16);
    System.out.println(num.stream()
        .mapToInt(Integer::intValue)
        .sum());

  }

  public static void main(String... args) { // varargs alternative to String[]
    question1();
    question2();
    question3();
    question4();
    question5();
    question6();
    question7();
    question8();
    question9();
    question10();
    question11();
    question12();
    question13();
    question14();
    question15();
    question15b();
    question15c();
  }
}