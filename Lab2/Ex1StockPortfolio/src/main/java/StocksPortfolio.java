import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private String name;
    private IStockMarket stockMarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockMarket stockMarket){
        this.stockMarket = stockMarket;
        stocks = new ArrayList<>();
    }


    public IStockMarket getMarketService(){
        return this.stockMarket;
    }

    public void setMarketService(IStockMarket market){
        this.stockMarket = market;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String n){
        this.name = n;
    }

    public double getTotalValue(){

        double tot = 0.0;
        for(Stock s : this.stocks){
            tot += stockMarket.getPrice(s.getName()) * s.getQuantity();
        }
        return tot;
    }

    public void addStock(Stock s){
        this.stocks.add(s);
    }
}