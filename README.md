# CGenius 


## Endpoints

### Cliente

`GET` /cliente

Lista todos os clientes cadastrados no sistema.

`200` sucesso

---

`GET` /cliente/{id}

Retorna os detalhes de um cliente com o `id` informado.

`GET` /cliente/cpf/{cpf}

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
|dataNascimento|LocalDate|✅|
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
`PUT` /cliente/cpf/{cpf} 

Altera o cliente com o `id` ou `cpf` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|nome|string|✅|
|cpf|string|✅|
|genero|string|✅|
|cep|string|✅|
|telefone|string|✅|
|email|String|✅|
|dataNascimento|LocalDate|✅|
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

`GET` /atendente

Lista todos os atendentes cadastrados no sistema.

`200` sucesso

---

`GET` /atendente/{id}

Retorna os detalhes de um atendente com o `id` informado.

`GET` /atendente/cpf/{cpf}

Retorna os detalhes de um atendente com o `cpf` informado.

**códigos de status**

`200` sucesso
`404` id/cpf não encontrado

---
`POST` /atendente

Cadastrar um novo atendente.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|nome|string|✅|
|cpf|string|✅|
|setor|string|✅|
|senha|string|✅|


**códigos de status**

`201` criado com sucesso
`400` validação falhou

---

`DELETE` /atendente/{id} 

Apaga a atendente com o `id` informado.

`DELETE` /atendente/cpf/{cpf} 

Apaga a atendente com o `cpf` informado.

**códigos de status**

`204` apagado com sucesso
`404` id/cpf não encontrado

---

`PUT` /atendente/{id}
`PUT` /atendente/cpf/{cpf} 

Altera o atendente com o `id` ou `cpf` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|nome|string|✅|
|cpf|string|✅|
|setor|string|✅|
|senha|string|✅|

**códigos de status**

`200` sucesso
`404` id/cpf não encontrado
`400` validação falhou

---

**Schema**

```js
{
    "nome":"João",
    "cpf":"32165498710",
    "setor": "vendas",
    "senha": "654321"
}
```

### Chamada

`GET` /chamada

Lista todos os chamadas cadastrados no sistema.

`200` sucesso

---

`GET` /chamada/{id}

Retorna os detalhes de um chamada com o `id` informado.

**códigos de status**

`200` sucesso
`404` id não encontrado

---
`POST` /chamada

Cadastrar uma nova chamada.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|dt_chamada|LocalDate|✅|
|hora|LocalTime|✅|
|duracao|LocalTime|✅|
|resultado|Boolean|✅|
|cpf_user|string|✅|
|cpf_atendente|String|✅|

**códigos de status**

`201` criado com sucesso
`400` validação falhou

---

`DELETE` /chamada/{id} 

Apaga a chamada com o `id` informado.

**códigos de status**

`204` apagado com sucesso
`404` id não encontrado

---

`PUT` /chamada/{id}

Altera o chamada com o `id` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|dt_chamada|LocalDate|✅|
|hora|LocalTime|✅|
|duracao|LocalTime|✅|
|resultado|Boolean|✅|
|cpf_user|string|✅|
|cpf_atendente|String|✅|

**códigos de status**

`200` sucesso
`404` id não encontrado
`400` validação falhou

---

**Schema**
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
`GET` /produto

Lista todos os produtos cadastrados no sistema.

`200` sucesso

---

`GET` /produto/{id}

Retorna os detalhes de um produto com o `id` informado.

**códigos de status**

`200` sucesso
`404` id não encontrado

---
`POST` /produto

Cadastrar uma nova produto.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|Descricao|string|✅|
|nome|string|✅|
|valor|BigDecimal|✅|

**códigos de status**

`201` criado com sucesso
`400` validação falhou

---

`DELETE` /produto/{id} 

Apaga a produto com o `id` informado.

**códigos de status**

`204` apagado com sucesso
`404` id não encontrado

---

`PUT` /produto/{id}

Altera o produto com o `id` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|Descricao|string|✅|
|nome|string|✅|
|valor|BigDecimal|✅|

**códigos de status**

`200` sucesso
`404` id não encontrado
`400` validação falhou

---

**Schema**
```js
{
    "descricao": "Descrição do Produto X",
    "nome": "produto X",
    "valor": "123.45"
}
```

### Histórico
`GET` /historico

Lista todos os historicos cadastrados no sistema.

`200` sucesso

---

`GET` /historico/{id}

Retorna os detalhes de um historico com o `id` informado.

**códigos de status**

`200` sucesso
`404` id não encontrado

---
`POST` /historico

Cadastrar uma nova historico.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|idProduto|Long|✅|
|cpfCliente|string|✅|
|dataCompra|LocalDate|✅|

**códigos de status**

`201` criado com sucesso
`400` validação falhou

---

`DELETE` /historico/{id} 

Apaga a historico com o `id` informado.

**códigos de status**

`204` apagado com sucesso
`404` id não encontrado

---

`PUT` /historico/{id}

Altera o historico com o `id` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|idProduto|Long|✅|
|cpfCliente|string|✅|
|dataCompra|LocalDate|✅|

**códigos de status**

`200` sucesso
`404` id não encontrado
`400` validação falhou

---

**Schema**
```js
{
    "idProduto": "01" ,
    "cpfCliente": "12345678910" ,
    "dataCompra": "2020-01-05" 
}
```

### Script
`GET` /script

Lista todos os scripts cadastrados no sistema.

`200` sucesso

---

`GET` /script/{id}

Retorna os detalhes de um script com o `id` informado.

**códigos de status**

`200` sucesso
`404` id não encontrado

---
`POST` /script

Cadastrar uma nova script.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|idCompra|Long|✅|
|idChamada|Long|✅|
|cpf_user|String|✅|

**códigos de status**

`201` criado com sucesso
`400` validação falhou

---

`DELETE` /script/{id} 

Apaga a script com o `id` informado.

**códigos de status**

`204` apagado com sucesso
`404` id não encontrado

---

`PUT` /script/{id}

Altera o script com o `id` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|idCompra|Long|✅|
|idChamada|Long|✅|
|cpf_user|String|✅|

**códigos de status**

`200` sucesso
`404` id não encontrado
`400` validação falhou

---

**Schema**
```js
{
    "id_compra": "01",
    "id_chamada": "01",
    "cpf_user": "32165498710"
}
```
