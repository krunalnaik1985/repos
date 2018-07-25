package main

import (
	"fmt"
	"git/repos/stock/stocksearch"
	"git/repos/stock/utils"
)

func main() {
	stockName := utils.GetEnvValue("STOCK")
	apiKey := utils.GetEnvValue("APIKEY")
	stock := stocksearch.StockPriceDetails{StockName: stockName,
		APIKey: apiKey}

	foundOpenVal, foundCloseVal := stock.GetStockValues()

	foundValues := fmt.Sprintf("Stock: %s Open is: "+
		"%s and Close is: %s", stockName,
		foundOpenVal, foundCloseVal)

	toEmail := utils.GetEnvValue("TOEMAIL")
	fromEmail := utils.GetEnvValue("FROMEMAIL")
	snsDetailsObj := utils.SNSDetails{
		AwsRegion: "us-east-1",
		FromEmail: fromEmail,
		ToEmail:   toEmail,
		Subject:   "Stock Notification for Today\n\n",
		CharSet:   "UTF-8",
		TextBody:  foundValues,
	}

	//GMAIL
	//sendEmail(toEmail, fromEmail, emailPassword, foundValues)

	//SNS
	snsDetailsObj.SendSNSEmail()
}
