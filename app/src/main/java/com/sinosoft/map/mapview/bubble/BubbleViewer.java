package com.sinosoft.map.mapview.bubble;

import javax.inject.Inject;

import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.sinosoft.map.mapview.bubble.listener.TouchCallOutListener;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

/**
 * 信息框 .
 *
 * @author: zuoyu
 * @create: 2020-11-05 08:16
 */
@Module
@InstallIn(ApplicationComponent.class)
public class BubbleViewer {

    private final Context context;

    @Inject
    public BubbleViewer(@ActivityContext Context context) {
        this.context = context;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void openBubble(@NonNull MapView mapView, GraphicsOverlay graphicsOverlay){
        TouchCallOutListener touchCallOutListener = new TouchCallOutListener(context, mapView, graphicsOverlay);
        mapView.setOnTouchListener(touchCallOutListener);
    }
}
