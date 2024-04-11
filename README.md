# CGenius 


## Endpoints

### Cliente

`GET` /cliente

Lista todos os clientes cadastrados no sistema.

`200` sucesso

---

`GET` /cliente/{id}

Retorna os detalhes de um cliente com o `id` informado.

`GET` /cliente/cpf/{id}

Retorna os detalhes de um cliente com o `cpf` informado.

**códigos de status**

`200` sucesso
`404` id/cpf não encontrado

---
`POST` /cliente

Cadastrar um novo cliente.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|nome|string|✅|
|cpf|string|✅|
|genero|string|✅|
|cep|string|✅|
|telefone|string|✅|
|email|String|✅|
|dataNascimento|Date|✅|
|senha|string|✅|


**códigos de status**

`201` criado com sucesso
`400` validação falhou

---

`DELETE` /cliente/{id} 

Apaga a cliente com o `id` informado.

`DELETE` /cliente/cpf/{cpf} 

Apaga a cliente com o `cpf` informado.

**códigos de status**

`204` apagado com sucesso
`404` id/cpf não encontrado

---

`PUT` /cliente/{id}
`PUT` /cliente/{cpf} 

Altera o cliente com o `id` ou o `cpf` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|nome|string|✅|
|cpf|string|✅|
|genero|string|✅|
|cep|string|✅|
|telefone|string|✅|
|email|String|✅|
|dataNascimento|Date|✅|
|senha|string|✅|

**códigos de status**

`200` sucesso
`404` id/cpf não encontrado
`400` validação falhou

---

**Schema**

```js
{
    "nome":"Antonio",
    "cpf":"12345678910",
    "genero": "masculino",
    "cep": "12345678",
    "telefone": "912345678",
    "email": "teste@gmail.com",
    "dataNascimento": "2020-02-03",
    "senha": "123456"
}
```

### Atendente

```js
{
    "nome":"João",
    "cpf":"32165498710",
    "setor": "vendas",
    "senha": "654321"
}
```

### Chamada

```js
{
    "dt_chamada": "2020-01-05",
    "hora": "10:15:30",
    "duracao": "00:05:30",
    "resultado": "True",
    "cpf_user": "12345678910",
    "cpf_atendente": "32165498710"
}
```

### Produto

```js
{
    "descricao": "Descrição do Produto X",
    "nome": "produto X",
    "valor": "123.45"
}
```

### Histórico

```js
{
    "idProduto": "01" ,
    "cpfCliente": "12345678910" ,
    "dataCompra": "2020-01-05" 
}
```

### Script

```js
{
    "id_compra": "01",
    "id_chamada": "01",
    "cpf_user": "32165498710"
}
```
