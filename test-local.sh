#!/bin/bash

# ===============================================
# Local Test Script for Kafka Order System
# ===============================================

set -e

echo " Starting Kafka and services..."
docker compose up -d

# Give Kafka and Spring Boot some time to start
echo " Waiting for services to be ready..."
sleep 20

# Test REST endpoint
echo " Sending test order..."
curl -s -X POST http://localhost:8080/api/orders/place \
  -H "Content-Type: application/json" \
  -d '{
        "orderId": "TEST-001",
        "product": "TestProduct",
        "price": 99.99,
        "status": "NEW"
      }'

echo -e "\n✅ Test order sent."

# Tail logs for a few seconds to verify processing
echo " Showing logs for 15 seconds..."
docker compose logs -f --tail=20 &
LOG_PID=$!
sleep 15
kill $LOG_PID || true

# Clean up
echo "Stopping and removing Docker containers..."
docker compose down -v

echo "Local test finished successfully!"
