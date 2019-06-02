package pocketruck.penny.pocketruck_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import pocketruck.penny.pocketruck_android.R;


public class SecondTabFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    final LatLng HOME = new LatLng(37.752125, 127.070990);
    private MapView mapView = null;
    private GoogleMap gMap;


    public SecondTabFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_second_tab,container,false);
        mapView = (MapView)layout.findViewById(R.id.map);
        mapView.getMapAsync(this);

        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //액티비티가 처음 생성될 때 실행되는 함수
        if (mapView != null){
            mapView.onCreate(savedInstanceState);
        }
    }

    //위치를 로그에 나타내기 위해 밖으로 뺐다,,,
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions mo = new MarkerOptions();
        mo.position(HOME);
        mo.title("홈플러스");
        mo.snippet("의정부 홈플러스");
        //mo.draggable(true);

        googleMap.addMarker(mo);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(HOME));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        googleMap.setOnMarkerClickListener(this);
        //googleMap.setOnMapLongClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng position = marker.getPosition();
        Log.w("위치", String.valueOf(position));
        return false;
    }

    /*@Override
    public void onMapLongClick(LatLng latLng) {
        MarkerOptions mo = new MarkerOptions();
        double lng = latLng.longitude;
        double lat = latLng.latitude;
        Log.i("위도경도","위도"+lat+"경도"+lng);
        mo.position(latLng);
        mo.title("");
        mo.snippet("");


        gMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        gMap.addMarker(mo);
    }*/


}
