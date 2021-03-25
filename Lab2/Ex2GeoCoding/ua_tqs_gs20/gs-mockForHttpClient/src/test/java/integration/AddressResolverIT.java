package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {
    private ISimpleHttpClient client;
    private AddressResolver resolver;

    // sem o mock deixa de ser teste unitário, este é teste de integracao (IT)
    // neste caso existe recurso à internet q só funciona se esta estiver a funcionar, pq precisamos de ir buscar a resposta json diretamente à API, coisa q no ex anterior foi colocada diretamente no codigo graças ao mock
    @BeforeEach
    public void init(){
        client =  new TqsBasicHttpClient();
        resolver = new AddressResolver(client);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {
        Address result = resolver.findAddressForLocation(40.640661, -8.656688);
        assertEquals( result, new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null) );

    }

    @Test
    public void whenBadCoordidates_trhowBadArrayindex() throws IOException, URISyntaxException, ParseException {
        assertThrows(IndexOutOfBoundsException.class, () -> resolver.findAddressForLocation(400.0000,-350.00000));
    }

}
// NOTA: com mvn test e mvn install o maven corre classes q terminam em test, logo, corre apenas os testes unitários (os + rapidos)
// pra executar integration tests (+ lentos e q gastam + recursos) e  testes unitários usar mvn install failsafe:integration-test