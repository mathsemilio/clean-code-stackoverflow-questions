# Clean Code - Stackoverflow Questions
Aplicação nativa Android para ilustrar boas práticas de código limpo e arquitetura.

## Objetivo da aplicação
Esta aplicação visa ilustrar boas práticas de código limpo e arquitetura se baseando nas melhoras práticas
difundidas na escola de código limpo e arquitetura. Para a estruturação do front-end, a arquitetura escolhida foi
o **MVC**. Além da estruturação do front, o fluxo de domínio da aplicação está abstraído pelos seus respectivos casos de uso,
além contar com abstrações com responsabilidades bem definidas como difundido pelo princípio **Single Responsability Principle**.
Outro padrão arquitetural utilizado foi o **Dependency Injection Architectural Pattern**, que visa separar a lógica de construção
da lógica funcional da aplicação.

**Importante:**
Dentro da branch `master` o código da aplicação se encontra no estado "sujo" e sem nenhuma estrutura. Para verificar o código bem
estruturado e "limpo", troque para a branch `refactoring/stackoverflow_questions/architecture_revision`. Esta estrutura foi mantida
por questões didáticas.

## Bibliotecas de terceiros utilizadas
1. Kotlin Coroutines for Android
2. Retrofit

**Dentro da branch** `refactoring/stackoverflow_questions/architecture_revision`:
1. Kotlin Coroutines for Android
2. Retrofit
3. Swipe-Refresh Layout

*Para mais informações sobre as bibliotecas utilizadas, utilize esses links:*
<p><a href="https://developer.android.com/kotlin/coroutines">Kotlin Coroutines for Android</a></p>
<p><a href="https://square.github.io/retrofit/">Retrofit</a></p>
<p><a href="https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout">Swipe-Refresh Layout</a></p>

## Licença
```
Copyright 2021 Matheus Menezes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```