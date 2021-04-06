query=$1
echo $query;
endpoint="https://app.orderdesk.me/api/v2/inventory-items"
url="$endpoint""?""$query" 
echo $url
echo "curl $url -H ORDERDESK-STORE-ID: $ORDERDESK_STORE_ID -H ORDERDESK-API-KEY: $ORDERDESK_API_KEY -H Content-Type: application/json | jq"

curl "$url" -H "ORDERDESK-STORE-ID: ""$ORDERDESK_STORE_ID" -H "ORDERDESK-API-KEY: ""$ORDERDESK_API_KEY" -H "Content-Type: application/json" | jq
