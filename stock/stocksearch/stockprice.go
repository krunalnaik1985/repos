package stocksearch

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"strings"
)

func (stock *StockPriceDetails) getStockData() map[string]interface{} {
	stockUrl := fmt.Sprintf("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&"+
		"symbol=%s&interval=1min&outputsize=compact&apikey=%s",
		stock.StockName,
		stock.APIKey)

	client := &http.Client{}
	req, err := http.NewRequest("GET", stockUrl, nil)
	if err != nil {
		log.Fatal("Found Error", err)
	}
	req.Header.Set("Content-Type", "application/json")
	resp, err := client.Do(req)
	if resp.StatusCode == 200 || resp.StatusCode == 429 {
		getStockData, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			log.Fatal("Found Error", err)
		}
		var stockToken StockValues
		err1 := json.Unmarshal(getStockData, &stockToken)
		if err1 != nil {
			log.Fatal("Unmarshal Error", err1)
		}
		log.Println("Stock Price Found")
		return stockToken.Token
	} else {
		log.Println("Stock Price not Found", resp)
	}

	return nil
}

func (stock *StockPriceDetails) QueryStock(timeT string) interface{} {
	stockData := stock.getStockData()
	var foundValue interface{}
	for key, value := range stockData {
		if timeT == key {
			foundValue = value
		}
	}
	return foundValue
}

func getStockValues(stock StockPriceDetails) (string, string) {
	currTime := getCurrentTime()
	gotValue := stock.QueryStock(currTime)
	var foundOpenVal string
	var foundCloseVal string
	if gotValue != nil {
		stringMap := gotValue.(map[string]interface{})
		for key, value := range stringMap {
			foundOpen := strings.Contains(key, "open")
			foundClose := strings.Contains(key, "close")
			if foundOpen {
				foundOpenVal = fmt.Sprintf("%v", value)
			}
			if foundClose {
				foundCloseVal = fmt.Sprintf("%v", value)
			}

		}
	}
	return foundOpenVal, foundCloseVal
}
