package com.sinosoft.map.mapview.graphics;

import java.util.HashMap;
import java.util.Objects;

import javax.inject.Inject;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.util.ListenableList;
import com.sinosoft.map.mapview.common.CommonValue;
import com.sinosoft.map.mapview.graphics.symbol.LocationSymbol;

import android.util.Log;
import androidx.annotation.NonNull;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import lombok.Getter;

/**
 * 图像渲染 .
 *
 * @author: zuoyu
 * @create: 2020-11-04 09:36
 */
@Module
@InstallIn(ActivityComponent.class)
public class GraphicsRender {

    private final LocationSymbol locationSymbol;

    @Getter
    private GraphicsOverlay graphicsOverlay;


    @Inject
    public GraphicsRender(LocationSymbol locationSymbol) {
        this.locationSymbol = locationSymbol;
    }


    public void loadGraphics(@NonNull MapView mapView) {
        ListenableList<GraphicsOverlay> graphicsOverlays = mapView.getGraphicsOverlays();
        graphicsOverlay = new GraphicsOverlay();
        graphicsOverlays.add(graphicsOverlay);
        graphicsOverlay.setMinScale(CommonValue.SCALE);
        dataSource(graphicsOverlay.getGraphics());
    }

    private void dataSource(@NonNull ListenableList<Graphic> graphics) {
        graphics.add(new Graphic(
                point(503592.713649, 305583.016182),
                new HashMap<String, Object>() {{
                    put("name", "Sam");
                    put("sex", "man");
                    put("age", 18);
                }},
                locationSymbol.newSymbol()
        ));
        graphics.add(new Graphic(
                point(503662.713649, 305583.016182),
                new HashMap<String, Object>() {{
                    put("name", "Jim");
                    put("sex", "man");
                    put("age", 20);
                }},
                locationSymbol.newSymbol()
        ));
        graphics.add(new Graphic(
                point(503566.713649, 305683.016182),
                new HashMap<String, Object>() {{
                    put("name", "Amy");
                    put("sex", "woman");
                    put("age", 18);
                }},
                locationSymbol.newSymbol()
        ));
        graphics.add(new Graphic(
                point(503682.713649, 305883.016182),
                new HashMap<String, Object>() {{
                    put("name", "Tom");
                    put("sex", "man");
                    put("age", 22);
                }},
                locationSymbol.newSymbol()
        ));
        graphics.add(new Graphic(
                point(503292.713649, 305582.016182),
                new HashMap<String, Object>() {{
                    put("name", "Mei");
                    put("sex", "woman");
                    put("age", 21);
                }},
                locationSymbol.newSymbol()
        ));
    }

    @NonNull
    private Point point(double x, double y) {
        return new Point(x, y, CommonValue.SR_ID_2436);
    }
}
