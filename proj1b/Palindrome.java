public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }


    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            char first = d.removeFirst();
            char last = d.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }

        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            char first = d.removeFirst();
            char last = d.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }

}
