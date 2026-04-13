Este projeto consiste no desenvolvimento de uma API REST para gerenciamento de sessões de votação em assembleias cooperativistas.

No cooperativismo, cada associado possui direito a um único voto por pauta, e as decisões são tomadas com base na contagem desses votos.

A solução foi desenvolvida utilizando Java + Spring Boot, com foco em:

Simplicidade e clareza de código Boas práticas de desenvolvimento Arquitetura limpa e organizada Facilidade de manutenção e evolução Tecnologias Utilizadas Java 21+ Spring Boot Spring Data JPA Hibernate Lombok Bean Validation (Jakarta) Springdoc OpenAPI (Swagger) Banco de dados relacional (PostgreSQL) Arquitetura do Projeto

O projeto segue uma arquitetura em camadas bem definida:

controller -> Camada de entrada (API REST) service -> Regras de negócio repository -> Acesso a dados (JPA) model -> Entidades do banco dto -> Objetos de saída input -> Objetos de entrada (requests) mapper -> Conversão entre camadas client -> Integrações externas (fake) exception -> Tratamento global de erros Funcionalidades Implementadas

Cadastro de Pauta
Permite criar uma nova pauta para votação.

Endpoint:

POST /v1/pautas/criar 2. Abertura de Sessão de Votação

Permite abrir uma sessão de votação para uma pauta.

Caso não seja informado o tempo, é utilizado o padrão de 1 minuto Não permite abrir mais de uma sessão para a mesma pauta

Endpoint:

POST /v1/sessoes/{pautaId}/pauta?minutos=5 3. Registro de Voto

Permite que um associado vote em uma pauta.

Regras aplicadas:

Apenas votos SIM ou NÃO Cada CPF pode votar apenas uma vez por pauta A sessão precisa estar aberta CPF deve ser válido e apto a votar

Endpoint:

POST /v1/votos

Exemplo de payload:

{ "cpf": "12345678900", "opcaoVoto": "SIM", "voto": { "id": 1 } } 4. Contabilização de Votos

Retorna o resultado da votação de uma pauta.

Endpoint:

GET /v1/resultados/{pautaId}/pauta Regras de Negócio Um associado pode votar apenas uma vez por pauta A votação só ocorre dentro do período da sessão Sessões possuem tempo configurável ou default de 1 minuto O resultado é baseado na contagem de votos SIM e NÃO

Funcionalidades Bônus Integração com sistema externo (Fake)

Foi implementado um client fake que simula a validação de CPF:

CPF inválido → retorna erro 404 CPF válido: ABLE_TO_VOTE → pode votar UNABLE_TO_VOTE → não pode votar

Essa abordagem simula uma integração real com serviço externo.

Performance Uso de consultas otimizadas (count direto no banco) Índices podem ser aplicados para melhorar performance em alto volume de votos Evita carregamento desnecessário de dados Versionamento da API

A API utiliza versionamento via URI:

/v1/...

Facilitando evolução futura sem quebra de contrato.

Tratamento de Erros

Foi implementado um handler global para exceções:

Regras de negócio → BusinessException Erros retornados com mensagens claras Uso de códigos HTTP apropriados 📊 Documentação da API

A API está documentada com Swagger:

http://localhost:8080/swagger-ui.html Como Executar o Projeto Pré-requisitos: Java 21+ Maven Passos: git clone cd desafio-votacao

mvn clean install mvn spring-boot:run

Testes

Foram implementados testes automatizados com foco em garantir a confiabilidade das regras de negócio e o correto funcionamento da API.

Tipos de testes implementados Testes unitários Focados na camada de service Validação das regras de negócio (sessão aberta, voto único, validação de CPF, etc.) Utilização de JUnit 5 e Mockito para isolamento das dependências Testes de controller Validação dos endpoints REST Verificação de status HTTP e contratos de resposta Uso de MockMvc para simular requisições HTTP Tecnologias utilizadas JUnit 5 Mockito Spring Boot Test MockMvc

Como executar os testes mvn test

Objetivo dos testes

Os testes foram implementados para garantir:

Confiabilidade das regras de negócio Prevenção de regressões Validação dos fluxos principais da aplicação Maior segurança na evolução do código

Observações As dependências externas (como o client de CPF) foram mockadas nos testes Os testes cobrem os principais cenários de sucesso e erro Estrutura preparada para fácil expansão de novos testes

O projeto foi desenvolvido com foco em:

Código limpo e legível Separação de responsabilidades Facilidade de manutenção Simulação de cenários reais de negócio

A solução atende todos os requisitos propostos, incluindo os bônus sugeridos.

Autor

Desenvolvido por Alisson Fernando Berçalini
