package pl.michalgorny.letmeknow.dagger.modules;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.michalgorny.letmeknow.api.parse.ParseRequestInterceptor;
import pl.michalgorny.letmeknow.api.parse.ParseService;
import pl.michalgorny.letmeknow.managers.GameManager;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;

/**
 * @author m.gorny
 */

@Module(
        complete = false,
        injects = GameManager.class,
        library = true
)
public class ApiModule {

    @Qualifier
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ApiName {
        String value() default "";
    }

    private static final String PARSE = "parse";
    private static final String PARSE_URL = "https://api.parse.com/1";

    @Provides
    @ApiName(PARSE)
    Endpoint provideParseApiEndpoint(){
        return Endpoints.newFixedEndpoint(PARSE_URL);
    }

    @Provides
    @Singleton
    @ApiName(PARSE)
    public RestAdapter provideParseApiRestAdapter(@ApiName(PARSE) Endpoint endpoint, ParseRequestInterceptor headers) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setRequestInterceptor(headers)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    @Provides
    @Singleton
    ParseService provideParseService(@ApiName(PARSE)RestAdapter restAdapter){
        return restAdapter.create(ParseService.class);
    }
}
