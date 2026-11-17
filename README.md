# Pilha de Log — Tema 03: Controle de Tráfego Aéreo

Parte do trabalho de Estruturas de Dados em Java. Aqui está a **Pilha** usada como
**registro (log) de eventos críticos para auditoria rápida**.

## Arquivos

| Arquivo | Papel |
|---|---|
| `NoPilha.java` | Nó da pilha: guarda o evento, a data/hora e o ponteiro para o nó de baixo |
| `PilhaLog.java` | A pilha em si, com todos os métodos |

## Por que uma Pilha?

A pilha é **LIFO** (Last In, First Out): o último evento registrado é o primeiro a sair.
Num log de auditoria isso é ideal, porque o que mais interessa é o que aconteceu **agora**
(o evento mais recente fica no topo, sem precisar percorrer nada).

## Como funciona

```
topo → [Evento 3] → [Evento 2] → [Evento 1] → null
```

- Cada nó aponta para o que foi empilhado antes dele.
- `inserir` coloca um novo nó no topo.
- `remover` tira o nó do topo.
- Nenhuma `Collection` do Java foi usada (regra do trabalho): tudo é feito com nós e ponteiros.

## Métodos

| Método | O que faz | Custo |
|---|---|---|
| `inserir(String evento)` | Empilha um evento com data/hora atual | O(1) |
| `remover()` | Desempilha e devolve o evento do topo (`null` se vazia) | O(1) |
| `topo()` | Mostra o evento do topo sem remover | O(1) |
| `buscar(String termo)` | Acha o evento mais recente que contém o termo (ex.: código do voo) | O(n) |
| `listar()` | Imprime todos os eventos, do mais recente ao mais antigo | O(n) |
| `estaVazia()` / `tamanho()` | Controle da pilha | O(1) |

## Exemplo de uso

```java
PilhaLog log = new PilhaLog();

log.inserir("Voo AZ123: arremetida por mau tempo");
log.inserir("Voo LA456: emergência declarada (falha de motor)");
log.inserir("Pista 2 interditada");

log.listar();                       // mostra do mais recente ao mais antigo
System.out.println(log.topo());     // Pista 2 interditada
System.out.println(log.buscar("AZ123")); // acha o evento do voo AZ123
log.remover();                      // tira "Pista 2 interditada"
```

## Como se encaixa no sistema

- **ABB**: cadastro dos voos programados (busca por código do voo).
- **Fila**: pista de decolagem/aterrissagem (ordem de chegada).
- **Pilha (este arquivo)**: sempre que algo crítico acontece (emergência, arremetida,
  pista interditada, voo cancelado), o sistema chama `log.inserir(...)`.

## Perguntas que a professora pode fazer

- **Por que pilha e não fila para o log?** Porque na auditoria o mais recente é o mais importante; a pilha dá acesso a ele em O(1).
- **O que acontece ao remover de uma pilha vazia?** O método devolve `null`, sem erro.
- **Qual o custo de inserir e remover?** O(1), só mexem no topo.
- **Por que `buscar` é O(n)?** Pode precisar percorrer todos os nós até achar o termo.
