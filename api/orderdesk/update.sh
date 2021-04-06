#!/bin/bash

# Experimentations with the OrderDesk API

query=$1

echo $query;
updateendpoint="https://app.orderdesk.me/api/v2/batch-inventory-items"
getendpoint="https://app.orderdesk.me/api/v2/inventory-items"
url="$getendpoint""?""$query" 
content="Content-Type: application/json" 

# Grab current results, update them, grab new results
curl "$url" \
  -H "ORDERDESK-STORE-ID: ""$ORDERDESK_STORE_ID" \
  -H "ORDERDESK-API-KEY: ""$ORDERDESK_API_KEY" \
  -H "$content" | \
  jq '.inventory_items|map(.stock=.stock-2)|map(.update_source="Foxy-Orderdesk-Webhook")' | \
curl -d @- \
  --url "$updateendpoint" \
  -H "ORDERDESK-STORE-ID: ""$ORDERDESK_STORE_ID" \
  -H "ORDERDESK-API-KEY: ""$ORDERDESK_API_KEY" \
  -X PUT \
  -H "$content" &&
curl "$url" \
  -H "ORDERDESK-STORE-ID: ""$ORDERDESK_STORE_ID" \
  -H "ORDERDESK-API-KEY: ""$ORDERDESK_API_KEY" \
  -H "$content" | jq

