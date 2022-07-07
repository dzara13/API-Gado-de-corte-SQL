## Documentação da API V1.0 alpha

###### V1.0 alpha

#### Retorna todos os registros

```http
  GET /animais
```

| Parâmetro (HEADER)  | Tipo       | Descrição                           |
| :---------- | :--------- | :------------------------------------------ |
| `mamando`| `boolean`  | Quando `true` retorna os animais que nao foram desmamados, caso contrário retorna todos os animais.|

#### Busca um animal por ID

```http
  GET /animais/{id}
```

#### Busca um animal por numero da mãe

```http
  GET /animais/numero/{numero}
```

#### Retorna todas as metricas aplicadas no periodo especificado pelo usuario

```https
  GET /animais/metricas
```

#### Busca em um intervalo de periodo especificado pelo usuario

```http
  GET /animais/periodonascimento
```

| Parâmetro (HEADER)  | Tipo       | Descrição                           |
| :---------- | :--------- | :------------------------------------------ |
| `inicio`    | `Date`     | **Obrigatório**. Data do inicio do intervalo.|
| `fim`       | `Date`     | **Obrigatório**. Data do fim do intervalo.  |

#### Deleta um animal por ID

```http
  DELETE /animais/{id}
```

#### Deleta um animal por numero da mãe

```http
  DELETE /animais/numero/{numero}
```

#### Marca um animal como desmamado

```http
  PUT /animais/{id}/desmamar
```

#### Registra um animal

```http
  POST /animais
```

| Parâmetro (BODY)  | Tipo       | Descrição                             |
| :---------- | :--------- | :------------------------------------------ |
| `numero`    | `Integer`  | **Obrigatório**. numero da mãe do animal.   |
| `sexo`      | `char`     | **Obrigatório**. char com sexo do animal.   |
| `nascimento`| `Date`     | **Obrigatório**. data de nascimento.        |
| `marca`     | `String`   | **Obrigatório**. marca.                     |
