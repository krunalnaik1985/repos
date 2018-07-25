package utils

import (
	"fmt"
	"log"
	"net/smtp"
	"os"
	"time"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/awserr"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/ses"
)

type SNSDetails struct {
	AwsRegion string
	ToEmail   string
	FromEmail string
	Subject   string
	CharSet   string
	TextBody  string
}


func GetCurrentTime() string {
	currentTime := time.Now()
	date := currentTime.Format("2006-01-02")
	fmt.Println(date)
	return date
}

func SendEmail(toEmail string, fromEmail string, emailPassword string, message string) {
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

func GetEnvValue(envValue string) string {
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

func (snsObj *SNSDetails) SendSNSEmail() {
	// Create a new session in the us-west-2 region.
	// Replace us-west-2 with the AWS Region you're using for Amazon SES.
	sess, err := session.NewSession(&aws.Config{
		Region: aws.String(snsObj.AwsRegion)},
	)

	// Create an SES session.
	svc := ses.New(sess)

	// Assemble the email.
	input := &ses.SendEmailInput{
		Destination: &ses.Destination{
			CcAddresses: []*string{},
			ToAddresses: []*string{
				aws.String(snsObj.ToEmail),
			},
		}, Message: &ses.Message{
			Body: &ses.Body{
				Text: &ses.Content{
					Charset: aws.String(snsObj.CharSet),
					Data:    aws.String(snsObj.TextBody),
				},
			},
			Subject: &ses.Content{
				Charset: aws.String(snsObj.CharSet),
				Data:    aws.String(snsObj.Subject),
			},
		},
		Source: aws.String(snsObj.FromEmail),
		// Uncomment to use a configuration set
		//ConfigurationSetName: aws.String(ConfigurationSet),
	}

	// Attempt to send the email.
	result, err := svc.SendEmail(input)

	// Display error messages if they occur.
	// Display error messages if they occur.
	if err != nil {
		if aerr, ok := err.(awserr.Error); ok {
			switch aerr.Code() {
			case ses.ErrCodeMessageRejected:
				fmt.Println(ses.ErrCodeMessageRejected, aerr.Error())
			case ses.ErrCodeMailFromDomainNotVerifiedException:
				fmt.Println(ses.ErrCodeMailFromDomainNotVerifiedException, aerr.Error())
			case ses.ErrCodeConfigurationSetDoesNotExistException:
				fmt.Println(ses.ErrCodeConfigurationSetDoesNotExistException, aerr.Error())
			default:
				fmt.Println(aerr.Error())
			}
		} else {
			// Print the error, cast err to awserr.Error to get the Code and
			// Message from an error.
			fmt.Println(err.Error())
		}

		return
	}

	fmt.Println("Email Sent to address: " + snsObj.ToEmail)
	fmt.Println(result)
}
