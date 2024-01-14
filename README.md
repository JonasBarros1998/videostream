# Tech Challange - Fase 4 | Serviço de Stream

# Arquitetura
![arquitetura](https://firebasestorage.googleapis.com/v0/b/app-english-class.appspot.com/o/postech-fase4%2Fservico-stream-de-videos.png?alt=media&token=44562634-f897-4a30-acdb-70616c862270)

# Inicializar a aplicação

### Configuração da infraestrutura na AWS

#### Antes de iniciar a aplicação será necessário configurar alguns serviços na AWS. 
1. Crie duas filas no AWS SQS
    - processamento-de-videos
    - processamento-de-videos-dlq (fila específica para armazenar as mensagens que não foram processadas na fila processamento-de-videos)
      
2. Crie dois buckets no AWS S3
    - videos-processados-v2
    - videos-nao-processados-v2
      
#### Configurar as permissões 

1. No IAM, crie um usuário na AWS que contenhas as seguintes políticas permissões.
    - Para acesso ao bucket: AmazonS3FullAccess 
    - Para acesso ao SQS: AmazonSQSFullAccess
    - Para acesso ao media convert: AWSElementalMediaConvertFullAccess
2. Após criar o usuário, adicione as credenciais na AWS CLI na sua máquina
      
#### Criar uma função do IAM para que o media convert tenha acesso ao S3
1. Antes de inicializar o media convert, será necessário criar uma nova função que permitir que o mesmo acesso o AWS S3

### Configuração da aplicação

#### Configuração do MongoDB
1. Crie um base de dados do mongodb, chamada de stream
2. Crie o login e senha para acesso a base de dados. A aplicação utiliza essas credenciais abaixo para se conectar ao mongodb
    - usuário: postech
    - senha: 123456789

#### configuração da aplicação
1. Após passar por toda essas etapas, podemos baixar as dependências e inicializar a aplicação
    - para instalação: mvn install
    - para rodar os testes unitários: mvn clean test


 ##### links úteis
 [conexão com o banco de dados](https://github.com/JonasBarros1998/videostream/blob/main/src/main/java/com/br/fiap/videostream/infra/bancodedados/conexao/Conexao.java)
 [configuração de paginação](https://github.com/JonasBarros1998/videostream/blob/main/src/main/java/com/br/fiap/videostream/infra/bancodedados/paginacao/PaginacaoHandler.java)


 ##### collection postman
 

