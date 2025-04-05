## Java 11 Features
1. `var` keyword in lambda expressions
   - `var` can be used in lambda expressions to infer the type of the parameter.
   - Example: `(var x, var y) -> x + y` infers types for `x` and `y`.
2. String Methods
   - `isBlank()`
   - `strip()`, `stripLeading()`, `stripTrailing()`
   - `repeat(int count)`
   - `lines()`
   - `formatted(String format, Object... args)`
3. Nonblocking Http Client out of the box
4. File I/O improvements
   - `Files.readString(Path path)`
   - `Files.writeString(Path path, String content)`
5. `Optional` improvements
   - `ifPresentOrElse()` - java 9
   - `stream()` - java 9
   - `or()` - java 9
   - `isEmpty()` - java 11
6. `CompletableFuture` improvements
   - `orTimeout(long timeout, TimeUnit unit)`
   - `completeOnTimeout(T value, long timeout, TimeUnit unit)`
   - `delayedExecutor(long delay, TimeUnit unit)`
   - `completeAsync(Supplier<U> fn, Executor executor)`
7. `Collection` improvements
   - `Set.of(E... elements)`
   - `Map.of(K k, V v)`
   - `List.of(E... elements)`
   - `Set.copyOf(Collection<? extends E> coll)`
   - `Map.copyOf(Map<? extends K, ? extends V> map)`
   - `List.copyOf(Collection<? extends E> coll)`
8. `Pattern` improvements
   - `Pattern.asPredicate()`
   - `Pattern.compile(String regex, int flags)` 
   - `Pattern.compile("\\p{IsGreek}+"); // matches Greek characters`

## Java 17 Features
1. `sealed` classes
   - `sealed` classes restrict which classes can extend them.
   - Example:
     ```java
     public sealed class Shape permits Circle, Square {}
     public final class Circle extends Shape {}
     public final class Square extends Shape {}
     ```
2. `record` classes
   - `record` classes are a special kind of class in Java that are used to model immutable data.
   - Example:
     ```java
     public record Point(int x, int y) {}
     ```
3. `pattern matching` for `instanceof`
   - `instanceof` can now be used to check the type of an object and cast it in one step.
   - Example:
     ```java
     if (obj instanceof String s) {
         System.out.println(s.toUpperCase());
     }
     ```
4. `text blocks`
   - Multi-line string literals that preserve formatting.
   - Example:
     ```java
     String json = """
         {
             "name": "John",
             "age": 30
         }
         """;
     ```
5. `new switch` expressions
   - `switch` expressions can now return a value.
   - Example:
     ```java
     String result = switch (day) {
         case MONDAY -> "Start of the week";
         case FRIDAY -> "End of the week";
         default -> "Midweek";
     };
     ``` 
6. `foreign function interface` (FFI)
It's a low-level API to: Allocate and manage off-heap memory (no Unsafe)
Access C libraries directly from Java—without JNI boilerplate .
Do pointer math, pass structs, and work with native types cleanly
7. `vector API`
   - A new API for working with vectors in Java.
   - Example:
     ```java
     Vector<Integer> vector = new Vector<>();
     vector.add(1);
     vector.add(2);
     ```
8. `JEP 411: Deprecate the Security Manager for Removal`
A mechanism introduced in Java back in the applet days (Java 1.0!) to restrict what code can do at runtime:
Prevent file access
Block network operations
Control reflection or process launching
9. `JEP 382: New macOS Rendering Pipeline`
   - A new rendering pipeline for macOS that uses the Apple Metal framework.
   - Example:
     ```java
     System.setProperty("sun.java2d.metal", "true");
     ```
10. `JEP 391: macOS/AArch64 Port`
    - A new port of Java for macOS on Apple Silicon.
    - Example:
      ```java
      System.setProperty("os.arch", "aarch64");
      ```
11. `JEP 390: Warnings for Value-Based Classes`
12. `JEP 393: Foreign Memory Access API`
    - A new API for accessing foreign memory in Java.
    - Example:
      ```java
      MemorySegment segment = MemorySegment.allocateNative(1024);
      segment.set(0, 42);
      ```

## Java 21 Features
### Java 21 Core Language Features
1. Virtual Threads
   - Lightweight threads that can be scheduled by the Java runtime.
   - Example:
     ```java
     Thread.startVirtualThread(() -> {
         System.out.println("Hello from a virtual thread!");
     });
     ```
2. Structured Concurrency 
   - A new concurrency model that simplifies working with multiple threads.
   - Example:
     ```java
     try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
         var task1 = scope.fork(() -> {
             // Task 1 code
         });
         var task2 = scope.fork(() -> {
             // Task 2 code
         });
         scope.join();
     }
     ```
3. Pattern Matching for Switch
   - A new way to use `switch` statements with pattern matching.
   - Example:
     ```java
     switch (shape) {
         case Circle c -> System.out.println("Circle with radius " + c.radius());
         case Square s -> System.out.println("Square with side " + s.side());
         default -> System.out.println("Unknown shape");
     }
     ```
4. Record Patterns
   - A new way to use `record` classes with pattern matching.
   - Example:
     ```java
     if (obj instanceof Point(int x, int y)) {
         System.out.println("Point at (" + x + ", " + y + ")");
     }
     ```
5. Unnamed Patterns
   - A new way to use unnamed patterns in `switch` statements.
   - Example:
     ```java
     switch (obj) {
         case String s -> System.out.println("String: " + s);
         case Integer i -> System.out.println("Integer: " + i);
         default -> System.out.println("Unknown type");
     }
     ```
6. String Template
   - A new way to create string templates in Java.
   - Example:
     ```java
     String name = "John";
     String template = "Hello, ${name}!";
     String result = StringTemplate.of(template).bind("name", name).render();
     ```
### Java 21 Runtime & API Additions
1. Sequenced Collections
   - New collections that maintain the order of elements.
   - Example:
     ```java
     SequencedSet<String> set = new SequencedSet<>();
     set.add("A");
     set.add("B");
     ```
2. Scoped Values
   - A new way to manage values in a scoped manner.
   - Example:
     ```java
     ScopedValue<String> value = ScopedValue.of("Hello");
     try (var scope = value.scope()) {
         System.out.println(value.get());
     }
     ```
3. Forign Function & Memory API
   - A new API for working with foreign functions and memory.
   - Example:
     ```java
     MemorySegment segment = MemorySegment.allocateNative(1024);
     segment.set(0, 42);
     ```

4. 



### Notes On Unicode Spaces
**Where Do They Come From?**
1. Copy-paste from Word/PDF/Webpages - Writers use editors that auto-format text with fancy spaces. You paste into your code, database, or config… and boom—ghost characters.
2. Multilingual Input
   East Asian scripts (like Chinese or Japanese) use full-width spaces. Unicode allows for proper spacing in every script.
3. APIs and External Systems
   Some APIs return strings with invisible padding. Legacy systems might use special encodings (like &nbsp; or BOMs).
4. Attacks / Obfuscation
   Hackers sometimes use zero-width spaces to bypass filters, poison logs, or hide data.
### 🧩 Common Unicode Spaces

| Character Name                | Unicode  | Width  | Common Use                                   |
|-------------------------------|----------|--------|----------------------------------------------|
| **Space**                     | `U+0020` | 1      | Standard space (ASCII)                       |
| **No-Break Space**            | `U+00A0` | 1      | Prevents line-breaks (e.g., `10 km`)         |
| **En Space**                  | `U+2002` | Medium | Typesetting, layout                          |
| **Em Space**                  | `U+2003` | Wide   | Full "M" width space                         |
| **Thin Space**                | `U+2009` | Thin   | Between numbers and units (`100 kg`)         |
| **Zero Width Space**          | `U+200B` | 0      | Invisible splitter (used for obfuscation)    |
| **Ideographic Space**         | `U+3000` | Full   | Used in Chinese / Japanese typography        |
| **Narrow No-Break Space**     | `U+202F` | Narrow | French typographic spacing                   |
| **Zero Width No-Break Space** | `U+FEFF` | 0      | Byte Order Mark (BOM) – legacy encoding tool |


```text
//Use following for handling unicode spaces
isBlank() ✅
strip(), stripLeading(), stripTrailing() ✅
normalize() from java.text.Normalizer (for diacritics & composition)
```