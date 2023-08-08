# LicenSysTech

<p><b>
Projeto que simula uma empresa de logística.
</b></p>

<p>
  Foi utilizada a linguagem java na versão 17, com o framework Spring Boot e outras dependencias no maven. Foi desenvolvido um crud para cada entity existente no projeto. 

  Obs.: Para a inicialização do banco de dados é necessário 'docker-compose up' o docker-file está localizado na pasta docker do projeto.
</p>

<p>
  <b>Endpoints:</b>
  
  <b>Requisições para clientes:</b>

 Registro de um novo cliente (POST): localhost:8080/cliente/salvar
    
  ```json
  Json required:
  {
    "nome": "Teste",
    "cnpj": "12345678900",
    "email": "teste@teste.com",
    "cep": "12345-6789"
}
 
  ```
-------------------------------------------------------------------------------------

Verificação de todos os clientes no banco de dados (GET): localhost:8080/cliente

-------------------------------------------------------------------------------------

Busca por cliente específico (GET): localhost:8080/cliente/cnpj=12345678900

-------------------------------------------------------------------------------------

Alteração de dados cadastrais (PUT): localhost:8080/cliente
 ```json
  Json required:
  {
    "nome": "Teste",
    "cnpj": "12345678900",
    "email": "teste@teste.com",
    "cep": "12345-6789"
}

  ```
-------------------------------------------------------------------------------------

Deletar cliente a partir do CNPJ (DELETE): localhost:8080/cliente/cnpj=123456789

-------------------------------------------------------------------------------------

<b>Requisições para produtos:</b>

Encomendar um novo produto (POST): localhost:8080/produto/encomenda
 ```json
  Json required:
  {
    "cliente": {
        "cnpj": "12345678900"
    },
    "nome_produto": "Teste",
    "quantidade": 10,
    "peso": 2.5,
    "tipoDeProduto": "BELEZA",
    "periculosidade": "CLASSE_1",
    "preco": 25.00,
    "transportadora": "Testes"
}

  ```

-------------------------------------------------------------------------------------

Comprar um novo produto (POST): localhost:8080/produto/compra
```json
  Json required:
  {
    "cliente": {
        "cnpj": "12345678900"
    },
    "nome_produto": "TESTE",
    "quantidade": 1,
    "peso": 2.5,
    "tipoDeProduto": "BELEZA",
    "periculosidade": "CLASSE_1",
    "preco": 25.00,
    "transportadora": "TESTE"
}
```

-------------------------------------------------------------------------------------

Verificação dos pedidos pelo CNPJ do cliente (GET): localhost:8080/produto/pedidos/cnpj=12345678900

-------------------------------------------------------------------------------------

Adicionar um novo produto ao estoque (POST): localhost:8080/produto/adicionarProduto

```json
  Json required:
  {
    "cliente": {
        "cnpj": "12345678900"
    },
    "nomeProduto": "TESTE",
    "tipo_de_produto": "INDUSTRIAL",
    "quantidade": 50
}
```

-------------------------------------------------------------------------------------

Deletar do estoque (DELETE): localhost:8080/produto/deletar
```json
  Json required:
  {
    "cliente": {
        "cnpj": "12345678900"
    },
    "nomeProduto": "TESTE"
}
```

-------------------------------------------------------------------------------------

Alterar a quantidade no estoque (PUT): localhost:8080/produto/deletar/quantidade=QUANTIDADE DESEJADA
```json
  Json required:
 {
    "cliente": {
        "cnpj": "12345678900"
    },
    "nomeProduto": "TESTE"
}
```
-------------------------------------------------------------------------------------

<b>Requisições para licenças:</b>

Adicionar uma nova licença para um cliente(POST): localhost:8080/cliente/licenca

```json
  Json required:
  {

  "tipoDeLicenca": "ALVARA_PARA_TRANSPORTE_E_DEPOSITO",
  "data_de_inicio": "22/07/2023",
  "data_de_vencimento": "22/05/2025",
  "cliente": {
    "cnpj": "12345678900"
    }
}

```

-------------------------------------------------------------------------------------

Verificar todas as licenças a partir de um cnpj(GET): localhost:8080/cliente/licenca/cnpj=12345678900

-------------------------------------------------------------------------------------

</p>

