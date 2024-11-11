# Greenify

**Greenify** is a fully functional Android app built entirely with Kotlin and Jetpack Compose. It adheres to Android design and development best practices, providing a sleek, responsive experience.

---

## Screenshots

Screenshots of the application.

---

## Features

Greenify provides detailed information about plants from around the world, including their names, families, scientific names, and authors. Users can filter plants based on their country of origin. The currently supported countries are:
- All
- Palestine
- Sudan
- Myanmar
- Transcaucasus
- Uzbekistan

The app retrieves its content from the **[Trefle API](https://trefle.io/)**.

---

## UI

The app has two main screens:
1. **Plants List Screen** – Displays a list of plants filtered by the selected country.
2. **Plant Details Screen** – Shows more detailed information about a selected plant.

The UI follows **Material 3** guidelines and is built entirely using Jetpack Compose. It includes two themes:
- **Dynamic Color** – Uses colors based on the user’s current color theme (if supported).
- **Default Theme** – Uses predefined colors if dynamic color is not supported.

Each theme also supports dark mode. The app is designed with adaptive layouts to enhance usability on various screen sizes.

---

## Architecture

The Greenify app follows the **[official Android architecture guidance](https://developer.android.com/topic/architecture)**.

---

## Modularization

Greenify is fully modularized, following a structured modularization strategy described in the [Official Android Modularization guidance](https://developer.android.com/topic/modularization).

### Project Structure

```plaintext
app                    // The application module
build-logic            // Folder containing project-specific convention plugins for configuration consistency.
└── convention         // Defines a set of plugins that all modules use for configuration.
core                   // Core functionality of the application
├── common             // Common models and utility functions
├── data               // Data layer of the application
├── designsystem       // Contains theme and custom Jetpack Compose components
├── domain             // Domain layer of the application
├── network            // Network Utilities
└── ui                 // Shared UI models and components
feature                // Application features
└── plants             // The plants feature
```

# 3rd Party Libraries

1. **Hilt** – Used for Dependency Injection management.
2. **Material3 Adaptive Layout Library** – Used for navigation and adaptive layouts.
3. **Glide** – Used for image loading and caching.

---

# Testing

Testing is not implemented yet.
