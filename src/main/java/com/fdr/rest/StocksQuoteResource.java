package com.fdr.rest;

import com.fdr.model.stocks.DailyAdjustedRequest;
import com.fdr.model.stocks.GlobalQuoteRequest;
import com.fdr.model.stocks.IntraDayRequest;
import com.fdr.model.stocks.AlphaVantageResponse;
import com.fdr.service.AlphaVantageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("stocks/quote")
public class StocksQuoteResource {

    private final AlphaVantageService alphaVantageService;

    @Autowired
    public StocksQuoteResource(AlphaVantageService alphaVantageService) {
        this.alphaVantageService = alphaVantageService;
    }

    @GetMapping(value = "intra-day", produces = "application/json")
    public AlphaVantageResponse getIntraDayResponse(@RequestParam @NotNull String symbol, @RequestParam(defaultValue = "5min") String timeInterval) {

        return alphaVantageService.intraDay(IntraDayRequest.builder().symbol(symbol).timeInterval(timeInterval).build());

    }

    @GetMapping(value = "daily-adjusted", produces = "application/json")
    public AlphaVantageResponse getDailyAdjustedResponse(@RequestParam @NotNull String symbol) {

        return alphaVantageService.dailyAdjusted(DailyAdjustedRequest.builder().symbol(symbol).build());

    }

    @GetMapping(value = "global-quote", produces = "application/json")
    public AlphaVantageResponse getGlobalQuoteResponse(@RequestParam @NotNull String symbol) {

        return alphaVantageService.globalQuote(GlobalQuoteRequest.builder().symbol(symbol).build());

    }

}