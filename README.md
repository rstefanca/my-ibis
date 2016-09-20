# my-ibis
Test project

TODO: popis projektu

##OAuth

Získání tokenu pro testovacího uživatele

curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=heslo&username=testovic@test.cz&grant_type=password&scope=write&client_secret=123456&client_id=clientapp"
