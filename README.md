
## STACK TECNOLOGICO

[Diseño y comentarios](https://linktodocumentation)

La API está hecha en java 11, usa JPA para persistencia y mapeo de relaciones, h2db para base de datos en memoria y lombok para achicar el volumen de las clases (getters y setters).

Mejoras que no llegué a implementar porque me quedé corto de tiempo:

 - Me hubiera gustado modelar las tarjetas de crédito.
   De forma tal de tenerlos como una entidad más y encapsular 
   las validaciones y toda la lógica propia en sus capas respectivas (service, controller). En mi API yo las diseñè como si fueran tarjetas de débito pero hubiera estado bueno hacerlo algo más sofisticado. (implementando fecha de corte, validacion de fecha de vencimiento, tope de consumo de la tarjeta, etc).

  
- Me faltó mejor manejo de excepciones en la capa de servicio y además me hubiera gustado implementar logging, ya sea con log4j o parecidos para facilitar el tracing.
# Banking-System / challenge técnico


Pasos para correr la aplicación. 

1) Clonar este repo o bien descargar el proyecto zipeado.
 LINK : 

2) tener seteado el JDK en java 11. 

3) cargar el proyecto como proyecto de Maven.
  (click en el pom.xml y darle a run as maven proyect)

4) en la terminal ejecutar spring-boot:run




        
##          PRUEBAS EN POSTMAN : 

#### crear usuario básico

```http
  POST /api/users

  {
    "firstName": "Edsger",
    "lastName": " dijkstra",
    "email": "shortestPath@foo.com",
    "password": "123"
}
```


#### listar usuarios

```http
  GET /api/users
```

#### listar cuentas bancarias de un usuario existente

```http
  GET /api/users/{id}/accounts
```


#### crear cuenta bancaria 

```http
  POST /api/accounts

  {
    "currency": "USD",
    "balance": 1000.0,
    "user": {
        "id": 1 // el id tiene que ser de un usuario existente
    }
}
```

#### Obtener una cuenta dado un id de una cuenta existente


```http
  GET /api/acccounts/{id}
```

#### Listar todas las cuentas existentes

```http
  GET /api/acccounts
```
#### Listar todas las cuentas pero paginadas

```http
  GET /api/acccounts/page

```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `page`      | `int` | Indica desde qué indice se va a mostrar |

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `size`      | `int` | indica cuántos elementos queremos visualizar |

#### Crear Transaccion de pago con Tarjeta


```http
  POST /api/transactions

  {
    "transaction_type": "CARD_PAYMENT",
    "paymentId": "abc123",
    "cardId": "43211234",
    "amount": 100.0,
    "currency": "USD",
    "status": "COMPLETED",
    "account": {
        "id": 1  // el id tiene que ser de una cuenta existente
    },
    "merchant": {
        "name": "Amazon",
        "merchantId": "12309"
    },
    "mccCode": 5411
}
```

#### Crear Transaccion de transferencia P2P


```http
  POST /api/transactions

{
    "transaction_type": "P2P_TRANSFER",
    "transferId": "p2p123456",
    "senderId": "user123",
    "recipientId": "user456",
    "note": "Payment for lunch",
    "amount": 250.0,
    "currency": "USD",
    "status": "PENDING",
    "account": {
        "id": 1  // el id debe ser de una cuenta existente
    }
}
```

#### Crear Transaccion de transferencia bancaria


```http
  POST /api/transactions

{
    "transaction_type": "BANK_TRANSFER",
    "transactionId": "bt123456",
    "bankCode": "123456",
    "recipientAccount": "987654321",
    "amount": 500.0,
    "currency": "USD",
    "status": "PENDING",
    "account": {
        "id": 1  // el id tiene que ser de una cuenta existente
    }
}

```

#### Obtener todas las transacciones pero paginadas


```http
  GET /api/transactions/page

```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `page`      | `int` | Indica desde qué indice se va a mostrar |

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `size`      | `int` | indica cuántos elementos queremos visualizar |