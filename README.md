# GroceryApp 🛒

A native Android application designed for quick grocery selection, custom weight calculation, date-stamped ordering, and automated bill receipt generation.

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Language-Java-ED8B00?logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Build-Gradle-02303A?logo=gradle&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green)

---

## Features

- **Dynamic Grocery Catalog:** Custom `ListView` powered by an optimized `ViewHolder` pattern in `GroceryAdapter` for smooth scrolling and clean item rendering.
- **Quantity & Price Calculation:** Input specific decimal weights (kg) for various items with automatic item-total and grand-total computations.
- **Date Picker Dialog:** Interactive Android `DatePickerDialog` allowing users to select and tag custom order dates.
- **Itemized Bill Receipt (`SummaryActivity`):** Generates a clean, detailed invoice displaying itemized weights, rates, subtotals, and the final bill summary.
- **Input Validation:** Prevents blank submissions and prompts alerts via `Toast` if no quantities are selected.

---

## Project Architecture

```
GroceryApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/priyanshi/groceryapp/
│   │   │   ├── MainActivity.java       # Main grocery listing & order calculation
│   │   │   ├── SummaryActivity.java    # Receipt display & order summary
│   │   │   ├── GroceryAdapter.java     # Custom BaseAdapter with ViewHolder pattern
│   │   │   └── GroceryItem.java        # Data model (name, price/kg, image, qty)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml   # Main screen layout
│   │   │   │   ├── activity_summary.xml# Invoice summary layout
│   │   │   │   └── page2.xml           # Custom list row item layout
│   │   │   └── drawable/               # High-res grocery item assets
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
└── README.md
```

---

## Tech Stack

- **Platform:** Android (Min SDK 24, Target SDK 36)
- **Language:** Java 11
- **Architecture:** Android Activities, Custom Adapters, Intent Data Passing
- **Build System:** Gradle (Kotlin DSL)

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/priyanshipanja/GroceryApp.git
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync project dependencies.
4. Select an Android Emulator or connected physical device and click **Run (Shift + F10)**.

---

## Author

**Priyanshi Panja**  
- GitHub: [@priyanshipanja](https://github.com/priyanshipanja)

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
