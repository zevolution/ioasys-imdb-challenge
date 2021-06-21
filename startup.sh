#!/bin/bash
echo -e "\e[1m---------------------< Building application >---------------------\e[0m"
echo ""
echo ""
./mvnw clean install -P production
echo "------------------------------------------------------------------------------"
echo ""
echo ""

echo -e "\e[1m---------------------< Application provisioning startup >---------------------\e[0m"
echo ""
echo ""
docker-compose up -d
echo "------------------------------------------------------------------------------"
echo ""
echo ""

echo -e "\e[1mAccess URLs:\e[0m"
echo    "----------------------------------------------------------------"
echo -e "             \033[1mAPI URL:\033[0m http://localhost:8180/api"
echo -e "             \033[1mSwagger:\033[0m http://localhost:8180/api/swagger-ui.html"
echo -e "          \033[1mDebug Port:\033[0m 5110 "
echo    "----------------------------------------------------------------"
