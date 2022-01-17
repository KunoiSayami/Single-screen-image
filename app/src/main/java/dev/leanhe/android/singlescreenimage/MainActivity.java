/*
 ** Copyright (C) 2022 KunoiSayami
 **
 ** This program is free software: you can redistribute it and/or modify
 ** it under the terms of the GNU Affero General Public License as published by
 ** the Free Software Foundation, either version 3 of the License, or
 ** any later version.
 **
 ** This program is distributed in the hope that it will be useful,
 ** but WITHOUT ANY WARRANTY; without even the implied warranty of
 ** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 ** GNU Affero General Public License for more details.
 **
 ** You should have received a copy of the GNU Affero General Public License
 ** along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package dev.leanhe.android.singlescreenimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_KEY = "uri";

    private Button btnFullScreen, btnConfigure, btnSelectUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }

    void initActivity() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        btnConfigure = findViewById(R.id.btnConfigure);
        btnConfigure.setOnClickListener( v -> startActivity(new Intent(this, SettingsActivity.class)));
        btnFullScreen = findViewById(R.id.btnFullPage);
        btnFullScreen.setOnClickListener( v -> startActivity(new Intent(this, FullScreen.class)));
        btnSelectUri = findViewById(R.id.btnSelectUrl);
        btnSelectUri.setOnClickListener( v -> {
            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK);
            pickIntent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

            startActivity(chooserIntent);
        });


        String imageUri = preferences.getString(IMAGE_KEY, null);

        if (imageUri != null) {
            startActivity(new Intent(this, FullScreen.class));
        }


    }

}