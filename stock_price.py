import requests
import datetime


class StockHelper(object):
    def __init__(self, stock_name='', api_key=''):
        self.stock_name = stock_name
        self.api_key = api_key


    def get_stock_data(self):
        with requests.Session() as sess_stock:
            r1 = sess_stock.get("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol={0}&interval=1min&outputsize=compact&apikey={1}".format(self.stock_name,
                                                                                                                                                                      self.api_key))
            r1.raise_for_status()
            data = r1.json()
            return data


    def get_current_date(self):
        curr_date = datetime.datetime.now().strftime("%Y-%m-%d")
        return str(curr_date)


    def query_stock(self):
        data = self.get_stock_data()
        currDate = self.get_current_date()
        daily_data = data['Time Series (Daily)']
        if currDate in daily_data:
            dataFound = daily_data[currDate]
        print(dataFound)



if __name__ == "__main__":
    sh = StockHelper()
    sh.query_stock()


