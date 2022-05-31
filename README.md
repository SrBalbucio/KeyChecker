# KeyChecker
Se você cria aplicativos pagos e necessita de um sistema de keys, essa pode ser uma boa opção, este é um KeyChecker simples que usa o Datacrack como armazenamento das Keys.

## Como configurar o seu Datacrack para receber Keys
(É altamente indicado que você tenha um database separado só para as Keys, devido as configurações que são necessárias para o uso do KeyChecker)

A configuração do Database é muito simples, na **config.yml** do seu Datacrack, procure por **DataPack Section** e altere as configurações abaixo:
```YML
pathEqualToUsernameAllow: true
```
Após isso, crie um User **ADMIN** para gerenciar as Keys:
```YML
  Admin:
    username: "Admin"
    password: "Admin"
    isAdmin: true
    permissions:
      - "createNewUsers"
```
Feito isso, seu Database está pronto para receber Keys.

## Como criar as Keys (via code)
(Há opção de criar Keys manualmente usando o KeyCheckerAdmin como App, para executá-lo basta dar dois cliques na JAR)

Em sua aplicação (admin) crie um KeyCheckerAdmin, como feito abaixo:
```java
// Aqui você deve colocar o usuário Admin do Bando de Dados
User admin = new User(<username>, <password>);
// Complete com o IP e a porta do banco de dados
KeyCheckerAdmin keyChecker = new KeyCheckerAdmin(admin, <ip>, <port>);
//Para criar uma key use o método addKey
keyChecker.addKey(<appName>, <email>);
```
## Como validar as Keys
Em sua aplicação final (ou seja, a aplicação pro usuário) adicione o Application, como feito abaixo:
```java
// Você pode extender a classe Application na sua Main se preferir
GenericApplication app = new GenericApplication(<appName>);
```
Feito isso, crie o KeyChecker:
```java
// Adicione o IP e porta do database
KeyChecker keyChecker = new KeyChecker(app, <ip>, <porta>);
//Crie a verificação da Key
if(!keyChecker.validate(<email>, <key>)){
  System.out.print("A sua key é inválida");
  return;
}
// Continue seu código aqui
