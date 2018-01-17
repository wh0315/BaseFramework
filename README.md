package com.caifulife.baseframework.fargment;
import android.view.View;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.caifulife.baseframework.R;

/**
 * Created by 皓 on 2017/8/29.
 */

public class MienFragment extends BaseFragment {

    private MapView myMapView = null;//地图控件
    private BaiduMap myBaiduMap;//百度地图对象
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    boolean isFirstLoc = true; // 是否首次定位

    @Override
    public View setContentView() {
        if(view == null ){
            view = View.inflate(getActivity(), R.layout.mienfragment,null);
        }
        return view;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        // 地图初始化
        myMapView =  view.findViewById(R.id.baiduMapView);
        myBaiduMap = myMapView.getMap();
        // 开启定位图层
        myBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
        myBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, null));
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }
    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || myBaiduMap == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            myBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                myBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    @Override
    public void onPause() {
        myMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        myMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        myBaiduMap.setMyLocationEnabled(false);
        myMapView.onDestroy();
        myMapView = null;
        super.onDestroy();
    }
}
