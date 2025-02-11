**Find a specific transaction with the transactionId**
curl --request GET \
  --url http://localhost:9092/api/transaction/A-s-4863-9ebb-eb0f0595cc96

**Response**

{
	"description": "This is the payment transaction description.",
	"transactionId": "A-s-4863-9ebb-eb0f0595cc96",
	"batchTransactionId": "dd2fa63a-8410-4350-b909-1f4cbf8ae414",
	"transactionAmount": {
		"total": 30.11,
		"currency": "USD",
		"details": {
			"subtotal": 30.00,
			"shipping": 0.03
		}
	},
	"tax": 3.01
}

**Retrive a transaction list , with the transaction Batch Id**

curl --request GET \
  --url http://localhost:9092/api/batch-transactions/05517347-bbde-4ae9-a685-6fd649295d72
  
**Response**

{
	"transactions": [
		{
			"description": "description.",
			"transactionId": "A3333-s-4863-9ebb-eb0f0595cc96",
			"batchTransactionId": "05517347-bbde-4ae9-a685-6fd649295d72",
			"transactionAmount": {
				"total": 10000.11,
				"currency": "ARS",
				"details": {
					"subtotal": 301.00,
					"shipping": 0.03
				}
			},
			"tax": 1000.01
		}
	]
}

**Retrive the all the taxes**

curl --request GET \
  --url http://localhost:9092/api/taxes
  
**Response**

[
	{
		"currency": "USD",
		"tax": 10.00,
		"id": 1
	},
	{
		"currency": "ARS",
		"tax": 10.00,
		"id": 2
	},
	{
		"currency": "REL",
		"tax": 10.00,
		"id": 52
	},
	{
		"currency": "YN",
		"tax": 107.00,
		"id": 53
	}
]
  

**Find a tax by currency**

curl --request GET \
  --url http://localhost:9092/api/tax/USD
  
**Response**

{
	"currency": "ARS",
	"tax": 10.00,
	"id": 2
}
  

**Create a new tax**

curl --request POST \
  --url http://localhost:9092/api/tax \
  --header 'Content-Type: application/json' \
  --data '{
	"currency": "YN",
	"tax": 107.00
}'

**Response**

{
	"currency": "YN",
	"tax": 107.00,
	"id": 53
}

  
