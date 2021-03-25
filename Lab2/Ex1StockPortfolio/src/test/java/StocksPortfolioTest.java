import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) //permite dependency injection do mock
class StocksPortfolioTest {

    @Mock
    IStockMarket market;
    @InjectMocks
    StocksPortfolio portfolio;

    @BeforeEach
    void setUp() {
        portfolio.setName("portfolio1");
    }

    @Test
    void getTotalValueTest() {
        portfolio.addStock(new Stock("WaltDisney", 5));
        portfolio.addStock(new Stock("Netflix", 10));
        portfolio.addStock(new Stock("Google", 6));

        Mockito.when(market.getPrice("WaltDisney")).thenReturn(8.0);
        Mockito.when(market.getPrice("Netflix")).thenReturn(6.0);
        Mockito.when(market.getPrice("Google")).thenReturn(6.0);

        assertThat(portfolio.getTotalValue(),is(136.0)); // total = 5*8+6*10+6*6

        Mockito.verify(market, Mockito.times(3)).getPrice(Mockito.anyString()); //Verify the Mock's function was called three times
    }
}