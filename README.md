# Progetto Tris TEPSIT
Progetto nella realizzazione del gioco Tris con l'aggiunta di una sessione multiplayer, implementato in Java.

***

### Prerequisiti
Per poter eseguire il progetto, è necessario aver installato un compilatore/interprete Java, con supporto Maven, per permettere la comunicazione in rete. È consigliato aver scaricato un IDE (es. Eclipse) per poter far avviare le classi e vederne il comportamento nel terminale.

## Installazione del software
Per eseguire il programma, scaricare il file ZIP da questa repository. Una volta fatto questo, se si usa un IDE con Java implementato, è necessario importare il progetto dentro l'applicazione. In caso contrario, è necessario  compilare il progetto da terminale per poi potero eseguire.

## Esecuzione
Una volta compilato, è fortemente consigliato di mandare in esecuzione ``` Server.java ``` precedentemente a tutte le altre istanze della classe ``` Client.java ```, dato che se si esegue la procedura contraria il client si auto-terminerà in assenza di connessione al server. Una volta avviato il server, ogni client si può collegare ad esso, senza limiti di connessioni, ed ogni client si chiude automaticamente con la fine della partita, mentre il server rimane in esecuzione all'infinito, fino ad un arresto forzato da parte dell'utente.
