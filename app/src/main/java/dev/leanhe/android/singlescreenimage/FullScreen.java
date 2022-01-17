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

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class FullScreen extends AppCompatActivity {

    ImageView realImage;
    private float default_brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        realImage = findViewById(R.id.imageView);
    }

    // https://stackoverflow.com/a/43650650
    @Override
    protected void onResume() {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        default_brightness = layout.screenBrightness;
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
        super.onResume();
    }

    @Override
    protected void onPause() {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = default_brightness;
        getWindow().setAttributes(layout);
        super.onPause();
    }

}