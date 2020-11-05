package com.sinosoft.map.mapview.common;

import java.io.File;

import com.esri.arcgisruntime.geometry.SpatialReference;

/**
 * 公用常量 .
 *
 * @author: zuoyu
 * @create: 2020-11-02 17:54
 */
public final class CommonValue {

    //        密钥标识
    public static String RUNTIME_LITE_LICENSE_KEY = "runtimelite,1000,rud9935659375,none,NKMFA0PL4P0F003AD057";

    //    标记图标
    public static String LOCATION_BLUE_BIG = "images" + File.separator + "location_blue_big.png";

    //    坐标系转换为2436
    public static SpatialReference SR_ID_2436 = SpatialReference.create("PROJCS[\"Beijing_1954_3_degree_Gauss_Kruger_CM_117E\",GEOGCS[\"GCS_Beijing 1954\",DATUM[\"D_Beijing_1954\",SPHEROID[\"Krasovsky_1940\",6378245,298.3]],PRIMEM[\"Greenwich\",0],UNIT[\"Degree\",0.017453292519943295]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"latitude_of_origin\",0],PARAMETER[\"central_meridian\",117],PARAMETER[\"scale_factor\",1],PARAMETER[\"false_easting\",500000],PARAMETER[\"false_northing\",0],UNIT[\"Meter\",1]]");

    //    缓存存储目录
    public static String MAP_CACHE_DIR = "map";

    //    缩放比例
    public static Double SCALE = 660D * 12D;

    //    中心点位X值
    public static Double CENTER_POINT_X = 503422.844D;

    //    中心点位Y值
    public static Double CENTER_POINT_Y = 305622.193D;
}
