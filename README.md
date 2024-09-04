# WeatherPrecixJava

WeatherPrecixJava is an Android application that allows users to input latitude and longitude coordinates to fetch and display the current weather data, including temperature, humidity, and wind speed, using the Open Meteo API.

## Features

- **Input Form:** Users can input latitude and longitude coordinates.
- **Weather Data:** Displays the current temperature, humidity, and wind speed.
- **Error Handling:** Handles network timeouts and API errors with appropriate user feedback.
- **Unit Testing:** Includes JUnit tests for input validation.

## Screenshots

[Include any relevant screenshots here]

## Getting Started

Follow these instructions to set up and run the project on your local machine.

### Prerequisites

- **Android Studio**: Ensure you have the latest version of [Android Studio](https://developer.android.com/studio) installed.
- **Java 8**: The project is set up to use Java 8. Ensure your development environment supports it.

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/WeatherPrecixJava.git
   cd WeatherPrecixJava
Open the Project in Android Studio

Open Android Studio.
Select File > Open and navigate to the cloned repository.
Select the project directory and click OK.
Sync Gradle Files

Android Studio should automatically prompt you to sync Gradle files. If not, click File > Sync Project with Gradle Files.
Set Up the Emulator or Physical Device

You can run the app on an Android emulator or a physical device. Ensure the device or emulator is set up correctly in Android Studio.
Run the Application

Click the Run button (green play button) in the Android Studio toolbar, or press Shift + F10 to build and run the app.
Running the Unit Tests
The project includes JUnit tests for validating user input.

Navigate to the Test Class

The test class is located in app/src/test/java/com/example/weatherprecixjava/InputValidationTest.java.
Run the Tests

Right-click on the InputValidationTest class and select Run 'InputValidationTest'.
Ensure all tests pass successfully.
Error Handling
The application handles several potential issues:

Network Timeouts: If the connection times out, the app displays a user-friendly error message.
API Unavailability: If the API cannot be reached, the app informs the user with a Toast message.
Project Structure
MainActivity.java: Contains the main logic for the application, including handling user input and making API calls.
activity_main.xml: Defines the layout of the main activity, including input fields and display text views.
InputValidationTest.java: Contains unit tests to validate the correctness of the user input.
