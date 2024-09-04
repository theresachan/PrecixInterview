package com.example.weatherprecixjava;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
// MainActivity.java

/**
 * MainActivity is the entry point of the app where users can input latitude and longitude
 * to fetch and display current weather data including temperature, humidity, and wind speed.
 */

public class MainActivity extends AppCompatActivity {

    private EditText latitudeInput, longitudeInput;
    private TextView temperatureView, humidityView, windSpeedView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudeInput = findViewById(R.id.latitudeInput);
        longitudeInput = findViewById(R.id.longitudeInput);
        temperatureView = findViewById(R.id.temperatureView);
        humidityView = findViewById(R.id.humidityView);
        windSpeedView = findViewById(R.id.windSpeedView);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitude = latitudeInput.getText().toString().trim();
                String longitude = longitudeInput.getText().toString().trim();

                if (latitude.isEmpty() || longitude.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter valid coordinates", Toast.LENGTH_SHORT).show();
                    return;
                }

                new FetchWeatherTask().execute(latitude, longitude);
            }
        });
    }


    /**
     * FetchWeatherTask handles the network request to the weather API in a background thread
     * and updates the UI with the retrieved data or displays an error message.
     */

    private class FetchWeatherTask extends AsyncTask<String, Void, String> {

        private Exception exception = null;

        @Override
        protected String doInBackground(String... params) {
            String latitude = params[0];
            String longitude = params[1];
            String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                    "&longitude=" + longitude +
                    "&current=temperature_2m,relative_humidity_2m,wind_speed_10m";

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(5000); // 5 seconds timeout
                urlConnection.setReadTimeout(5000);    // 5 seconds timeout
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } catch (SocketTimeoutException e) {
                exception = new Exception("Connection timed out. Please try again.");
                return null;
            } catch (Exception e) {
                exception = new Exception("Failed to fetch weather data. Check your connection.");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (exception != null) {
                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject currentWeather = jsonObject.getJSONObject("current_weather");

                double temperature = currentWeather.getDouble("temperature_2m");
                double humidity = currentWeather.getDouble("relative_humidity_2m");
                double windSpeed = currentWeather.getDouble("wind_speed_10m");

                temperatureView.setText("Temperature: " + temperature + "°C");
                humidityView.setText("Humidity: " + humidity + "%");
                windSpeedView.setText("Wind Speed: " + windSpeed + " m/s");

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Error parsing weather data.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
