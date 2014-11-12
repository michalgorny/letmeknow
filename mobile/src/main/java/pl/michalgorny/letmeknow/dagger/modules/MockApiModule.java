package pl.michalgorny.letmeknow.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.michalgorny.letmeknow.api.parse.ParseService;
import pl.michalgorny.letmeknow.ui.MainActivity;
import retrofit.RestAdapter;
import retrofit.client.Client;

/**
 * @author m.gorny
 */
@Module(
        complete = false,
        library = true,
        injects = {MainActivity.class}
)
public class MockApiModule {

    private static final String MOCK_PARSE = "mock_parse";

    @Provides
    @ApiModule.ApiName(MOCK_PARSE)
    ParseService provideMockParseService(Client client){
        return new RestAdapter.Builder()
                .setClient(client)
                .build().create(ParseService.class);
    }

    @Singleton
    @Provides
    Client provideMockClient(){
        return new MockClient();
    }
}
