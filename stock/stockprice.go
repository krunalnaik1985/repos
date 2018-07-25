import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"net/smtp"
	"os"
	"time"
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

func getCurrentTime() string {
	currentTime := time.Now()
	date := currentTime.Format("2006-01-02")
	fmt.Println(date)
	return date
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

func sendEmail(toEmail string, fromEmail string, emailPassword string, message string) {
	subject := "Stock Notification for Today\n\n"

	body := "From: " + fromEmail + "\n" +
		"To: " + toEmail + "\n" +
		"Subject:" + subject + message
	auth := smtp.PlainAuth("", fromEmail, emailPassword, "smtp.gmail.com")
	err := smtp.SendMail(
		"smtp.gmail.com:587",
		auth,
		fromEmail,
		[]string{toEmail},
		[]byte(body),
	)
	if err != nil {
		log.Fatal(err)
	}
}

func getEnvValue(envValue string) string {
	var valueName string
	value, con := os.LookupEnv(envValue)
	if con {
		log.Println("Found Environment Variables:", envValue)
		valueName = value
	} else {
		log.Fatalln("Value is not found", envValue)
	}
	return valueName
}