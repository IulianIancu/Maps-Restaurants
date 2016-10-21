package kalpesh.mac.com.raandroid_header.services;

import kalpesh.mac.com.raandroid_header.observables.IRestaurant;
import kalpesh.mac.com.raandroid_header.utilities.APIErrorHandler;
import retrofit.RestAdapter;

import static kalpesh.mac.com.raandroid_header.constants.Constants.BASE_URL;

/**
 * Created by kalpesh on 27/12/2015.
 */
public class Services {
//    private static Retrofit retrofit = null;
//    private static OkHttpClient okHttpClient;

    public static IRestaurant _createRestruentshubApi() {

        /**
         * Retrofit 1.9
         */
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new APIErrorHandler());
        return builder.build().create(IRestaurant.class);


        /**
         * Retrofit 2.0
         */


    }
    /**
     * Exceptions: Caused by: java.lang.IllegalArgumentException: Unable to create call adapter for rx.Observable<
     */
//
//    public static IRestaurant getClient() {
//        okHttpClient = buildClient();
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .client(okHttpClient)
//                    .build();
//        }
//        return retrofit.create(IRestaurant.class);
//    }
//
//
//    public static OkHttpClient buildClient() {
//
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        //  builder.addNetworkInterceptor(new ResponseCacheInterceptor());
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        builder.addInterceptor(interceptor).build();
//        builder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Response response = chain.proceed(chain.request());
//                // Do anything with response here
//                //if we ant to grab a specific cookie or something..
//                return response;
//            }
//        });
//
//        builder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                //this is where we will add whatever we want to our request headers.
//                Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
//                return chain.proceed(request);
//            }
//        });
//return new OkHttpClient();
//
//    }
}
