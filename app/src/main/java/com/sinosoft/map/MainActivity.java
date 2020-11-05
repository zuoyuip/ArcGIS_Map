package com.sinosoft.map;

import java.util.Objects;

import javax.inject.Inject;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.sinosoft.map.mapview.bubble.BubbleViewer;
import com.sinosoft.map.mapview.common.CommonValue;
import com.sinosoft.map.mapview.graphics.GraphicsRender;
import com.sinosoft.map.mapview.preview.Map;
import com.sinosoft.map.vm.MapViewModel;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    Map map;
    @Inject
    GraphicsRender graphicsRender;
    @Inject
    BubbleViewer bubbleViewer;
    private MapView mapView;

    @Override
    protected void onStart() {
        //  标识
        ArcGISRuntimeEnvironment.setLicense(CommonValue.RUNTIME_LITE_LICENSE_KEY);
        super.onStart();
        Log.e("onStart", "onStart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        MapViewModel mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        mapViewModel.getArcGISMapLiveData().observe(this, arcGISMap -> mapView.setMap(arcGISMap));
        mapViewModel.loadMapProperties(mapView);
        Log.e("onCreate", "onCreate");
    }

    @Override
    protected void onPause() {
        if (Objects.nonNull(mapView)) {
            mapView.pause();
        }
        super.onPause();
        Log.e("onPause", "onPause");
    }

    @Override
    protected void onResume() {
        if (Objects.nonNull(mapView)) {
            mapView.resume();
        }
        super.onResume();
        Log.e("onResume", "onResume");
    }

    @Override
    protected void onDestroy() {
        if (Objects.nonNull(mapView)) {
            mapView.dispose();
        }
        super.onDestroy();
        Log.e("onDestroy", "onDestroy");
    }
}