# Nfe Consult

[![NPM](https://img.shields.io/badge/LICENCE-MIT-blue)](https://github.com/thiagobritto/NfeConsult/blob/main/LICENSE)

# Sobre o projeto

O propósito do software é extrair dados de arquivos XML de notas fiscais. Ao informar o caminho do diretório raiz contendo os arquivos XML, 
a leitura é realizada de forma recursiva, abrangendo subdiretórios. Após carregar o diretório, a busca inicial já pode ser realizada retornando 
todos os dados dos arquivos sem filtro.

Para realizar uma busca específica, insira uma ou mais palavras-chave no campo "Busca por produto" e adicione à pilha. Ao refazer 
a busca, os resultados são filtrados de acordo com as palavras-chave inseridas. Em seguida, você pode gerar um arquivo CSV, configurado 
com ponto e vírgula como separador.

O software oferece a opção de escolher o local de salvamento do arquivo CSV. Certifique-se de seguir as instruções para selecionar o local 
desejado durante o processo.

Estamos abertos a sugestões para melhorias! Caso tenha ideias ou recomendações para futuras versões do software, ficaríamos felizes em 
ouvi-las. Envie suas sugestões para thiagobritto.developer@gmail.com. Sua contribuição é valiosa para aprimorarmos o software e oferecer 
uma experiência ainda melhor.

Se surgirem mais dúvidas ou se precisar de assistência adicional, estamos à disposição!

# Apresentação dos dados

> Os dados do CSV, assim que abertos no software de planilha,
> serão apresentados no seguinte formato.

|   | A | B | C | D | E |
|---|---|---|---|---|---|
| 1  |00001|Numero da nota|   |   |   |
| 2  |Nome|Nome do cliente|   |   |   |
| 3  |CPF/CNPJ|00000000000|   |   |   |
| 4  |Endereço|Endereço do cliente|   |   |   |
| 5  |Bairro|Bairro do cliente|   |   |   |
| 6  |Cidade|Cidade do cliente|   |UF|uf|
| 7  |Código|Descrição|Qtd|Valor|Total|
| 8  |0001|PRODUTO_1|1|3,50|3,50|
| 9  |0002|PRODUTO_2|5|5,00|25,00|
| 10 |0003|PRODUTO_3|10|8,50|85,00|
| 11 | | | | |113,50|
| 12 | | | | | |
| 13 | | | | | |
| 14  |00002|Numero da nota|   |   |   |
| ... | | | | | |

# Tecnologias utilizadas

- Java 17

# Downloads

### Releases

## [NfeConsult_Beta(v1.0.0)](https://github.com/thiagobritto/NfeConsult/releases/tag/Beta_(v1.0.0))

### Assets
- [NfeConsult_Beta.v1.0.0.jar](https://github.com/thiagobritto/NfeConsult/releases/download/Beta_(v1.0.0)/NfeConsult_Beta.v1.0.0.jar)
- [Source code (zip)](https://github.com/thiagobritto/NfeConsult/archive/refs/tags/Beta_(v1.0.0).zip)
- [Source code (tar.gz)](https://github.com/thiagobritto/NfeConsult/archive/refs/tags/Beta_(v1.0.0).tar.gz)

# Autor

Thiago Messias de Brito

Email: [thiagobritto.developer@gmail.com](mailto:thiagobritto.developer@gmail.com)
