package com.avesyarboles.avesdebenjaminaceval;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by brian_000 on 10/4/2017.
 */

public class MapViewFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    final int MY_MAPS_REQUEST = 31415;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.map_view, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        final ImageButton returnButton = (ImageButton) rootView.findViewById(R.id.returnLocationButton);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                // For showing a move to my location button
                /*String[] permissionRequests = new String[1];
                permissionRequests[0] = Manifest.permission.ACCESS_FINE_LOCATION;
                ActivityCompat.requestPermissions(getActivity(),permissionRequests,0);*/
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {

                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_MAPS_REQUEST);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }
                else{
                    googleMap.setMyLocationEnabled(true);
                }

                // For dropping a marker at a point on the Map
                LatLng point1 = new LatLng(-25.002585, -57.537447);
                LatLng point2 = new LatLng(-25.000537, -57.539784);
                LatLng point3 = new LatLng(-24.998973, -57.543151);
                LatLng point4 = new LatLng(-24.997918, -57.545758);
                LatLng point5 = new LatLng(-24.996218, -57.545862);
                LatLng point6 = new LatLng(-24.99435, -57.545302);
                LatLng point7 = new LatLng(-24.992286, -57.544671);
                LatLng point8 = new LatLng(-24.990286, -57.544661);
                LatLng point9 = new LatLng(-24.989569, -57.544452);
                LatLng point10 = new LatLng(-24.989856, -57.542852);
                LatLng point11 = new LatLng(-24.990154, -57.540949);
                LatLng point12 = new LatLng(-24.990564, -57.539017);
                LatLng point13 = new LatLng(-24.990997, -57.536903);

                LatLng centerPoint = new LatLng(-24.995305, -57.540481);
                LatLng hotelCerrito = new LatLng(-24.967177, -57.559704);
                googleMap.addMarker(new MarkerOptions().position(hotelCerrito).title("Hotel Cerrito").snippet("+(595) 0271 272799")).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_hotel_marker));

                String pointLabel = getString(R.string.pointLabel);
                final String title1 = pointLabel+" 1";
                final String title2 = pointLabel+" 2";
                final String title3 = pointLabel+" 3";
                final String title4 = pointLabel+" 4";
                final String title5 = pointLabel+" 5";
                final String title6 = pointLabel+" 6";
                final String title7 = pointLabel+" 7";
                final String title8 = pointLabel+" 8";
                final String title9 = pointLabel+" 9";
                final String title10 = pointLabel+" 10";
                final String title11 = pointLabel+" 11";
                final String title12 = pointLabel+" 12";
                final String title13 = pointLabel+" 13";

                googleMap.addMarker(new MarkerOptions().position(point1).title(title1).snippet(getString(R.string.point1Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker1));
                googleMap.addMarker(new MarkerOptions().position(point2).title(title2).snippet(getString(R.string.point2Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker2));
                googleMap.addMarker(new MarkerOptions().position(point3).title(title3).snippet(getString(R.string.point3Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker3));
                googleMap.addMarker(new MarkerOptions().position(point4).title(title4).snippet(getString(R.string.point4Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker4));
                googleMap.addMarker(new MarkerOptions().position(point5).title(title5).snippet(getString(R.string.point5Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker5));
                googleMap.addMarker(new MarkerOptions().position(point6).title(title6).snippet(getString(R.string.point6Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker6));
                googleMap.addMarker(new MarkerOptions().position(point7).title(title7).snippet(getString(R.string.point7Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker7));
                googleMap.addMarker(new MarkerOptions().position(point8).title(title8).snippet(getString(R.string.point8Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker8));
                googleMap.addMarker(new MarkerOptions().position(point9).title(title9).snippet(getString(R.string.point9Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker9));
                googleMap.addMarker(new MarkerOptions().position(point10).title(title10).snippet(getString(R.string.point10Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker10));
                googleMap.addMarker(new MarkerOptions().position(point11).title(title11).snippet(getString(R.string.point11Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker11));
                googleMap.addMarker(new MarkerOptions().position(point12).title(title12).snippet(getString(R.string.point12Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker12));
                googleMap.addMarker(new MarkerOptions().position(point13).title(title13).snippet(getString(R.string.point13Description) + "\n" + getString(R.string.map_description_label))).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker13));

                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        //Get view from the layout file
                        Bundle bundle = new Bundle();
                        View v = getLayoutInflater(bundle).inflate(R.layout.map_infowindow, null);

                        TextView titleTV = (TextView) v.findViewById(R.id.titleTV);
                        TextView descriptionTV = (TextView) v.findViewById(R.id.descriptionTV);

                        titleTV.setText(marker.getTitle());
                        descriptionTV.setText(marker.getSnippet());

                        return v;
                    }
                });

                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Intent intent = new Intent(getContext(), ObservationPointActivity.class);
                        String title = marker.getTitle();
                        int numPoint;
                        String description;
                        if(title.equals(title1)){
                            numPoint = 1;
                            description = getString(R.string.point1Description);
                        }else if(title.equals(title2)){
                            numPoint = 2;
                            description = getString(R.string.point2Description);
                        }else if(title.equals(title3)){
                            numPoint = 3;
                            description = getString(R.string.point3Description);
                        }else if(title.equals(title4)){
                            numPoint = 4;
                            description = getString(R.string.point4Description);
                        }else if(title.equals(title5)){
                            numPoint = 5;
                            description = getString(R.string.point5Description);
                        }else if(title.equals(title6)){
                            numPoint = 6;
                            description = getString(R.string.point6Description);
                        }else if(title.equals(title7)){
                            numPoint = 7;
                            description = getString(R.string.point7Description);
                        }else if(title.equals(title8)){
                            numPoint = 8;
                            description = getString(R.string.point8Description);
                        }else if(title.equals(title9)){
                            numPoint = 9;
                            description = getString(R.string.point9Description);
                        }else if(title.equals(title10)){
                            numPoint = 10;
                            description = getString(R.string.point10Description);
                        }else if(title.equals(title11)){
                            numPoint = 11;
                            description = getString(R.string.point11Description);
                        }else if(title.equals(title12)){
                            numPoint = 12;
                            description = getString(R.string.point12Description);
                        }else if(title.equals(title13)){
                            numPoint = 13;
                            description = getString(R.string.point13Description);
                        }
                        else{
                            numPoint = -1;
                            description = "";
                        }

                        if(numPoint != -1) {
                            intent.putExtra("title", getString(R.string.pointLabel) + " " + numPoint);
                            intent.putExtra("description", description);
                            startActivity(intent);
                        }
                    }
                });

                // For zooming automatically to the location of the marker
                final CameraPosition cameraPositionWetlands = new CameraPosition.Builder().target(centerPoint).zoom(15).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPositionWetlands));

                //set up button that will allow users to return to the wetlands on the map if they cannot find it
                returnButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPositionWetlands));
                    }
                });

            }
        });

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_MAPS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        googleMap.setMyLocationEnabled(true);
                    }
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mMapView != null) {
            mMapView.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(mMapView != null) {
            mMapView.onLowMemory();
        }
    }
}
