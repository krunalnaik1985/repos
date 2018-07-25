package main

import (
	"fmt"

	"github.com/aws/aws-lambda-go/lambda"
)

func main() {
	stockName := getEnvValue("STOCK")
	apiKey := getEnvValue("APIKEY")
	stock := StockPriceDetails{StockName: stockName,
		APIKey: apiKey}

	foundOpenVal, foundCloseVal := getStockValues(stock)

	foundValues := fmt.Sprintf("Stock: %s Open is: "+
		"%s and Close is: %s", stockName,
		foundOpenVal, foundCloseVal)

	toEmail := getEnvValue("TOEMAIL")
	fromEmail := getEnvValue("FROMEMAIL")
	snsDetailsObj := SNSDetails{
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
