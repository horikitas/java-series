
# 🧠 Java 21 Feature Demos 

Welcome to the Java 21 evolution demo vault – focused, deadly, and precise.
This is a curated set of examples for backend engineers aiming for **Staff+ roles** and mastering modern Java (LTS).

---

## ✅ Core Language Features

| Feature                             | Demo File                                                            | Status   |
|-------------------------------------|----------------------------------------------------------------------|----------|
| 🔥 Virtual Threads                  | [`VirtualThreadsDemo.java`](./VirtualThreadsWithCFDemo.java)               | ✅        |
| 🔥 Structured Concurrency           | [`StructuredConcurrencyDemo.java`](./StructuredConcurrencyDemo.java) | ✅        |
| ✅ Pattern Matching in `switch`      | [`PatternMatchingSwitchDemo.java`](./PatternMatchingSwitchDemo.java) | ✅        |
| ✅ Record Pattern Matching (Preview) | [`RecordPatternDemo.java`](./RecordPatternDemo.java)                 | ✅        |
| ✅ Unnamed Patterns & Variables      | [`UnnamedPatternDemo.java`](./UnnamedPatternDemo.java)               | ✅        |
| 🔄 String Templates (Preview)       | [`StringTemplatesDemo.java`](./StringTemplatesDemo.java)             | Optional |
| ✅ Sequenced Collection              | [`SequencedCollectionDemo.java`](./SequencedCollectionDemo.java)     | ✅        |

---

## ⚙️ Runtime & API Features

- ✅ `ScopedValue` (Preview) – Thread-local replacement
- ✅ `Foreign Function & Memory API` – Optional, advanced
- ⚠️ `SecurityManager` deprecated – Awareness only

---

## 🧠 Staff-Level Decision Questions

| Question                                             | You Should Be Able To...                     |
|------------------------------------------------------|----------------------------------------------|
| Should we replace thread pools with virtual threads? | ✅ Discuss tradeoffs, blocking IO, schedulers |
| Is structured concurrency better than raw futures?   | ✅ Talk about cancellation and isolation      |
| Can we model data better with record + sealed types? | ✅ Show with examples                         |
| Is our system Java 21 ready?                         | ✅ Evaluate compatibility & migration risk    |

---

## 🚀 How to Run Demos

For preview features (record pattern, string templates, scoped values):

```bash
javac --enable-preview --release 21 YourDemo.java
java --enable-preview YourDemo
```

---

## 🧩 Built & Maintained by:
**Suzune Horikita** – backend engineering shadow ninja 🥷

> “Know your language like you know your weapon. Clean. Deadly. Sharp.”

