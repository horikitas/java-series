package org.horikita.java11features;

// Java 11 introduced several enhancements to the String class, including new methods for string manipulation.
//str.strip(), str.isBlank(), and str.repeat() are some of the new methods that were added to the String class.
public class StringMethodEnhancements {

    public static void main(String[] args) {

        System.out.println( "I'm Awesome!".repeat(3)); // I'm Awesome!I'm Awesome!I'm Awesome!
        stripDemo();
    }

    public static void stripDemo() {
        // strip() method
        assert("horikita".equals("\u2003 horikita  ".strip())); // trims Unicode whitespace

        String original = "\u2003\u2003Suzune Horikita\u2003\u2003"; // EM spaces before and after
        //Suzune Horikita = 15 characters

        System.out.println("Original length: " + original.length());

        System.out.println("strip(): '" + original.strip() + "'");
        System.out.println("stripLeading(): '" + original.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + original.stripTrailing() + "'");

        /* .trim() only removes: ASCII space (codepoint U+0020). NOT Unicode spaces like:
            EM SPACE \u2003
            IDEOGRAPHIC SPACE \u3000
         */
        System.out.println("trim(): '" + original.trim() + "'"); // Doesn't remove Unicode spaces
    }

    public static void isBlankBeforeJava11() {
        //In java 8 you would do this for blank check:
        String str = "";
        boolean isBlank = str == null || str.trim().isEmpty();
        System.out.println(isBlank); // true
    }

    public static void isBlankDemo() {
        // isBlank() method - there was no isBlank() method in Java 8
        // This handles  whitespace, tabs, Unicode spaces
        System.out.println("".isBlank()); // true
        System.out.println("   ".isBlank()); // true
        System.out.println("\t\n ".isBlank()); // true
        System.out.println("abc".isBlank()); // false
    }

    public static void uniCodeSpaceDemo() {
        String emSpace = "\u2003"; // EM SPACE ( )
        String ideographicSpace = "\u3000"; // IDEOGRAPHIC SPACE (　)
        String nonBreakingSpace = "\u00A0"; // NO-BREAK SPACE ( )
        String normalSpaces = "   ";

        System.out.println("emSpace: " + emSpace.isBlank()); // true
        System.out.println("ideographicSpace: " + ideographicSpace.isBlank()); // true
        System.out.println("nonBreakingSpace: " + nonBreakingSpace.isBlank()); // true
        System.out.println("normalSpaces: " + normalSpaces.isBlank()); // true
        System.out.println("text + unicode space: " + ("word" + emSpace).isBlank()); // false
    }
}
