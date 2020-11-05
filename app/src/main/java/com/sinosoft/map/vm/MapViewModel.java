package com.sinosoft.map.vm;


import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.sinosoft.map.mapview.bubble.BubbleViewer;
import com.sinosoft.map.mapview.common.CommonValue;
import com.sinosoft.map.mapview.graphics.GraphicsRender;
import com.sinosoft.map.mapview.preview.Map;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import lombok.Getter;

/**
 * 地图ViewModel .
 *
 * @author: zuoyu
 * @create: 2020-11-05 14:41
 */
public class MapViewModel extends ViewModel {

    private final MutableLiveData<ArcGISMap> arcGISMapMutableLiveData = new MutableLiveData<>();

    @Getter
    private final LiveData<ArcGISMap> arcGISMapLiveData = arcGISMapMutableLiveData;

    private final GraphicsRender graphicsRender;

    private final BubbleViewer bubbleViewer;

    @ViewModelInject
    public MapViewModel(@NonNull Map map, GraphicsRender graphicsRender, BubbleViewer bubbleViewer) {
        this.bubbleViewer = bubbleViewer;
        this.graphicsRender = graphicsRender;
        ArcGISMap arcGISMap = map.loadMap();
        arcGISMapMutableLiveData.setValue(arcGISMap);
    }

    public void loadMapProperties(MapView arcGisMapView) {
        loadAttribute(arcGisMapView);
        graphicsRender.loadGraphics(arcGisMapView);
        bubbleViewer.openBubble(arcGisMapView, graphicsRender.getGraphicsOverlay());
    }

    /**
     * 加载属性
     */
    private void loadAttribute(@NonNull MapView arcGisMapView) {
        Point centerPoint = new Point(CommonValue.CENTER_POINT_X, CommonValue.CENTER_POINT_Y, CommonValue.SR_ID_2436);
        //  设置地图的默认中心点和缩放比例
        arcGisMapView.setViewpointCenterAsync(centerPoint, CommonValue.SCALE);
        //  加载动画类型
        arcGisMapView.setVisibility(View.VISIBLE);
        arcGisMapView.getChildAt(0).setVisibility(View.VISIBLE);
    }

}
