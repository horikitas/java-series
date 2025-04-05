package org.horikita.java11features;

//Enables inner classes and outer classes in the same "nest" to access each other's private members without needing synthetic bridge methods.
public class NestBasedAccessControl {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        System.out.println(inner.reveal()); // Prints "Uchiha"
    }
}

/*
JAVA 11:
Outer.class now has a NestMembers attribute:
NestMembers:
  Outer$Inner
Inner.class now has a NestHost attribute:
NestHost: Outer

JAVA 8: in bytecode:
// Synthetic bridge method generated
private String access$000(Outer outer) {
    return outer.secret;
}


 */

class Outer {
    private String secret = "Uchiha";

    class Inner {
        String reveal() {
            return secret; // Works in Java 11+
        }
    }
}
