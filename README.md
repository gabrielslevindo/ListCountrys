🌍 ListCountrys

Aplicativo Android desenvolvido com Jetpack Compose, Apollo GraphQL, Hilt e Room. Ele exibe uma lista de países obtidos de uma API pública, permite visualizar detalhes, pesquisar e marcar favoritos localmente.

✨ Funcionalidades

🔍 Pesquisa em tempo real por nome de país
📋 Lista de países com nome, bandeira (emoji) e capital
📌 Tela de detalhes com:
Capital
Continente
Moeda
Idiomas
Marcar/desmarcar como favorito
⭐ Armazenamento de favoritos localmente usando Room
🧱 Arquitetura

O projeto segue a Clean Architecture com separação clara de responsabilidades:

presentation/
│
├── screens/          -> Composables da UI (Jetpack Compose)

├── state/            -> State management para UI

├── viewmodel/        -> ViewModel com lógica da UI
│
domain/
│
├── dataclass/        -> Entidades da camada de domínio

├── repository/       -> Interface do repositório

├── usecase/          -> Casos de uso (business rules)
│
data/
│
├── local/            -> Implementação com Room

├── remote/           -> Apollo GraphQL client

├── repository/       -> Implementação concreta dos repositórios



🧪 Tecnologias Utilizadas

| Categoria     | Tecnologia                         |
| ------------- | ---------------------------------- |
| UI            | Jetpack Compose                    |
| DI            | Hilt                               |
| Armazenamento | Room Database                      |
| GraphQL       | Apollo Client                      |
| State         | StateFlow + Compose                |
| Persistência  | DataStore + Room                   |

🔧 Instalação e Execução

Pré-requisitos
Android Studio Giraffe ou superior
SDK Android 34+
Conexão com internet (para GraphQL)

Clonar o projeto

git clone https://github.com/seu-usuario/ListCountrys.git
cd ListCountrys

Executar
Abra com o Android Studio e clique em Run ▶️.

🌐 Fonte dos Dados

API pública GraphQL de países:
https://countries.trevorblades.com
🧠 Aprendizados e Desafios

Integração de Apollo com GraphQL e Jetpack Compose
Controle de estado assíncrono com StateFlow e ViewModel
Armazenamento eficiente de favoritos com Room
Uso prático de Clean Architecture no mundo real
Testes unitários e instrumentados com injeção de dependência via Hilt
🛠️ Melhorias Futuras

Tela separada para listar favoritos
Suporte offline com cache do Apollo
Internacionalização (i18n)

📸 Capturas de Tela 

![Screenshot_20250611_140524](https://github.com/user-attachments/assets/6692ef53-460d-442d-a348-9eca7d2c4416)
![Screenshot_20250611_140418](https://github.com/user-attachments/assets/662a6c12-5837-4f92-8690-61d8ed96567d)
![Screenshot_20250611_140351](https://github.com/user-attachments/assets/40dba5f1-6555-47de-98c0-7c8b04db6993)


<img width="1397" alt="Captura de Tela 2025-06-11 às 14 05 51" src="https://github.com/user-attachments/assets/7dd2663d-2bfa-4006-a8bf-dff2c364ca26" />

🧑‍💻 Autor

Gabriel Levindo – 
[github](https://github.com/gabrielslevindo)
[Linkdln](https://www.linkedin.com/in/gabrielslevindo/)

License

The MIT License (MIT)

Copyright (c) 2023 Bernardo Oechsler; Gabriel Levindo.
Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

