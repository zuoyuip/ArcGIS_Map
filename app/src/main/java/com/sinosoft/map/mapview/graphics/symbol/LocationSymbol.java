package com.sinosoft.map.mapview.graphics.symbol;

import java.io.File;

import javax.inject.Inject;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.Symbol;
import com.sinosoft.map.exception.CustomException;
import com.sinosoft.map.exception.enums.ExceptionType;
import com.sinosoft.map.mapview.common.CommonValue;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.PictureDrawable;
import androidx.annotation.NonNull;
import cn.hutool.core.lang.Assert;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import lombok.SneakyThrows;

/**
 * 本地symbol .
 *
 * @author: zuoyu
 * @create: 2020-11-04 19:34
 */
@Module
@InstallIn(ActivityComponent.class)
public class LocationSymbol {


    private final String symbolJson;


    @SneakyThrows
    @Inject
    public LocationSymbol(@ActivityContext @NonNull Context context) {
        File locationBlue = context.getExternalFilesDir(CommonValue.LOCATION_BLUE_BIG);
        BitmapDrawable drawable = (BitmapDrawable) PictureDrawable.createFromPath(locationBlue.getPath());
        Assert.notNull(drawable, () -> new CustomException(ExceptionType.NOT_CAN_CREATE_OBJ));
        ListenableFuture<PictureMarkerSymbol> pictureMarkerSymbols = PictureMarkerSymbol.createAsync(drawable);

        PictureMarkerSymbol pictureMarkerSymbol = pictureMarkerSymbols.get();
        pictureMarkerSymbol.setHeight(20F);
        pictureMarkerSymbol.setWidth(20F);
        pictureMarkerSymbol.loadAsync();
        symbolJson = pictureMarkerSymbol.toJson();
    }


    public Symbol newSymbol(){
        return PictureMarkerSymbol.fromJson(symbolJson);
    }

    public static void changeBig(@NonNull PictureMarkerSymbol pictureMarkerSymbol) {
        change(pictureMarkerSymbol, 30F);
    }

    public static void changeSmall(@NonNull PictureMarkerSymbol pictureMarkerSymbol) {
        change(pictureMarkerSymbol, 20F);
    }

    private static void change(@NonNull PictureMarkerSymbol pictureMarkerSymbol, Float changeMath){
        pictureMarkerSymbol.setWidth(changeMath);
        pictureMarkerSymbol.setHeight(changeMath);
        pictureMarkerSymbol.loadAsync();
    }

}
