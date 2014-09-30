package pl.michalgorny.letmeknow.dagger.modules;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.michalgorny.letmeknow.events.GameManager;
import pl.michalgorny.letmeknow.events.RestService;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;

/**
 * @author m.gorny
 */

@Module(
        complete = false,
        injects = GameManager.class
)
public class ApiModule {

    @Qualifier
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ApiName {
        String value() default "";
    }

    private static final String PARSE = "parse";
    private static final String PARSE_URL = "fill_it_with_proper_url";

    @Provides
    @ApiName(PARSE)
    Endpoint provideParseApiEndpoint(){
        return Endpoints.newFixedEndpoint(PARSE_URL);
    }

    @Provides
    @Singleton
    @ApiName(PARSE)
    public RestAdapter provideParseApiRestAdapter(@ApiName(PARSE) Endpoint endpoint) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .build();
    }

    @Provides
    @Singleton
    RestService provideRestService(@ApiName(PARSE)RestAdapter restAdapter){
        return restAdapter.create(RestService.class);
    }
}
