package com.example.decurd.screenshot;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   ImageView imageView;
    View main;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);

        new TedPermission(this)
                .setRationaleMessage("이 기능은 외부 저장소에 쓰기 권한이 필요합니다.")
                .setDeniedMessage("설정 메뉴에서 언제든지 권한을 변경할 수 있습니다")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // 기능 수행
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bitmap b = ScreenShot.takeScreenShotOfRootView(imageView);
                                imageView.setImageBitmap(b);
                                main.setBackgroundColor(Color.parseColor("#999999"));

                                /**
                                 * 0.5~1초 정도 딜레이를 주고 공유 화면이 넘어가도 좋겠다
                                 * 그런데 폰에서 자동적으로 딜레이가 된다
                                 * */

                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("image/png");
                                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                                b.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                                String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                                        b, "Title", null);
                                Uri imageUri = Uri.parse(path);
                                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                                startActivity(Intent.createChooser(intent, "SELECT"));

                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                }).check();


    }
}
