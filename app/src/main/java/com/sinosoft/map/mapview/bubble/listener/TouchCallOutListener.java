package com.sinosoft.map.mapview.bubble.listener;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.Symbol;
import com.sinosoft.map.mapview.graphics.symbol.LocationSymbol;

import android.content.Context;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;

/**
 * 点击触发信息框 .
 *
 * @author: zuoyu
 * @create: 2020-11-05 08:32
 */
public class TouchCallOutListener extends DefaultMapViewOnTouchListener {

    private Symbol symbol;

    private final Context context;

    private final MapView mapView;

    private final Callout callOut;

    private final GraphicsOverlay graphicsOverlay;

    public TouchCallOutListener(Context context, MapView mapView, GraphicsOverlay graphicsOverlay) {
        super(context, mapView);
        this.context = context;
        this.mapView = mapView;
        this.callOut = mapView.getCallout();
        this.graphicsOverlay = graphicsOverlay;
    }


    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
        android.graphics.Point screenPoint = new android.graphics.Point((int) e.getX(), (int) e.getY());
        if (Objects.nonNull(this.symbol)){
            LocationSymbol.changeSmall((PictureMarkerSymbol) this.symbol);
            this.symbol = null;
        }
        final ListenableFuture<IdentifyGraphicsOverlayResult> identifyGraphics = mapView.identifyGraphicsOverlayAsync(graphicsOverlay, screenPoint, 10.0, false, 1);
        identifyGraphics.addDoneListener(() -> {
            if (callOut.isShowing()){
                callOut.dismiss();
            }
            try {
                IdentifyGraphicsOverlayResult identifyGraphicsOverlayResult = identifyGraphics.get();
                List<Graphic> graphics = identifyGraphicsOverlayResult.getGraphics();
                if (graphics.size() != 1){
                    return;
                }
                Graphic graphic = graphics.get(0);
                Symbol symbol = graphic.getSymbol();
                LocationSymbol.changeBig((PictureMarkerSymbol) symbol);
                this.symbol = symbol;
                TextView textView = new TextView(context);

                textView.setTextColor(Color.BLACK);
                textView.setSingleLine(false);
                textView.setVerticalScrollBarEnabled(true);
                textView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
                textView.setMovementMethod(new ScrollingMovementMethod());

                Map<String, Object> attributes = graphic.getAttributes();
                textView.setLines(attributes.size());

                attributes.forEach((key, value) -> {
                    textView.append(key + " | " + value + "\n");
                });

                Point point = (Point) graphic.getGeometry();
                Envelope envelope = point.getExtent();
                mMapView.setViewpointGeometryAsync(envelope, 200);

                callOut.setLocation(envelope.getCenter());
                callOut.setContent(textView);
                callOut.show();
            } catch (ExecutionException | InterruptedException executionException) {
                executionException.printStackTrace();
            }
        });
        return super.onSingleTapConfirmed(e);
    }
}
