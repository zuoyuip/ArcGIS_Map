package com.sinosoft.map.mapview.preview;


import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * 依赖注入 .
 *
 * @author: zuoyu
 * @create: 2020-11-03 17:15
 */
@Module
@InstallIn(ActivityComponent.class)
public abstract class MapModule {

    @Binds
    public abstract Map map(CacheMap cacheMap);
}
