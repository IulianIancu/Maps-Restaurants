package kalpesh.mac.com.raandroid_header.observables;

import kalpesh.mac.com.raandroid_header.model.Embedded;
import kalpesh.mac.com.raandroid_header.model.Example;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

//import retrofit.http.GET;
//import retrofit.http.Headers;

/**
 * Created by kalpesh on 27/12/2015.
 */
public interface IRestaurant {
    /**
     * In retrofit 2.0 remove slash
     * @return
     */
    @Headers({
            "Accept-Tenant: uk",
            "Accept-Language: en-GB",
            "Authorization: Basic VGVjaFRlc3RBUEk6dXNlcjI=",
            "Host: public.je-apis.com"
    })
    @GET("/restaurants")
    Observable<Example> getRestraurent(@Query("q") String pcode);



    @Headers({
            "Authorization: Bearer 68918301e04831dde6bb85975e43d07262321"
    })
    @GET("/subscriptions?locale=en-GB&appstore=2")
    Observable<Embedded> getSubcriptions();

}
