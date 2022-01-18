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

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME = "0x9114514";

    public static final String IMAGE_KEY = "uri";

    private static final String TAG = "MainActivity";

    private Button btnFullScreen, btnConfigure, btnSelectUri;

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(IMAGE_KEY, uri.toString());
                    //Log.d(TAG, "initActivity: " + uri.toString());
                    editor.commit();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }

    void initActivity() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        btnConfigure = findViewById(R.id.btnConfigure);
        btnConfigure.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        btnFullScreen = findViewById(R.id.btnFullPage);
        btnFullScreen.setOnClickListener(v -> startActivity(new Intent(this, FullScreen.class)));
        btnSelectUri = findViewById(R.id.btnSelectUrl);
        btnSelectUri.setOnClickListener(v -> {
            mGetContent.launch("image/*");
        });


        String imageUri = preferences.getString(IMAGE_KEY, null);
        boolean instantMode = preferences.getBoolean("instantMode", false);

        if (instantMode && imageUri != null) {
            startActivity(new Intent(this, FullScreen.class));
        }


    }

}