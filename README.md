# Controle de Tráfego Aéreo

Sistema em Java que simula o controle de tráfego aéreo de um aeroporto, usando **estruturas de dados implementadas manualmente**: Árvore Binária de Busca, Fila e Pilha.

Trabalho da disciplina de Estruturas de Dados (Tema 03: Controle de Tráfego Aéreo).

> Nenhuma `Collection` do Java foi usada (`ArrayList`, `LinkedList`, `Stack`, `Queue` etc.). Tudo é feito com nós e ponteiros.

## Estruturas e o papel de cada uma

| Estrutura | Classe | Papel no sistema |
|---|---|---|
| **ABB** (Árvore Binária de Busca) | `ArvoreBinariaBusca` | Guarda os voos programados. A chave é o **código do voo**, o que permite busca rápida |
| **Fila** (FIFO) | `FilaPista` | Pista de decolagem/aterrissagem, respeitando a ordem de chegada |
| **Pilha** (LIFO) | `PilhaLog` | Log de eventos críticos para auditoria rápida (o mais recente fica no topo) |

## Como as estruturas interagem

```
Cadastro do voo ──► ABB (voos programados)
                     │
        Solicita pista: busca o voo na ABB
                     │
                     ▼
                Fila da pista ──► Pista liberada (voo sai da fila)

Eventos críticos (emergência, arremetida, pista interditada, voo cancelado)
                     │
                     ▼
              Pilha de log (auditoria)
```

1. O voo é cadastrado na **ABB**.
2. Ao pedir pista, o sistema **busca o voo na ABB** pelo código. Se ele existir, entra na **Fila**.
3. Quando a pista é liberada, o primeiro da **Fila** sai.
4. Qualquer evento crítico é registrado na **Pilha**, com data e hora.
5. Ao cancelar um voo, ele é removido da **ABB** e o cancelamento é registrado na **Pilha**.

## Funcionalidades

- Cadastrar, buscar e remover voos (ABB)
- Listar os voos em **in-ordem**, **pré-ordem** e **pós-ordem** (ABB)
- Colocar um voo na fila da pista, ver a posição e liberar a pista (Fila)
- Registrar eventos críticos e consultar o log, do mais recente ao mais antigo (Pilha)

## Métodos de cada estrutura

**ABB**: `inserir`, `buscar`, `remover` (trata nó folha, nó com 1 filho e nó com 2 filhos), `emOrdem`, `preOrdem`, `posOrdem`, `percursos`

**Fila**: `adicionar`, `buscar`, `remover`, `listar` (além de `primeiro`, `posicao` e `removerPorCodigo`)

**Pilha**: `adicionar`, `buscar`, `remover`, `listar` (além de `topo`)

## Estrutura do projeto

```
src/
├── model/
│   └── InformacaoDoVoo.java    # dados do voo (código, origem, destino, horário, status)
├── estruturas/
│   ├── ArvoreBinariaBusca.java
│   ├── NoArvore.java
│   ├── FilaPista.java
│   ├── NoFila.java
│   ├── PilhaLog.java
│   └── NoPilha.java
└── Principal.java              # menu e integração das estruturas
```

## Como executar

Requisito: **JDK 17 ou superior**.

Na raiz do projeto:

```bash
javac -d out src/model/InformacaoDoVoo.java src/estruturas/NoArvore.java src/estruturas/ArvoreBinariaBusca.java src/estruturas/NoFila.java src/estruturas/FilaPista.java src/estruturas/NoPilha.java src/estruturas/PilhaLog.java src/Principal.java
java -cp out Principal
```

Ou, no VS Code (com a extensão *Extension Pack for Java*), abra o `Principal.java` e clique em **Run**.

## Equipe

| Integrante | Responsabilidade |
|---|---|
| Gustavo Caldeira | Classe `InformacaoDoVoo`, `Principal` (menu e integração) |
| Robert Lima | ABB (`ArvoreBinariaBusca`, `NoArvore`) |
| Paulo Gabriel | Fila (`FilaPista`, `NoFila`) |
| Pablo Eduardo | Pilha (`PilhaLog`, `NoPilha`) e README |