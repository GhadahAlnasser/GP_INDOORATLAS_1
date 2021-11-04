package com.example.test_gp12;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IARegion;
import com.indooratlas.android.sdk.resources.IAFloorPlan;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    IALocationManager mIALocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIALocationManager = IALocationManager.create(this);
    }
    private IARegion.Listener mRegionListener = new IARegion.Listener() {
        @Override
        public void onEnterRegion(IARegion region) {
            if (region.getType() == IARegion.TYPE_FLOOR_PLAN) {
                handleFloorPlanChange(region.getFloorPlan());
            }
        }

        @Override
        public void onExitRegion(IARegion region) {
            // leaving a previously entered region
        }
    };

    private void handleFloorPlanChange(IAFloorPlan newFloorPlan) {
        ImageView mFloorPlanImage = findViewById(R.id.map1);
        Picasso.with(this)
                .load(newFloorPlan.getUrl())
                .into(mFloorPlanImage);
    }
}