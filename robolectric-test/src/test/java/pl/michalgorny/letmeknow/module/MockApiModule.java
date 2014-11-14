package pl.michalgorny.letmeknow.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.michalgorny.letmeknow.api.parse.ParseService;
import pl.michalgorny.letmeknow.dagger.modules.ApiModule;
import pl.michalgorny.letmeknow.dagger.modules.AppModule;
import retrofit.RestAdapter;
import retrofit.client.Client;

/**
 * @author m.gorny
 */
@Module(
        includes = AppModule.class,
        complete = false,
        library = true
)
public class MockApiModule {

    private static final String MOCK_PARSE = "mock_parse";

    @Provides
    @ApiModule.ApiName("parse")
    ParseService provideMockParseService(Client client) {
        return new RestAdapter.Builder()
                .setClient(client)
                .build()
                .create(ParseService.class);


/*        ParseService service = mock(ParseService.class);
        when(mock(service.postStartNewGame(anyObject(), null))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        })*/
    }

    @Singleton
    @Provides
    Client provideMockClient() {
        return new MockClient();
    }
}
