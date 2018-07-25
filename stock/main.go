package main

import (
	"fmt"

	"github.com/aws/aws-lambda-go/lambda"
	"github.com/naik1985/repos/stock/stocksearch"
	"github.com/naik1985/repos/stock/structs"
	"github.com/naik1985/repos/stock/utils"
)

func main() {
	stockName := utils.getEnvValue("STOCK")
	apiKey := utils.getEnvValue("APIKEY")
	stock := structs.StockPriceDetails{StockName: stockName,
		APIKey: apiKey}

	foundOpenVal, foundCloseVal := stocksearch.getStockValues(stock)

	foundValues := fmt.Sprintf("Stock: %s Open is: "+
		"%s and Close is: %s", stockName,
		foundOpenVal, foundCloseVal)

	toEmail := utils.getEnvValue("TOEMAIL")
	fromEmail := utils.getEnvValue("FROMEMAIL")
	snsDetailsObj := structs.SNSDetails{
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
	snsDetailsObj.sendSNSEmail()
	lambda.Start(Handler)

}
