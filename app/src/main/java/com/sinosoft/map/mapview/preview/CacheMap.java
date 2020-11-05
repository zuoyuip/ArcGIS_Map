package com.sinosoft.map.mapview.preview;

import java.io.File;
import java.util.Objects;

import javax.inject.Inject;

import com.esri.arcgisruntime.data.TileCache;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.sinosoft.map.exception.CustomException;
import com.sinosoft.map.exception.enums.ExceptionType;
import com.sinosoft.map.mapview.common.CommonValue;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import androidx.annotation.NonNull;
import cn.hutool.core.lang.Assert;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

/**
 * Geo瓦片服务缓存渲染 .
 *
 * @author: zuoyu
 * @create: 2020-11-02 17:50
 */
@Module
@InstallIn(ActivityComponent.class)
public class CacheMap implements Map {


    private final static String ANDROID_FLAG = "Android";

    private final Context context;


    @Inject
    public CacheMap(@ActivityContext Context context) {
        this.context = context;
    }

    @Override
    public ArcGISMap loadMap() {
        return loadCacheMap(privateMapsDir());
    }

    @Override
    public ArcGISMap loadMap(Activity activity) {
        return loadCacheMap(publicMapsDir());
    }

    @NonNull
    private ArcGISMap loadCacheMap(String mapsDir) {
        TileCache tileCache = new TileCache(mapsDir);
        final ArcGISTiledLayer arcGISTiledLayer = new ArcGISTiledLayer(tileCache);
        Basemap cacheMap = new Basemap(arcGISTiledLayer);
//            创建地图对象
        ArcGISMap arcGISMap = new ArcGISMap(cacheMap);
        arcGISTiledLayer.loadAsync();
        arcGISMap.loadAsync();
        return arcGISMap;
    }

    @NonNull
    private String privateMapsDir() {
        File mapsDir = context.getExternalFilesDir(CommonValue.MAP_CACHE_DIR);
        return mapsDir.getPath();
    }


    @NonNull
    private String publicMapsDir() {
        if (privateMapsDir().contains(ANDROID_FLAG)) {
            String externalStorageDirectory = privateMapsDir().substring(0, privateMapsDir().indexOf(ANDROID_FLAG));
            String mapsCacheDir = externalStorageDirectory.concat(CommonValue.MAP_CACHE_DIR);
            File file = new File(mapsCacheDir);
            if (!file.exists()) {
                Assert.isFalse(file.mkdir(), () -> new CustomException(ExceptionType.NOT_CAN_MKDIR));
            }
            Assert.isTrue(
                    Objects.equals(Environment.getExternalStorageState(file), Environment.MEDIA_MOUNTED),
                    () -> new CustomException(ExceptionType.MEDIA_NOT_MOUNTED)
            );
            return mapsCacheDir;
        } else {
            throw new CustomException(ExceptionType.PATH_NOT_EXISTS);
        }
    }

}
