## Modulo 2 - Aplicacao Login

Nesse modulo foi criado uma aplicacao para login de usuarios, que pode ser acessada atraves de um API Gateway, que está no projeto router. O projeto Auth é responsavel por gerenciar a autenticacao dos usuarios, retornando um token JWT caso o usuario realize login.

Foi usado uma arquitetura hexagonal para melhorar a separacao entre as camadas da palicacao, através de adapters que permitem a comunicacao entre camadas.

As rotas são:

http://localhost:8080/auth/cadastro 

http://localhost:8080/auth/login

http://localhost:8080/auth/me

