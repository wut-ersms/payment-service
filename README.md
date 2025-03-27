# Payment Service
## Use SDKManRC
https://sdkman.io/usage/
### Set Java version
```
sdk env
```

## Commands
### Build app

```
./gradlew clean build
```

### Run app
```
./gradlew bootRun
```
Or simply from Intellij. Run Application class (No build is needed).

### Build Docker Image
https://bmuschko.github.io/gradle-docker-plugin/current/user-guide/#spring_boot_application_plugin
```
./gradlew dockerBuildImage
```

## Swagger
http://localhost:8080/swagger-ui/index.html

### Annotations
@Tag - use on class level to describe controller  
@Operation - use on method level to describe endpoint  
@ApiResponse - use to define response codes

## TPay docs
- https://docs.tpay.com/  
- https://docs-api.tpay.com/  
- https://docs-api.tpay.com/pl/first-steps/environments/
- https://panel.sandbox.tpay.com/
- https://openapi.sandbox.tpay.com
- https://support.tpay.com/sprzedawca/srodowisko-testowe-sandbox
