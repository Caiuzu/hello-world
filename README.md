# Hello World: Uma abordagem não convencional

###### By: [@caiuzu](https://github.com/Caiuzu/)

![GitHub repo size](https://img.shields.io/github/repo-size/Caiuzu/hello-world)
![ViewCount](https://views.whatilearened.today/views/github/Caiuzu/hello-world.svg)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_hello-world&metric=bugs)](https://sonarcloud.io/dashboard?id=Caiuzu_hello-world)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_hello-world&metric=code_smells)](https://sonarcloud.io/dashboard?id=Caiuzu_hello-world)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_hello-world&metric=ncloc)](https://sonarcloud.io/dashboard?id=Caiuzu_hello-world)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_hello-world&metric=coverage)](https://sonarcloud.io/dashboard?id=Caiuzu_hello-world)
[![Java CI with Gradle](https://github.com/Caiuzu/hello-world/actions/workflows/gradle.yml/badge.svg)](https://github.com/Caiuzu/hello-world/actions/workflows/gradle.yml)
---

### Resumo:

> Este projeto tem por intuito introduzir o desenvolvedor a algumas tecnologias e ferramentas complementares, vastamente utilizadas no dia-a-dia.
> Através de um Hello World, implementaremos os itens descritos abaixo.

## Tecnologias/Ferramentas Utilizadas no Projeto:

- [x] Spring Initializr
- [x] Spring Boot
- [X] Gradle
- [x] Actuator
- [x] Springfox Swagger
- [X] JUnit
- [X] Cucumber
- [x] SonarCloud
- [x] CI/CD (GitHub Actions)
- [x] Commit Semântico

---

## 1. Actuator:

- ### O que é:
    - **Spring Boot Actuator** é um sub-projeto do Spring Boot Framework. Inclui vários recursos adicionais que nos
      ajudam a monitorar e gerir o aplicativo Spring Boot. Ele usa endpoints HTTP ou beans JMX para nos permitir
      interagir com ele. Expõe informações operacionais sobre o aplicativo em execução — integridade, métricas,
      informações, etc.

- ### Configurando Actuator no projeto:
  <details>
  <summary>Configurando Actuator no projeto:</summary>

    - Adicionaremos as dependências para o actuator no arquivo [build.gradle](./build.gradle):
      ```
        ext {
          set('springBootVersion', "2.4.5")
        }
  
      dependencies {
      // Spring Boot
      implementation "org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}"
      }
      ```

    - Em [application.yml](./src/main/resources/application.yml), iremos colocar as propriedades abaixo:
      ```yaml
      management:
        endpoint:
          health:
            enabled: true
            show-details: always
      ```

    - Desta forma, o actuator estará pronto, basta acessar: http://localhost:8080/actuator/

  </details>

- ### EndPoint:
  > http://localhost:8080/actuator

---

## 2. Springfox Swagger2:

- ### O que é:
    - O Swagger é usado com um conjunto de ferramentas de ‘software’ de código aberto para projetar, construir,
      documentar e usar serviços da Web RESTful.
    - A biblioteca Springfox tem por objetivo gerar a especificação (contrato) em tempo de execução com base em seu
      código e também implanta o cliente Swagger UI com seu aplicativo, permitindo que você teste imediatamente sua API
      REST.

- ### Configurando Springfox Swagger2 no projeto:
  <details>
  <summary>Configurando Springfox Swagger2 no projeto:</summary>

    - Em [build.gradle](./build.gradle), adicionaremos as dependências abaixo:

        ```yaml
        ext {
            set('swaggerVersion', "2.9.2")
        }
        
        dependencies {
            // Swagger
            implementation "io.springfox:springfox-swagger2:${springCloudVersion}"
            implementation "io.springfox:springfox-swagger-ui:${springCloudVersion}"
        }
        ```

        - Iremos adicionar a anotação `@EnableSwagger2` em nosso
          [main](./src/main/java/br/com/hello/world/HelloWorldApplication.java)
        - Em seguida, criaremos o diretório [config](./src/main/java/br/com/hello/world/config), que será destinado a
          todas as configurações do nosso projeto. Dentro iremos criar a classe de configuração;
          [SwaggerConfiguration](./src/main/java/br/com/hello/world/config/SwaggerConfiguration.java)
            - Para funcionamento básico do swagger, devemos adicionar apenas as linhas abaixo. Para configurações
              adicionais, podemos utilizar os outros métodos contidos na classe (Autenticação, informações sobre o
              projeto, etc).

              ```java
                @EnableSwagger2
                @Configuration
                public class SwaggerConfiguration {
              
                    @Bean
                    public Docket api() {
                        return new Docket(DocumentationType.SWAGGER_2)
                                .select()
                                .apis(RequestHandlerSelectors.basePackage("br.com.hello.world"))
                                .paths(PathSelectors.any())
                                .build();
                    }
                }
              ```
            - Desta forma, já estamos prontos para o utilizar o swagger.
  </details>

- ### EndPoint:
  > http://localhost:8080/swagger-ui.html

---

## 3. JUnit

- ### O que é:
    - JUnit pode verificar se cada unidade de código funciona da forma esperada; Facilita a criação, execução automática
      de testes e a apresentação dos resultados;

- ### Configurando JUnit no projeto:
  <details>
  <summary>Configurando JUnit no projeto:</summary>

    - Adicionaremos as dependências para os testes unitários no arquivo [build.gradle](./build.gradle):
      ```yaml
      ext {
        set('springBootVersion', "2.4.5")
      }
  
      dependencies {
        // Test
        testImplementation "org.springframework.boot:spring-boot-starter-test:${springBootVersion}"
      }
      
      tasks.named('test') {
        useJUnitPlatform()
      }
      ```

    - Haverá um [arquivo main para testes](./src/test/java/br/com/hello/world/HelloWorldApplicationTests.java)
      organizado em um diretório semelhante ao que encontramos em nosso main da aplicação:
        - Notamos abaixo que o arquivo será anotado com `@SpringBootTest` e `@Test`, que indicaram o contexto de testes
      ```Java
        @SpringBootTest
        class HelloWorldApplicationTests {
    
            @Test
            void contextLoads() {
            }
    
        }
      ```

  </details>

- ### Comandos:
  ```shell
    gradlew test --info --stacktrace
  ``` 

---

## 4. Cucumber

- ### O que é:
    - Cucumber é uma ferramenta de software que oferece suporte ao desenvolvimento orientado por comportamento. Central
      para a abordagem do Cucumber BDD é seu analisador de linguagem comum chamado Gherkin.
    - Cucumber é usado para descrever o valor do negócio em uma linguagem natural, por isso permite que equipes de
      desenvolvimento de software descrevam como o software deve se comportar em texto simples, escrevendo
      especificações através de exemplos.

- ### Configurando Cucumber no projeto:
  <details>
  <summary>Configurando Cucumber no projeto:</summary>

    - Adicionaremos as dependências para Cucumber no arquivo [build.gradle](./build.gradle):

      ```yaml
      ext {
          set('cucumberVersion', "7.2.3")
      }  
  
      dependencies {
        // Test
        testImplementation "io.cucumber:cucumber-junit:${cucumberVersion}"
        testImplementation "io.cucumber:cucumber-spring:${cucumberVersion}"
        testImplementation "io.cucumber:cucumber-java:${cucumberVersion}"
      }
      ```
        - Criaremos uma classe de
          configuração [CucumberTeste.java](./src/test/java/br/com/hello/world/cucumber/CucumberTest.java):
            - Anotaremos o arquivo com
                - `@CucumberContextConfiguration`:
                    - Que define uma classe de contexto de aplicativo que o Cucumber deverá usar
                - `@RunWith(Cucumber.class)`:
                    - O JUnit invocará a classe referenciada para executar os testes nessa classe em vez do executor
                      embutido no JUnit.
                - `@CucumberOptions(features = "src/test/resources/features", glue = "br.com.hello.world.cucumber.step")`:
                    - De forma que passamos em `features` o caminho onde estão nossos cenários de testes (.feature).
                    - Passaremos em `glue`
      ```Java
        @RunWith(Cucumber.class)
        @CucumberContextConfiguration()
        @CucumberOptions(features = "src/test/resources/features", glue = "br.com.hello.world.cucumber.step")
        public class CucumberTest {
        }
      ```  

  </details>

- ### Como utilizar Cucumber?
  <details>
  <summary>Como utilizar Cucumber?</summary>

    - Descreva um comportamento em um texto simples;
    - Escreva uma definição dos passos em Java ou em outras linguagens;
    - Execute e veja os passos falhar;
    - Escreva o código para fazer os passos passar;
    - Se necessário, refatorar o código ou o comportamento descrito.

- ### Mais Informações:
  > https://www.devmedia.com.br/desenvolvimento-orientado-a-comportamento-bdd-com-cucumber/33547

</details>

---

## 5. SonarCloud

- ### O que é:
    - O SonarCloud é uma plataforma em nuvem para exibir o processo de inspeção continua do código de sua aplicação.
      Para isso, o SonarCloud utiliza o SonarQube para realizar a “varredura” em seu código fonte e analisar possíveis
      vulnerabilidade, erros e regras específicas da linguagem (Code Smells).

- ### Configurando SonarCloud no projeto:
  <details>
  <summary>Configurando SonarCloud no projeto:</summary>

    - **Configurando com Github Actions**:
        - Logue com sua conta GitHub
        - Acesse [https://sonarcloud.io/projects/create](https://sonarcloud.io/projects/create)
        - O repositório do projeto deverá estar listado na organização onde criou o repositório, selecione-o e click
          em `Set Up` ao lado
            - Caso nenhum item seja exibido, acesse https://github.com/settings/installations/ e verifique se o
              sonarcloud possuí os acessos necessários para encontrar seus repositórios
        - Serão exibidas 3 opções, escolha a recomendada: `With GitHub Actions`
        - Siga os passos "Create a GitHub Secret" e "Create or update a .github/workflows/build.yml file"

    <br>

    - **Configuração no projeto**:
        - Adicionaremos as seguintes propriedades em nosso [build.gradle](./build.gradle):
          ```yaml
            plugins {
              id "org.sonarqube" version "3.3"
            }
  
            sonarqube {
                properties {
                  property "sonar.projectKey", "Project_hello-world"
                  property "sonar.organization", "user"
                  property "sonar.host.url", "https://sonarcloud.io"
              }
            }
          ```

    - **Configurando cobertura de código**:

        - Para que nosso os valores de cobertura de código sejam reportados corretamente, iremos adicionar as seguinte
          linhas em nosso arquivo [build.gradle](./build.gradle):

            - Primeiramente, ativaremos o plugin jacoco:
              ```yaml
              apply plugin: 'jacoco'
              ```

            - Em nossa configuração de sonar, adicionaremos a propriedade para excluirmos classes que não necessitam de
              cobertura:
              ```yaml
              sonarqube {
                properties {
                    property 'sonar.exclusions',
                            "src/main/java/br/com/hello/world/HelloWorldApplication.java," +
                                    "src/main/java/br/com/hello/world/config/**"
                }
              }
              ```

            - Adicionaremos a chamada da task de jacoco que criaremos logo abaixo:
              ```yaml
              tasks.named('test') {
                useJUnitPlatform()
                test.finalizedBy jacocoTestReport
              }
              ```

            - Aqui criaremos o método para reportarmos os dados de cobertura via xml. O sonarcloud possuí uma
              peculiaridade, apenas utiliza valores calculados previamente (seja processados no servidor da aplicação ou
              gerados na máquina do dev), ou seja, sem o report xml, aparecerá 0% de cobertuda, desta forma, ativaremos:
              ```yaml
              tasks.jacocoTestReport {
                reports {
                    xml.enabled true
                }
              }
              ```

  </details>

- ### Comandos:
    - Para execução local:
      ```shell
       gradlew clean build test sonarqube --info --stacktrace
      ```

- ### Análises Realizadas:
    - Podem ser acessadas através das bagdes no topo do readme ou no link abaixo:
      > https://sonarcloud.io/project/overview?id=Caiuzu_hello-world

---

## 6. CI/CD (GitHub Actions)

- ### O que é:
    - A integração contínua (CI, Continuous Integration) é uma prática de software que exige commits frequentes de
      códigos para um repositório compartilhado. Fazer commits de códigos com frequência detecta erros com mais
      antecedência e reduz a quantidade de código necessária para depuração quando os desenvolvedores chegam à origem de
      um erro.
    - As atualizações frequentes de código também facilitam o merge de alterações dos integrantes de uma equipe de
      desenvolvimento de software. Assim, os desenvolvedores podem se dedicar mais à gravação de códigos e se preocupar
      menos com erros de depuração ou conflitos de merge.

- ### Configurando GitHub Actions no projeto:
  <details>
  <summary>Configurando GitHub Actions no projeto:</summary>

    - Criaremos o arquivo [.github/workflows/gradle.yml](.github/workflows/gradle.yml).
        - Com ele, automaticamente será criado um workflow ao ser versionado.

  </details>

- ### Erros & Soluções:
  <details>
  <summary>Possíveis problemas com build:</summary>

    - [Gradlew: Permission denied](https://github.com/actions/starter-workflows/issues/171#issuecomment-555036259)
      | Problemas com permissão no Github Actions de permissão do gradlew
        - "The underlying problem is that the file is not executable, which you'll need to change in your repository. If
          you're on Windows, that may be why the file did not get added as executable in your repository. On Windows,
          you can run: `git update-index --chmod=+x gradlew` Then commit and push this change."
    - Seguindo as colocações acima, foram adicionadas as seguintes linhas
      em [.github/workflows/gradle.yml](.github/workflows/gradle.yml).
      ```yaml
          - name: Make gradlew executable # Sometimes the gradlew file is not executable
            run: chmod +x ./gradlew
      ```
  -----

  </details>

- ### Mais informações:
  > https://docs.github.com/pt/actions/automating-builds-and-tests/building-and-testing-java-with-gradle

---

## 6. Integrações (Discord WebHook)

- ### O que é:
    - Imagine o seguinte cenário, você acabou de finalizar uma feature e agora deseja subir para QA. Em um fluxo de
      trabalho ideal, será criado um Pull Request para QA. Sendo assim, no momento da criação da 'PR', podemos enviar a
      notificação no canal do time, desta forma, qualquer integrante da equipe receberá a notificação e revisará o pull
      request.

- ### Configurando Notificações de Repositório e Deploy para Discord via WebHook:
  <details>
  <summary>Configurando Integrações no projeto:</summary>

  #### 1 - Criando webhook no Discord:
    - Encontre o canal Discord no qual você gostaria de enviar commits e outras atualizações:

    - Nas configurações desse canal, encontre a opção Webhooks e crie um novo webhook. Nota: NÃO forneça este URL ao
      público. Qualquer pessoa ou serviço pode postar mensagens neste canal, mesmo sem precisar estar no servidor.
      Mantenha-o seguro!

      ![WebhookDiscord](https://i.imgur.com/dQSjx6e.jpg)

  #### 2 - Configurando o webhook no Github:
    - Navegue até seu repositório no Github e abra as configurações:

      ![Settings](https://imgur.com/7ZQaQOR.jpg)

    - Selecione Adicionar Webhook:

      ![Add](https://imgur.com/tzYUtId.jpg)

    - Cole no URL do webhook e acrescente `/github` ao final. Selecione "Enviar-me tudo", defina o tipo
      como `application/json` e, em seguida, Adicionar Webhook:

      ![WebhookSettings](https://imgur.com/XYqS0MD.jpg)

    - Teste atualizando algo ou estrelando o repositório!

      ![Star](https://imgur.com/twntXXy.jpg)

  </details>

---

## 7. Endpoints da Aplicação:

### Hello World:

- Hello, World!
    ```http request
    GET http://localhost:8080/v1/hello/world
    ```
- Hello, Foo!
    ```http request
    GET http://localhost:8080/v1/hello/foo?name=Fulano
    ```

---

### Tecnologias a serem estudadas em projetos futuros:

- [ ] Heroku
- [ ] New Relic
- [ ] Apache Kafka
- [ ] Hystrix
- [ ] Spark
- [ ] Docker
- [ ] AWS (AWS LocalStack)
    - [ ] S3
    - [ ] EC2
- [ ] Kubernetes
- [ ] Mockito
- [ ] Lombok
- [ ] Audited JPA Envers
- [ ] PostgreSQL
- [ ] JPA
- [ ] Hibernate
- [ ] Validator
- [ ] Docker
- [ ] Heroku
- [ ] New Relic
- [ ] Tratamento de Exceptions
- [ ] Pagination
- [ ] Sleuth
- [ ] Zipkin UI
- [ ] Liquibase
- [ ] Spring Cloud Config (Remote Configuration)
- [ ] Spring Cloud Bus

---

## Como Contribuir?

Para organizar o nosso fluxo de trabalho, seguimos de forma conceitual o modelo do
[Git Flow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow). Os passos para contribuir com
o projeto são:

- Criar uma feature branch a partir da `develop`;
- Desenvolver o que precisar nesta nova branch;
- Criar um Pull Request;
- Após a aprovação do Pull Request, a sua versão deve ser mergeada na `develop`.

É importante seguir os padrões
de [commit semântico](https://blog.geekhunter.com.br/o-que-e-commit-e-como-usar-commits-semanticos/)

<details>
<summary>Prefixos para commit:</summary>

- **build**: Alterações que afetam o sistema de construção ou dependências externas (escopos de exemplo: gulp, broccoli,
  npm),
- **ci**: Mudanças em nossos arquivos e scripts de configuração de CI (escopos de exemplo: Travis, Circle, BrowserStack,
  SauceLabs);
- **docs**: referem-se a inclusão ou alteração somente de arquivos de documentação;
- **feat**: Tratam adições de novas funcionalidades ou de quaisquer outras novas implantações ao código;
- **fix**: Essencialmente definem o tratamento de correções de bugs;
- **perf**: Uma alteração de código que melhora o desempenho;
- **refactor**: Tipo utilizado em quaisquer mudanças que sejam executados no código, porém não alterem a funcionalidade
  final da tarefa impactada;
- **style**: Alterações referentes a formatações na apresentação do código que não afetam o significado do código, como
  por exemplo: espaço em branco, formatação, ponto e vírgula ausente etc.);
- **test**: Adicionando testes ausentes ou corrigindo testes existentes nos processos de testes automatizados (TDD);
- **chore**: Atualização de tarefas que não ocasionam alteração no código de produção, mas mudanças de ferramentas,
  mudanças de configuração e bibliotecas que realmente não entram em produção;
- **env**: basicamente utilizado na descrição de modificações ou adições em arquivos de configuração em processos e
  métodos de integração contínua (CI), como parâmetros em arquivos de configuração de containers.
- **improvement**: commits que melhoram uma implementação atual sem adicionar um novo recurso ou consertar um bug.

-----

</details>

---
