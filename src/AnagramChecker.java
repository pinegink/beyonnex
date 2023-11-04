import java.util.*;

public class AnagramChecker {
    private Map<String, Set<String>> anagramMap = new HashMap<>();

    public void runAnagramChecker(){
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {
            System.out.println("Enter '1' to check if two texts are anagrams");
            System.out.println("Enter '2' to get all anagrams for a given text");
            System.out.println("Enter 'exit' to quit");
            System.out.print("Enter your choice: ");
            input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.print("Enter the first text: ");
                    String text1 = scanner.nextLine();
                    System.out.print("Enter the second text: ");
                    String text2 = scanner.nextLine();
                    boolean areAnagrams = checkAndAddAnagram(text1, text2);
                    System.out.println("The texts are " + (areAnagrams ? "" : "not ") + "anagrams.");
                }
                case "2" -> {
                    System.out.print("Enter the text to find anagrams for: ");
                    String text = scanner.nextLine();
                    Set<String> anagrams = getAnagrams(text);
                    System.out.println("Anagrams for " + text + ": " + anagrams);
                }
                case "exit" -> System.out.println("Exiting the program.");
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private boolean checkAndAddAnagram(String text1, String text2) {
        if (text1.length() != text2.length()) {
            return false;
        }

        char[] charArray1 = text1.toLowerCase().toCharArray();
        char[] charArray2 = text2.toLowerCase().toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        String sortedText1 = new String(charArray1);
        String sortedText2 = new String(charArray2);

        if (sortedText1.equals(sortedText2)) {
            addAnagram(sortedText1, text1);
            addAnagram(sortedText2, text2);
            return true;
        }

        return false;
    }

    private void addAnagram(String sortedText, String originalText) {
        if (!anagramMap.containsKey(sortedText)) {
            anagramMap.put(sortedText, new HashSet<>());
        }

        anagramMap.get(sortedText).add(originalText);
    }

    private Set<String> getAnagrams(String text) {
        char[] charArray = text.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        String key = new String(charArray);

        if (anagramMap.containsKey(key)) {
            Set<String> anagrams = new HashSet<>(anagramMap.get(key));
            anagrams.remove(text);
            return anagrams;
        }

        return new HashSet<>();
    }
}