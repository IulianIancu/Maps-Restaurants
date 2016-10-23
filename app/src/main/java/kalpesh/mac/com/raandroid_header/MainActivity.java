package kalpesh.mac.com.raandroid_header;

import android.Manifest;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.concurrent.TimeUnit;

import kalpesh.mac.com.raandroid_header.adapter.Adapter;
import kalpesh.mac.com.raandroid_header.constants.Constants;
import kalpesh.mac.com.raandroid_header.model.Example;
import kalpesh.mac.com.raandroid_header.model.Restaurant;
import kalpesh.mac.com.raandroid_header.observables.IRestaurant;
import kalpesh.mac.com.raandroid_header.services.FetchAddressIntentService;
import kalpesh.mac.com.raandroid_header.services.Services;
import kalpesh.mac.com.raandroid_header.utilities.RxUtils;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends ListActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    //Composite Subscription
    private IRestaurant _api;
    private List<Restaurant> mRestaurantList;

    //    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ProgressDialog pDialog;
    private String pcode = "E14";
    private EditText search;
    private ImageButton myLoc;
    private Button go;
    private Button maps;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    private AddressResultReceiver mResultReceiver;
    private boolean first;
//    Toolbar toolbar;

    /**
     * Subscription that represents a group of Subscriptions that are unsubscribed together.
     */
    private CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first=true;
        search = (EditText) findViewById(R.id.search_bar);
        myLoc = (ImageButton) findViewById(R.id.go);
        go = (Button) findViewById(R.id.submit_area);
        maps = (Button) findViewById(R.id.show_maps);
        mResultReceiver = new AddressResultReceiver(new Handler());
//        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        buildGoogleApiClient();

/**
 * Retrofit 1.9
 */

        _api = Services._createRestruentshubApi();

        /**
         * Retrofit 2.0
         */
        // _api = Services.getClient();


//        pattern();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(MainActivity.this);
                // Showing progress dialog before making http request
                pDialog.setMessage("Loading...");
                pDialog.show();
                pattern();
            }
        });
        myLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntentService();
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRestaurantList!=null){
                    Intent maps = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(maps);}
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
    }

    @Override
    public void onPause() {
        super.onPause();
        RxUtils.unsubscribeIfNotNull(_subscriptions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public void pattern() {
        String temp = "W30HU";
        Log.e("HYAAA", search.getText().toString());
        if (search.getText().toString().equals("")) Log.i("E14", temp);
        else {
            Log.i("Whaaaaaat?", search.getText().toString());
            temp = search.getText().toString();
        }
        _subscriptions.add(_api.getRestraurent(temp)
//http://docs.couchbase.com/developer/java-2.0/observables.html
                .timeout(5000, TimeUnit.MILLISECONDS)
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Example>>() {
                    @Override
                    public Observable<? extends Example> call(Throwable throwable) {
                        Toast.makeText(getBaseContext(), "Error ", Toast.LENGTH_SHORT).show();
                        Log.i("ERROR RX", "NO MSG");
                        return Observable.error(throwable);
                    }
                })

//                .retry(5)
                .distinct()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Example>() {
                    @Override
                    public void onCompleted() {
                        hidePDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hidePDialog();
                    }

                    @Override
                    public void onNext(Example example) {
                        mRestaurantList = example.getRestaurants();
                        System.out.println("Got: " + " (" + Thread.currentThread().getName() + ")");
                        mAdapter = new Adapter(getApplicationContext(), R.layout.row, mRestaurantList);
                        Log.i("DATA IS", "" + mRestaurantList);
                        setListAdapter(mAdapter);
                    }
                }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e("THIS ","IS BEING CALLED");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e("CONNECTION","IT DIDN'T DO IT");
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Log.e("LOCATION","GOT");
            // Determine whether a Geocoder is available.
            if (!Geocoder.isPresent()) {
                Toast.makeText(this, "no_geocoder_available", Toast.LENGTH_LONG).show();
                Log.e("Geocoder","BROKEN!");
                return;
            }
            // It is possible that the user presses the button to get the address before the
            // GoogleApiClient object successfully connects. In such a case, mAddressRequested
            // is set to true, but no attempt is made to fetch the address (see
            // fetchAddressButtonHandler()) . Instead, we start the intent service here if the
            // user has requested an address, since we now have a connection to GoogleApiClient.
            if (!first){
                startIntentService();
            }else first=false;

        }

    }
    protected void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         *  Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
         */
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string or an error message sent from the intent service.
            search.setText(resultData.getString(Constants.RESULT_DATA_KEY));
            Log.e("DISPLAY","Is there any text?");



        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        stopService(intent);
        super.onDestroy();
    }
}
