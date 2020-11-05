package com.sinosoft.map.mapview.preview;

import com.esri.arcgisruntime.mapping.ArcGISMap;

import android.app.Activity;

/**
 * 地图源 .
 *
 * @author: zuoyu
 * @create: 2020-11-03 11:13
 */
public interface Map {


    /**
     * 加载地图源
     */
    ArcGISMap loadMap();

    /**
     * 加载地图源
     */
    ArcGISMap loadMap(Activity activity);
}
