package com.example.lab8;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.content.Context;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button myButton;
    ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = findViewById(R.id.my_button);
        myImageView = findViewById(R.id.my_image);
        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());

                if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    myButton.setBackgroundResource(R.drawable.online);
                    myImageView.setImageResource(R.drawable.online);
                    Toast.makeText(getApplicationContext(), "Подключение есть \nДобро пожаловать", Toast.LENGTH_SHORT).show();
                } else {
                    myButton.setBackgroundResource(R.drawable.offline);
                    myImageView.setImageResource(R.drawable.offline);
                    Toast.makeText(getApplicationContext(), "Подключения нет \nПопробуйте ещё раз", Toast.LENGTH_SHORT).show();
                }
                myButton.setVisibility(View.GONE);
            }
        });
    }
}