Find a specific transaction with the transactionId
curl --request GET \
  --url http://localhost:9092/api/transaction/A-s-4863-9ebb-eb0f0595cc96

Retrive a transaction list , with the transaction Batch Id
curl --request GET \
  --url http://localhost:9092/api/batch-transactions/05517347-bbde-4ae9-a685-6fd649295d72

Retrive the all the taxes
curl --request GET \
  --url http://localhost:9092/api/taxes

Find a tax by currency
curl --request GET \
  --url http://localhost:9092/api/tax/USD

Create a new tax

curl --request POST \
  --url http://localhost:9092/api/tax \
  --header 'Content-Type: application/json' \
  --data '{
	"currency": "YN",
	"tax": 107.00
}'
  

  
