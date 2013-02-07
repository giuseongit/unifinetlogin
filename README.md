unifinetlogin
=============

strumento per rimanere connessi alla rete dell'ateneo fiorentino

Un pratico strumento che, una volta avviato, ti permetter i non preoccuparti di dover reinserire continuamente matricola e password per poter andare online.

Versione attuale (0.9b)
  Caratteristiche:
   - Supporto dei sistemi Windows e Linux
   - Salvataggio del file di configurazione nella home dell'utente come file nascosto
   - Riduzione ad icona nella system tray con ridimensionamento dell'immagine a seconda dell'os
   - Sistema di logging su file e da shell
   - Semplice codifica dei dati di accesso salvati in locale
   - Supporto per la lettura/scrittura di file testuali e file contenenti oggetti
   
  Caratteristiche da implementare:
   - Supporto per Mac OS
   
  Bug noti:
   - File di configurazione non nascosto su win7
   - Mancata trasparenza dell'icona nella tray su linux (Bug noto di X11, risolvibile via Java)