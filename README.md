# Mão na Massa - Sistema de Capacitação e Conexão de Profissionais

## O que é?

Plataforma que capacita pessoas em ofícios manuais (pedreiro, eletricista, encanador, pintor) através de cursos online, e conecta profissionais certificados com clientes que precisam de serviços.

## Integrantes do Grupo

- **RM 560812** - Gabriel Dos Santos Souza
- **RM 560649** - Thomas Henrique Baute
- **RM 559999** - Bruno Mateus Tizer das Chagas

## Como rodar?

1. Abrir o projeto no IntelliJ
2. Rodar a classe `MaoNaMassaApplication.java`
3. A aplicação vai subir na porta `http://localhost:8080`

## Como acessar o banco H2?

- Acessar: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:maonamassa-db`
- Usuario: `sa`
- Senha: (deixar em branco)

## Entidades do Sistema

### Area
- id: UUID
- nome: String

### Usuario
- id: UUID
- nome: String
- email: String
- senha: String
- cidade: String
- area: Area (relacionamento)
- tipoUsuario: String
- dataCriacao: LocalDate

### Curso
- id: UUID
- titulo: String
- descricao: String
- area: Area (relacionamento)
- nivel: String
- dataCriacao: LocalDate

### Aula
- id: UUID
- curso: Curso (relacionamento)
- titulo: String
- conteudo: String
- ordem: Integer

### Quiz
- id: UUID
- curso: Curso (relacionamento)
- pergunta: String
- respostaCorreta: String

### RespostaQuiz
- id: UUID
- usuario: Usuario (relacionamento)
- quiz: Quiz (relacionamento)
- resposta: String
- correta: Boolean
- dataResposta: LocalDate

### Certificado
- id: UUID
- usuario: Usuario (relacionamento)
- curso: Curso (relacionamento)
- notaFinal: Double
- dataConclusao: LocalDate
- codigoCertificado: String

### Profissional
- id: UUID
- usuario: Usuario (relacionamento)
- descricao: String
- avaliacaoMedia: Double
- disponivel: Boolean

### Servico
- id: UUID
- profissional: Profissional (relacionamento)
- titulo: String
- descricao: String
- cidade: String
- preco: Double
- dataPublicacao: LocalDate

### Avaliacao
- id: UUID
- servico: Servico (relacionamento)
- usuario: Usuario (relacionamento)
- nota: Integer
- comentario: String
- data: LocalDate

## Relacionamentos

- Usuario tem Area (ManyToOne)
- Curso tem Area (ManyToOne)
- Aula tem Curso (ManyToOne)
- Quiz tem Curso (ManyToOne)
- RespostaQuiz tem Usuario e Quiz (ManyToOne)
- Certificado tem Usuario e Curso (ManyToOne)
- Profissional tem Usuario (ManyToOne)
- Servico tem Profissional (ManyToOne)
- Avaliacao tem Servico e Usuario (ManyToOne)

## Documentação da API

A documentação completa da API está disponível via Swagger:
- Acessar: `http://localhost:8080/swagger-ui.html`

## Paginação e Ordenação

Todos os endpoints de listagem suportam paginação e ordenação:

- `?page=0` - Número da página (começa em 0)
- `?size=10` - Quantidade de itens por página
- `?sort=campo` - Ordenar por campo
- `?sort=campo,desc` - Ordenar descendente

Exemplos:
- `GET /api/v1/usuarios?page=0&size=5`
- `GET /api/v1/cursos?page=0&size=10&sort=titulo`
- `GET /api/v1/servicos?sort=preco,desc`

## Filtros

Alguns endpoints possuem filtros opcionais:

- `GET /api/v1/usuarios?cidade=Sao Paulo` - Filtrar por cidade
- `GET /api/v1/cursos?areaId=xxx` - Filtrar por área
- `GET /api/v1/servicos?cidade=Rio de Janeiro` - Filtrar por cidade
- `GET /api/v1/profissionais?disponivel=true` - Filtrar por disponibilidade

## Endpoints da API

### Areas
- `GET /api/v1/areas` - Listar
- `GET /api/v1/areas/{id}` - Buscar
- `POST /api/v1/areas` - Criar
- `PUT /api/v1/areas/{id}` - Atualizar
- `DELETE /api/v1/areas/{id}` - Deletar

### Usuarios
- `GET /api/v1/usuarios` - Listar
- `GET /api/v1/usuarios/{id}` - Buscar
- `POST /api/v1/usuarios` - Criar
- `PUT /api/v1/usuarios/{id}` - Atualizar
- `DELETE /api/v1/usuarios/{id}` - Deletar

### Cursos
- `GET /api/v1/cursos` - Listar
- `GET /api/v1/cursos/{id}` - Buscar
- `POST /api/v1/cursos` - Criar
- `PUT /api/v1/cursos/{id}` - Atualizar
- `DELETE /api/v1/cursos/{id}` - Deletar

### Aulas
- `GET /api/v1/aulas` - Listar
- `GET /api/v1/aulas/{id}` - Buscar
- `POST /api/v1/aulas` - Criar
- `PUT /api/v1/aulas/{id}` - Atualizar
- `DELETE /api/v1/aulas/{id}` - Deletar

### Quizzes
- `GET /api/v1/quizzes` - Listar
- `GET /api/v1/quizzes/{id}` - Buscar
- `POST /api/v1/quizzes` - Criar
- `PUT /api/v1/quizzes/{id}` - Atualizar
- `DELETE /api/v1/quizzes/{id}` - Deletar

### Respostas Quiz
- `GET /api/v1/respostas-quiz` - Listar
- `GET /api/v1/respostas-quiz/{id}` - Buscar
- `POST /api/v1/respostas-quiz` - Criar
- `DELETE /api/v1/respostas-quiz/{id}` - Deletar

### Certificados
- `GET /api/v1/certificados` - Listar
- `GET /api/v1/certificados/{id}` - Buscar
- `POST /api/v1/certificados` - Criar
- `DELETE /api/v1/certificados/{id}` - Deletar

### Profissionais
- `GET /api/v1/profissionais` - Listar
- `GET /api/v1/profissionais/{id}` - Buscar
- `POST /api/v1/profissionais` - Criar
- `PUT /api/v1/profissionais/{id}` - Atualizar
- `DELETE /api/v1/profissionais/{id}` - Deletar

### Servicos
- `GET /api/v1/servicos` - Listar
- `GET /api/v1/servicos/{id}` - Buscar
- `POST /api/v1/servicos` - Criar
- `PUT /api/v1/servicos/{id}` - Atualizar
- `DELETE /api/v1/servicos/{id}` - Deletar

### Avaliacoes
- `GET /api/v1/avaliacoes` - Listar
- `GET /api/v1/avaliacoes/{id}` - Buscar
- `POST /api/v1/avaliacoes` - Criar
- `DELETE /api/v1/avaliacoes/{id}` - Deletar
