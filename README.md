# aula-engenharia-software
Projeto criado para a disciplina de engenharia de software do curso de Ciência da Computação da UNIFESO.

O projeto usa a linguagem Java na sua versão 11.

# Configurações do Banco de Dados
O projeto já está configurado para usar o MySQL.

## Caso queira usar outro banco de dados
Caso queira usar outro banco de dados, será necessário alterar o driver SQL. Isso pode ser definido na classe `src/main/java/ccomp.engsoft.loja.config.JpaConfiguration.java`.
Dentro do método `hikariConfigDev`, na linha `hikariConfig.setDriverClassName("INSIRA O DRIVER DO BANCO DE DADOS DESEJADO AQUI")` deve ser inserido a classe do driver do banco de dados desejado.

Abaixo está um exemplo da configuração para o PostgreSQL: 
```
    @Bean
    public HikariConfig hikariConfigDev() {
        HikariConfig hikariConfig = new HikariConfig();

        // driver do PostgreSQL
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        // outras configurações omitidas
        
        return hikariConfig;

    }
```

Além da configuração na classe `JpaConfiguration`, é necessário adicionar a dependência do driver no arquivo `pom.xml` dentro da tag `<dependencies>`.

Abaixo está um exemplo da dependência do driver do PostgreSQL:
```
    <dependencies>
        <-- outras dependências do projeto -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.20</version>
        </dependency>
    <dependencies>
```
## Configurações de acesso
Estas configurções são obrigatorias, sem elas a aplicação não funcionará.

As configurações de acesso ao banco de dados devem estar num arquivo chamado `local-dev-environment.properties` que precisa ser criado na pasta `src/main/resources/`

O arquivo `local-dev-environment.properties` deve conter o seguinte conteúdo:

```
database.username=INSIRA O NOME DE USUÁRIO DO BANCO DE DADOS AQUI
database.password=INSIRA A SENHA DO BANCO DE DADOS AQUI
database.url=INSIRA A URL DO BANCO DE DADOS AQUI
```

Um exemplo de URL para o MySQL seria: `jdbc:mysql://localhost:3306/loja_engsoft`. Neste exemplo o nome do banco de dados é `loja_engsoft`. É necessário criar um banco de dados com o nome definido na URL. Basta criar o banco de dados e ele já estará pronto para ser usado.

# Usuário default

É necessário inserir um usuário default diretamente no banco de dados.
Primeiro rode a aplicação para que as tabelas sejam criadas. Depois insira dos dados do usuário default.
A senha deve estar encriptada com a criptografia BCrypt.
Abaixo um script SQL para inserção de um usuário 'admin', com a senha 'admin':

```
INSERT INTO usuario (nome, nome_de_usuario, senha, nivel_acesso) values 
    ('admin', 'admin', '$2y$12$Q9M8qkJC9An5rVplcPY.NeoYEG7hWP4Rl8uI2OPMo9HNR67f.F0UC', "ADMINISTRADOR");
```


# Rodando o Programa
Execute a classe `src/main/java/ccomp.engsoft.loja.SistemaLojaApplication.java`. 

Por padrão a aplicação irá escutar na porta 8080, portanto basta acessar http://localhost:8080/, fazer login com o usuário default que você inseriu no banco de dados e navegar pelo site. 
