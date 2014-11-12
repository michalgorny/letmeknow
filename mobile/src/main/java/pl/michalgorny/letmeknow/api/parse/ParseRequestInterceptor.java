package pl.michalgorny.letmeknow.api.parse;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.michalgorny.letmeknow.R;
import retrofit.RequestInterceptor;

/**
 * @author m.gorny
 */

@Singleton
public class ParseRequestInterceptor implements RequestInterceptor{

    private static final String PARSE_APPLICATION_ID = "X-Parse-Application-Id";
    private static final String PARSE_REST_API_KEY = "X-Parse-REST-API-Key";

    @Inject
    Context mContext;

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(PARSE_APPLICATION_ID, mContext.getString(R.string.application_id));
        request.addHeader(PARSE_REST_API_KEY, mContext.getString(R.string.rest_client_key));
    }
}
