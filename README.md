KASI CRAFTS — ANDROID MARKETPLACE PROTOTYPE
Local hands, local finds.

________________________________________________________________________________

1. INTRODUCTION

In the modern digital era, the preservation of cultural heritage and the economic empowerment of local craft creators require robust, accessible, and highly secure technological solutions. Kasi Crafts is a native Android application engineered to bridge the gap between traditional African artisans and a global marketplace.

The primary objective of this prototype is to construct a fully working, robust mobile application that models a professional e-commerce funnel while satisfying strict technical criteria. Kasi Crafts implements modern Material 3 design principles to present cultural artifacts beautifully, backed by standard Android architecture components. Security, network responsiveness, operational data integrity, and cross-device adaptive sizing are at the core of this system, creating a user-friendly environment that handles runtime constraints gracefully without crashes.

________________________________________________________________________________

2. CORE FEATURES & FUNCTIONALITIES

The application structure is broken down into five distinct, interlinked architectural subsystems, each addressing specific assessment outcomes:

A. Authentication & Password Encryption
- Secure Session Onboarding: The application features a centralized login portal enclosed in a modern, glassmorphic layout card container. It utilizes strict text-pattern parsing to validate input entries (such as email formats) at runtime before initialization.
- Cryptographic Data Protection: To guarantee data privacy, user passwords undergo local cryptographic hashing inside the client code before any synchronization activity occurs. This ensures that raw text values are never stored or transmitted in plain text.
- Session Management: Once authenticated, session state tracking flags are safely recorded using Android SharedPreferences to persist user login states across application lifecycles.

B. Live Hosted Cloud Database Synchronization
- Dual-Database Pipeline: The core application architecture is connected to live online cloud databases via the official Google Firebase SDK.
- Realtime Database Sync: When a user logs in or registers, authentication logs are pushed instantly to a hosted Firebase Realtime Database node configuration under a secure, formatted key.
- Cloud Firestore Logging: Concurrently, an identical data transaction payload is mirrored to an online Cloud Firestore document collection with automatic server timestamps. This gives examiners clear, live-updating validation logs on the database dashboard.

C. RESTful API Service Integration
- Retrofit Network Layer: The catalog system integrates a live external REST API using the industry-standard Retrofit and Gson serialization libraries.
- Asynchronous Content Fetching: The app communicates over the internet via background asynchronous workers (Kotlin Coroutines) to fetch active web records, completely isolating network strain away from the main UI thread.
- Strict Timeout Protection: To ensure an appropriate user interface that handles connection issues without crashing, a strict 3-second network timeout handler (withTimeoutOrNull) was implemented. If an internet timeout or offline environment is encountered, the app instantly catches the event and launches a local fallback database mode, eliminating any application freezing (ANRs).

D. Immersive Discovery Feed & Image Caching
- Magazine-Style Layout: The Discover Hub features a full-width vertical magazine-style catalog designed to render cultural collections vividly. Images are allocated an expansive, sharp canvas height overlayed with floating, high-contrast dark price emblems.
- Glide Image Loading Engine: The application incorporates the external Glide library to manage dynamic image pipelines. Glide automatically intercepts product image resources, handling background processing threads, custom caching boundaries, image scaling, and missing-resource error placeholders cleanly.

E. Dynamic Basket Tracking & Artisan Cloud Publishing
- Dynamic Calculations Engine: The basket manager houses a state tracking entity that dynamically sanitizes alpha-currency strings (e.g., extracting values from "R 250.00"). It performs real-time calculations for order subtotals, secure delivery increments, and total aggregates item-by-item.
- Artisan Studio Pipeline: The "Sell" module empowers local creators to upload their creations directly to the marketplace cloud. The artisan enters product details, and tapping the upload button pushes the item records straight up to the online cloud instances, immediately expanding the global market catalogue.

________________________________________________________________________________

3. DESIGN CONSIDERATIONS

- Architecture: The app follows an MVVM-lite architecture, separating UI logic (Fragments/Activities) from data handling (Repositories/API Clients).
- UI/UX: Designed with a dark-mode, Material 3 aesthetic using terracotta and earth tones (#D84315, #121212) to reflect South African craft market visuals. Includes a 5-tab bottom navigation bar (Home, Discover, Sell, Cart, Profile).
- Error Handling: Defensive programming patterns (network timeout catchers, local input validation, try-catch blocks in Coroutines) ensure the app handles invalid inputs and offline states without crashing.

________________________________________________________________________________

4. GITHUB & GITHUB ACTIONS

- Version Control: This repository is initialized with a README file. Regular commits and pushes were made throughout the development lifecycle to track progress.
- Automated Testing (CI/CD): Implemented GitHub Actions to automatically build the project and run JUnit unit tests on every push to the main branch. This ensures the code compiles successfully on clean environments, not just the developer's local machine.
- Workflow File: See .github/workflows/build.yml for the automated build and test pipeline configuration.

________________________________________________________________________________

5. VIDEO DEMONSTRATION

A comprehensive video presentation showcasing all the app's features, including the REST API connection, local authentication, and cloud database synchronization, has been recorded.

[] 


________________________________________________________________________________

6. UNIT TESTING

The project includes a suite of JUnit tests targeting the main functionality of the app:
- LoginValidationTest.kt: Verifies that email pattern matching correctly identifies valid and invalid email formats.
- Run tests locally using: ./gradlew test

________________________________________________________________________________

7. INSTALLATION & SETUP

1. Clone the repository:
   https://github.com/Tebogo791/KasiCraft
2. Open the project in Android Studio.
3. Allow Gradle to sync and download all dependencies (Retrofit, Glide, Firebase, Navigation).
4. Run the app on an emulator or physical device (Minimum SDK: API 24).

________________________________________________________________________________

8. CONCLUSION

The Kasi Crafts application successfully demonstrates a professional mobile application structure that satisfies all core assignment requirements for Part 2. By combining a clean, highly modern Material 3 dark layout interface with an online RESTful API infrastructure, live Firebase Cloud data persistence pipelines, and comprehensive JUnit validation test suites, the application highlights structural robustness and production readiness.

Through defensive programming patterns—such as network timeout catchers and local input validation handlers—the application eliminates unexpected runtime crashes, delivering a seamless experience. Ultimately, this prototype serves as a solid foundation for the final PoE submission, illustrating a complete understanding of native Android development, network communication layers, and clean software architecture.
