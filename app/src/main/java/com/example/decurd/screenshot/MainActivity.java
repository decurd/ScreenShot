package com.example.decurd.screenshot;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

   ImageView imageView;
    View main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap b = ScreenShot.takeScreenShotOfRootView(imageView);
                imageView.setImageBitmap(b);
                main.setBackgroundColor(Color.parseColor("#999999"));
            }
        });
    }
}
