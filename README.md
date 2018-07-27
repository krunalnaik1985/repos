# repos
Monitor Stock Daily

Server less App. stock/main.go

1) Make sure go/golang is installed
2) go build . or use scripts/build.sh if gox is installed.

requirement:
1) AWS lambda
2) AWS dynamodb
3) AWS SNS
4) AWS SES

set this env in AWS Lambda or in Env if running locally.
export STOCK=MSFT
export APIKEY={keyID} from Advantage.io
export DYNAMODBTABLE={dynadb_table_name}
export BOUGHTSTOCKPRICE=105.89 ( If i bought today)
export BOUGHTSTOCKSIZE=80.00 

It will email daily gain or loss and monitor high price throughout.
